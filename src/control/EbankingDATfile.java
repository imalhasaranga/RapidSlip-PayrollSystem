
package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import model.DB;


public class EbankingDATfile {

    public void GenTempFIle(ResultSet rs) {
        int numofemps = 0;
        
        DateFormat df = new SimpleDateFormat("yyyy-MM");
        DateFormat df1 = new SimpleDateFormat("yyMMdd");
        String filename = "Temp.DAT";
        File f = new File(System.getProperty("user.dir") + "/CU1449 SALARY SOFTWARE/" + filename);
        if (f.exists()) {
            f.delete();
        }
        try {
            String OriginatingBank = null;
            String OriginatingBranch = null;
            String OriginatingAccount = null;
            String originatingAccountNumber = null;

            ResultSet r = getCompany();
            while (r.next()) {
                OriginatingBank = r.getString("MCIRNO");
                OriginatingBranch = r.getString("BranchNo");
                OriginatingAccount = r.getString("comp_bankName");
                originatingAccountNumber = r.getString("accountnumber");
            }
            try (FileOutputStream fo = new FileOutputStream(f);PrintStream ps = new PrintStream(fo)) {
                String rp = null;
                double value = 0;
                //writing employee details
                while (rs.next()) {

                     
                         if(rs.getString("PayType").trim().equals("BANK")) {
                            numofemps++;
                            ps.print(getWriteString("0000", 4));
                            ps.print(getWriteString(rs.getString("bankcode"), 4));
                            ps.print(getWriteString(branchCode(rs.getString("branchcode")), 3));
                            ps.print(getWriteString(getAccountNumber(rs.getString("Account_number") + ""), 12));
                            ps.print(getWriteString(rs.getString("FMname") + " " + rs.getString("Lname"), 20));
                            ps.print(getWriteString("23", 2));
                            ps.print(getWriteString("00", 2));
                            ps.print(getWriteString("0", 1));
                            ps.print(getWriteString("000000", 6));
                            value += rs.getDouble("PackSal");
                             System.out.println("--"+getAmount(rs.getDouble("PackSal"))+"--");
                            ps.print(getWriteString(getAmount(rs.getDouble("PackSal")), 12));
                            ps.print(getWriteString("SLR", 3));
                            ps.print(getWriteString(OriginatingBank, 4));
                            ps.print(getWriteString(branchCode(OriginatingBranch), 3));
                            ps.print(getWriteString(getAccountNumber(originatingAccountNumber), 12));
                            ps.print(getWriteString(OriginatingAccount, 20));
                            ps.print(getWriteString("EMP NO", 15));
                            Date d = rs.getDate("Date");
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(d);
                            rp = "SAL" + getMonth(cal.get(Calendar.MONTH) + 1) + "" + cal.get(Calendar.YEAR);
                            ps.print(getWriteString(rp, 15));
                            ps.print(getWriteString(df1.format(new Date()), 6));
                            ps.print(getWriteString("      ", 6));
                            ps.print(getWriteString("@", 1));
                            ps.print("\n");
                        }
                    


                }
                //writing company bank details
                ps.print(getWriteString("0000", 4));
                ps.print(getWriteString(OriginatingBank, 4));
                ps.print(getWriteString(branchCode(OriginatingBranch), 3));
                ps.print(getWriteString(getAccountNumber(originatingAccountNumber), 12));
                ps.print(getWriteString(OriginatingAccount, 20));
                ps.print(getWriteString("23", 2));
                ps.print(getWriteString("00", 2));
                ps.print(getWriteString("1", 1));
                ps.print(getWriteString("000000", 6));
                System.out.println("--"+getAmount(value)+"--");
                ps.print(getWriteString(getAmount(value), 12));
                ps.print(getWriteString("SLR", 3));
                ps.print(getWriteString(OriginatingBank, 4));
                ps.print(getWriteString(branchCode(OriginatingBranch), 3));
                ps.print(getWriteString(getAccountNumber(originatingAccountNumber), 12));
                ps.print(getWriteString(OriginatingAccount, 20));
                ps.print(getWriteString("EMP NO", 15));
                ps.print(getWriteString(rp, 15));
                ps.print(getWriteString(df1.format(new Date()), 6));
                ps.print(getWriteString("      ", 6));
                ps.print(getWriteString("@", 1));
                ps.println();
                ps.close();
            }
            JOptionPane.showMessageDialog(null, "Temp.dat File has been successfully created for " + numofemps + " employees", "Temp.dat", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Error While Creating DAT File!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.out.println("Batch printing error " + ex);
        }


    }

    public String getWriteString(String text, int size) {
        String b = "";
        if (size != 0) {
            int i = size - text.length();
            if (i > 0) {
                for (int j = 0; j < i; j++) {
                    b += " ";
                }
                return (text + b);
            } else if (i < 0) {

                return text.substring(0, size);
            }
        }
        return text;
    }

    public String getAmount(double amount) {
        String nuber = ((int) (amount * 100)) + "";
        int size = 12 - nuber.length();
        for (int i = 0; i < size; i++) {
            nuber = "0" + nuber;

        }
        return nuber;
    }

    public String branchCode(String code) {
        int lent = code.length();
        if (lent == 3) {
            return code;
        } else {
            lent = 3 - code.length();
            for (int i = 0; i < lent; i++) {
                code = "0" + code;

            }
            return code;
        }

    }

    public String getAccountNumber(String acountnuber) {

        int lent = acountnuber.length();
        if (lent == 12) {
            return acountnuber;
        } else {
            lent = 12 - acountnuber.length();
            for (int i = 0; i < lent; i++) {
                acountnuber = "0" + acountnuber;

            }
            return acountnuber;
        }

    }

    public ResultSet getCompany() {
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT 	"
                    + "Compname, "
                    + "Address, "
                    + "contact, "
                    + "contact2, "
                    + "logourl, "
                    + "web, "
                    + "email,"
                    + "fax, "
                    + "comp_bankName, "
                    + "accountnumber, "
                    + "MCIRNO, "
                    + "BranchNo"
                    + " FROM "
                    + "comp_parameters ");
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public String getMonth(int monthindex) {

        String months[] = {
            " ", "JAN", "FEB", "MAR", "APR", "MAY",
            "JUN", "JUL", "AUG", "SEP", "OCT",
            "NOV", "DEC"
        };
        return months[monthindex];
    }
}
