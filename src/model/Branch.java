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
public class Branch {
    private String bankcode;
    private String branchcode;
    private String branchname;

    /**
     * @return the bankcode
     */
    public String getBankcode() {
        return bankcode;
    }

    /**
     * @param bankcode the bankcode to set
     */
    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    /**
     * @return the branchcode
     */
    public String getBranchcode() {
        return branchcode;
    }

    /**
     * @param branchcode the branchcode to set
     */
    public void setBranchcode(String branchcode) {
        this.branchcode = branchcode;
    }

    /**
     * @return the branchname
     */
    public String getBranchname() {
        return branchname;
    }

    /**
     * @param branchname the branchname to set
     */
    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }
    
    public boolean addBranch(){
        boolean val = false;
        
        try {
            DB.setData("insert into bank_branch(BankCode, BranchCode, BranchName) values('"+getBankcode()+"', '"+getBranchcode()+"', '"+getBranchname()+"')");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From addBranch Method: " + e);
            e.printStackTrace();
        }
        
        return val;
    }
    
    public boolean updateBranch(String upbank, String upbranch){
        boolean val = false;
        
        try {
            DB.setData("update bank_branch set BranchName = '"+getBranchname()+"' where BankCode = '"+upbank+"' AND BranchCode = '"+upbranch+"'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From addBranch Method: " + e);
            e.printStackTrace();
        }
        
        return val;
    }
    
    public boolean deleteBranch(String delbank, String delbranch){
        boolean val = false;
        
        try {
            DB.setData("delete from bank_branch where BankCode = '"+delbank+"' AND BranchCode = '"+delbranch+"'");
            val = true;
        } catch (Exception e) {
            val = false;
            System.out.println("Error From addBranch Method: " + e);
            e.printStackTrace();
        }
        
        return val;
    }
    
    public ResultSet getBankBranch(String bacode){
        ResultSet rs = null;
        
        try {
            rs = DB.getData("SELECT bk.BankName, br.branchcode, br.BranchName FROM bank bk, bank_branch br WHERE br.BankCode = bk.BaCode AND bk.BaCode = '"+bacode+"'");
        } catch (Exception e) {
            System.out.println("Error From getBankBranch Method: " + e);
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getBankWiseTotal(int yr, int mn){
        ResultSet rs = null;
        
        try {
            rs = DB.getData("SELECT COUNT(FinalSalary) numofrecs, SUM(FinalSalary) totsalary, SUM(PackSal) totpack, bd.bankcode, bn.BankName FROM payroll py, emppayroldata bd, bank bn WHERE py.payRollNum = bd.payRollNum AND py.year = '"+yr+"' AND py.month = '"+mn+"' AND py.is_active = '1' AND py.PayType = 'BANK' AND bn.BaCode = bd.bankcode GROUP BY bankcode ORDER BY BankName");
        } catch (Exception e) {
            System.out.println("Error From getBankBranch Method: " + e);
            e.printStackTrace();
        }
        
        return rs;
    }
    
    public ResultSet getBankWiseTotal2(int yr, int mn, String bcode){
        ResultSet rs = null;
        
        try {
            rs = DB.getData("SELECT py.EmpNumber, em.FMname, em.Lname, bd.bankcode, bn.BankName, bdt.Account_number, py.FinalSalary, py.PackSal FROM payroll py, emppayroldata bd, bank bn, employee em, bankdetails bdt WHERE py.year = '"+yr+"' AND py.month = '"+mn+"' AND py.is_active = '1' AND py.PayType = 'BANK' AND bd.payRollNum = py.payRollNum AND bdt.EmpNumber = py.EmpNumber AND bn.BaCode = bd.bankcode AND em.EmpNumber = py.EmpNumber AND bd.bankcode = '"+bcode+"' ORDER BY em.EmpNumber");
        } catch (Exception e) {
            System.out.println("Error From getBankBranch Method: " + e);
            e.printStackTrace();
        }
        
        return rs;
    }
    
}
