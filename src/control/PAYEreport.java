package control;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.*;
import java.text.NumberFormat;
import javax.swing.JTable;
import model.Employee;

/**
 *
 * @author Gihan
 */
public class PAYEreport implements Printable {

    public int WIDTH = 600;
    public int HEIGHT = 567;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 15;
    Font fonth = new Font("verdana", Font.BOLD, 10);
    Font fontn = new Font("verdana", Font.PLAIN, 9);
    Font fontBol = new Font("verdana", Font.BOLD, 9);
    Font fontd = new Font("verdana", Font.PLAIN, 8);
    Font fontN = new Font("verdana", Font.BOLD, 8);
    Graphics g;
    JTable jt;
    int month;
    int year;
    int startingIndex = 0;
    int endindex = 0;
    ///-----------------------------
    double completeTotal = 0;
    double grandgross = 0;
    //-------------------------------
    int totalpages = 0;
    int currentpage = 0;

    public synchronized void printThisInvoice(JTable jt, int month, int year) {
        this.jt = jt;
        this.month = month;
        this.year = year;





//        paper.setImageableArea(0, 0, 420, HEIGHT);




        for (int i = 0; i < jt.getRowCount(); i++) {
            completeTotal += Double.parseDouble(jt.getValueAt(i, 15).toString());
            grandgross += Double.parseDouble(getvl(i, 19)) + Double.parseDouble(getvl(i, 7)) + Double.parseDouble(getvl(i, 15)) + Double.parseDouble(getvl(i, 16)) + Double.parseDouble(getvl(i, 17));

        }
        System.out.println(jt.getRowCount() + " ------///");

        int sets = jt.getRowCount() / 28;
        int left = jt.getRowCount() % 28;
        totalpages = sets + (left > 0 ? 1 : 0);
        for (int i = 0; i < sets; i++) {
            startingIndex = i * 28;
            endindex = i * 28 + 28 - 1;
            ++currentpage;
            try {
                System.out.println("Starting........");
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(this);
                pj.print();
                System.out.println("Done!......");
            } catch (Exception e) {
                System.out.println("Error: Print Error: " + e);
                e.printStackTrace();
            }
        }
        if (left > 0) {

            startingIndex = endindex == 0 ? 0 : endindex + 1;
            endindex = jt.getRowCount() - 1;
            ++currentpage;
            try {
                System.out.println("Starting........");
                PrinterJob pj = PrinterJob.getPrinterJob();
                pj.setPrintable(this);
                pj.print();
                System.out.println("Done!......");
            } catch (Exception e) {
                System.out.println("Error: Print Error: " + e);
                e.printStackTrace();
            }
        }

        //  if (pj.printDialog()) {
//        try {
//            System.out.println("Starting........");
//            pj.print();
//            System.out.println("Done!......");
//        } catch (Exception e) {
//            System.out.println("Error: Print Error: " + e);
//            e.printStackTrace();
//        }
        //  }


    }
    int VariTopMargin;

    @Override
    public synchronized int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

        this.g = g;
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        VariTopMargin = TOPMARGIN;
        int VariTopDidMargin = 0;
        int AviWidth = WIDTH - LEFTMARGIN - RIGHTMARGIN;

        int coloumwidth = AviWidth / 3;



