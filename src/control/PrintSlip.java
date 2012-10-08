/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
import java.util.ArrayList;

/**
 *
 * @author Gihan
 */
public class PrintSlip implements Printable{
    private String companyname;
    private String address;
    private String contacts;
    private int year;
    private int month;
    private String empname;
    private String empno;
    private String epfno;
    private double basicpay;
    private double gvtallow;
    private double budallow;
    private double npratebasic;
    private double npdays;
    private double nptotalbasic;
    private double totalforepf;
    private double epfemployee;
    private double epfcompany;
    private double etf;
    private double balancebasic;
    private double otrate;
    private double othours;
    private double otamount;
    private double totallowance;
    private double nprateallow;
    private double nptotalallowance;
    private double grossallowance;
    private double additiontotal;
    private double deducttotal;
    private double advancepay;
    private double paye;
    private double netsalary;
    private double gross_salary;
    private double balancepay;
    private double epftotal;
    
    private ArrayList<Allsaldetails> array_allow;
    private ArrayList<Allsaldetails> array_addition;
    private ArrayList<Allsaldetails> array_deduct;
    
    private String bank;
    private String branch;
    private String accno;

    /**
     * @return the companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the empname
     */
    public String getEmpname() {
        return empname;
    }

    /**
     * @param empname the empname to set
     */
    public void setEmpname(String empname) {
        this.empname = empname;
    }

    /**
     * @return the empno
     */
    public String getEmpno() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    /**
     * @return the epfno
     */
    public String getEpfno() {
        return epfno;
    }

    /**
     * @param epfno the epfno to set
     */
    public void setEpfno(String epfno) {
        this.epfno = epfno;
    }

    /**
     * @return the basicpay
     */
    public double getBasicpay() {
        return basicpay;
    }

    /**
     * @param basicpay the basicpay to set
     */
    public void setBasicpay(double basicpay) {
        this.basicpay = basicpay;
    }

    /**
     * @return the gvtallow
     */
    public double getGvtallow() {
        return gvtallow;
    }

    /**
     * @param gvtallow the gvtallow to set
     */
    public void setGvtallow(double gvtallow) {
        this.gvtallow = gvtallow;
    }

    /**
     * @return the budallow
     */
    public double getBudallow() {
        return budallow;
    }

    /**
     * @param budallow the budallow to set
     */
    public void setBudallow(double budallow) {
        this.budallow = budallow;
    }

    /**
     * @return the npratebasic
     */
    public double getNpratebasic() {
        return npratebasic;
    }

