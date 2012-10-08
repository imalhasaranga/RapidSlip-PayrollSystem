package control;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import model.DB;
import model.Employee;

/**
 *
 * @author Imal
 */
public final class CalculateSalary {

    //main variables we should set
    //___________________________________________
    private double BasicSalary; //---
    private double GvAllowanse;//---
    private double BudAllowanse; //---
    private double AbsentDays;
    private double OThours;
    private double Advance; //---
    private int EmpTypeValue;//---
    private String EmpTypeName;//---
    //______________________________________________  
    private double NpRateBasic;
    private double OTRate;
    private double NpRateAllow;
    private double OTValue;
    private double NpValueSal;
    private double NpValueAllow;
    //______________________________________________  
    private double CalSalaryvalue;
    //______________________________________________  
    private double SalaryPF;
    private double EPFemployee;
    private double EPFcompany;
    private double ETF;
    //______________________________________________  
    private double grosTotalAllwonce;
    private double grosTotalAdditions;
    //______________________________________________  
    private double netTotalAllowance;
    private double TotalAdditionses;
    private double TotalDiductions;
    //______________________________________________  
    private double BalanceBasic;
    private double GrossSalary;
    private double GrossSalaryforpayee;
    private double PayErate;
    private double PAYEForEmp;
    private double allDiductionsTotal;
    private double netSalary;
    DecimalFormat format = new DecimalFormat("#.##");
    private Employee employee;

    private String paytype;
    
    public CalculateSalary() {
    }

    public CalculateSalary(Employee emp) {
        this.employee = emp;
        if (emp.getEmployeeType() == 1) {
            setEmpTypeValue(26);
            emp.setEmployeeTypeName("Site");
        } else if (emp.getEmployeeType() == 2) {
            setEmpTypeValue(30);
            emp.setEmployeeTypeName("Office");
        }

        setAdvance(emp.getAdvanceTaken());
        setBasicSalary(emp.getBasicSalary());
        setGvAllowanse(emp.getGallowance());
        setBudAllowanse(emp.getBallowance());
        setPaytype(emp.getPayType());
        CalSalaryvalue = getBasicSalary() + getBudAllowanse();
    }
    
    public CalculateSalary(Employee emp, int year, int month) {
        this.employee = emp;
        if (emp.getEmployeeType() == 1) {
            setEmpTypeValue(26);
            emp.setEmployeeTypeName("Site");
        } else if (emp.getEmployeeType() == 2) {
            setEmpTypeValue(30);
            emp.setEmployeeTypeName("Office");
        }

        setAdvance(emp.getAdvanceTaken());
        setBasicSalary(emp.getBasicSalary());
        setGvAllowanse(emp.getGallowance());
        setBudAllowanse(emp.getBallowance());
        CalSalaryvalue = getBasicSalary() + getBudAllowanse();
    }
    
    //------------------------------ nopay calculation --------------------------------------------------

    public double calNopayRateForBasic() {
        NpRateBasic = Math.round(Double.parseDouble(format.format((CalSalaryvalue / getEmpTypeValue()))));
        return getNpRateBasic();

    }

    public double calNopay() {

        NpValueSal = Math.round(Double.parseDouble(format.format((calNopayRateForBasic() * getAbsentDays()))));

        return getNpValueSal();
    }
//------------------------------ nopay calculation end--------------------------------------------------

//------------------------------ ot rate calculation-------------------------------------------------   
    public double calOTrate() {

        return OTRate = Math.round(Double.parseDouble(format.format(((CalSalaryvalue / (getEmpTypeValue() * 8))) * 1.5)));

    }

    public double callOT() {
        OTValue = Math.round(Double.parseDouble(format.format((calOTrate() * getOThours()))));
        return getOTValue();
    }
//------------------------------ ot rate calculation end-------------------------------------------------   

    public double CalSalaryPF() {

        setSalaryPF(Math.round(Double.parseDouble(format.format((getBasicSalary() + getBudAllowanse() + getGvAllowanse() - calNopay())))));

        return getSalaryPF();
    }

    public double calEPFemployee() {

        setEPFemployee(Math.round(Double.parseDouble(format.format((CalSalaryPF() * 0.08)))));
        return getEPFemployee();
    }

    public double calEPFcompany() {

        setEPFcompany(Math.round(Double.parseDouble(format.format((CalSalaryPF() * 0.12)))));
        return getEPFcompany();
    }

    public double calETF() {
        setETF(Math.round(Double.parseDouble(format.format((CalSalaryPF() * 0.03)))));
        return getETF();
    }

    public double CalBalanceBasic() {

        BalanceBasic = Math.round(CalSalaryPF() - getEPFemployee());
        return Math.round(Double.parseDouble(format.format(getBalanceBasic())));
    }

    //------------------------- allowanceses ---------------------------------------------------
    public void UpdateGrossTotalAlwonse(double grosTotalAlwonce) {
        this.grosTotalAllwonce += grosTotalAlwonce;
    }

