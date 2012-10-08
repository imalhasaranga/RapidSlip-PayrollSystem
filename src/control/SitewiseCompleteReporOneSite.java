/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import model.DB;

/**
 *
 * @author Imal
 */
public class SitewiseCompleteReporOneSite implements Printable {

    private String companyname;
    private String address;
    private String contacts;
    
    
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
    public int WIDTH = 1750;
    public int HEIGHT = 850;
    public int LEFTMARGIN = 25;
    public int RIGHTMARGIN = 0;
    public int TOPMARGIN = 20;
    public int LINEHEIGHT = 10;
    ///
    JTable jt;
    Graphics g;
    int VariTopMargin;
    int columnVariable = 0;
    
    int page = 0;
    //
    Font fonth = new Font("verdana", Font.BOLD, 12);
    Font fontn = new Font("verdana", Font.PLAIN, 10);
    Font fontN = new Font("verdana", Font.BOLD, 11);
    Font fontd = new Font("arial", Font.PLAIN, 8);
    Font fontd1 = new Font("arial", Font.PLAIN, 7);
    
    int month;
    int Year;
    String value;

    public SitewiseCompleteReporOneSite(JTable jt,int month,int Year,String value) {
        this.jt = jt;
        this.month = month;
        this.Year = Year;
        this.value = value;
    }

    public synchronized void printThisInvoice() {
        try {


            PrinterJob pj = PrinterJob.getPrinterJob();

            pj.setPrintable(this);


            //pj.setPrintable(this);

            
           
                ++page;

                // if (pj.printDialog()) {
                try {
                    System.out.println("Starting........");
                    pj.print();
                    System.out.println("Done!......");
                } catch (Exception e) {
                    System.out.println("Error: Print Error: " + e);
                }
                // }
            
        } catch (Exception e) {
        }


    }

    @Override
    public synchronized int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        this.g = g;

        double Btotal =0;
        double NpayDid=0;
        double TotalPF=0;
        double AllowTotoal=0;
        double AllowaNopy=0;
        double Otam=0;
        double additionTotal=0;
        double grossamount=0;
        double epf8=0;
        double payeo=0;
        double salAdvance=0;
        double FestivalAd=0;
        double Didother=0;
        double DidTOtal=0;
        double netSalry=0;


        try {
            if (pageIndex > 0) {
                return NO_SUCH_PAGE;
            }

            VariTopMargin = TOPMARGIN;
            int AviWidth = WIDTH - LEFTMARGIN - RIGHTMARGIN;




            Graphics2D gr2d = (Graphics2D) g;
            gr2d.translate(pf.getImageableX(), pf.getImageableY());


            //Company Name, Address & SlipHeading Details****************************************************************************        

            int column = 50;




            g.setFont(fonth);
            CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -250, VariTopMargin, g);
            g.setFont(fontn);
            CentrePrint("8/16, Thalapathpitiya Road, Nugegoda.", WIDTH, -250, VariTopMargin += LINEHEIGHT + 3, g);
            
                    CentrePrint("PaySheet For " + getMonth(month).toUpperCase() + " " + Year, WIDTH, -250, VariTopMargin += LINEHEIGHT + 3, g);
            CentrePrint("Site : " + value + "  (Page: " + (page)+" )", WIDTH, -250, VariTopMargin += LINEHEIGHT + 3, g);
            VariTopMargin += LINEHEIGHT - 3;
            g.drawLine(LEFTMARGIN, VariTopMargin + 2, AviWidth + LEFTMARGIN, VariTopMargin + 2);
            VariTopMargin += LINEHEIGHT - 3;
            g.setFont(fontd);
            columnVariable = 0;
            String print1 = "||" + createColumnStringcen1("Emp", column - 27) + "|"
                    + "|" + createColumnStringcen1("Full Name", (int) (column * 2)) + "|"
                    + "|" + createColumnStringcen1("Designation", (int) (column * 2) - 40) + "|"
                    + "|" + createColumnStringcen2("Basic Total", column) + "|"
                    + "|" + createColumnStringcen2("Nopay", column - 10) + "|"
                    + "|" + createColumnStringcen2("Total", column - 7) + "|"
                    + "|" + createColumnStringcen2("Allowance", column - 7) + "|"
                    + "|" + createColumnStringcen2("Allowance", column - 7) + "|"
                    + "|" + createColumnStringcen2("OT", column - 10) + "|"
                    + "|" + createColumnStringcen2("OT", column - 7) + "|"
                    + "|" + createColumnStringcen2("Addition", column - 7) + "|"
                    + "|" + createColumnStringcen2("Gross", column - 7) + "|"
                    
