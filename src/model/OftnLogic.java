/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.imal.practicalJcombSearchable.JComboPractical;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 *
 * @author Imal
 */
public class OftnLogic {

    public static void LoadEmpType(JComboPractical pract) {
        try {


            ResultSet rs = DB.getData("select* from emtype");
            while (rs.next()) {
                String text = rs.getString("Emp_Type");
                int ID = rs.getInt("Emp_TypeID");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Designation(JComboPractical pract) {
        try {


            ResultSet rs = Designation.showAllDesignations();
            while (rs.next()) {
                String text = rs.getString("Designation");
                int ID = rs.getInt("Designation_ID");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Division(JComboPractical pract) {

        try {


            ResultSet rs = Division.getDivisions();
            while (rs.next()) {
                String text = rs.getString("Division_name");
                int ID = rs.getInt("Div_iD");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Sites(JComboPractical pract) {
        try {


            ResultSet rs = Site.getSites();
            while (rs.next()) {
                String text = rs.getString("Site_location");
                int ID = rs.getInt("Site_ID");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Allownaces(JComboPractical pract) {
        try {


            ResultSet rs = DB.getData("select* from extravalues where Type ='ALLOW' && is_active='1' ");
            while (rs.next()) {
                String text = rs.getString("Name");
                int ID = rs.getInt("extraval_id");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Diductions(JComboPractical pract) {
        try {


            ResultSet rs = DB.getData("select* from extravalues where Type ='DID' && is_active='1' ");
            while (rs.next()) {
                String text = rs.getString("Name");
                int ID = rs.getInt("extraval_id");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void additions(JComboPractical pract) {
        try {


            ResultSet rs = DB.getData("select* from additions where  is_active='1' ");
            while (rs.next()) {
                String text = rs.getString("addition_name");
                int ID = rs.getInt("addition_id");
                pract.addDataItem(ID, text);

            }
            pract.showAllDataItmes();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static String bankID = "";

    public static void Banks(JComboPractical bankp) {
        try {


            ResultSet rs = DB.getData("select* from bank ");
            while (rs.next()) {

                Details d0 = new Details();
                d0.name = rs.getString("BankName");
                d0.ID = rs.getString("BaCode");
                bankp.addItem(d0);

            }

        } catch (Exception e) {

            System.out.println(e);
        }
    }

    public static void BankBranch(JComboPractical bankbr) {
        try {
            if (bankbr != null) {
                bankbr.removeAllItems();
                ResultSet rs = DB.getData("SELECT BankCode, BranchCode, BranchName FROM bank_branch where BankCode = '" + OftnLogic.bankID + "'");
                while (rs.next()) {


                    Details d0 = new Details();
                    d0.name = rs.getString("BranchName");
                    d0.ID = rs.getString("BranchCode");

                    bankbr.addItem(d0);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void fillData(JTable table) {
        File f = saveFile();
        if (f != null) {

            try {

                WritableWorkbook workbook1 = Workbook.createWorkbook(f);
                WritableSheet sheet1 = workbook1.createSheet("emp names", 0);
                TableModel model = table.getModel();

                for (int i = 0; i < model.getColumnCount(); i++) {
                    Label column = new Label(i, 0, model.getColumnName(i));
                    sheet1.addCell(column);
                }

                int j = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (j = 0; j < model.getColumnCount(); j++) {
                        Object s = model.getValueAt(i, j);
                        String value = "";
                        if (s != null) {
                            value = s.toString();
                        }
                        Label row = new Label(j, i + 1, value);
                        sheet1.addCell(row);
                    }
                }
                workbook1.write();
                workbook1.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public File saveFile() {

        JFileChooser jchor = new JFileChooser();
        FileNameExtensionFilter ft = new FileNameExtensionFilter("Excel File", "xls");
        jchor.addChoosableFileFilter(ft);
        int returnval = jchor.showSaveDialog(jchor);
        if (returnval == javax.swing.JFileChooser.APPROVE_OPTION) {

            return jchor.getSelectedFile();
        } else {
            return null;
        }

    }

    public static class Details {

        public String ID;
        public String name;

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj != null && this != null) {
                return ((Details) obj).ID.equals(this.ID);
            } else {
                return false; 
           }

        }
    }
}
