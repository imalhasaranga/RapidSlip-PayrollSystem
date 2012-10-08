package control;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Gihan
 */
public class PrintSiteTotal implements Printable {

    private int month;
    private int year;
    private Vector sitename;
    private Vector cashamount;
    private Vector bankamount;
    private Vector siteamount;
    
    private double totalamount;
    private double cashtotalamount;
    private double banktotalamount;
    
    public int WIDTH = 400;
    public int HEIGHT = 567;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 15;
    Font fonth = new Font("verdana", Font.BOLD, 12);
    Font fontn = new Font("verdana", Font.PLAIN, 10);
    Font fontd = new Font("verdana", Font.PLAIN, 8);
    Font fontN = new Font("verdana", Font.BOLD, 8);

    public synchronized void printThisInvoice() {
        PrinterJob pj = PrinterJob.getPrinterJob();

//        PageFormat pf = new PageFormat();
//
//        Paper paper = new Paper();
//
//
//        paper.setSize(WIDTH, HEIGHT);
//        paper.setImageableArea(0, 0, 420, HEIGHT);
//        pf.setPaper(paper);
//        pj.setPrintable(this, pf);
        pj.setPrintable(this);


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

        int coloumwidth = (AviWidth / 2) ;



        Graphics2D gr2d = (Graphics2D) g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());


//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);
        CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -40, VariTopMargin, g);
        g.setFont(fontn);
        CentrePrint("Site Wise Salary Amount Report", WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("" + getMonth(getMonth()) + " " + getYear(), WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
//Deaw a line to seperate------------------------------------------------------------------------------------------------
        VariTopMargin += LINEHEIGHT * 1;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
        g.setFont(fontd);

        g.setFont(fontd);


//        CentrePrint("SITE", coloumwidth, LEFTMARGIN - 100, VariTopMargin += LINEHEIGHT + 5, g);
//        CentrePrint("SALARY TOTAL", coloumwidth, LEFTMARGIN + 100, VariTopMargin, g);

        g.drawString("Site", 30, VariTopMargin += LINEHEIGHT);
        g.drawString("Cash Total", 120, VariTopMargin);
        g.drawString("Bank Total", 200, VariTopMargin);
        g.drawString("Total", 280, VariTopMargin);

        VariTopDidMargin = VariTopMargin;

        for (int x = 0; x < sitename.size(); x++) {
            String a = colStr(formatForDouble(Double.parseDouble(cashamount.get(x).toString()))) + colStr(formatForDouble(Double.parseDouble(bankamount.get(x).toString()))) + colStr(formatForDouble(Double.parseDouble(siteamount.get(x).toString())));
            
            //ArrangedTextPrint(sitename.get(x)+"", a, coloumwidth+200 , LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
            
            ArrangedTextPrint(""+sitename.get(x), "", coloumwidth-10 , LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
            ArrangedTextPrint(""+formatForDouble(Double.parseDouble(cashamount.get(x).toString())), "", coloumwidth-10 , LEFTMARGIN + 120, VariTopMargin, g);
            ArrangedTextPrint(""+formatForDouble(Double.parseDouble(bankamount.get(x).toString())), "", coloumwidth-10 , LEFTMARGIN + 200, VariTopMargin, g);
            ArrangedTextPrint(""+formatForDouble(Double.parseDouble(siteamount.get(x).toString())), "", coloumwidth-10 , LEFTMARGIN + 280, VariTopMargin, g);
            
//            g.drawString("" + sitename.get(x), 30, VariTopMargin += LINEHEIGHT);
//            g.drawString("" + cashamount.get(x), 150, VariTopMargin += LINEHEIGHT);
//            g.drawString("" + bankamount.get(x), 220, VariTopMargin);
//            g.drawString("" + siteamount.get(x), 300, VariTopMargin);
//
//            ArrangedTextPrint(""+sitename.get(x), formatForDouble(Double.parseDouble(siteamount.get(x).toString())), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
//            ArrangedTextPrint(""+sitename.get(x), formatForDouble(Double.parseDouble(siteamount.get(x).toString())), coloumwidth - 10, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        }

        DrawBreakLine(LEFTMARGIN, VariTopMargin + 10, g);
        g.setFont(fontd);
        
        ArrangedTextPrint(""+formatForDouble(cashtotalamount), "", coloumwidth-10 , LEFTMARGIN + 120, VariTopMargin+20, g);
        ArrangedTextPrint(""+formatForDouble(banktotalamount), "", coloumwidth-10 , LEFTMARGIN + 200, VariTopMargin+20, g);
        ArrangedTextPrint(""+formatForDouble(totalamount), "", coloumwidth-10 , LEFTMARGIN + 280, VariTopMargin+20, g);


        ArrangedTextPrint("..........................", "", AviWidth / 3, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT * 10, g);
        ArrangedTextPrint("..........................", "", AviWidth / 3, coloumwidth - 60, VariTopMargin, g);
        ArrangedTextPrint("      Prepared By", "", AviWidth / 3, LEFTMARGIN + 10, VariTopMargin += LINEHEIGHT, g);
        ArrangedTextPrint("       Checked By", "", AviWidth / 3, coloumwidth - 60, VariTopMargin, g);


        return PAGE_EXISTS;



    }

    public void DrawBreakLine(int fromleft, int fromtop, Graphics g) {
        g.setFont(fontn);
        g.drawString("-------------------------------------------------------------------", fromleft, fromtop);
    }
    
    public String colStr(String abc){
        int length = 12-abc.length();
        for (int i = 0; i < length; i++) {
            abc = ""+abc;
        }
        
        return abc;
        
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
     * @return the sitename
     */
    public Vector getSitename() {
        return sitename;
    }

    /**
     * @param sitename the sitename to set
     */
    public void setSitename(Vector sitename) {
        this.sitename = sitename;
    }

    /**
     * @return the siteamount
     */
    public Vector getSiteamount() {
        return siteamount;
    }

    /**
     * @param siteamount the siteamount to set
     */
    public void setSiteamount(Vector siteamount) {
        this.siteamount = siteamount;
    }

    /**
     * @return the totalamount
     */
    public double getTotalamount() {
        return totalamount;
    }

    /**
     * @param totalamount the totalamount to set
     */
    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
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
     * @return the cashamount
     */
    public Vector getCashamount() {
        return cashamount;
    }

    /**
     * @param cashamount the cashamount to set
     */
    public void setCashamount(Vector cashamount) {
        this.cashamount = cashamount;
    }

    /**
     * @return the bankamount
     */
    public Vector getBankamount() {
        return bankamount;
    }

    /**
     * @param bankamount the bankamount to set
     */
    public void setBankamount(Vector bankamount) {
        this.bankamount = bankamount;
    }

    /**
     * @return the cashtotalamount
     */
    public double getCashtotalamount() {
        return cashtotalamount;
    }

    /**
     * @param cashtotalamount the cashtotalamount to set
     */
    public void setCashtotalamount(double cashtotalamount) {
        this.cashtotalamount = cashtotalamount;
    }

    /**
     * @return the banktotalamount
     */
    public double getBanktotalamount() {
        return banktotalamount;
    }

    /**
     * @param banktotalamount the banktotalamount to set
     */
    public void setBanktotalamount(double banktotalamount) {
        this.banktotalamount = banktotalamount;
    }
    /**
     * @return the companyname
     */
}
