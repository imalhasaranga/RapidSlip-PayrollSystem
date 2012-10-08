package model;

import control.Datecontrol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gihan
 */
public final class Employee {

    private String fmname;
    private String lname;
    private String addno;
    private String city;
    private String conhome;
    private String conmobi;
    private String nicno;
    private String bank;
    private String branch;
    private String bankNumber;
    private String branchNumber;
    private String accno;
    private String payType;
    private int empno;
    private int epfno;
    private int etype;
    private String employeeTypeName;
    private int designation;
    private int division;
    private int site;
    private String joineddate;
    private String regdate;
    private double BasicSalary;
    private double Gallowance;
    private double Ballowance;
    private int EPFNumber;
    private int EmployeeType;
    private double Advance;
    private int AdvanceTaknYear;
    private int AvannceTakinMonth;
    public static int ALLEMPLOYES = 1;
    public static int THISEMPLOYE = 2;
    int falg = 0;
    private int payrollnumber;
    private double npdays;
    private double othours;
    private String msg;
    private ArrayList addlist;

    /**
     * @return the fmname
     */
    public Employee() {
    }

    public Employee(int empID) {

        ResultSet rs = null;
        try {
            setEmpno(empID);
            rs = DB.getData("SELECT* FROM employee LEFT JOIN emp_salary ON employee.EmpNumber = emp_salary.EmpNumber where employee.EmpNumber ='" + empID + "'");
            if (rs.next()) {
                setBasicSalary(rs.getDouble("Basic_salary"));
                setGallowance(rs.getDouble("gvtAllow"));
                setBallowance(rs.getDouble("budAllow"));
                setEPFNumber(rs.getInt("EpfNumber"));
                int type = rs.getInt("Emp_TypeID");
                setEmployeeType(type);
            }
        } catch (Exception e) {
            System.out.println("Error in getEmployee" + e);
        }



    }

    public Employee(int empID, int year, int month) {
        ResultSet rs = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;

        try {
            setEmpno(empID);

            rs2 = DB.getData("SELECT payRollNum, EmpNumber, NpDays, OT_hours FROM payroll WHERE EmpNumber = '" + empID + "' AND YEAR = '" + year + "' AND MONTH = '" + month + "' AND is_active = '1'");

            if (rs2.next()) {
                setPayrollnumber(rs2.getInt("payRollNum"));
                setNpdays(rs2.getDouble("NpDays"));
                setOthours(rs2.getDouble("OT_hours"));
                setMsg("Duplicate");



            } else {
                setMsg("First");
            }

            rs = DB.getData("SELECT* FROM employee LEFT JOIN emp_salary ON employee.EmpNumber = emp_salary.EmpNumber where employee.EmpNumber ='" + empID + "'");

            if (rs.next()) {
                setBasicSalary(rs.getDouble("Basic_salary"));
                setGallowance(rs.getDouble("gvtAllow"));
                setBallowance(rs.getDouble("budAllow"));
                setEPFNumber(rs.getInt("EpfNumber"));
                setPayType(rs.getString("PayType"));
                int type = rs.getInt("Emp_TypeID");
                setEmployeeType(type);
            }
            
           
            
            
        } catch (Exception e) {
            System.out.println("Error in getEmployee" + e);
            e.printStackTrace();
        }



    }

