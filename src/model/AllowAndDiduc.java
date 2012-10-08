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
public class AllowAndDiduc {

    public static final int ALLOWANCE = 10;
    public static final int DIDUCTION = 12;
    private int extravlID;
    private String name;
    private String type;
    private int is_active;

    public boolean addExtraRecord(int RecodeType) {
        String TypeExt = "";

        if (RecodeType == AllowAndDiduc.ALLOWANCE) {
            TypeExt = "ALLOW";
        } else if (RecodeType == AllowAndDiduc.DIDUCTION) {
            TypeExt = "DID";
        }

        try {
            DB.setData("INSERT INTO extravalues(NAME,TYPE) values('" + getName() + "','" + TypeExt + "')");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }


    }

    public boolean updateExtraRec(int extravalid) {


        try {
            DB.setData("update extravalues set NAME='" + getName() + "' where  extraval_id ='" + extravalid + "'  ");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }
    }

    public boolean DeactivateRec(int extravalid) {


        try {
            DB.setData("update extravalues set is_active = '0' where  extraval_id ='" + extravalid + "'  ");
            return true;
        } catch (Exception e) {
            System.out.println("add extra " + e);
            return false;
        }
    }

    public static ResultSet getallowDe(int type) {
        String TypeExt = "";

        if (type == AllowAndDiduc.ALLOWANCE) {
            TypeExt = "ALLOW";
        } else if (type == AllowAndDiduc.DIDUCTION) {
            TypeExt = "DID";
        }
        ResultSet rs = null;
        try {

            rs = DB.getData("select* from extravalues where is_active='1' AND Type ='" + TypeExt + "'");

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
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

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