                    + "|" + createColumnStringcen2("EPF 8%", column - 7) + "|"
                    + "|" + createColumnStringcen2("PAYE", column - 7) + "|"
                    + "|" + createColumnStringcen2("Salary", column - 7) + "|"
                    + "|" + createColumnStringcen2("Festival", column - 7) + "|"
                    + "|" + createColumnStringcen2("Deduction", column - 7) + "|"
                    + "|" + createColumnStringcen2("Deduction", column - 7) + "|"
                    + "|" + createColumnStringcen2("Net", column - 7) + "||";
            columnVariable = 0;
            VariTopMargin += LINEHEIGHT * 1;
            String print12 = "||" + createColumnStringcen1("No", column - 27) + "|"
                    + "|" + createColumnStringcen1(" ", (int) (column * 2)) + "|"
                    + "|" + createColumnStringcen1("", (int) (column * 2) - 40) + "|"
                    + "|" + createColumnStringcen2(" ", column) + "|"
                    + "|" + createColumnStringcen2("Deduct", column - 10) + "|"
                    + "|" + createColumnStringcen2("For P.F", column - 7) + "|"
                    + "|" + createColumnStringcen2("Total", column - 7) + "|"
                    + "|" + createColumnStringcen2("After NP", column - 7) + "|"
                    + "|" + createColumnStringcen2("Hours", column - 10) + "|"
                    + "|" + createColumnStringcen2("Amount", column - 7) + "|"
                    + "|" + createColumnStringcen2("Total", column - 7) + "|"
                    + "|" + createColumnStringcen2("Amount", column - 7) + "|"
                    + "|" + createColumnStringcen2("", column - 7) + "|"
                    + "|" + createColumnStringcen2("", column - 7) + "|"
                    + "|" + createColumnStringcen2("Advance", column - 7) + "|"
                    + "|" + createColumnStringcen2("Advance", column - 7) + "|"
                    + "|" + createColumnStringcen2("Other", column - 7) + "|"
                    + "|" + createColumnStringcen2("Total", column - 7) + "|"
                    + "|" + createColumnStringcen2("Salary", column - 7) + "||";
            columnVariable = 0;
            VariTopMargin += LINEHEIGHT;