    public boolean deleteEmployee(int empID) {

        try {

            String sql = "Delete from emp_site where EmployeeEmpNumber ='" + empID + "'";
            DB.setData(sql);
            sql = "Delete from designation_employee where EmpNumber ='" + empID + "'";
            DB.setData(sql);
            sql = "Delete from division_employee where EmployeeEmpNumber ='" + empID + "'";
            DB.setData(sql);
            sql = "Delete from bankdetails where EmpNumber='" + empID + "' ";
            DB.setData(sql);
            sql = "Delete from employee where EmpNumber = '" + empID + "'";
            DB.setData(sql);

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean addEmployee() {
        boolean val = false;
        Connection con = null;

        try {
            con = DB.getCon();
            con.setAutoCommit(false);

            String sql = "INSERT INTO employee(EmpNumber,Emp_TypeID,EpfNumber,FMname,Lname,Add_nub,City,ContactHome,contactMobile,JontDate,Nic,PayType) "
                    + "VALUES('" + getEmpno() + "','" + getEtype() + "','" + getEpfno() + "','" + getFmname() + "','" + getLname() + "','" + getAddno() + "','" + getCity() + "','" + getConhome() + "','" + getConmobi() + "','" + getJoineddate() + "','" + getNicno() + "','" + getPayType() + "')";
            con.createStatement().executeUpdate(sql);

            String bankbranch1 = "'" + getBranch() + "'";
            String branchNumb = "'" + getBranchNumber() + "'";
            String Acountnub = "'" + getAccno() + "'";
            String bankcod = "'" + getBankNumber() + "'";
            String bank1 = "'" + getBank() + "'";
            if (!getPayType().equals("BANK")) {
                bankbranch1 = "NULL";
                branchNumb = "NULL";
                Acountnub = "NULL";
                bankcod = "NULL";
                bank1 = "NULL";
            }

            sql = "INSERT INTO bankdetails(EmpNumber,Bank_branch,Account_number,Bank,bankcode,branchcode) VALUES('" + getEmpno() + "'," + bankbranch1 + "," + Acountnub + "," + bank1 + "," + bankcod + "," + branchNumb + ")";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO division_employee(Div_id,TimeRange,EmployeeEmpNumber) values('" + getDivision() + "','" + Datecontrol.getCurrentDateString() + "','" + getEmpno() + "')";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO designation_employee(EmpNumber,Designation_ID,Time_range) values('" + getEmpno() + "','" + getDesignation() + "','" + Datecontrol.getCurrentDateString() + "')";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO emp_site(EmployeeEmpNumber,Time_range,site_ID) VALUES('" + getEmpno() + "','" + Datecontrol.getCurrentDateString() + "','" + getSite() + "')";
            con.createStatement().executeUpdate(sql);
            con.commit();
            con.setAutoCommit(true);
            val = true;
        } catch (Exception e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                System.out.println("Error From addEmployee Method Rollback: " + e);
            }
            val = false;
            System.out.println("Error From addEmployee Method: " + e);
        }

        return val;

    }

    public static ResultSet getEmployees(int activin) {
        ResultSet rs = null;
        try {
            rs = DB.getData("select* from employeeSum where is_active='" + activin + "' ");
        } catch (Exception e) {
            System.out.println("Error in getEmployee" + e);
        }

        return rs;
    }
    
    public static ResultSet getOneYearCompletedEmployees(String time) {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT * FROM employee em, employeesum es WHERE em.EmpNumber = es.EmpNumber AND em.JontDate < '"+time+"' AND em.is_active = '1'");
        } catch (Exception e) {
            System.out.println("Error in getEmployee" + e);
        }

        return rs;
    }
    
    public static ResultSet getCompleteDetailsEmp(int empID) {

        ResultSet rs = null;
        try {
            rs = DB.getData("select* from AllEmpDetails where EmpNumber ='" + empID + "'");
        } catch (Exception e) {
            System.out.println("Error in getCompleteDetailsEmp()" + e);
        }

        return rs;
    }

