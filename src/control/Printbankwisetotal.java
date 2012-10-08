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
import javax.swing.JTable;

/**
 *
 * @author Gihan
 */
public class Printbankwisetotal implements Printable {

    private int month;
    private int year;
    private int totrecords;
    private double totamount;
    private double totpackamount;
    
    private int month1;
    private int year1;
    private int month2;
    private int year2;
    
    private double total1;
    private double total2;
    private double compamount;
    
    public int WIDTH = 500;
    public int HEIGHT = 600;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 10;
    public JTable tblprint;

    public synchronized void printThisInvoice(JTable tbl) {
        this.tblprint = tbl;

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

        int coloumwidth = AviWidth / 5;



        Graphics2D gr2d = (Graphics2D) g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());

        Font fonth = new Font("verdana", Font.BOLD, 12);
        Font fontn = new Font("verdana", Font.PLAIN, 10);
        Font fontd = new Font("verdana", Font.BOLD, 8);
        Font fontd2 = new Font("verdana", Font.PLAIN, 8);

//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);


        CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -40, VariTopMargin, g);
        g.setFont(fontn);
        CentrePrint("8/16, Thalapathpitiya Road, Nugegoda", WIDTH, -40, VariTopMargin += LINEHEIGHT + 3, g);
        CentrePrint("Bank Wise Total - " + getMonth(getMonth1()) + "-" + getYear1(), WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);

        VariTopMargin += LINEHEIGHT * 1;

        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        g.setFont(fontd);

        CentrePrint("Bank", coloumwidth + 24, LEFTMARGIN, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("Records", coloumwidth - 10, LEFTMARGIN + 40 + coloumwidth, VariTopMargin, g);
        CentrePrint("Tot.Amount", coloumwidth - 10, LEFTMARGIN + 40 + (2 * coloumwidth), VariTopMargin, g);
        CentrePrint("Tot.Pack.Amount", coloumwidth - 4, LEFTMARGIN + 40 + (3 * coloumwidth), VariTopMargin, g);
        VariTopDidMargin = VariTopMargin;

        g.setFont(fontd2);

        for (int x = 0; x < tblprint.getRowCount(); x++) {
            ArrangedTextPrint("", tblprint.getValueAt(x, 1).toString(), coloumwidth + 14, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
            ArrangedTextPrint("", tblprint.getValueAt(x, 2).toString(), coloumwidth - 15, LEFTMARGIN + 10 + coloumwidth, VariTopMargin, g);
            ArrangedTextPrint("", formatForDouble(Double.parseDouble(tblprint.getValueAt(x, 3).toString())), coloumwidth - 15, LEFTMARGIN + 10 + (2 * coloumwidth), VariTopMargin, g);
            ArrangedTextPrint("", formatForDouble(Double.parseDouble(tblprint.getValueAt(x, 4).toString())), coloumwidth - 9, LEFTMARGIN + 10 + (3 * coloumwidth), VariTopMargin, g);
        }
        VariTopMargin += 5;
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        g.setFont(fontd);

        g.drawString("Total Records: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + getTotrecords(), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Bank Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(getTotamount()), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Packet Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(getTotpackamount()), LEFTMARGIN + 150, VariTopMargin);
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
     * @return the month1
     */
    public int getMonth1() {
        return month1;
    }

    /**
     * @param month1 the month1 to set
     */
    public void setMonth1(int month1) {
        this.month1 = month1;
    }

    /**
     * @return the year1
     */
    public int getYear1() {
        return year1;
    }

    /**
     * @param year1 the year1 to set
     */
    public void setYear1(int year1) {
        this.year1 = year1;
    }

    /**
     * @return the month2
     */
    public int getMonth2() {
        return month2;
    }

    /**
     * @param month2 the month2 to set
     */
    public void setMonth2(int month2) {
        this.month2 = month2;
    }

    /**
     * @return the year2
     */
    public int getYear2() {
        return year2;
    }

    /**
     * @param year2 the year2 to set
     */
    public void setYear2(int year2) {
        this.year2 = year2;
    }

    /**
     * @return the total1
     */
    public double getTotal1() {
        return total1;
    }

    /**
     * @param total1 the total1 to set
     */
    public void setTotal1(double total1) {
        this.total1 = total1;
    }

    /**
     * @return the total2
     */
    public double getTotal2() {
        return total2;
    }

    /**
     * @param total2 the total2 to set
     */
    public void setTotal2(double total2) {
        this.total2 = total2;
    }

    /**
     * @return the compamount
     */
    public double getCompamount() {
        return compamount;
    }

    /**
     * @param compamount the compamount to set
     */
    public void setCompamount(double compamount) {
        this.compamount = compamount;
    }
    /**
     * @return the companyname
     */
}