            g.setFont(fontd);
            for (int i = 0; i < jt.getRowCount(); i++) {

                if (value.equals(jt.getValueAt(i, 4).toString())) {

                    //Deaw a line to seperate------------------------------------------------------------------------------------------------
                    VariTopMargin += LINEHEIGHT * 1;

                    String balbasic = "0";
                    String payrolId = jt.getValueAt(i, 20).toString();
                    double Bforpf = 0;
                    double OTHours = 0;

                    ResultSet rs1 = DB.getData("SELECT 	(Besic_salary+Gvt_allow+Bud_allow) AS BT,((Besic_salary+Gvt_allow+Bud_allow)-(NpRateBasic*NpDays)) AS BFORPF,OT_hours FROM payroll WHERE payRollNum ='" + payrolId + "'");
                    if (rs1.next()) {
                        balbasic = rs1.getString("BT");
                        Bforpf = rs1.getDouble("BFORPF");
                        OTHours = rs1.getDouble("OT_hours");
                    }


                    ResultSet rss = DB.getData("SELECT amount FROM  extravalues_payroll WHERE payrol_id = '" + payrolId + "' && esextraval_id = '5'	");
                    double amount = 0;
                    if (rss.next()) {
                        amount = rss.getDouble("amount");
                    }

                    double didutiontotal = Double.parseDouble(jt.getValueAt(i, 16).toString()) + Double.parseDouble(jt.getValueAt(i, 15).toString()) + Double.parseDouble(jt.getValueAt(i, 17).toString()) + Double.parseDouble(jt.getValueAt(i, 7).toString());
                    double netAllow = Double.parseDouble(jt.getValueAt(i, 11).toString()) - Double.parseDouble(jt.getValueAt(i, 12).toString());

                    double otherdid = Double.parseDouble(jt.getValueAt(i, 16).toString()) - amount;
                    double GrossAmount = Bforpf + netAllow + Double.parseDouble(jt.getValueAt(i, 14).toString()) + Double.parseDouble(jt.getValueAt(i, 13).toString());
                    columnVariable = 0;

                    Btotal += Double.parseDouble(balbasic);
                    NpayDid += Double.parseDouble(jt.getValueAt(i, 6).toString());
                    TotalPF += Bforpf;
                    AllowTotoal += Double.parseDouble(jt.getValueAt(i, 11).toString());
                    AllowaNopy += netAllow;
                    Otam += Double.parseDouble(jt.getValueAt(i, 14).toString());
                    additionTotal += Double.parseDouble(jt.getValueAt(i, 13).toString());
                    grossamount += GrossAmount;
                    epf8 += Double.parseDouble(jt.getValueAt(i, 7).toString());
                    payeo += Double.parseDouble(jt.getValueAt(i, 15).toString());
                    salAdvance += Double.parseDouble(jt.getValueAt(i, 17).toString());
                    FestivalAd += amount;
                    Didother += otherdid;
                    DidTOtal += didutiontotal;
                    netSalry += Double.parseDouble(jt.getValueAt(i, 19).toString());

                    String print =
                            "  " + getTableColumLeft(i, 0, column - 27) + "  "
                            + " " + getTableColumLeftTrankate1(i, 2, (int) (column * 2)) + " "
                            + " " + getTableColumLeftTrankate(i, 3, (int) (column * 2) - 40) + " "
                            + " " + createColumnStringFormated(balbasic, column) + " "
                            + " " + getTableColumVal(i, 6, column - 10) + " "
                            + " " + createColumnStringFormated(Bforpf + "", column - 7) + " "
                            + " " + getTableColumVal(i, 11, column - 7) + " "
                            + " " + createColumnStringFormated(netAllow + "", column - 7) + " "
                            + " " + createColumnStringFormated(OTHours + "", column - 10) + " "
                            + " " + getTableColumVal(i, 14, column - 7) + " "
                            + " " + getTableColumVal(i, 13, column - 7) + " "
                            + " " + createColumnStringFormated(GrossAmount + "", column - 7) + " "
                            + " " + getTableColumVal(i, 7, column - 7) + " "
                            + " " + getTableColumVal(i, 15, column - 7) + " "
                            + " " + getTableColumVal(i, 17, column - 7) + " "
                            + " " + createColumnStringFormated(amount + "", column - 7) + " "
                            + " " + createColumnStringFormated(otherdid + "", column - 7) + " "
                            + " " + createColumnStringFormated(didutiontotal + "", column - 7) + " "
                            + " " + getTableColumVal(i, 19, column - 7) + "  ";

                    columnVariable = 0;

                }
            }



            VariTopMargin += 15;
            g.drawLine(LEFTMARGIN, VariTopMargin + 2, AviWidth + LEFTMARGIN, VariTopMargin + 2);
            VariTopMargin += LINEHEIGHT * 1;
            columnVariable = 0;
            g.setFont(fontd1);
            String print11 =
                    "  " + createColumnString("", column - 27) + "  "
                    + " " + createColumnString("", (int) (column * 2)) + " "
                    + " " + createColumnString("", (int) (column * 2) - 40) + " "
                    + " " + createColumnStringFormated(Btotal + "", column) + " "
                    + " " + createColumnStringFormated(NpayDid + "", column - 10) + " "
                    + " " + createColumnStringFormated(TotalPF + "", column - 7) + " "
                    + " " + createColumnStringFormated(AllowTotoal + "", column - 7) + " "
                    + " " + createColumnStringFormated(AllowaNopy + "", column - 7) + " "
                    + " " + createColumnString("", column - 10) + " "
                    + " " + createColumnStringFormated(Otam + "", column - 7) + " "
                    + " " + createColumnStringFormated(additionTotal + "", column - 7) + " "
                    + " " + createColumnStringFormated(grossamount + "", column - 7) + " "
                    + " " + createColumnStringFormated(epf8 + "", column - 7) + " "
                    + " " + createColumnStringFormated(payeo + "", column - 7) + " "
                    + " " + createColumnStringFormated(salAdvance + "", column - 7) + " "
                    + " " + createColumnStringFormated(FestivalAd + "", column - 7) + " "
                    + " " + createColumnStringFormated(Didother + "", column - 7) + " "
                    + " " + createColumnStringFormated(DidTOtal + "", column - 7) + " "
                    + " " + createColumnStringFormated(netSalry + "", column - 7) + "  ";
            g.setFont(fontd);
            columnVariable = 0;
            VariTopMargin += LINEHEIGHT * 1;
            g.drawLine(LEFTMARGIN, VariTopMargin + 5, AviWidth + LEFTMARGIN, VariTopMargin + 5);