    public boolean updateEmployee(int updateid) {
        boolean val = false;
        Connection con = null;
        try {
            con = DB.getCon();
            con.setAutoCommit(false);

            String sql = "Update employee set Emp_TypeID = '" + getEtype() + "', EpfNumber = '" + getEpfno() + "', FMname = '" + getFmname() + "',Lname = '" + getLname() + "',Add_nub = '" + getAddno() + "',City = '" + getCity() + "',ContactHome = '" + getConhome() + "'"
                    + ",contactMobile = '" + getConmobi() + "',JontDate='" + getJoineddate() + "',Nic='" + getNicno() + "',PayType = '" + getPayType() + "' where EmpNumber = '" + getEmpno() + "' ";
            con.createStatement().executeUpdate(sql);

            String bankbranch1 = "'" + getBranch() + "'";
            String branchNumb = "'" + getBranchNumber() + "'";
            String Acountnub = "'" + getAccno() + "'";
            String bankcod = "'" + getBankNumber() + "'";
            String bank1 = "'" + getBank() + "'";
            if (getPayType().equals("CASH")) {
                bankbranch1 = "Bank_branch";
                branchNumb = "branchcode";
                Acountnub = "Account_number";
                bankcod = "bankcode";
                bank1 = "Bank";
            }


            sql = "update bankdetails set Bank_branch =" + bankbranch1 + ",Account_number = " + Acountnub + ",Bank =" + bank1 + ",bankcode = " + bankcod + ",branchcode = " + branchNumb + " where EmpNumber ='" + getEmpno() + "' ";
            con.createStatement().executeUpdate(sql);

            sql = "DELETE FROM division_employee WHERE Div_id = '" + getDivision() + "' && EmployeeEmpNumber ='" + getEmpno() + "' ;";
            con.createStatement().executeUpdate(sql);
            sql = "UPDATE division_employee SET is_active = '0', TimeRange = NOW() WHERE EmployeeEmpNumber = '" + getEmpno() + "' && is_active='1'";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO division_employee(Div_id,TimeRange,EmployeeEmpNumber) values('" + getDivision() + "','" + Datecontrol.getCurrentDateString() + "','" + getEmpno() + "')";
            con.createStatement().executeUpdate(sql);

            sql = "DELETE FROM designation_employee WHERE EmpNumber = '" + getEmpno() + "' && Designation_ID ='" + getDesignation() + "'";
            con.createStatement().executeUpdate(sql);
            sql = "UPDATE designation_employee SET is_active = '0',Time_range = NOW() WHERE is_active = '1' && EmpNumber = '" + getEmpno() + "'";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO designation_employee(EmpNumber,Designation_ID,Time_range) values('" + getEmpno() + "','" + getDesignation() + "','" + Datecontrol.getCurrentDateString() + "')";
            con.createStatement().executeUpdate(sql);

            sql = "DELETE FROM emp_site WHERE EmployeeEmpNumber = '" + getEmpno() + "' && EMp_siteID ='" + getSite() + "'";
            con.createStatement().executeUpdate(sql);
            sql = "UPDATE emp_site SET Time_range = NOW(),is_active = '0'  WHERE is_active = '1' && EmployeeEmpNumber = '" + getEmpno() + "' ";
            con.createStatement().executeUpdate(sql);
            sql = "INSERT INTO emp_site(EmployeeEmpNumber,Time_range,site_ID) VALUES('" + getEmpno() + "','" + Datecontrol.getCurrentDateString() + "','" + getSite() + "')";
            con.createStatement().executeUpdate(sql);

            con.commit();
            con.setAutoCommit(true);
            val = true;
        } catch (Exception e) {
            val = false;
            try {
                con.rollback();
            } catch (Exception e1) {
                System.out.println(e1);
            }
            System.out.println("Error From updateEmployee Method: " + e);
        }
        return val;

    }

    public boolean deactivateEmployee(int deactid) {
        boolean val = false;
        try {
            DB.setData("update employee set is_active='0' where EmpNumber ='" + deactid + "'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From deactivateEmployee Method: " + e);
        }
        return val;

    }

    public ResultSet getEmployeeDetails(String searchterm) {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT * FROM employee WHERE fmname LIKE '" + searchterm + "%' OR lname LIKE '" + searchterm + "%' AND is_active = '1'");
        } catch (Exception e) {
            System.out.println("Error from getEmployeeDetails: " + e);
        }