    /**
     * @param npratebasic the npratebasic to set
     */
    public void setNpratebasic(double npratebasic) {
        this.npratebasic = npratebasic;
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
     * @return the nptotalbasic
     */
    public double getNptotalbasic() {
        return nptotalbasic;
    }

    /**
     * @param nptotalbasic the nptotalbasic to set
     */
    public void setNptotalbasic(double nptotalbasic) {
        this.nptotalbasic = nptotalbasic;
    }

    /**
     * @return the totalforepf
     */
    public double getTotalforepf() {
        return totalforepf;
    }

    /**
     * @param totalforepf the totalforepf to set
     */
    public void setTotalforepf(double totalforepf) {
        this.totalforepf = totalforepf;
    }

    /**
     * @return the epfemployee
     */
    public double getEpfemployee() {
        return epfemployee;
    }

    /**
     * @param epfemployee the epfemployee to set
     */
    public void setEpfemployee(double epfemployee) {
        this.epfemployee = epfemployee;
    }

    /**
     * @return the epfcompany
     */
    public double getEpfcompany() {
        return epfcompany;
    }

    /**
     * @param epfcompany the epfcompany to set
     */
    public void setEpfcompany(double epfcompany) {
        this.epfcompany = epfcompany;
    }

    /**
     * @return the etf
     */
    public double getEtf() {
        return etf;
    }

    /**
     * @param etf the etf to set
     */
    public void setEtf(double etf) {
        this.etf = etf;
    }

    /**
     * @return the balancebasic
     */
    public double getBalancebasic() {
        return balancebasic;
    }

    /**
     * @param balancebasic the balancebasic to set
     */
    public void setBalancebasic(double balancebasic) {
        this.balancebasic = balancebasic;
    }

    /**
     * @return the otrate
     */
    public double getOtrate() {
        return otrate;
    }

    /**
     * @param otrate the otrate to set
     */
    public void setOtrate(double otrate) {
        this.otrate = otrate;
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

    /**
     * @return the otamount
     */
    public double getOtamount() {
        return otamount;
    }

    /**
     * @param otamount the otamount to set
     */
    public void setOtamount(double otamount) {
        this.otamount = otamount;
    }

    /**
     * @return the totallowance
     */
    public double getTotallowance() {
        return totallowance;
    }

    /**
     * @param totallowance the totallowance to set
     */
    public void setTotallowance(double totallowance) {
        this.totallowance = totallowance;
    }

    /**
     * @return the nprateallow
     */
    public double getNprateallow() {
        return nprateallow;
    }

    /**
     * @param nprateallow the nprateallow to set
     */
    public void setNprateallow(double nprateallow) {
        this.nprateallow = nprateallow;
    }

    /**
     * @return the nptotalallowance
     */
    public double getNptotalallowance() {
        return nptotalallowance;
    }

    /**
     * @param nptotalallowance the nptotalallowance to set
     */
    public void setNptotalallowance(double nptotalallowance) {
        this.nptotalallowance = nptotalallowance;
    }

    /**
     * @return the grossallowance
     */
    public double getGrossallowance() {
        return grossallowance;
    }

    /**
     * @param grossallowance the grossallowance to set
     */
    public void setGrossallowance(double grossallowance) {
        this.grossallowance = grossallowance;
    }

    /**
     * @return the additiontotal
     */
    public double getAdditiontotal() {
        return additiontotal;
    }

    /**
     * @param additiontotal the additiontotal to set
     */
    public void setAdditiontotal(double additiontotal) {
        this.additiontotal = additiontotal;
    }

    /**
     * @return the deducttotal
     */
    public double getDeducttotal() {
        return deducttotal;
    }

    /**
     * @param deducttotal the deducttotal to set
     */
    public void setDeducttotal(double deducttotal) {
        this.deducttotal = deducttotal;
    }

    /**
     * @return the advancepay
     */
    public double getAdvancepay() {
        return advancepay;
    }

    /**
     * @param advancepay the advancepay to set
     */
    public void setAdvancepay(double advancepay) {
        this.advancepay = advancepay;
    }

    /**
     * @return the paye
     */
    public double getPaye() {
        return paye;
    }

    /**
     * @param paye the paye to set
     */
    public void setPaye(double paye) {
        this.paye = paye;
    }

    /**
     * @return the netsalary
     */
    public double getNetsalary() {
        return netsalary;
    }

    /**
     * @param netsalary the netsalary to set
     */
    public void setNetsalary(double netsalary) {
        this.netsalary = netsalary;
    }

    /**
     * @return the gross_salary
     */
    public double getGross_salary() {
        return gross_salary;
    }

    /**
     * @param gross_salary the gross_salary to set
     */
    public void setGross_salary(double gross_salary) {
        this.gross_salary = gross_salary;
    }

    /**
     * @return the balancepay
     */
    public double getBalancepay() {
        return balancepay;
    }

    /**
     * @param balancepay the balancepay to set
     */
    public void setBalancepay(double balancepay) {
        this.balancepay = balancepay;
    }

    /**
     * @return the epftotal
     */
    public double getEpftotal() {
        return epftotal;
    }

    /**
     * @param epftotal the epftotal to set
     */
    public void setEpftotal(double epftotal) {
        this.epftotal = epftotal;
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
    
    public void printThisInvoice(){
        PrinterJob pj = PrinterJob.getPrinterJob();
        
        PageFormat pf = new PageFormat();
        Paper paper = new Paper();
        
        double width = 597;
        double height = 843; 
        paper.setSize(width, height); 
        paper.setImageableArea(width, height, width, height);
        pf.setPaper(paper); 
        pj.setPrintable(this, pf); 
        
     // pj.printDialog()  
        
            if(pj.printDialog() ){
                try {
                    System.out.println("Starting........");
                    pj.print();
                    System.out.println("Done!......");
                } catch (Exception e) {
                    System.out.println("Error: Print Error: " + e);
                    e.printStackTrace();
                }
            }
            
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if(pageIndex > 0){
            return NO_SUCH_PAGE;
        }
        
        
        Graphics2D gr2d = (Graphics2D)g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());
        
        Font fonth = new Font("arial", Font.BOLD, 12);
        Font fontn = new Font("arial", Font.PLAIN, 10);
        Font fontd = new Font("arial", Font.PLAIN, 8);
        
//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);
        g.drawString("K.D.A. Weerasinghe & Co. (Pvt) Ltd", 150, 30);
        g.setFont(fontn);
        g.drawString("8/16, Thalapathpitiya Road, Nugegoda", 150, 40);
        g.drawString("Employee Pay slip", 190, 50);
//Deaw a line to seperate------------------------------------------------------------------------------------------------
        g.drawLine(20, 60, 550, 60);
        g.setFont(fontd);
//Sub HEading Details****************************************************************************************************
        g.drawString("Year-Month: " + year + "-" + month, 20, 70);
        g.drawString("Employee Name: " + empname + "    Employee No: " + empno + "    EPF No: " + epfno, 20, 80);

//Deaw a line to seperate------------------------------------------------------------------------------------------------
        g.drawLine(20, 90, 550, 90);

//Earnings & Deductions Title********************************************************************************************
        g.drawString("Earnings", 150, 100);
        g.drawString("Deductions", 300, 100);
        
//Earning Column Details*************************************************************************************************
        g.drawString("Basic Pay: ", 30, 110);
        g.drawString(""+basicpay, 200, 110);
        g.drawString("Gvt. Allowance: ", 30, 120);
        g.drawString(""+gvtallow, 200, 120);
        g.drawString("Bud. Allowance: ", 30, 130);
        g.drawString(""+budallow, 200, 130);
        g.drawString("No Pay Deduction: ", 30, 140);
        g.drawString(""+nptotalbasic + "(" + npratebasic + "*" + npdays + ")", 200, 140);
        g.drawString("Total for EPF: ", 30, 150);
        g.drawString(""+totalforepf, 200, 150);
        g.drawString("Balance Basic: ", 30, 160);
        g.drawString(""+balancebasic, 200, 160);
        g.drawString("Over Time Amount: ", 30, 170);
        g.drawString(""+otamount + "(" + otrate + "*" + othours + ")", 200, 170);
        g.drawString("Total Allowance: ", 30, 180);
        g.drawString(""+totallowance, 200, 180);
        g.drawString("No Pay Deduction: ", 30, 190);
        g.drawString(""+nptotalallowance + "(" + nprateallow + "*" + npdays + ")", 200, 190);
        g.drawString("Gross Allowance: ", 30, 200);
        g.drawString(""+grossallowance, 200, 200);
        g.drawString("Additional Total: ", 30, 210);
        g.drawString(""+additiontotal, 200, 210);
        g.drawString("Net Salary: ", 30, 220);
        g.drawString(""+netsalary, 200, 220);

//Final Details****************************************************************************************************
        g.drawString("GROSS SALARY: ", 150, 240);
        g.drawString(""+gross_salary, 300, 240);
        g.drawString("BALANCE PAY: ", 150, 250);
        g.drawString(""+balancepay, 300, 250);

//Deduction Column Details****************************************************************************************************        
        g.drawString("EPF Employee: ", 300, 110);
        g.drawString(""+epfemployee, 450, 110);
        g.drawString("Deduction Total: ", 300, 120);
        g.drawString(""+deducttotal, 450, 120);
        g.drawString("P.A.Y.E.: ", 300, 130);
        g.drawString(""+paye, 450, 130);
        g.drawString("Advance: ", 300, 140);
        g.drawString(""+advancepay, 450, 140);
        
        g.drawLine(20, 260, 550, 260);
        
        g.drawString("Total EPF(20%): ", 30, 270);
        g.drawString(""+epftotal, 200, 270);
        g.drawString("Employer's Con. for EPF(12%): ", 30, 280);
        g.drawString(""+epfcompany, 200, 280);
        g.drawString("Employer's Con. for EPF(3%): ", 30, 290);
        g.drawString(""+etf, 200, 290);
        
        g.drawLine(20, 300, 550, 300);
        
        int height = 300;
        int height2 = 300;
        
        g.drawString("Allowances", 150, height += 10);
        for(Allsaldetails sal : getArray_allow()){
            g.drawString(sal.detailname, 30, height += 10);
            g.drawString(""+sal.amount, 200, height);
        }
        height += 5;
        g.drawString("Addtion", 150, height += 10);
        for(Allsaldetails sal2 : getArray_addition()){
            g.drawString(sal2.detailname, 30, height += 10);
            g.drawString(sal2.description, 150, height);
            g.drawString(""+sal2.amount, 200, height);
        }
        
        for(Allsaldetails sal3 : getArray_deduct()){
            g.drawString(sal3.detailname, 300, height2 += 10);
            g.drawString(""+sal3.amount, 500, height2);
        }
        height += 10;
        g.drawLine(20, height, 550, height);
        
        g.drawString("Bank: " + bank + "  Branch: " + branch + "  Acc.No: " + accno, 30, height += 10);
        height += 10;
        g.drawLine(20, height, 550, height);

        return PAGE_EXISTS;
        
        
        
    }

    /**
     * @return the array_allow
     */
    public ArrayList<Allsaldetails> getArray_allow() {
        return array_allow;
    }

    /**
     * @param array_allow the array_allow to set
     */
    public void setArray_allow(ArrayList<Allsaldetails> array_allow) {
        this.array_allow = array_allow;
    }

    /**
     * @return the array_addition
     */
    public ArrayList<Allsaldetails> getArray_addition() {
        return array_addition;
    }

    /**
     * @param array_addition the array_addition to set
     */
    public void setArray_addition(ArrayList<Allsaldetails> array_addition) {
        this.array_addition = array_addition;
    }

    /**
     * @return the array_deduct
     */
    public ArrayList<Allsaldetails> getArray_deduct() {
        return array_deduct;
    }

    /**
     * @param array_deduct the array_deduct to set
     */
    public void setArray_deduct(ArrayList<Allsaldetails> array_deduct) {
        this.array_deduct = array_deduct;
    }
    
    
}
