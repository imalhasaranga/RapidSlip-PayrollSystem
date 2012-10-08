/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import control.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.InProgress;

/**
 *
 * @author Imal
 */
public class PayRoll {

    CalculateSalary sal;
    Employee emp;
    JTable Aditions;
    JTable diduct;

    public PayRoll() {
    }

    public PayRoll(CalculateSalary salryReport) {
        sal = salryReport;
        emp = sal.getEmployee();
    }

    public void setAlloAddValues(JTable tableValue) {
        Aditions = tableValue;
    }

    public void setDeductions(JTable didution) {
        diduct = didution;
    }

    public boolean AddRecordToDb() {
        Connection con = null;
        try {

            double pakpay = sal.getNetSalary() > 25000 ? (sal.getNetSalary() - 25) : sal.getNetSalary();


            con = DB.getCon();
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            String sql = "INSERT INTO payroll("
                    + "EmpNumber,"
                    + "Besic_salary,"
                    + "Gvt_allow,"
                    + "Bud_allow,"
                    + "NpRateBasic,"
                    + "NpDays,"
                    + "NpTotalBasic,"
                    + "Epf_employee,"
                    + "EpF_company,"
                    + "Etf,"
                    + "Balance_basic,"
                    + "OT_rate,"
                    + "OT_hours,"
                    + "OT_total,"
                    + "Total_allowances,"
                    + "NpRateAllow,"
                    + "NpTotalAllowances,"
                    + "Netallowances,"
                    + "GrossTotal_addition,"
                    + "TotalAdditionsWithOT,"
                    + "GrosssalaryEarnings,"
                    + "PayeEmp,"
                    + "Total_deduction,"
                    + "SalaryShouldget,"
                    + "AdvanceTaken,"
                    + "FinalSalary,"
                    + "PackSal,"
                    + "PayType,"
                    + "Date,"
                    + "YEAR,"
                    + "MONTH,"
                    + "Time,EmpType,sysuserID)"
                    + "VALUES("
                    + "'" + emp.getEmpno() + "'"
                    + ", "
                    + "'" + sal.getBasicSalary() + "'"
                    + ", "
                    + "'" + sal.getGvAllowanse() + "'"
                    + ", "
                    + "'" + sal.getBudAllowanse() + "'"
                    + ", "
                    + "'" + sal.getNpRateBasic() + "'"
                    + ", "
                    + "'" + sal.getAbsentDays() + "'"
                    + " , "
                    + "'" + sal.getNpValueSal() + "'"
                    + ", "
                    + "'" + sal.getEPFemployee() + "'"
                    + ", "
                    + "'" + sal.getEPFcompany() + "'"
                    + ", "
                    + "'" + sal.getETF() + "'"
                    + ", "
                    + "'" + sal.getBalanceBasic() + "'"
                    + ", "
                    + "'" + sal.getOTRate() + "'"
                    + ", "
                    + "'" + sal.getOThours() + "'"
                    + ", "
                    + "'" + sal.getOTValue() + "'"
                    + ", "
                    + "'" + sal.getGrossTotalAlwonse() + "'"
                    + ", "
                    + "'" + sal.getNpRateAllow() + "'"
                    + ", "
                    + "'" + sal.getNpValueAllow() + "'"
                    + ", "
                    + "'" + sal.getNetTotalAllowance() + "'"
                    + ", "
                    + "'" + sal.getGrossTotalAddition() + "'"
                    + ", "
                    + "'" + sal.getTotalAdditoins() + "'"
                    + ", "
                    + "'" + sal.getGrossSalary() + "'"
                    + ", "
                    + "'" + sal.getPAYEForEmp() + "'"
                    + ", "
                    + "'" + sal.getTotalDiductions() + "'"
                    + ", "
                    + "'" + sal.getSalaryShouldGet() + "'"
                    + ", "
                    + "'" + sal.getAdvance() + "'"
                    + ", "
                    + "'" + sal.getNetSalary() + "'"
                    + ",'" + pakpay + "'"
                    + ",'" + sal.getPaytype() + "'"
                    + ", "
                    + "NOW()"
                    + ", "
                    + "'" + emp.getAdvanceTaknYear() + "'"
                    + ", "
                    + "'" + emp.getAvannceTakinMonth() + "',"
                    + "CURTIME(),'" + emp.getEmployeeTypeName() + "','" + User.SisUserID + "')";


            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();
            rs.first();
            int ID = rs.getInt(1);

            DefaultTableModel df = (DefaultTableModel) Aditions.getModel();
            Vector v = df.getDataVector();
            for (int i = 0; i < df.getRowCount(); ++i) {
                Vector row = (Vector) v.elementAt(i);
                if (row.get(0).toString().equals("Alow")) {
                    sql = "INSERT INTO extravalues_payroll"
                            + "(payrol_id,"
                            + "esextraval_id,"
                            + "amount"
                            + ")"
                            + "VALUES"
                            + "('" + ID + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + row.get(2) + "'"
                            + ")";

                    con.createStatement().executeUpdate(sql);
                } else if (row.get(0).toString().equals("ADD")) {
                    String ar[] = row.get(1).toString().split("-")[1].split("X");
                    sql = "INSERT INTO additions_payroll"
                            + "(payrol_id,"
                            + "addition_id,"
                            + "addition_price,"
                            + "addition_qty,"
                            + "addition_amount"
                            + ")"
                            + "VALUES"
                            + "('" + ID + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + ar[0].toString().trim() + "', "
                            + "'" + ar[1].toString().trim() + "', "
                            + "'" + row.get(2) + "'"
                            + ");";

                    con.createStatement().executeUpdate(sql);
                }
            }

            df = (DefaultTableModel) diduct.getModel();
            v = df.getDataVector();
            for (int i = 0; i < df.getRowCount(); ++i) {
                Vector row = (Vector) v.elementAt(i);
                if (row.get(0).toString().equals("DID")) {
                    sql = "INSERT INTO extravalues_payroll"
                            + "(payrol_id,"
                            + "esextraval_id,"
                            + "amount"
                            + ")"
                            + "VALUES"
                            + "('" + ID + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + row.get(2) + "'"
                            + ")";

                    con.createStatement().executeUpdate(sql);
                }

            }

            ResultSet bankdetails = emp.getEmpVaridata(emp.getEmpno());
            bankdetails.first();

            String bankcode = "'" + (bankdetails.getString("bankcode")) + "'";
            String branchcode = "'" + (bankdetails.getString("branchcode")) + "'";
            String Accountnub = "'" + (bankdetails.getString("ccountnumber")) + "'";


            if (branchcode.trim().replace("'", "").toLowerCase().equals("null")) {
                bankcode = "Bankcode";
                branchcode = "branchCode";
                Accountnub = "Account_numb";
            }

            sql = "insert into emppayroldata "
                    + "(payRollNum, "
                    + "Designation_id, "
                    + "Site_id, "
                    + "Bankcode, "
                    + "branchCode, "
                    + "Account_numb "
                    + ") "
                    + "values "
                    + "('" + ID + "', "
                    + "'" + (bankdetails.getString("Designation_ID")) + "', "
                    + "'" + (bankdetails.getString("SiteID")) + "', "
                    + " " + bankcode + ", "
                    + " " + branchcode + ", "
                    + " " + Accountnub + " "
                    + " )";

            con.createStatement().executeUpdate(sql);

            con.commit();
            con.setAutoCommit(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception e1) {
                System.out.println("erro in roll back " + e);
            }

            System.out.println(e);
            return false;
        }
    }

