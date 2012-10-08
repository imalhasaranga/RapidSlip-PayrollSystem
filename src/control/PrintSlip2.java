/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * @author Gihan
 */
public class PrintSlip2 implements Printable {

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
    public int WIDTH = 500;
    public int HEIGHT = 600;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 10;

    public synchronized void printThisInvoice() {
        PrinterJob pj = PrinterJob.getPrinterJob();

        PageFormat pf = new PageFormat();
        
        Paper paper = new Paper();


        paper.setSize(WIDTH, HEIGHT);
        paper.setImageableArea(0, 0, WIDTH, HEIGHT);
        pf.setPaper(paper);
        pj.setPrintable(this, pf);

       

        //if (pj.printDialog()) {
            try {
                System.out.println("Starting........");
                pj.print();
                System.out.println("Done!......");
            } catch (Exception e) {
                System.out.println("Error: Print Error: " + e);
                e.printStackTrace();
            }
      //  }

    }

    @Override
    public synchronized int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        int VariTopMargin = TOPMARGIN;
        int VariTopDidMargin = 0;
        int AviWidth = WIDTH - LEFTMARGIN - RIGHTMARGIN;

        int coloumwidth = AviWidth / 3;



        Graphics2D gr2d = (Graphics2D) g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());

        Font fonth = new Font("verdana", Font.BOLD, 12);
        Font fontn = new Font("verdana", Font.PLAIN, 10);
        Font fontd = new Font("verdana", Font.PLAIN, 8);

//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);


        CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -40, VariTopMargin, g);
        g.setFont(fontn);
        CentrePrint("8/16, Thalapathpitiya Road, Nugegoda", WIDTH, -40, VariTopMargin += LINEHEIGHT + 3, g);
        CentrePrint("Employee Pay slip - " + getMonth(month) + " " + year, WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
//Deaw a line to seperate------------------------------------------------------------------------------------------------
        VariTopMargin += LINEHEIGHT * 1;
       
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        g.setFont(fontd);
//Sub HEading Details****************************************************************************************************

        g.drawString(("Employee Name : " + empname + "          Employee No: " + empno + "          EPF No: " + epfno).toUpperCase(), LEFTMARGIN, VariTopMargin += LINEHEIGHT + 5);

//Deaw a line to seperate------------------------------------------------------------------------------------------------
        VariTopMargin += LINEHEIGHT * 1;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin); //90