            VariTopMargin += LINEHEIGHT + 25;

        } catch (Exception ex) {
            System.out.println(ex);
        }
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

    // always txt.lenth() > len 
    public String createColumnString(String txt, int len) {
        FontMetrics fm = g.getFontMetrics();
        int len1 = len - (fm.stringWidth(txt));
        g.drawString(txt, LEFTMARGIN + columnVariable + len1, VariTopMargin + LINEHEIGHT);
        columnVariable += len;

        return txt;
    }

    public synchronized String createColumnStringFormated(String txt, int len) {
        txt = formatForDouble(Double.parseDouble(txt));
        FontMetrics fm = g.getFontMetrics();
        int len1 = len - (fm.stringWidth(txt));
        g.drawString(txt, LEFTMARGIN + columnVariable + len1, VariTopMargin + LINEHEIGHT);
        columnVariable += len;

        return txt;
    }

    public String getTableColumLeft(int row, int col, int len) {
        String txt = jt.getValueAt(row, col).toString().trim();
        //int len1 = len - (fm.stringWidth(txt) / 2);
        g.drawString(txt, LEFTMARGIN + columnVariable, VariTopMargin + LINEHEIGHT);
        columnVariable += len;
        return txt;
    }

    public String getTableColumLeftTrankate(int row, int col, int len) {
        String txt = jt.getValueAt(row, col).toString().trim();
        //int len1 = len - (fm.stringWidth(txt) / 2);
        if (txt.length() > 18) {
            txt = txt.substring(0, 18);
        }
        g.drawString(txt, LEFTMARGIN + columnVariable, VariTopMargin + LINEHEIGHT);
        columnVariable += len;
        return txt;
    }

    public String getTableColumLeftTrankate1(int row, int col, int len) {
        String txt = jt.getValueAt(row, col).toString().trim();
        //int len1 = len - (fm.stringWidth(txt) / 2);
        if (txt.length() > 20) {
            txt = txt.substring(0, 20);
        }
        g.drawString(txt, LEFTMARGIN + columnVariable, VariTopMargin + LINEHEIGHT);
        columnVariable += len;
        return txt;
    }

    public String getTableColumVal(int row, int col, int len) {
        String s = formatForDouble(Double.parseDouble(jt.getValueAt(row, col).toString().trim()));
        return createColumnString(s, len);
    }

    public String getTableColumVal1(int row, int col, int len) {
        String s = jt.getValueAt(row, col).toString().trim();
        int z = 4 - s.length();
        for (int i = 0; i < z; i++) {
            s = "0" + s;
        }
        return createColumnString(s, len);
    }

    public String createColumnStringcen(String txt, int len) {
        FontMetrics fm = g.getFontMetrics();
        int len1 = len - (fm.stringWidth(txt) / 2);
        g.drawString(txt, LEFTMARGIN + columnVariable + len1, VariTopMargin + LINEHEIGHT);
        columnVariable += len;

        return txt;
    }

    public String createColumnStringcen1(String txt, int len) {


        g.drawString(txt, LEFTMARGIN + columnVariable, VariTopMargin + LINEHEIGHT);
        columnVariable += len;

        return txt;
    }

    public String createColumnStringcen2(String txt, int len) {

        FontMetrics fm = g.getFontMetrics();
        int len1 = len - (fm.stringWidth(txt));
        g.drawString(txt, LEFTMARGIN + columnVariable + len1, VariTopMargin + LINEHEIGHT);
        columnVariable += len;

        return txt;
    }

    public void newLine() {
        columnVariable = 0;
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
