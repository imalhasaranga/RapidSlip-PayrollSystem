/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;

/**
 *
 * @author Imal
 */
public class Additions {

    public static final int ALLOWANCE = 10;
    public static final int DIDUCTION = 12;
    private int extravlID;
    private String name;
    private int is_active;

    public boolean addAddition() {
       

        try {
            DB.setData("INSERT INTO additions(addition_name) VALUES('"+getName()+"')");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }


    }

    public boolean updateAddition(int additionID) {


        try {
            DB.setData("update additions set addition_name='" + getName() + "' where  addition_id ='" + additionID + "'  ");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }
    }

    public boolean DeactivateRec(int additionID) {


        try {
            DB.setData("update additions set is_active = '0' where  addition_id ='" + additionID + "'  ");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }
    }

    public static ResultSet getadditions() {
       
        ResultSet rs = null;
        try {

            rs = DB.getData("select* from additions where is_active='1' ");

        } catch (Exception e) {
            System.out.println(e);
        }


        return rs;
    }

    /**
     * @return the extravlID
     */
    public int getExtravlID() {
        return extravlID;
    }

    /**
     * @param extravlID the extravlID to set
     */
    public void setExtravlID(int extravlID) {
        this.extravlID = extravlID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
  

    /**
     * @return the is_active
     */
    public int getIs_active() {
        return is_active;
    }

    /**
     * @param is_active the is_active to set
     */
    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