        return rs;
    }

    public ResultSet getEmployeeBankData(int empnum) {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT bankcode, branchcode, Account_number FROM bankdetails WHERE EmpNumber = '" + empnum + "'");
        } catch (Exception e) {
            System.out.println("Error from getEmployeeBankData: " + e);
        }

        return rs;
    }

    public void AddvanceSave(int Year, int month) {
        try {
            DB.setData("INSERT INTO emp_advance(EmpNumber,Advance,YEAR,MONTH,DATE,TIME) VALUES('" + getEmpno() + "','" + getAdvance() + "','" + Year + "','" + month + "',NOW( ),CURTIME())");
            JOptionPane.showMessageDialog(null, "Advance has successfully added", "Advance Saving", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AddvanceUpdate(int Year, int month) {

        try {
            DB.setData("update emp_advance set Advance ='" + getAdvance() + "' ,YEAR = '" + Year + "' ,MONTH ='" + month + "',DATE=NOW( ),TIME=CURTIME() where  EmpNumber = '" + getEmpno() + "' AND YEAR = '" + Year + "' AND MONTH = '" + month + "'");
            JOptionPane.showMessageDialog(null, "Advance has successfully saved", "Advance Saving", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error While Updating: " + e, "Error!", 0);
            e.printStackTrace();
        }
    }

    public ResultSet getEmpAdvance(int typeorresult, int yr, int mt) {
        ResultSet rs = null;
        String querypart = "";
        try {
            if (typeorresult == THISEMPLOYE) {
                querypart = "emp_advance.EmpNumber='" + getEmpno() + "' AND";
            }

            rs = DB.getData("select* from emp_advance LEFT JOIN employee ON emp_advance.EmpNumber = employee.EmpNumber WHERE " + querypart + " YEAR = '" + yr + "' AND MONTH = '" + mt + "'  order by employee.EmpNumber");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public double getAdvanceTaken() {

        try {
            if (falg == 0) {

                ResultSet rs = DB.getData("select* from emp_advance   where EmpNumber='" + getEmpno() + "' && Year='" + getAdvanceTaknYear() + "' && month ='" + getAvannceTakinMonth() + "'  ");
                if (rs.next()) {
                    Advance = rs.getDouble("Advance");
                    ++falg;
                } else {
                    Advance = 0;
                    ++falg;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return Advance;
    }

    public static ResultSet getAdditions(int empnumber) {
        ResultSet rs = null;
        try {
            rs = DB.getData("select* from emp_has_addition left join additions on emp_has_addition.addition_id = additions.addition_id  where EmpNumber ='" + empnumber + "' && emp_has_addition.is_active = '1' ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;

    }

    public static ResultSet getAllAllownace(int empnumber) {
        ResultSet rs = null;
        try {
            rs = DB.getData("select* from emp_has_extra left join extravalues on emp_has_extra.extraval_id = extravalues.extraval_id  where Type ='ALLOW' && EmpNumber ='" + empnumber + "' && emp_has_extra.is_active = '1' ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;

    }

    public static ResultSet getAllDiductions(int empnumber) {

        ResultSet rs = null;
        try {
            rs = DB.getData("select* from emp_has_extra left join extravalues on emp_has_extra.extraval_id = extravalues.extraval_id where Type ='DID' && EmpNumber ='" + empnumber + "' && emp_has_extra.is_active = '1' ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public static void AddAllowDID(int id, int empNumb, String value) {
        try {
            //System.out.println("insert into emp_has_extra(EmpNumber,extraval_id,Value) values('"+empNumb+"','"+id+"','"+value+"')");
            DB.setData("insert into emp_has_extra(EmpNumber,extraval_id,Value) values('" + empNumb + "','" + id + "','" + value + "')");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void deleteAddition(int id, int empNumb) {
        try {
            DB.setData("DELETE FROM emp_has_addition  WHERE addition_id = '" + id + "' AND EmpNumber = '" + empNumb + "'");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void deleteAllowAdd(int id, int empNumb) {

        try {
            DB.setData("DELETE FROM emp_has_extra  WHERE extraval_id = '" + id + "' AND EmpNumber = '" + empNumb + "' ;");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void AddAaddition(int id, int empNumb, String value) {
        try {

            DB.setData("insert into emp_has_addition(EmpNumber,addition_id,Value) values('" + empNumb + "','" + id + "','" + value + "')");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ResultSet getPayAdditions() {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT ap.addition_id AS addid, ad.addition_name AS addtext, ap.addition_price AS addprice, ap.addition_qty AS addqty, ap.addition_amount AS addamount FROM additions_payroll ap, additions ad WHERE ap.payrol_id = '" + getPayrollnumber() + "' AND ap.addition_id = ad.addition_id");
        } catch (Exception e) {
            System.out.println("Error While getting additions: " + e);
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getPayDeductions() {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT ep.esextraval_id AS deductid, ev.NAME AS deductname, ep.amount AS deductamount FROM extravalues_payroll ep, extravalues ev WHERE ep.payrol_id = '" + getPayrollnumber() + "' AND ev.Type = 'DID' AND ep.esextraval_id = ev.extraval_id");
        } catch (Exception e) {
            System.out.println("Error While getting additions: " + e);
            e.printStackTrace();
        }

        return rs;
    }
    
    public ResultSet getFestivalAdvance(int yr, int mn) {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT py.payRollNum, py.EmpNumber, ep.amount, em.FMname, em.Lname, es.site_ID, st.Site_location FROM payroll py, extravalues_payroll ep, employee em, emppayroldata es, site st WHERE py.payRollNum = ep.payrol_id AND py.year = '"+yr+"' AND py.MONTH = '"+mn+"' AND py.is_active = '1' AND ep.esextraval_id = '5' AND em.EmpNumber = py.EmpNumber AND py.payRollNum = es.payRollNum AND es.site_ID = st.site_ID AND st.is_active = '1' ORDER BY st.site_ID");
        } catch (Exception e) {
            System.out.println("Error While getting additions: " + e);
            e.printStackTrace();
        }

        return rs;
    }
    
    public ResultSet getEmpVaridata(int empnumb){
    
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT bankcode,branchcode,SiteID,Designation_ID,ccountnumber FROM allempdetails WHERE EmpNumber = '"+empnumb+"'");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    
    
    public String getFmname() {
        return fmname;
    }

    /**
     * @param fmname the fmname to set
     */
    public void setFmname(String fmname) {
        this.fmname = fmname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the addno
     */
    public String getAddno() {
        return addno;
    }

    /**
     * @param addno the addno to set
     */
    public void setAddno(String addno) {
        this.addno = addno;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the conhome
     */
    public String getConhome() {
        return conhome;
    }

    /**
     * @param conhome the conhome to set
     */
    public void setConhome(String conhome) {
        this.conhome = conhome;
    }

    /**
     * @return the conmobi
     */
    public String getConmobi() {
        return conmobi;
    }

    /**
     * @param conmobi the conmobi to set
     */
    public void setConmobi(String conmobi) {
        this.conmobi = conmobi;
    }

    /**
     * @return the nicno
     */
    public String getNicno() {
        return nicno;
    }

    /**
     * @param nicno the nicno to set
     */
    public void setNicno(String nicno) {
        this.nicno = nicno;
    }

    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @return the accno
     */
    public String getAccno() {
        return accno;
    }

    /**
     * @param accno the accno to set
     */
    public void setAccno(String accno) {
        this.accno = accno;
    }

    /**
     * @return the empno
     */
    public int getEmpno() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpno(int empno) {
        this.empno = empno;
    }

    /**
     * @return the epfno
     */
    public int getEpfno() {
        return epfno;
    }

    /**
     * @param epfno the epfno to set
     */
    public void setEpfno(int epfno) {
        this.epfno = epfno;
    }

    /**
     * @return the etype
     */
    public int getEtype() {
        return etype;
    }

    /**
     * @param etype the etype to set
     */
    public void setEtype(int etype) {
        this.etype = etype;
    }

    /**
     * @return the designation
     */
    public int getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(int designation) {
        this.designation = designation;
    }

    /**
     * @return the division
     */
    public int getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(int division) {
        this.division = division;
    }

    /**
     * @return the site
     */
    public int getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(int site) {
        this.site = site;
    }

    /**
     * @return the joineddate
     */
    public String getJoineddate() {
        return joineddate;
    }

    /**
     * @param joineddate the joineddate to set
     */
    public void setJoineddate(String joineddate) {
        this.joineddate = joineddate;
    }

    /**
     * @return the regdate
     */
    public String getRegdate() {
        return regdate;
    }

    /**
     * @param regdate the regdate to set
     */
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    /**
     * @return the BasicSalary
     */
    public double getBasicSalary() {
        return BasicSalary;
    }

    /**
     * @param BasicSalary the BasicSalary to set
     */
    public void setBasicSalary(double BasicSalary) {
        this.BasicSalary = BasicSalary;
    }

    /**
     * @return the Gallowance
     */
    public double getGallowance() {
        return Gallowance;
    }

    /**
     * @param Gallowance the Gallowance to set
     */
    public void setGallowance(double Gallowance) {
        this.Gallowance = Gallowance;
    }

    /**
     * @return the Ballowance
     */
    public double getBallowance() {
        return Ballowance;
    }

    /**
     * @param Ballowance the Ballowance to set
     */
    public void setBallowance(double Ballowance) {
        this.Ballowance = Ballowance;
    }

    /**
     * @return the EPFNumber
     */
    public int getEPFNumber() {
        return EPFNumber;
    }

    /**
     * @param EPFNumber the EPFNumber to set
     */
    public void setEPFNumber(int EPFNumber) {
        this.EPFNumber = EPFNumber;
    }

    /**
     * @return the EmployeeType
     */
    public int getEmployeeType() {
        return EmployeeType;
    }

    /**
     * @param EmployeeType the EmployeeType to set
     */
    public void setEmployeeType(int EmployeeType) {
        this.EmployeeType = EmployeeType;
    }

    /**
     * @return the Advance
     */
    /**
     * @param Advance the Advance to set
     */
    public void setAdvance(double Advance) {
        this.Advance = Advance;
    }

    /**
     * @return the AdvanceTaknYear
     */
    public int getAdvanceTaknYear() {
        return AdvanceTaknYear;
    }

    /**
     * @param AdvanceTaknYear the AdvanceTaknYear to set
     */
    public void setAdvanceTaknYear(int AdvanceTaknYear) {
        this.AdvanceTaknYear = AdvanceTaknYear;
    }

    /**
     * @return the AvannceTakinMonth
     */
    public int getAvannceTakinMonth() {
        return AvannceTakinMonth;
    }

    /**
     * @param AvannceTakinMonth the AvannceTakinMonth to set
     */
    public void setAvannceTakinMonth(int AvannceTakinMonth) {
        this.AvannceTakinMonth = AvannceTakinMonth;
    }

    /**
     * @return the employeeTypeName
     */
    public String getEmployeeTypeName() {
        return employeeTypeName;
    }

    /**
     * @param employeeTypeName the employeeTypeName to set
     */
    public void setEmployeeTypeName(String employeeTypeName) {
        this.employeeTypeName = employeeTypeName;
    }

    public double getAdvance() {
        return Advance;
    }

    /**
     * @return the bankNumber
     */
    public String getBankNumber() {
        return bankNumber;
    }

    /**
     * @param bankNumber the bankNumber to set
     */
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    /**
     * @return the branchNumber
     */
    public String getBranchNumber() {
        return branchNumber;
    }

    /**
     * @param branchNumber the branchNumber to set
     */
    public void setBranchNumber(String branchNumber) {
        this.branchNumber = branchNumber;
    }

    /**
     * @return the payrollnumber
     */
    public int getPayrollnumber() {
        return payrollnumber;
    }

    /**
     * @param payrollnumber the payrollnumber to set
     */
    public void setPayrollnumber(int payrollnumber) {
        this.payrollnumber = payrollnumber;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the addlist
     */
    public ArrayList getAddlist() {
        return addlist;
    }

    /**
     * @param addlist the addlist to set
     */
    public void setAddlist(ArrayList addlist) {
        this.addlist = addlist;
    }

    /**
     * @return the payType
     */
    public String getPayType() {
        return payType;
    }

    /**
     * @param payType the payType to set
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * @return the npdays
     */
    public double getNpdays() {
        return npdays;
    }

    /**
     * @param npdays the npdays to set
     */
    public void setNpdays(double npdays) {
        this.npdays = npdays;
    }

    /**
     * @return the othours
     */
    public double getOthours() {
        return othours;
    }

    /**
     * @param othours the othours to set
     */
    public void setOthours(double othours) {
        this.othours = othours;
    }
}