//Earnings & Deductions Title********************************************************************************************
        CentrePrint("EARNINGS", coloumwidth, LEFTMARGIN, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("DEDUCTIONS", coloumwidth, LEFTMARGIN + 40 + coloumwidth, VariTopMargin, g);
        VariTopDidMargin = VariTopMargin;
//Earning Column Details*************************************************************************************************
        //g.drawString("Basic Pay: ", LEFTMARGIN+10, 110);
        ArrangedTextPrint("Basic Pay: ", formatForDouble(basicpay), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Gvt. Allowance: ", formatForDouble(gvtallow), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Bud. Allowance: ", formatForDouble(budallow), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("", "---------------", coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("         Total", formatForDouble(budallow + basicpay + gvtallow), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("No Pay Deduction: ", formatForDouble(nptotalbasic), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("", "---------------", coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Total for PF: ", formatForDouble(totalforepf), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("OT Amount: ", formatForDouble(otamount), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Other: ", formatForDouble(grossallowance + additiontotal + otamount), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("", "---------------", coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Gross Pay: ", formatForDouble(totalforepf + grossallowance + additiontotal), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("BALANCE PAY : ", formatForDouble((totalforepf + grossallowance + additiontotal) - (deducttotal + epfemployee + paye + advancepay)), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopMargin -= LINEHEIGHT, g);



//Final Details****************************************************************************************************
//        g.drawString("GROSS SALARY: ", 150, 240+100);
//        g.drawString("" + gross_salary, 300, 240+100);
//        g.drawString("BALANCE PAY: ", 150, 250+100);
//        g.drawString("" + balancepay, 300, 250+100);

//Deduction Column Details****************************************************************************************************        
        ArrangedTextPrint("EPF Employee: ", formatForDouble(epfemployee), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("P.A.Y.E.: ", formatForDouble(paye), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Advance: ", formatForDouble(advancepay), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("", "---------------", coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("S.Total: ", formatForDouble(epfemployee + paye + advancepay), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Other: ", formatForDouble(deducttotal), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("", "---------------", coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Total: ", formatForDouble(deducttotal + epfemployee + paye + advancepay), coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopDidMargin += LINEHEIGHT, g);


        VariTopMargin += LINEHEIGHT * 2;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);



        ArrangedTextPrint("Total EPF(20%): ", formatForDouble(epftotal), coloumwidth + 20, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT+4, g);
        ArrangedTextPrint("Employer's Con. for EPF(12%): ", formatForDouble(epfcompany), coloumwidth + 20, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("Employer's Con. for EPF(3%): ", formatForDouble(etf), coloumwidth + 20, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);

        VariTopMargin += LINEHEIGHT;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);




        CentrePrint("OTHER EARNINGS", coloumwidth, LEFTMARGIN, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("OTHER DEDUCTIONS", coloumwidth, LEFTMARGIN + 40 + coloumwidth, VariTopMargin, g);
        int forDid = VariTopMargin;
        for (Allsaldetails sal : getArray_allow()) {
            ArrangedTextPrint(sal.detailname, formatForDouble(sal.amount), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        }

        for (Allsaldetails sal2 : getArray_addition()) {
            ArrangedTextPrint(sal2.detailname, formatForDouble(sal2.amount), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
            g.drawString("(" + sal2.description + ")", LEFTMARGIN + coloumwidth / 2 - 5, VariTopMargin);
        }

        for (Allsaldetails sal3 : getArray_deduct()) {
            ArrangedTextPrint(sal3.detailname, formatForDouble(sal3.amount), coloumwidth - 10, LEFTMARGIN + 40 + (coloumwidth), forDid += LINEHEIGHT, g);

        }

        if (VariTopMargin < forDid) {
            VariTopMargin = forDid;
        }
        VariTopMargin += LINEHEIGHT * 2;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);

        g.drawString("Bank:  " + bank + "   Branch: " + branch + "    Acc.No: " + accno, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT + 3);

        VariTopMargin += LINEHEIGHT - 5;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        
        ArrangedTextPrint("....................................", "....................................", AviWidth/2, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT*3, g);
        ArrangedTextPrint("Employee Signature", "Administrator Signature", AviWidth/2, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        VariTopMargin += LINEHEIGHT;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        return PAGE_EXISTS;



    }

    public String formatForDouble(double contents) {
        String newFormNo = "";
        try {
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);
            newFormNo = nf.format(contents);
        } catch (Exception ex) {
        }
        return newFormNo;
    }

    public void ArrangedTextPrint(String text1, String text2, int PrintArea, int StartingPoint, int HightFromTop, Graphics g) {
        String tab = "";
        FontMetrics fm = g.getFontMetrics();
        int wid = PrintArea - fm.stringWidth(text2);
        g.drawString(text1, StartingPoint, HightFromTop);
        g.drawString(text2, StartingPoint + wid, HightFromTop);

    }

    public void CentrePrint(String text, int PrintArea, int StartingPoint, int HightFromTop, Graphics g) {
        String tab = "";
        FontMetrics fm = g.getFontMetrics();
        int tillmidle = ((PrintArea - fm.stringWidth(text)) / 2);
        g.drawString(text, StartingPoint + tillmidle, HightFromTop);
    }

    public String getMonth(int monthindex) {

        String months[] = {
            " ", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October",
            "November", "December"
        };
        return months[monthindex];
    }

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
