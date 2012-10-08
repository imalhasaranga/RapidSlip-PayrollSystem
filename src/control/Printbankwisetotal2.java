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
public class Printbankwisetotal2 implements Printable {
    private int month;
    private int year;
    private int totrecords;
    private double totamount;
    private double totpackamount;
    private String bankname;
    
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

        int coloumwidth = AviWidth / 6;



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
        CentrePrint("Bank Wise Sub Report - " + getMonth(getMonth()) + " " + getYear(), WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
        CentrePrint("Bank: " + getBankname(), WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
        
        VariTopMargin += LINEHEIGHT * 1;
       
        g.drawLine(LEFTMARGIN, VariTopMargin, AviWidth + LEFTMARGIN, VariTopMargin);
        g.setFont(fontd);
        
        CentrePrint("Emp.No", coloumwidth - 40, LEFTMARGIN, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("Name", coloumwidth + 35, LEFTMARGIN + 40 + coloumwidth, VariTopMargin, g);
        CentrePrint("Acc.No", coloumwidth, LEFTMARGIN + 35 + (2*coloumwidth), VariTopMargin, g);
        CentrePrint("F.Total", coloumwidth, LEFTMARGIN + 35 + (3*coloumwidth), VariTopMargin, g);
        CentrePrint("P.Total", coloumwidth, LEFTMARGIN + 35 + (4*coloumwidth), VariTopMargin, g);
        VariTopDidMargin = VariTopMargin;
        
        g.setFont(fontd2);
        
        for (int x = 0; x < tblprint.getRowCount(); x++) {
            ArrangedTextPrint("", tblprint.getValueAt(x, 0).toString(), coloumwidth -65, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
            ArrangedTextPrint("", tblprint.getValueAt(x, 1).toString(), coloumwidth + 10, LEFTMARGIN + 10 + coloumwidth, VariTopMargin, g);
            ArrangedTextPrint("", tblprint.getValueAt(x, 2).toString(), coloumwidth , LEFTMARGIN + 10 + (2*coloumwidth), VariTopMargin, g);
            ArrangedTextPrint("", formatForDouble(Double.parseDouble(tblprint.getValueAt(x, 3).toString())), coloumwidth, LEFTMARGIN + 10 + (3*coloumwidth), VariTopMargin, g);
            ArrangedTextPrint("", formatForDouble(Double.parseDouble(tblprint.getValueAt(x, 4).toString())), coloumwidth , LEFTMARGIN + 10 + (4*coloumwidth), VariTopMargin, g);
        }
       
        g.drawLine(LEFTMARGIN, VariTopMargin + LINEHEIGHT, AviWidth + LEFTMARGIN, VariTopMargin + LINEHEIGHT);
         VariTopMargin += LINEHEIGHT;
        g.drawString("Total Records: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString(""+getTotrecords(), LEFTMARGIN + 150, VariTopMargin);
        g.drawString("Total Bank Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(getTotamount()), LEFTMARGIN + 150, VariTopMargin );
        g.drawString("Total Packet Amount: ", LEFTMARGIN, VariTopMargin += LINEHEIGHT);
        g.drawString("" + formatForDouble(getTotpackamount()), LEFTMARGIN + 150, VariTopMargin);
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
     * @return the companyname
     */
   
}
