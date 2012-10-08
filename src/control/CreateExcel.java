/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.NumberFormat;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Employee;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 *
 * @author Gihan
 */
public class CreateExcel {
    Employee employee;
    
    private int empno;
    private String empname;
    private String empdesignation;
    private double empbalancepay;
    private int year;
    private int month;
    private String site;
    private double sitetotal;
    private String paytype;
    

    /**
     * @return the empno
     */
    public int getEmpno() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpno(int empno) {
        this.empno = empno;
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
     * @return the empdesignation
     */
    public String getEmpdesignation() {
        return empdesignation;
    }

    /**
     * @param empdesignation the empdesignation to set
     */
    public void setEmpdesignation(String empdesignation) {
        this.empdesignation = empdesignation;
    }

    /**
     * @return the empbalancepay
     */
    public double getEmpbalancepay() {
        return empbalancepay;
    }

    /**
     * @param empbalancepay the empbalancepay to set
     */
    public void setEmpbalancepay(double empbalancepay) {
        this.empbalancepay = empbalancepay;
    }

    /**
     * @return the site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(String site) {
        this.site = site;
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

    public String getMonthText(int monthindex) {

        String months[] = {
            " ", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October",
            "November", "December"
        };
        return months[monthindex];
    }

    public void createSiteSalaryFile(JTable tblsalary) {
        int empnum = 0;
        double banktotal = 0.0;
        double cashtotal = 0.0;
        
        employee = new Employee();
        
        if (JOptionPane.showConfirmDialog(null, "Export Site & Salary Data to Excel?", "Question", 0, 1) == 0) {
            JFileChooser filechoose = new JFileChooser();
            filechoose.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int choose = filechoose.showSaveDialog(null);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Books Total Report");

            HSSFRow rowmain = sheet.createRow((short) 0);
            HSSFRow rowempty = sheet.createRow((short) 1);
            HSSFRow rowhead = sheet.createRow((short) 2);

            rowmain.createCell((short) 1).setCellValue("Salary Data For: " + getSite() + " Site");
            rowmain.createCell((short) 3).setCellValue("Year-Month: " + getYear() + "-" + getMonthText(getMonth()));

            rowhead.createCell((short) 0).setCellValue("Emp. No");
            rowhead.createCell((short) 1).setCellValue("Employee Name");
            rowhead.createCell((short) 2).setCellValue("Designation");
            rowhead.createCell((short) 3).setCellValue("Balance Pay");
            rowhead.createCell((short) 4).setCellValue("Pay Type");

            int index = 3;
            for (int y = 0; y < tblsalary.getRowCount(); y++) {
                empnum = Integer.parseInt(tblsalary.getValueAt(y, 0).toString());
                System.out.println("EMPNO: " + empnum);
                try {
                    ResultSet rs = employee.getEmployeeBankData(empnum);
                    rs.next();
                    if(rs.getString("bankcode") == null || rs.getString("branchcode") == null || rs.getString("Account_number") == null){
                        paytype = "Cash";
                        
                        cashtotal += Double.parseDouble(tblsalary.getValueAt(y, 19).toString());
                        
                        
                    }else if(rs.getString("bankcode").trim().equals("") || rs.getString("branchcode").trim().equals("") || rs.getString("Account_number").trim().equals("")){
                        paytype = "Cash";
                        
                        cashtotal += Double.parseDouble(tblsalary.getValueAt(y, 19).toString());
                        
                    }else{
                        paytype = "Bank";
                        
                        banktotal += Double.parseDouble(tblsalary.getValueAt(y, 19).toString());
                        
                    }
                } catch (Exception e) {
                    System.out.println("Error Banking Data: " + e);
                    e.printStackTrace();
                }
                
                
                HSSFRow row = sheet.createRow((short) index);

                row.createCell((short) 0).setCellValue(tblsalary.getValueAt(y, 0).toString());
                row.createCell((short) 1).setCellValue(tblsalary.getValueAt(y, 2).toString());
                row.createCell((short) 2).setCellValue(tblsalary.getValueAt(y, 3).toString());
                row.createCell((short) 3).setCellValue(formatForDouble(Double.parseDouble(tblsalary.getValueAt(y, 19).toString())));
                row.createCell((short) 4).setCellValue(paytype);
                index++;
                
            }
            
            System.out.println("Cash Total: " + cashtotal);
            System.out.println("Bank Total: " + banktotal);
            
            HSSFRow rowtotal = sheet.createRow((short) index);
            rowtotal.createCell((short) 2).setCellValue("Total");
            rowtotal.createCell((short) 3).setCellValue(formatForDouble(getSitetotal()));
            
            
            if (choose == JFileChooser.APPROVE_OPTION) {
                File file = filechoose.getSelectedFile();

                try {
                    FileOutputStream fileOut = new FileOutputStream(file + ".xls");
                    wb.write(fileOut);

                    fileOut.close();
                    JOptionPane.showMessageDialog(null, "Successfully Created, Path: " + file, "Done!", 1);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error! While Generating: " + e, "Error!", 0);
                    e.printStackTrace();
                }
            }

        }
        
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
    
    public void createPDF(){
        

        
        
        
    }

    /**
     * @return the sitetotal
     */
    public double getSitetotal() {
        return sitetotal;
    }

    /**
     * @param sitetotal the sitetotal to set
     */
    public void setSitetotal(double sitetotal) {
        this.sitetotal = sitetotal;
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