        Graphics2D gr2d = (Graphics2D) g;
        gr2d.translate(pf.getImageableX(), pf.getImageableY());


//Company Name, Address & SlipHeading Details****************************************************************************        
        g.setFont(fonth);
        CentrePrint("K.D.A. Weerasinghe & Co. (Pvt) Ltd", WIDTH, -40, VariTopMargin, g);
        g.setFont(fontn);
        CentrePrint("8/16, Thalapathpitiya Road, Nugegoda", WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
        CentrePrint("PAYEE REPORT FOR " + getMonth(month).toUpperCase() + " " + year + "", WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
//Deaw a line to seperate------------------------------------------------------------------------------------------------
        VariTopMargin += LINEHEIGHT * 1;

        g.setFont(fontn);
        if (currentpage == 1) {
            DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
            g.setFont(fontd);
            g.drawString("PAYE Total : " + formatForDouble(completeTotal), LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
            g.drawString("Total Pages : " + totalpages, LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
            // g.drawString("Page :", LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
        } else {
            g.drawString("Page : " + currentpage, LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
        }

        DrawBreakLine(LEFTMARGIN, VariTopMargin + 10, g);
        g.setFont(fontd);

        g.setFont(fontd);

        VariTopMargin += 3;
        g.setFont(fontd);

        int colwidth = 42;
        int colval = 0;




        printColumn("Ser", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
        printColumn("EMP", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("E.P.F", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("Employee Name", (int) (colwidth * 2.4), ((int) (colval += colwidth * 0.58) + 8), 1);
        printColumn("Designation", (int) (colwidth * 2.6), ((int) (colval += colwidth * 2.75)) + 20, 1);
        printColumn("Gross", colwidth + 5, ((int) (colval += colwidth * 3.1) - 4), 3); //Remunetion 
        printColumn("Employee", colwidth + 5, ((int) (colval += colwidth * 1.1) - 2), 3);

        newLine();
        colval = 0;



        printColumn("No", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
        printColumn("No", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("No", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("", (int) (colwidth * 2.4), ((int) (colval += colwidth * 0.58) + 8), 1);
        printColumn("", (int) (colwidth * 2.6), ((int) (colval += colwidth * 2.75)) + 20, 1);
        printColumn("Remuneration", colwidth + 5, ((int) (colval += colwidth * 3.1) - 4), 3); //Remunetion 
        printColumn("PAYEE", colwidth + 5, ((int) (colval += colwidth * 1.1) - 2), 3);






        colval = 0;



        VariTopMargin += LINEHEIGHT + 7;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
        g.setFont(fontd);
        int io;

        double GrosRem = 0, tPAYE = 0;

        for (int i = startingIndex; i <= endindex; i++) {

            Employee emp = new Employee(Integer.parseInt(getvl(i, 0)));


            double rem = Double.parseDouble(getvl(i, 19)) + Double.parseDouble(getvl(i, 7)) + Double.parseDouble(getvl(i, 15)) + Double.parseDouble(getvl(i, 16)) + Double.parseDouble(getvl(i, 17));
            GrosRem += rem;

            double employeePAYE = Double.parseDouble(getvl(i, 15));
            tPAYE += employeePAYE;


            printColumn((i + 1) + "", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
            printColumn(emp.getEmpno() + "", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
            printColumn(emp.getEPFNumber() + "", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
            printColumn(getvl(i, 2), (int) (colwidth * 2.4), ((int) (colval += colwidth * 0.58) + 8), 1);
            printColumnTranc(getvl(i, 3), (int) (colwidth * 2.6), ((int) (colval += colwidth * 2.75)) + 20, 1);
            printColumn(formatForDouble(rem), colwidth + 5, ((int) (colval += colwidth * 3.1) - 4), 3); //Remunetion 
            printColumn(formatForDouble(employeePAYE), colwidth + 5, ((int) (colval += colwidth * 1.1) - 2), 3);



            newLine();
            colval = 0;

        }




        VariTopMargin += 8;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
        g.setFont(fontN);

        printColumn("", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
        printColumn("", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("", colwidth / 2, ((int) (colval += colwidth * 0.75)), 3);
        printColumn("Total", (int) (colwidth * 2.4), ((int) (colval += colwidth * 0.58) + 8), 2);
        printColumn("", (int) (colwidth * 2.6), ((int) (colval += colwidth * 2.75)) + 20, 1);
        printColumn(formatForDouble(GrosRem), colwidth + 5, ((int) (colval += colwidth * 3.1) - 4), 3); //Remunetion 
        printColumn(formatForDouble(tPAYE), colwidth + 5, ((int) (colval += colwidth * 1.1) - 2), 3);

        g.setFont(fontd);
        colval = 0;

        VariTopMargin += LINEHEIGHT + 8;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);

        VariTopMargin += LINEHEIGHT * 1;
        if (totalpages == currentpage) {
            g.setFont(fontBol);
            printColumn("", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
            printColumn("GRAND TOTAL", colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
            //printColumn("", colwidth, ((int) (colval += colwidth * 2)), 2);
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 2)) + 19, 3); //
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)) - 20, 3);
            printColumn(formatForDouble(grandgross), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
            printColumn(formatForDouble(completeTotal), colwidth + 5, ((int) (colval += colwidth * 1.2)) + 20, 3);
            printColumn("", colwidth, ((int) (colval += colwidth * 1.2)), 3);
            colval = 0;
            VariTopMargin += LINEHEIGHT * 3;
//            CentrePrint("I Certify that all The Particulars in the above returns", WIDTH, -40, VariTopMargin, g);
//            CentrePrint("are Correct and that no Part of the Employer's Contribution ", WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
//            CentrePrint("has Been Deducted form any Employees Earnings.", WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
//            CentrePrint("…………………………………", WIDTH, -40, VariTopMargin += LINEHEIGHT * 2, g);
//            CentrePrint("Signature of Employer", WIDTH, -40, VariTopMargin += LINEHEIGHT, g);
            g.setFont(fontd);

        }


        g.setFont(fontd);

        return PAGE_EXISTS;



    }

    public String getvl(int row, int col) {
        return jt.getValueAt(row, col).toString();
    }

    public void DrawBreakLine(int fromleft, int fromtop, Graphics g) {
        g.setFont(fontn);
        g.drawString("-------------------------------------------------------------------------------------------------------", fromleft, fromtop);
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

    public void printColumn(String txt, int collenth, int distance, int type) {

        FontMetrics fm = g.getFontMetrics();
        if (type == 1) {
            collenth = 0;
        } else if (type == 2) {
            collenth = collenth - fm.stringWidth(txt) / 2;

        } else if (type == 3) {
            collenth = collenth - fm.stringWidth(txt);
        }

        g.drawString(txt, LEFTMARGIN + distance + collenth, VariTopMargin + LINEHEIGHT);

    }

    public void printColumnTranc(String txt, int collenth, int distance, int type) {
        if (23 < txt.length()) {
            txt = txt.substring(0, 23);
        }


        FontMetrics fm = g.getFontMetrics();
        if (type == 1) {
            collenth = 0;
        } else if (type == 2) {
            collenth = collenth - fm.stringWidth(txt) / 2;

        } else if (type == 3) {
            collenth = collenth - fm.stringWidth(txt);
        }

        g.drawString(txt, LEFTMARGIN + distance + collenth, VariTopMargin + LINEHEIGHT);

    }

    public void newLine() {
        VariTopMargin += LINEHEIGHT;
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
    public static void main(String[] args) {
        PAYEreport q = new PAYEreport();
        synchronized (q) {
            q.printThisInvoice(null, 0, 0);
        }
    }
}
