/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JList;
import javax.swing.JScrollPane;
import model.DB;
import model.Employee;

/**
 *
 * @author Gihan
 */
public class Empsearch {

    Employee employee;
    int employeenum;
    int epfnum;

    public void employeeSearch(JList list, JScrollPane scroll, String searchterm) {
       ArrayList vec = new ArrayList();
        employee = new Employee();

        try {
            ResultSet rs = employee.getEmployeeDetails(searchterm);
            while (rs.next()) {
                vec.add(rs.getString("fmname") + " " + rs.getString("lname") + "-" + rs.getString("EmpNumber"));
            }
        } catch (Exception e) {
            System.out.println("Error: While Searching " + e);
        }

        list.setListData(vec.toArray());
        list.setVisible(true);
        scroll.setVisible(true);

        if (vec.isEmpty()) {
            scroll.setSize(scroll.getWidth(), 0);
        } else {
            int y = (32 + (vec.size() - 1) * 18);
            scroll.setSize(scroll.getWidth(), y);
        }

    }

    public int getEmployeeNumber(String text) {
        String textarray[] = text.split("-");
        employeenum = Integer.parseInt(textarray[1]);

        return employeenum;
    }

    

    
}
