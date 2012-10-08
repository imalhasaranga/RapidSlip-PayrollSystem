/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Gihan
 */
public class DB {

    public static Connection con;

    public static Connection getCon() throws Exception {
        DbProperty dbproperty = null;

        if (con == null) {
            try {
                FileInputStream connectdata = new FileInputStream(System.getProperty("user.dir") + "/dbdata.dat");
                ObjectInputStream input = new ObjectInputStream(connectdata);

                dbproperty = (DbProperty) input.readObject();
                input.close();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Connection Failure: " + e, "Error", 0);
            }

            Class.forName(dbproperty.getDriver()).newInstance();
            con = DriverManager.getConnection("jdbc:mysql://" + dbproperty.getHost() + ":" + dbproperty.getPort() + "/payroll_data?autoReconnect=true", dbproperty.getUsername(), dbproperty.getPassword());
        }
        return con;
    }

    public static ResultSet getData(String sql) throws Exception {


        return getCon().createStatement().executeQuery(sql);
    }

    public static void setData(String sql) throws Exception {


        getCon().createStatement().executeUpdate(sql);

    }

    public static PreparedStatement prepareData(String sql) throws Exception {


        return getCon().prepareStatement(sql);
    }

    public static int setDataAndGet(String sql) throws Exception {

        int returnInt = getCon().createStatement().executeUpdate(sql);

        return returnInt;
    }
}
