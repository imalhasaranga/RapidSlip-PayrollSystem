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
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import model.PayRoll;

/**
 *
 * @author Gihan
 */
public class Printsitereport implements Printable {
    PayRoll Proll;
    
    private int month;
    private int year;
    private int totrecords = 0;
    private double totcashamount = 0.00;
    private double totbankamount = 0.00;
    private double totamount = 0.00;
    private double totpackamount;
    private double packetpay;
    private String bankname;
    private String sitename;
    private String paytype;
    private Vector vecsalary;
    public int WIDTH = 500;
    public int HEIGHT = 600;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 10;
    public JTable tblprint;

    public synchronized void printThisInvoice(JTable tbl) {
        this.tblprint = tbl;
        //this.totrecords = totrec;

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

        int coloumwidth = AviWidth / 6;
        
        Proll = new PayRoll();

        Graphics2D gr2d = (Graphics2D) g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());

        Font fonth = new Font("verdana", Font.BOLD, 12);
        Font fontn = new Font("verdana", Font.PLAIN, 10);
        Font fontd = new Font("verdana", Font.BOLD, 7);
        Font fontd2 = new Font("verdana", Font.PLAIN, 7);

//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);


        CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -40, VariTopMargin, g);
        g.setFont(fontn);
        CentrePrint("8/16, Thalapathpitiya Road, Nugegoda", WIDTH, -40, VariTopMargin += LINEHEIGHT + 3, g);
        CentrePrint("Site Wise Balance Report - " + getMonth(getMonth()) + " " + getYear(), WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
        CentrePrint("Site: " + getSitename(), WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);

        VariTopMargin += LINEHEIGHT * 1;

        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        g.setFont(fontd);

        g.drawString("Emp.No", LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT);
        g.drawString("Name", LEFTMARGIN + 50, VariTopMargin);
        g.drawString("Designation", LEFTMARGIN + 2 * coloumwidth + 50, VariTopMargin);
        g.drawString("Pack.Pay", LEFTMARGIN + 3 * coloumwidth + 50, VariTopMargin);
        g.drawString("P.Type", LEFTMARGIN + 4 * coloumwidth + 30, VariTopMargin);

        VariTopDidMargin = VariTopMargin;

        g.setFont(fontd2);

        for (int x = 0; x < tblprint.getRowCount(); x++) {
            
            g.drawString(tblprint.getValueAt(x, 0).toString(), LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT);
            g.drawString(tblprint.getValueAt(x, 2).toString(), LEFTMARGIN + 50, VariTopMargin);
            ArrangedTextPrint("", tblprint.getValueAt(x, 3).toString(), coloumwidth, LEFTMARGIN + (2 * coloumwidth) + 30, VariTopMargin, g);
            
            
            try {
                ResultSet rs = Proll.getPayrollByEmp(Integer.parseInt(tblprint.getValueAt(x, 0).toString()), getYear(), getMonth());
                
                while (rs.next()) {
//                    System.out.println("VAL: " + rs.getString("finalsalary"));
//                    System.out.println("VAL: " + rs.getString("paytype"));
                    packetpay = rs.getDouble("PackSal");
                    paytype = rs.getString("paytype");
                    if(paytype.equals("CASH")){
                        totcashamount += packetpay;
                    }else if(paytype.equals("BANK")){
                        totbankamount += packetpay;
                    }
                    
                }
                totamount = totcashamount + totbankamount;
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
                e.printStackTrace();
            }
            
            ArrangedTextPrint("", formatForDouble(packetpay), coloumwidth, LEFTMARGIN + (3 * coloumwidth), VariTopMargin, g);
            g.drawString(paytype, LEFTMARGIN + 4 * coloumwidth + 30, VariTopMargin);

//            ArrangedTextPrint("", "...", coloumwidth , LEFTMARGIN + 10 + (4*coloumwidth), VariTopMargin, g);
        }

        g.drawLine(LEFTMARGIN, VariTopMargin + LINEHEIGHT, AviWidth + LEFTMARGIN, VariTopMargin + LINEHEIGHT);
        VariTopMargin += LINEHEIGHT;
        g.drawString("Total Employees: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + getTotrecords(), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Cash Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(totcashamount/2), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Bank Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(totbankamount/2), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Site Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(totamount/2), LEFTMARGIN + 150, VariTopMargin);
        VariTopMargin += LINEHEIGHT;
        VariTopMargin += 5;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);

//Sub HEading Details****************************************************************************************************

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
     * @return the totrecords
     */
    public int getTotrecords() {
        return totrecords;
    }

    /**
     * @param totrecords the totrecords to set
     */
    public void setTotrecords(int totrecords) {
        this.totrecords = totrecords;
    }

    /**
     * @return the totamount
     */
    public double getTotamount() {
        return totamount;
    }

    /**
     * @param totamount the totamount to set
     */
    public void setTotamount(double totamount) {
        this.totamount = totamount;
    }

    /**
     * @return the totpackamount
     */
    public double getTotpackamount() {
        return totpackamount;
    }

    /**
     * @param totpackamount the totpackamount to set
     */
    public void setTotpackamount(double totpackamount) {
        this.totpackamount = totpackamount;
    }

    /**
     * @return the bankname
     */
    public String getBankname() {
        return bankname;
    }

    /**
     * @param bankname the bankname to set
     */
    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    /**
     * @return the sitename
     */
    public String getSitename() {
        return sitename;
    }

    /**
     * @param sitename the sitename to set
     */
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    /**
     * @return the vecsalary
     */
    public Vector getVecsalary() {
        return vecsalary;
    }

    /**
     * @param vecsalary the vecsalary to set
     */
    public void setVecsalary(Vector vecsalary) {
        this.vecsalary = vecsalary;
    }

    /**
     * @return the totcashamount
     */
    public double getTotcashamount() {
        return totcashamount;
    }

    /**
     * @param totcashamount the totcashamount to set
     */
    public void setTotcashamount(double totcashamount) {
        this.totcashamount = totcashamount;
    }

    /**
     * @return the totbankamount
     */
    public double getTotbankamount() {
        return totbankamount;
    }

    /**
     * @param totbankamount the totbankamount to set
     */
    public void setTotbankamount(double totbankamount) {
        this.totbankamount = totbankamount;
    }

    /**
     * @return the packetpay
     */
    public double getPacketpay() {
        return packetpay;
    }

    /**
     * @param packetpay the packetpay to set
     */
    public void setPacketpay(double packetpay) {
        this.packetpay = packetpay;
    }
    /**
     * @return the companyname
     */
}
