/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;

/**
 *
 * @author Gihan
 */
public class Division {
    private int divisionid;
    private String division;
    private int isactive;

    /**
     * @return the divisionid
     */
    public int getDivisionid() {
        return divisionid;
    }

    /**
     * @param divisionid the divisionid to set
     */
    public void setDivisionid(int divisionid) {
        this.divisionid = divisionid;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @param division the division to set
     */
    public void setDivision(String division) {
        this.division = division;
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
    
    public boolean addDivision(){
        boolean val = false;
        
        try {
            DB.setData("insert into division(Division_name) values('"+getDivision()+"')");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From addDivision Method: " + e);
        }
        
        return val;
        
    }
    
    public boolean updateDivision(int updateid){
        boolean val = false;
        
        try {
            DB.setData("update division set Division_name ='"+getDivision()+"' where Div_id = '"+updateid+"'");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From updateDivision Method: " + e);
        }
        
        return val;
        
    }
    
    public boolean deactivateDivision(int deactid){
        boolean val = false;
        
        try {
           //DB.setData("update division set Division_name ='"+getDivision()+"' where Div_id = '"+updateid+"'");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From deactivateDivision Method: " + e);
        }
        
        return val;
        
    }
    
    
    public static ResultSet getDivisions(){
        ResultSet rs = null;
        try {
            rs = DB.getData("select* from division ");
        } catch (Exception e) {
        }
    return rs;
    }
}
