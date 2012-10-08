/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gihan
 */
public class Designation {

    private int did;
    private String designation;
    private int isactive;
    private static ResultSet result;

    /**
     * @return the did
     */
    public int getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(int did) {
        this.did = did;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the isactive
     */
    public int getIsactive() {
        return isactive;
    }

    /**
     * @param isactive the isactive to set
     */
    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public boolean addDesignation() {


        try {
            DB.setData("insert into designation(Designation) values('" + getDesignation() + "')");

            return true;
        } catch (Exception e) {

            System.out.println("Error From addDesignation Method: " + e);
            return false;
        }



    }

    public boolean updateDesignation(int updateid) {
        

        try {
            DB.setData("update designation set designation = '"+getDesignation()+"' where designation_ID ='"+updateid+"'");
            return true;
        } catch (Exception e) {
            
            System.out.println("Error From updateDesignation Method: " + e);
            return false;
        }

        

    }

    public boolean deactivateDesignation(int deactid) {
        boolean val = false;

        try {
            DB.setData("update designation set is_active ='0' where designation_ID ='"+deactid+"'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From deactivateDesignation Method: " + e);
        }

        return val;

    }

    public static ResultSet showAllDesignations() {
        ResultSet rs = null;

        try {
            rs = DB.getData("select* from designation where is_active = '1'");
        } catch (Exception e) {
            System.out.println(e);
        }



        return rs;
    }
    
    
    public static ResultSet showAllDesignationsOrder() {
        ResultSet rs = null;

        try {
            rs = DB.getData("select* from designation where is_active = '1' order by Designation");
        } catch (Exception e) {
            System.out.println(e);
        }



        return rs;
    }
}
