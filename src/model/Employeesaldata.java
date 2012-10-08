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
public class Employeesaldata {
    private int empno;
    private double basic;
    private double gvtall;
    private double budall;

    /**
     * @return the empno
     */
    public int getEmpno() {
        return empno;
    }

    /**
     * @param empno the empno to set
     */
    public void setEmpno(int empno) {
        this.empno = empno;
    }

    /**
     * @return the basic
     */
    public double getBasic() {
        return basic;
    }

    /**
     * @param basic the basic to set
     */
    public void setBasic(double basic) {
        this.basic = basic;
    }

    /**
     * @return the gvtall
     */
    public double getGvtall() {
        return gvtall;
    }

    /**
     * @param gvtall the gvtall to set
     */
    public void setGvtall(double gvtall) {
        this.gvtall = gvtall;
    }

    /**
     * @return the budall
     */
    public double getBudall() {
        return budall;
    }

    /**
     * @param budall the budall to set
     */
    public void setBudall(double budall) {
        this.budall = budall;
    }
    
    public boolean assignSalaryData(){
        boolean val = false;
        
        try {
            DB.setData("INSERT INTO emp_salary(Basic_salary,EmpNumber,gvtAllow,budAllow) VALUES('"+getBasic()+"','"+getEmpno()+"','"+getGvtall()+"','"+getBudall()+"')");  
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From assignSalaryData Method: " + e);
        }
        
        return val;
        
    }
    
    public boolean updateSalaryData(int updateid){
        boolean val = false;
        
        try {
            
            DB.setData("update emp_salary set Basic_salary ='"+getBasic()+"',gvtAllow='"+getGvtall()+"',budAllow='"+getBudall()+"' where EmpNumber ='"+updateid+"'");  
            System.out.println("update emp_salary set Basic_salary ='"+getBasic()+"',gvtAllow='"+getGvtall()+"',budAllow='"+getBudall()+"' where EmpNumber ='"+updateid+"'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From updateSalaryData Method: " + e);
        }
        
        return val;
        
    }
    
    
    public static ResultSet getallSalaryinfo(int empid){
        String querpart = "";
        if(empid != -1){
        querpart = "&& employee.EmpNumber ='"+empid+"' ";
        }
        
        ResultSet rs = null;
        try {
            rs = DB.getData("SELECT* FROM   emp_salary LEFT JOIN employee ON employee.EmpNumber = emp_salary.EmpNumber WHERE employee.is_active ='1' "+querpart);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return rs;
    
    }
    
}