    public double getGrossTotalAlwonse() {
        return grosTotalAllwonce;
    }

    public double calNopayRateForAllownse() {
        NpRateAllow = Math.round(Double.parseDouble(format.format((grosTotalAllwonce / getEmpTypeValue()))));
        return getNpRateAllow();
    }

    public double NopayValueForAllwonses() {
        NpValueAllow = Math.round(Double.parseDouble(format.format((calNopayRateForAllownse() * getAbsentDays()))));
        return getNpValueAllow();
    }

    public double NetTotalAllownse() {
        netTotalAllowance = Math.round(Double.parseDouble(format.format(getGrossTotalAlwonse() - getNpValueAllow())));
        return netTotalAllowance;
    }

    public double getNetTotalAllowance() {
        return netTotalAllowance;
    }
    //----------------------------------------------------------------------------------------------
    // ------------------------- additions ----------------------------------------------

    public void UpadateAdditions(double grosAdditions) {
        this.grosTotalAdditions = this.grosTotalAdditions + grosAdditions;
    }

    public double calTotalAdditions() {
        return TotalAdditionses = grosTotalAdditions + getOTValue();
    }

    public double getGrossTotalAddition() {
        return this.grosTotalAdditions;
    }

    public double getTotalAdditoins() {
        return TotalAdditionses;
    }

    //-------------------------------------------------------------------------------------------------
    //-------------------------- Diductions -----------------------------------------------------------
    public void UpdateTotalDiductions(double TotalDiductions) {
        this.TotalDiductions += TotalDiductions;
    }

    public double getTotalDiductions() {
        return TotalDiductions;
    }

    //-------------------------------------------------------------------------------------------------
    public double calGrossSalary() {
        return this.GrossSalary = Math.round(Double.parseDouble(format.format((CalBalanceBasic() + getTotalAdditoins() + getNetTotalAllowance()))));
    }

    public void setGrossSalary(double grosssarly) {
        this.GrossSalary = grosssarly;
    }

    public double CalGrossSalaryForPayee() {
        return this.GrossSalaryforpayee = Math.round(Double.parseDouble(format.format((CalBalanceBasic() + getTotalAdditoins() + getNetTotalAllowance() + getEPFemployee()))));
    }

    public double getSalaryShouldGet() {

        return Math.round(Double.parseDouble(format.format(getGrossSalary() - getPAYEForEmp() - getTotalDiductions())));
    }

    public double calAllTotalDiductions() {
        return allDiductionsTotal = Math.round(getPAYEForEmp() + getTotalDiductions() + getAdvance());
    }

    public double calPAYE() {
        double GrossSalary1 = CalGrossSalaryForPayee();
        if (GrossSalary1 <= 50000) {
            setPAYEForEmp(0);
        } else if (GrossSalary1 >= 300000) {
            setPAYEForEmp(Double.parseDouble(format.format(((GrossSalary1 - 300000) * 0.24) + 33333)));

        } else {
            try {

                String sql = "SELECT aval FROM paye WHERE '" + GrossSalary1 + "' >= afrom AND '" + GrossSalary1 + "' <= ato";
                ResultSet salry = DB.getData(sql);
                salry.first();
                setPAYEForEmp(salry.getDouble("aval"));
            } catch (Exception e) {
                System.out.println("error in getpayerate " + e);
            }
        }
        return getPAYEForEmp();
    }

    public double calPAYE(double salary) {
        double GrossSalary1 = salary;
        if (GrossSalary1 <= 50000) {
            salary = 0;
        } else if (GrossSalary1 >= 300000) {
            salary = Double.parseDouble(format.format(((GrossSalary1 - 300000) * 0.24) + 33333));

        } else {
            try {

                String sql = "SELECT aval FROM paye WHERE '" + GrossSalary1 + "' >= afrom AND '" + GrossSalary1 + "' <= ato";
                ResultSet salry = DB.getData(sql);
                salry.first();
                salary = salry.getDouble("aval");
            } catch (Exception e) {
                System.out.println("error in getpayerate " + e);
            }
        }
        return salary;
    }

    public double calFinalSalary() {

        System.out.println(Math.round(Double.parseDouble(format.format((getGrossSalary() - getPAYEForEmp()) - getTotalDiductions() - getAdvance()))));
        setNetSalary(round(Math.round(Double.parseDouble(format.format((getGrossSalary() - getPAYEForEmp()) - getTotalDiductions() - getAdvance()))),5));
        return getNetSalary();
    }

     public static double round(double num, int multipleOf) {
        return Math.floor((num +  (double)multipleOf / 2) / multipleOf) * multipleOf;
    }

    //Getters and setter --------------------------------------------------------
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
     * @return the GvAllowanse
     */
    public double getGvAllowanse() {
        return GvAllowanse;
    }

    /**
     * @param GvAllowanse the GvAllowanse to set
     */
    public void setGvAllowanse(double GvAllowanse) {
        this.GvAllowanse = GvAllowanse;
    }

    /**
     * @return the BudAllowanse
     */
    public double getBudAllowanse() {
        return BudAllowanse;
    }