    public boolean updatePayroll(int updateid) {
        Connection con = null;
        try {

            double pakpay = sal.getNetSalary() > 25000 ? (sal.getNetSalary() - 25) : sal.getNetSalary();

            con = DB.getCon();
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            String sql = "update payroll set NpDays = '" + sal.getAbsentDays() + "',"
                    + "NpRateBasic = '" + sal.getNpRateBasic() + "',"
                    + "NpTotalBasic = '" + sal.getNpValueSal() + "',"
                    + "Epf_employee = '" + sal.getEPFemployee() + "',"
                    + "EpF_company = '" + sal.getEPFcompany() + "',"
                    + "Etf = '" + sal.getETF() + "',"
                    + "Balance_basic = '" + sal.getBalanceBasic() + "',"
                    + "OT_rate = '" + sal.getOTRate() + "',"
                    + "OT_hours = '" + sal.getOThours() + "',"
                    + "OT_total = '" + sal.getOTValue() + "',"
                    + "Total_allowances = '" + sal.getGrossTotalAlwonse() + "',"
                    + "NpRateAllow = '" + sal.getNpRateAllow() + "',"
                    + "NpTotalAllowances = '" + sal.getNpValueAllow() + "',"
                    + "Netallowances = '" + sal.getNetTotalAllowance() + "',"
                    + "GrossTotal_addition = '" + sal.getGrossTotalAddition() + "',"
                    + "TotalAdditionsWithOT = '" + sal.getTotalAdditoins() + "',"
                    + "GrosssalaryEarnings = '" + sal.getGrossSalary() + "',"
                    + "PayeEmp = '" + sal.getPAYEForEmp() + "',"
                    + "Total_deduction = '" + sal.getTotalDiductions() + "',"
                    + "SalaryShouldget = '" + sal.getSalaryShouldGet() + "',"
                    + "AdvanceTaken = '" + sal.getAdvance() + "',"
                    + "FinalSalary = '" + sal.getNetSalary() + "',"
                    + "PackSal = '" + pakpay + "',"
                    + "PayType = '" + sal.getPaytype() + "',"
                    + "Date = NOW(),"
                    + "Time = CURTIME(), sysuserID = '" + User.SisUserID + "' where payRollNum = '" + updateid + "'";

            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);



//Delete all current values(Allowances, additions, deductions) from relevant tables................
            String sql2 = "DELETE FROM extravalues_payroll WHERE payrol_id = '" + updateid + "'";
            String sql3 = "DELETE FROM additions_payroll WHERE payrol_id = '" + updateid + "'";

            con.createStatement().executeUpdate(sql2);
            con.createStatement().executeUpdate(sql3);

//Again Enter Allowance, Additions & Deductions data from Jtables, Similar to above method.........

            DefaultTableModel df = (DefaultTableModel) Aditions.getModel();
            Vector v = df.getDataVector();
            for (int i = 0; i < df.getRowCount(); ++i) {
                Vector row = (Vector) v.elementAt(i);
                if (row.get(0).toString().equals("Alow")) {
                    sql = "INSERT INTO extravalues_payroll"
                            + "(payrol_id,"
                            + "esextraval_id,"
                            + "amount"
                            + ")"
                            + "VALUES"
                            + "('" + updateid + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + row.get(2) + "'"
                            + ")";

                    con.createStatement().executeUpdate(sql);
                } else if (row.get(0).toString().equals("ADD")) {
                    String ar[] = row.get(1).toString().split("-")[1].split("X");
                    System.out.println(row.get(1) + "there is an error");
                    sql = "INSERT INTO additions_payroll"
                            + "(payrol_id,"
                            + "addition_id,"
                            + "addition_price,"
                            + "addition_qty,"
                            + "addition_amount"
                            + ")"
                            + "VALUES"
                            + "('" + updateid + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + ar[0].toString().trim() + "', "
                            + "'" + ar[1].toString().trim() + "', "
                            + "'" + row.get(2) + "'"
                            + ");";

                    con.createStatement().executeUpdate(sql);
                }
            }

            df = (DefaultTableModel) diduct.getModel();
            v = df.getDataVector();
            for (int i = 0; i < df.getRowCount(); ++i) {
                Vector row = (Vector) v.elementAt(i);
                if (row.get(0).toString().equals("DID")) {
                    sql = "INSERT INTO extravalues_payroll"
                            + "(payrol_id,"
                            + "esextraval_id,"
                            + "amount"
                            + ")"
                            + "VALUES"
                            + "('" + updateid + "', "
                            + "'" + row.get(3) + "', "
                            + "'" + row.get(2) + "'"
                            + ")";

                    con.createStatement().executeUpdate(sql);
                }

            }


            ResultSet bankdetails = emp.getEmpVaridata(emp.getEmpno());
            bankdetails.first();

            String bankcode = "'" + (bankdetails.getString("bankcode")) + "'";
            String branchcode = "'" + (bankdetails.getString("branchcode")) + "'";
            String Accountnub = "'" + (bankdetails.getString("ccountnumber")) + "'";

            if (branchcode.trim().replace("'", "").toLowerCase().equals("null")) {
                bankcode = "Bankcode";
                branchcode = "branchCode";
                Accountnub = "Account_numb";
            }

            sql = "update emppayroldata set "
                    + "Designation_id = '" + (bankdetails.getString("Designation_ID")) + "', "
                    + "Site_id = '" + (bankdetails.getString("SiteID")) + "', "
                    + "Bankcode =  " + bankcode + ", "
                    + "branchCode = " + branchcode + ", "
                    + "Account_numb = " + Accountnub + " "
                    + " where  payRollNum = '" + updateid + "' ";

            con.createStatement().executeUpdate(sql);

            con.commit();
            con.setAutoCommit(true);
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (Exception e1) {
                System.out.println("erro in roll back " + e);
            }

            System.out.println(e);
            return false;

        }

    }

    public ResultSet getPayroll(int year, int month, String Site) {
        ResultSet rs = null;
        try {
            String querypart = "";
            if (!Site.equals("All Sites")) {
                querypart = " && Site_location ='" + Site + "' ";
            }

            String sql = "SELECT "
                    + "payroll.PayrollNum  AS PayrollNum, "
                    + "payroll.EmpNumber, "
                    + "FMname, "
                    + "Lname, "
                    + "Designation, "
                    + "Besic_salary, "
                    + "NpTotalBasic, "
                    + "payroll.PayType, "
                    + "Site_location, "
                    + "FinalSalary, "
                    + "SalaryShouldget, "
                    + "AdvanceTaken, "
                    + "Total_deduction, "
                    + "PayeEmp, "
                    + "OT_total, "
                    + "GrossTotal_addition, "
                    + "NpTotalAllowances, "
                    + "Total_allowances, "
                    + "Balance_basic, "
                    + "etf, "
                    + "payroll.is_active, "
                    + "Epf_company, "
                    + "Epf_employee  "
                    + "FROM payroll "
                    + "LEFT JOIN emppayroldata "
                    + "  ON payroll.payRollNum = emppayroldata.payRollNum "
                    + "LEFT JOIN site "
                    + "  ON emppayroldata.Site_id = site.site_ID  "
                    + "LEFT JOIN designation "
                    + "  ON emppayroldata.Designation_id = designation.Designation_ID "
                    + "LEFT JOIN employee "
                    + "  ON payroll.EmpNumber = employee.EmpNumber WHERE YEAR ='" + year + "' && MONTH = '" + month + "' && payroll.is_active = '1' " + querypart + " ORDER BY payroll.EmpNumber";
            rs = DB.getData(sql);
            //  rs = DB.getData("SELECT* FROM payroll LEFT JOIN employeesum ON payroll.EmpNumber = employeesum.EmpNumber WHERE YEAR ='" + year + "' && MONTH = '" + month + "' && payroll.is_active = '1' " + querypart + " ORDER BY employeesum.EmpNumber");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public double getPayrollTotal(int yr, int mn) {
        double payrolltotal = 0.0;

        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT SUM(FinalSalary) AS totpayamount FROM payroll WHERE YEAR = '" + yr + "' AND MONTH = '" + mn + "' AND is_active = '1'");
            rs.next();
            payrolltotal = rs.getDouble("totpayamount");
        } catch (Exception e) {
            System.out.println(e);
        }

        return payrolltotal;
    }

    public ResultSet getPayrollByEmp(int empno, int year, int month) {
        ResultSet rs = null;
        try {
            rs = DB.getData("select * from payroll where empnumber = '" + empno + "' and year = '" + year + "' and month = '" + month + "' and is_active = '1'");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet getPayrollOrderd(int year, int month, String Site) {
        ResultSet rs = null;
        try {
            String querypart = "";
            if (!Site.equals("All Sites")) {
                querypart = " && Site_location ='" + Site + "' ";
            }

            rs = DB.getData("SELECT* FROM payroll LEFT JOIN employeesum ON payroll.EmpNumber = employeesum.EmpNumber WHERE YEAR ='" + year + "' && MONTH = '" + month + "' && payroll.is_active = '1' " + querypart + " ORDER BY payroll.PayType");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public ResultSet getPayrolForTEMPDAT(int year, int month) {
        ResultSet rs = null;
        try {

            String sql = "SELECT "
                    + "Date, "
                    +"payroll.EmpNumber, "
                    + "employee.Emp_TypeID, "
                    + "payroll.PayType            AS PayType, "
                    + "emppayroldata.Bankcode     AS bankcode, "
                    + "emppayroldata.branchCode   AS branchcode, "
                    + "emppayroldata.Account_numb AS Account_number, "
                    + "FMname, "
                    + "Lname, "
                    + "PackSal, "
                    + "payroll.is_active, "
                    + "FinalSalary "
                    + "FROM payroll_data.payroll "
                    + "LEFT JOIN payroll_data.employee "
                    + " ON (payroll.EmpNumber = employee.EmpNumber) "
                    + "LEFT JOIN payroll_data.emppayroldata "
                    + " ON (emppayroldata.payRollNum = payroll.payRollNum) WHERE YEAR ='" + year + "' && MONTH = '" + month + "' && payroll.is_active = '1'  ";
            
            System.out.println(sql);
            rs = DB.getData(sql);
            // rs = DB.getData("SELECT* FROM payroll LEFT JOIN employeesum ON payroll.EmpNumber = employeesum.EmpNumber LEFT JOIN bankdetails ON payroll.EmpNumber = bankdetails.EmpNumber  WHERE YEAR ='" + year + "' && MONTH = '" + month + "' && payroll.is_active = '1'  ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;

    }

    public void deActivateReocrd(int i) {

        try {
            DB.setData("update payroll set is_active='0' where PayRollNum='" + i + "' ");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized void printRecords(final JTable jt, final int year, final int month) {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                int rowcount = 0;
                ArrayList<EmpDetails> ar = new ArrayList<>();
                for (int i = 0; i < jt.getRowCount(); i++) {
                    boolean bol = Boolean.parseBoolean(jt.getValueAt(i, 1).toString());
                    if (bol) {
                        ++rowcount;
                        EmpDetails emp = new EmpDetails();
                        emp.ID = Integer.parseInt(jt.getValueAt(i, 20).toString());
                        emp.site = jt.getValueAt(i, 4).toString();
                        emp.Designation = jt.getValueAt(i, 3).toString();
                        ar.add(emp);
                    }
                }
                InProgress inpro = new InProgress(0, rowcount);
                inpro.setVisible(true);
                int counter = 0;
                for (EmpDetails emp1 : ar) {
                    try {

                        ResultSet rs = DB.getData("SELECT* FROM payroll LEFT JOIN employee ON payroll.EmpNumber = employee.EmpNumber LEFT JOIN bankdetails ON employee.EmpNumber = bankdetails.EmpNumber where payRollNum ='" + emp1.ID + "'");

                        //PrintSlipNormal slip = new PrintSlipNormal();
                        PrintSlip1 slip = new PrintSlip1();
                        if (rs.next()) {
                            String payrolid = rs.getString("payroll.payRollNum");
                            ArrayList<Allsaldetails> allowances = new ArrayList();
                            ArrayList<Allsaldetails> didcution = new ArrayList();
                            ArrayList<Allsaldetails> additon = new ArrayList();

                            ResultSet rs1 = DB.getData("SELECT* FROM extravalues_payroll LEFT JOIN extravalues  ON (extravalues_payroll.esextraval_id = extravalues.extraval_id) where payrol_id ='" + payrolid + "' && Type ='ALLOW' ");
                            while (rs1.next()) {
                                Allsaldetails allow = new Allsaldetails();
                                allow.detailname = rs1.getString("extravalues.Name");
                                allow.amount = rs1.getDouble("amount");
                                allowances.add(allow);
                            }
                            slip.setArray_allow(allowances);

                            ResultSet rs2 = DB.getData("SELECT* FROM extravalues_payroll LEFT JOIN extravalues  ON (extravalues_payroll.esextraval_id = extravalues.extraval_id) where payrol_id ='" + payrolid + "' && Type ='DID' ");
                            while (rs2.next()) {
                                Allsaldetails did = new Allsaldetails();
                                did.detailname = rs2.getString("extravalues.Name");
                                did.amount = rs2.getDouble("amount");
                                didcution.add(did);
                            }
                            slip.setArray_deduct(didcution);

                            String query = "SELECT* FROM additions_payroll LEFT JOIN additions  ON (additions_payroll.addition_id = additions.addition_id) where payrol_id ='" + payrolid + "' ";
                            ResultSet rs3 = DB.getData(query);
                            while (rs3.next()) {
                                Allsaldetails add = new Allsaldetails();
                                add.detailname = rs3.getString("addition_name");
                                add.amount = rs3.getDouble("addition_amount");
                                add.description = rs3.getString("addition_price") + " X " + rs3.getString("addition_qty");
                                additon.add(add);
                            }
                            slip.setArray_addition(additon);

                            slip.setEmpname(rs.getString("FMname") + " " + rs.getString("Lname"));
                            slip.setEmpno(rs.getString("employee.EmpNumber"));
                            slip.setEpfno(rs.getString("employee.EpfNumber"));
                            slip.setSite(emp1.site);
                            slip.setDesignation(emp1.Designation);

                            slip.setYear(year);
                            slip.setMonth(month);
                            slip.setBasicpay(rs.getDouble("Besic_salary"));
                            slip.setGvtallow(rs.getDouble("Gvt_allow"));
                            slip.setBudallow(rs.getDouble("Bud_allow"));
                            slip.setNpratebasic(rs.getDouble("NpRateBasic"));
                            slip.setNpdays(rs.getDouble("NpDays"));
                            slip.setNptotalbasic(rs.getDouble("NpTotalBasic"));
                            slip.setTotalforepf(rs.getDouble("Besic_salary") + rs.getDouble("Bud_allow") + rs.getDouble("Gvt_allow") - rs.getDouble("NpTotalBasic"));
                            slip.setEpfemployee(rs.getDouble("Epf_employee"));
                            slip.setEpfcompany(rs.getDouble("EpF_company"));
                            slip.setEtf(rs.getDouble("Etf"));
                            slip.setBalancebasic(rs.getDouble("Balance_basic"));
                            slip.setOtrate(rs.getDouble("OT_rate"));
                            slip.setOthours(rs.getDouble("OT_hours"));
                            slip.setOtamount(rs.getDouble("OT_total"));
                            slip.setTotallowance(rs.getDouble("Total_allowances"));
                            slip.setNprateallow(rs.getDouble("NpRateAllow"));
                            slip.setNptotalallowance(rs.getDouble("NpTotalAllowances"));
                            slip.setGrossallowance(rs.getDouble("Netallowances"));
                            slip.setAdditiontotal(rs.getDouble("TotalAdditionsWithOT"));
                            //slip.setNetsalary(rs.getDouble(""));
                            slip.setAdvancepay(rs.getDouble("AdvanceTaken"));
                            slip.setGross_salary(rs.getDouble("SalaryShouldget"));
                            slip.setDeducttotal(rs.getDouble("Total_deduction"));
                            slip.setPaye(rs.getDouble("PayeEmp"));
                            slip.setBalancepay(rs.getDouble("FinalSalary"));
                            slip.setPacketPay(rs.getDouble("PackSal"));

                            slip.setEpftotal(rs.getDouble("Epf_employee") + rs.getDouble("EpF_company"));


                            slip.setBank(rs.getString("Bank"));
                            slip.setBranch(rs.getString("Bank_branch"));
                            slip.setAccno(rs.getString("Account_number"));




                            slip.printThisInvoice();
                        }
                        inpro.moveProgress(++counter);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

            }
        });

        t.start();

    }
}

class EmpDetails {

    public int ID;
    public String site;
    public String Designation;
}
