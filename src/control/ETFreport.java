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
public class ETFreport implements Printable {

    public int WIDTH = 600;
    public int HEIGHT = 567;
    public int LEFTMARGIN = 10;
    public int RIGHTMARGIN = 10;
    public int TOPMARGIN = 10;
    public int LINEHEIGHT = 15;
    Font fonth = new Font("verdana", Font.BOLD, 10);
    Font fontn = new Font("verdana", Font.PLAIN, 9);
    Font fontd = new Font("verdana", Font.PLAIN, 8);
    Font fontN = new Font("verdana", Font.BOLD, 8);
    Graphics g;
    JTable jt;
    int month;
    int year;
    int startingIndex = 0;
    int endindex = 0;
    ///-----------------------------
    
    double grandEmploREM = 0;
    double grandEmployeeETF = 0;
    //-------------------------------
    int totalpages = 0;
    int currentpage = 0;

    public synchronized void printThisInvoice(JTable jt, int month, int year) {
        this.jt = jt;
        this.month = month;
        this.year = year;





//        paper.setImageableArea(0, 0, 420, HEIGHT);




        for (int i = 0; i < jt.getRowCount(); i++) {
            Employee emp = new Employee(Integer.parseInt(getvl(i, 0)));
            
            grandEmploREM += emp.getBasicSalary() + emp.getGallowance() + emp.getBallowance() - Double.parseDouble(getvl(i, 6));
            grandEmployeeETF += Double.parseDouble(jt.getValueAt(i, 9).toString());
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
        CentrePrint("E.T.F. REPORT FOR " + getMonth(month).toUpperCase() + " " + year + "", WIDTH, -40, VariTopMargin += LINEHEIGHT + 2, g);
//Deaw a line to seperate------------------------------------------------------------------------------------------------
        VariTopMargin += LINEHEIGHT * 1;

        g.setFont(fontn);
        if (currentpage == 1) {
            DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
            g.setFont(fontd);
            // g.drawString("REG. NO :  21380 / B / CW", LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
            g.drawString("E.T.F Total : " + formatForDouble(grandEmployeeETF), LEFTMARGIN, VariTopMargin += LINEHEIGHT - 5);
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
        printColumn("Employee Name", colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
        //printColumn("NIC", colwidth, ((int) (colval += colwidth * 2)), 2);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 2)) + 19, 3); //Remunetion
        printColumn("E.P.F", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("Gross", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("E.T.F", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("KDAW", colwidth, ((int) (colval += colwidth * 1.2)), 3);
        newLine();
        colval = 0;

        printColumn("No", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
        printColumn("", colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
        //printColumn("", colwidth, ((int) (colval += colwidth * 2)), 2);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 2)) + 19, 3); //
        printColumn("No", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("Remuneration", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("3%", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("No.", colwidth, ((int) (colval += colwidth * 1.2)), 3);

        colval = 0;



        VariTopMargin += LINEHEIGHT + 7;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
        g.setFont(fontd);
        int io;

        double EmployeeETF = 0, remunation = 0;

        for (int i = startingIndex; i <= endindex; i++) {

            Employee emp = new Employee(Integer.parseInt(getvl(i, 0)));
            printColumn((i + 1) + "", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
            printColumn(getvl(i, 2), colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
            //printColumn(getvl(i, 2), colwidth, ((int) (colval += colwidth * 2)), 2);
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 2) + 19), 3); //
            printColumn(emp.getEPFNumber() + "", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);


            printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
            double rem = emp.getBasicSalary() + emp.getGallowance() + emp.getBallowance() - Double.parseDouble(getvl(i, 6));
            remunation += rem;
            printColumn(formatForDouble(rem), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
            double employee = Double.parseDouble(getvl(i, 9));
            EmployeeETF += employee;
            printColumn(formatForDouble(employee), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);

            printColumn(emp.getEmpno() + "", colwidth, ((int) (colval += colwidth * 1.2)), 3);
            newLine();
            colval = 0;

        }




        VariTopMargin += 8;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);
        g.setFont(fontN);

        printColumn("", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
        printColumn("Page Total", colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
        //printColumn("", colwidth, ((int) (colval += colwidth * 2)), 2);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 2)) + 19, 3); //
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);
        printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn(formatForDouble(remunation), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn(formatForDouble(EmployeeETF), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
        printColumn("", colwidth, ((int) (colval += colwidth * 1.2)), 3);
        g.setFont(fontd);
        colval = 0;

        VariTopMargin += LINEHEIGHT + 8;

        DrawBreakLine(LEFTMARGIN, VariTopMargin, g);

        VariTopMargin += LINEHEIGHT * 1;
        if (totalpages == currentpage) {

            printColumn("", colwidth / 2, ((int) (colval += colwidth * 0)), 1);
            printColumn("GRAND TOTAL", colwidth * 2, ((int) (colval += colwidth * 1)) - 15, 1);
            //printColumn("", colwidth, ((int) (colval += colwidth * 2)), 2);
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 2)) + 19, 3); //
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)) + 1, 3);
            printColumn("", colwidth + 5, ((int) (colval += colwidth * 1.2)) - 20, 3);
            printColumn(formatForDouble(grandEmploREM), colwidth + 5, ((int) (colval += colwidth * 1.2)), 3);
            printColumn(formatForDouble(grandEmployeeETF), colwidth + 5, ((int) (colval += colwidth * 1.2)) + 20, 3);
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
        ETFreport q = new ETFreport();
        synchronized (q) {
            q.printThisInvoice(null, 0, 0);
        }
    }
}