    /**
     * @param BudAllowanse the BudAllowanse to set
     */
    public void setBudAllowanse(double BudAllowanse) {
        this.BudAllowanse = BudAllowanse;
    }

    /**
     * @return the AbsentDays
     */
    public double getAbsentDays() {
        return AbsentDays;
    }

    /**
     * @param AbsentDays the AbsentDays to set
     */
    public void setAbsentDays(double AbsentDays) {
        this.AbsentDays = AbsentDays;
    }

    /**
     * @return the OThours
     */
    public double getOThours() {
        return OThours;
    }

    /**
     * @param OThours the OThours to set
     */
    public void setOThours(double OThours) {
        this.OThours = OThours;
    }

    /**
     * @return the Advance
     */
    public double getAdvance() {
        return Advance;
    }

    /**
     * @param Advance the Advance to set
     */
    public void setAdvance(double Advance) {
        this.Advance = Advance;
    }

    /**
     * @return the EmpTypeValue
     */
    public int getEmpTypeValue() {
        return EmpTypeValue;
    }

    /**
     * @param EmpTypeValue the EmpTypeValue to set
     */
    public void setEmpTypeValue(int EmpTypeValue) {
        this.EmpTypeValue = EmpTypeValue;
    }

    /**
     * @return the SalaryPF
     */
    public double getSalaryPF() {
        return SalaryPF;
    }

    /**
     * @param SalaryPF the SalaryPF to set
     */
    public void setSalaryPF(double SalaryPF) {
        this.SalaryPF = SalaryPF;
    }

    /**
     * @return the EPFemployee
     */
    public double getEPFemployee() {
        return EPFemployee;
    }

    /**
     * @param EPFemployee the EPFemployee to set
     */
    public void setEPFemployee(double EPFemployee) {
        this.EPFemployee = EPFemployee;
    }

    /**
     * @return the EPFcompany
     */
    public double getEPFcompany() {
        return EPFcompany;
    }

    /**
     * @param EPFcompany the EPFcompany to set
     */
    public void setEPFcompany(double EPFcompany) {
        this.EPFcompany = EPFcompany;
    }

    /**
     * @return the ETF
     */
    public double getETF() {
        return ETF;
    }

    /**
     * @param ETF the ETF to set
     */
    public void setETF(double ETF) {
        this.ETF = ETF;
    }

    /**
     * @param TotalAdditions the TotalAdditions to set
     */
    /**
     * @param BalanceBasic the BalanceBasic to set
     */
    public void setBalanceBasic(double BalanceBasic) {
        this.BalanceBasic = BalanceBasic;
    }

    /**
     * @return the GrossSalary
     */
    public double getGrossSalary() {
        return GrossSalary;
    }

    /**
     * @param GrossSalary the GrossSalary to set
     */
    /**
     * @return the PAYEForEmp
     */
    public double getPAYEForEmp() {
        return PAYEForEmp;
    }

    /**
     * @param PAYEForEmp the PAYEForEmp to set
     */
    private void setPAYEForEmp(double PAYEForEmp) {
        this.PAYEForEmp = PAYEForEmp;
    }

    /**
     * @return the netSalary
     */
    public double getNetSalary() {
        return netSalary;
    }

    /**
     * @param netSalary the netSalary to set
     */
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    /**
     * @return the EmpTypeName
     */
    public String getEmpTypeName() {
        return EmpTypeName;
    }

    /**
     * @param EmpTypeName the EmpTypeName to set
     */
    public void setEmpTypeName(String EmpTypeName) {
        this.EmpTypeName = EmpTypeName;
    }

    /**
     * @return the employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * @param employee the employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * @return the NpRateBasic
     */
    public double getNpRateBasic() {
        return NpRateBasic;
    }

    /**
     * @return the NpValueSal
     */
    public double getNpValueSal() {
        return NpValueSal;
    }

    /**
     * @return the BalanceBasic
     */
    public double getBalanceBasic() {
        return BalanceBasic;
    }

    /**
     * @return the OTRate
     */
    public double getOTRate() {
        return OTRate;
    }

    /**
     * @return the OTValue
     */
    public double getOTValue() {
        return OTValue;
    }

    /**
     * @return the NpRateAllow
     */
    public double getNpRateAllow() {
        return NpRateAllow;
    }

    /**
     * @return the NpValueAllow
     */
    public double getNpValueAllow() {
        return NpValueAllow;
    }

    /**
     * @return the GrossSalaryforpayee
     */
    public double getGrossSalaryforpayee() {
        return GrossSalaryforpayee;
    }

    /**
     * @param GrossSalaryforpayee the GrossSalaryforpayee to set
     */
    public void setGrossSalaryforpayee(double GrossSalaryforpayee) {
        this.GrossSalaryforpayee = GrossSalaryforpayee;
    }

    /**
     * @return the paytype
     */
    public String getPaytype() {
        return paytype;
    }

    /**
     * @param paytype the paytype to set
     */
    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }
}
