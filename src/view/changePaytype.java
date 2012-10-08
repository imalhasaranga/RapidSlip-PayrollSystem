/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DB;
import model.PayRoll;

/**
 *
 * @author Imal
 */
public final class changePaytype extends javax.swing.JFrame {

    int Year;
    int Month;
    String Branch;

    public changePaytype() {
        initComponents();
    }

    public changePaytype(int year, int month, String barnch) {
        this();
        this.Year = year;
        this.Month = month;
        this.Branch = barnch;
        LoadTable(year, month, barnch);
    }

    public void LoadTable(int year, int month, String branch) {

        try {
            DefaultTableModel df = (DefaultTableModel) paytypetable.getModel();
            df.setRowCount(0);
            ResultSet rs = (new PayRoll()).getPayroll(year, month, branch);
            while (rs.next()) {
                ArrayList ar = new ArrayList<>();
                ar.add(rs.getString("PayrollNum"));
                ar.add(rs.getString("payroll.EmpNumber"));
                ar.add(rs.getString("FMname") + " " + rs.getString("Lname"));
                ar.add(rs.getString("payroll.PayType"));


                df.addRow(ar.toArray());
            }


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        paytypetable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cmpaytype = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change Pay Type for Selected Month");
        setResizable(false);

        paytypetable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        paytypetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PayRolID", "EMP No", "Name", "PayType"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Short.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paytypetable.setRowHeight(21);
        paytypetable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(paytypetable);
        paytypetable.getColumnModel().getColumn(0).setMinWidth(60);
        paytypetable.getColumnModel().getColumn(0).setPreferredWidth(60);
        paytypetable.getColumnModel().getColumn(0).setMaxWidth(60);
        paytypetable.getColumnModel().getColumn(1).setMinWidth(60);
        paytypetable.getColumnModel().getColumn(1).setPreferredWidth(60);
        paytypetable.getColumnModel().getColumn(1).setMaxWidth(60);
        paytypetable.getColumnModel().getColumn(3).setPreferredWidth(70);
        paytypetable.getColumnModel().getColumn(3).setMaxWidth(70);

        jLabel1.setText("Change PayType");

        cmpaytype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BANK", "CASH" }));

        jButton1.setText("Change Selected");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("APPLY CHANGES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(cmpaytype, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmpaytype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-488)/2, (screenSize.height-555)/2, 488, 555);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (paytypetable.getSelectedRow() != -1) {
            paytypetable.setValueAt(cmpaytype.getSelectedItem().toString(), paytypetable.getSelectedRow(), 3);
        } else {
            JOptionPane.showMessageDialog(null, "Please a row in the Table anc click this button", "ERROR", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (paytypetable.getRowCount() > 0) {

            int i0 = JOptionPane.showConfirmDialog(null, "Are you Sure You want to Change Details", "PAYTYPECHANGE", JOptionPane.YES_NO_OPTION);
            if (i0 == JOptionPane.YES_OPTION) {
                for (int i = 0; i < paytypetable.getRowCount(); i++) {
                    try {
                        String st = paytypetable.getValueAt(i, 3).toString();
                        String id = paytypetable.getValueAt(i, 0).toString();
                        DB.setData("UPDATE payroll SET  PayType = '" + st + "' where payRollNum = '" + id + "' ");
                    } catch (Exception ex) {
                        System.out.println("error in paytype" + ex);
                    }
                }
                LoadTable(Year, Month, Branch);
                JOptionPane.showMessageDialog(null, "Sucessfully Updated!!!");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(changePaytype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(changePaytype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(changePaytype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(changePaytype.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new changePaytype().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmpaytype;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable paytypetable;
    // End of variables declaration//GEN-END:variables
}
