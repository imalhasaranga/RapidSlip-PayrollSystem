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
public class User {
    private int utypeid;
    private int compid;
    private String username;
    private String password;
    private int isactive;
    public static int SisUserID;

    /**
     * @return the utypeid
     */
    public int getUtypeid() {
        return utypeid;
    }

    /**
     * @param utypeid the utypeid to set
     */
    public void setUtypeid(int utypeid) {
        this.utypeid = utypeid;
    }

    /**
     * @return the compid
     */
    public int getCompid() {
        return compid;
    }

    /**
     * @param compid the compid to set
     */
    public void setCompid(int compid) {
        this.compid = compid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
    
    public boolean userValidate(String username, String password){
        boolean val = false;
        try {
            ResultSet rs = DB.getData("select * from sysuser where username = '"+username+"' && password = '"+password+"'");
                if(rs.next()){
                    val = true;
                    User.SisUserID = rs.getInt("sysuser_ID");
                }else{
                    val = false;
                }
        } catch (Exception e) {
            val = false;
            System.out.println("Error: " + e);
        }
        
        return val;
        
    }
    
    public int getUserTypeId(String logusername){
        int id = 0;
        
        try {
            ResultSet rs = DB.getData("select user_type as utypeid from sysuser where username = '"+logusername+"'");
            rs.next();
            id = rs.getInt("utypeid");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        //System.out.println("ID: " + id);
        return id;
    }
    
    public boolean changePassword(String username, String newpassword){
        boolean val = false;
        try {
            DB.setData("update sysuser set password = '"+newpassword+"' where username = '"+username+"'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error: " + e);
        }
        
        return val;
        
    }
    
    
}
