/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Branch;
import model.OftnLogic;

/**
 *
 * @author Gihan
 */
public class Branches extends javax.swing.JFrame {

    Branch branch;
    String bankcode;
    String brcode;

    /**
     * Creates new form Branches
     */
    public Branches() {
        initComponents();

        branch = new Branch();
        loadTable(branch.getBankBranch(bankcode));
        OftnLogic.Banks(cmbbank);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbank = new javax.swing.JTable();
        cmbbank = new com.imal.practicalJcombSearchable.JComboPractical();
        lbankcode = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tbranch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tbrcode = new javax.swing.JTextField();
        badd = new javax.swing.JButton();
        bupdate = new javax.swing.JButton();
        bdelete = new javax.swing.JButton();
        bview = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Bank Branches");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(225, 225, 225));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Bank");

        tblbank.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tblbank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Bank", "Branch", "Code"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblbank.setRowHeight(21);
        tblbank.setSelectionBackground(new java.awt.Color(0, 51, 255));
        tblbank.setSelectionForeground(new java.awt.Color(255, 255, 204));
        tblbank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbankMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbank);
        tblbank.getColumnModel().getColumn(2).setMinWidth(60);
        tblbank.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblbank.getColumnModel().getColumn(2).setMaxWidth(60);

        cmbbank.setForeground(new java.awt.Color(102, 0, 0));
        cmbbank.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cmbbank.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbbankItemStateChanged(evt);
            }
        });
        cmbbank.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbbankKeyPressed(evt);
            }
        });

        lbankcode.setBackground(new java.awt.Color(51, 51, 51));
        lbankcode.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbankcode.setForeground(new java.awt.Color(102, 0, 0));
        lbankcode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbankcode.setText("0000");
        lbankcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Branch Name");

        tbranch.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbranch.setForeground(new java.awt.Color(51, 51, 51));
        tbranch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbranchKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Branch Code");

        tbrcode.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbrcode.setForeground(new java.awt.Color(51, 51, 51));
        tbrcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbrcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbrcodeKeyReleased(evt);
            }
        });

        badd.setText("Add Branch");
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });

        bupdate.setText("Update Branch");
        bupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bupdateActionPerformed(evt);
            }
        });

        bdelete.setText("Delete Branch");
        bdelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdeleteActionPerformed(evt);
            }
        });

        bview.setText("View");
        bview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bviewActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("This must be according to Sri Lankan Bank-Branch Codes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(tbrcode, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(badd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bupdate)
                        .addGap(18, 18, 18)
                        .addComponent(bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbbank, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(tbranch))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbankcode, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbbank, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbankcode, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bview))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbranch, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbrcode, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(badd, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bdelete, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-531)/2, (screenSize.height-530)/2, 531, 530);
    }// </editor-fold>//GEN-END:initComponents

    private void tblbankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbankMouseClicked
        brcode = tblbank.getValueAt(tblbank.getSelectedRow(), 2).toString();
        badd.setEnabled(false);
        tbrcode.setEnabled(false);
        cmbbank.setEnabled(false);

        tbranch.setText(tblbank.getValueAt(tblbank.getSelectedRow(), 1).toString());
        tbrcode.setText(brcode);

    }//GEN-LAST:event_tblbankMouseClicked

    private void cmbbankItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbbankItemStateChanged
        OftnLogic.bankID = ((OftnLogic.Details) cmbbank.getSelectedItem()).ID;
        bankcode = OftnLogic.bankID;
        lbankcode.setText(bankcode);

    }//GEN-LAST:event_cmbbankItemStateChanged

    private void tbranchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbranchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tbrcode.grabFocus();
        }
    }//GEN-LAST:event_tbranchKeyPressed

    private void tbrcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbrcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbrcodeKeyPressed

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed
        if (formCheck()) {
            branch = new Branch();
            branch.setBankcode(this.bankcode);
            branch.setBranchcode(tbrcode.getText());
            branch.setBranchname(tbranch.getText());
            if (branch.addBranch()) {
                JOptionPane.showMessageDialog(null, "Successfully Added Branch!", "Done!", 1);
                clearAll();
                loadTable(branch.getBankBranch(bankcode));
            } else {
                JOptionPane.showMessageDialog(null, "Error While Adding Branch!", "Error!", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Branch Name & Branch Code is Essential", "Warning!", 2);
        }

    }//GEN-LAST:event_baddActionPerformed

    private void cmbbankKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbbankKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tbranch.grabFocus();
        }
    }//GEN-LAST:event_cmbbankKeyPressed

    private void tbrcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbrcodeKeyReleased
        if (!tbrcode.getText().isEmpty()) {
            for (int x = 0; x < tbrcode.getText().length(); x++) {
                char c = tbrcode.getText().charAt(x);
                if (!Character.isDigit(c)) {
                    String text = tbrcode.getText().substring(0, x);
                    tbrcode.setText(null);
                    tbrcode.setText(text);
                    tbrcode.setText("");
                }
            }

            if (tbrcode.getText().trim().length() > 3) {
                tbrcode.setText("");

            }
        } else {
            tbrcode.setText("");
        }
    }//GEN-LAST:event_tbrcodeKeyReleased

    private void bviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bviewActionPerformed
        loadTable(branch.getBankBranch(bankcode));

    }//GEN-LAST:event_bviewActionPerformed

    private void bupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bupdateActionPerformed
        if (formCheck()) {
            branch = new Branch();
            branch.setBankcode(this.bankcode);
            branch.setBranchcode(tbrcode.getText());
            branch.setBranchname(tbranch.getText());
            if (branch.updateBranch(bankcode, tbrcode.getText())) {
                JOptionPane.showMessageDialog(null, "Successfully Updated Branch!", "Done!", 1);
                clearAll();
                loadTable(branch.getBankBranch(bankcode));
            } else {
                JOptionPane.showMessageDialog(null, "Error While Updating Branch!", "Error!", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Branch Name & Branch Code is Essential", "Warning!", 2);
        }
    }//GEN-LAST:event_bupdateActionPerformed

    private void bdeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdeleteActionPerformed
        if (tblbank.getSelectedRow() > 0) {
            if (JOptionPane.showConfirmDialog(null, "Sure to Delete the Branch?", "Warning!", 0, 3) == 0) {
                branch = new Branch();
                    if(branch.deleteBranch(bankcode, brcode)){
                        JOptionPane.showMessageDialog(null, "Deleted Successfully!", "Done!", 1);
                        clearAll();
                        loadTable(branch.getBankBranch(bankcode));
                    }else{
                        JOptionPane.showMessageDialog(null, "Error While Deleting!", "Error!", 0);
                    }
                    
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select the Branch to Delete!", "Warning!", 2);
        }




    }//GEN-LAST:event_bdeleteActionPerformed

    boolean formCheck() {
        boolean val = false;

        try {
            if (tbranch.getText().trim().equals("") || tbrcode.getText().trim().equals("")) {
                val = false;
            } else {
                val = true;
            }
        } catch (Exception e) {
            val = false;
        }

        return val;
    }

    void clearAll() {
        tbranch.setText("");
        tbrcode.setText("");

    }

    void loadTable(ResultSet rs) {
        clearTable();
        DefaultTableModel dt = (DefaultTableModel) tblbank.getModel();

        try {
            while (rs.next()) {
                ArrayList ar = new ArrayList();
                ar.add(rs.getString("BankName"));
                ar.add(rs.getString("BranchName"));
                ar.add(rs.getString("branchcode"));
                dt.addRow(ar.toArray());
            }

        } catch (Exception e) {
            System.out.println("Error From loadTable Method: " + e);
        }

    }

    void clearTable() {
        DefaultTableModel dt = (DefaultTableModel) tblbank.getModel();
        int rows = tblbank.getRowCount();
        for (int x = 0; x < rows; x++) {
            dt.removeRow(0);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Branches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Branches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Branches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Branches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Branches().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badd;
    private javax.swing.JButton bdelete;
    private javax.swing.JButton bupdate;
    private javax.swing.JButton bview;
    private com.imal.practicalJcombSearchable.JComboPractical cmbbank;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbankcode;
    private javax.swing.JTable tblbank;
    private javax.swing.JTextField tbranch;
    private javax.swing.JTextField tbrcode;
    // End of variables declaration//GEN-END:variables
}
