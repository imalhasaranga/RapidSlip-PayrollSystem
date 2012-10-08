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
public class Site {
    private int siteid;
    private String site;
    private int isactive;

    /**
     * @return the siteid
     */
    public int getSiteid() {
        return siteid;
    }

    /**
     * @param siteid the siteid to set
     */
    public void setSiteid(int siteid) {
        this.siteid = siteid;
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
    
     public boolean addSite(){
        boolean val = false;
        
        try {
            DB.setData("insert into site(Site_location) values('"+getSite()+"')");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From addSite Method: " + e);
        }
        
        return val;
        
    }
    
    public boolean updateSite(int updateid){
        boolean val = false;
        
        try {
            DB.setData("update site set Site_location ='"+getSite()+"' where site_ID = '"+updateid+"' ");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From updateSite Method: " + e);
        }
        
        return val;
        
    }
    
    public boolean deactivateSite(int deactid){
        boolean val = false;    
        try {
            DB.setData("update site set is_active ='0' where site_ID = '"+deactid+"' ");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From deactivateSite Method: " + e);
        }
        
        return val;
        
    }
    
    public static ResultSet getSites(){
    
        ResultSet rs =null;
        try {
            rs = DB.getData("select* from site where is_active ='1'");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return rs;
    }
    
    public static ResultSet getSitePayAmount(int yr, int mn, int sid){
    
        ResultSet rs =null;
        try {
            rs = DB.getData("SELECT SUM(FinalSalary) AS finaltotal FROM payroll py, emp_site es WHERE es.site_ID = '"+sid+"' AND es.is_active = '1' AND py.EmpNumber = es.EmployeeEmpNumber AND py.Year = '"+yr+"' AND py.Month = '"+mn+"' AND py.is_active = '1'");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return rs;
    }
    
    public static ResultSet getSitePayAmount2(int yr, int mn, int sid){
    
        ResultSet rs =null;
        try {
            rs = DB.getData("SELECT py.PackSal, py.PayType FROM payroll py, emppayroldata es WHERE es.Site_id = '"+sid+"' AND py.payRollNum = es.payRollNum AND py.Year = '"+yr+"' AND py.Month = '"+mn+"' AND py.is_active = '1'");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return rs;
    }
    
    public static ResultSet getBankPayments(int yr, int mn, int bid){
    
        ResultSet rs =null;
        try {
            rs = DB.getData("SELECT COUNT(py.PackSal) AS numrec, SUM(py.PackSal) AS tot FROM payroll py, emppayroldata bd WHERE py.YEAR = '"+yr+"' AND py.month = '"+mn+"' AND py.is_active = '1' AND py.PayType = 'BANK' AND py.payRollNum = bd.payRollNum AND bd.bankcode = '7056'");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return rs;
    }
    
    public static ResultSet getBankPayments2(int yr, int mn, int bid){
    
        ResultSet rs =null;
        try {
            rs = DB.getData("SELECT COUNT(py.PackSal) AS numrec, SUM(py.PackSal) AS tot FROM payroll py, emppayroldata bd WHERE py.YEAR = '"+yr+"' AND py.month = '"+mn+"' AND py.is_active = '1' AND py.PayType = 'BANK' AND py.payRollNum = bd.payRollNum AND bd.bankcode != '7056'");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    return rs;
    }
    
}
