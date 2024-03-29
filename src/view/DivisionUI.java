/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Division;

/**
 *
 * @author Gihan
 */
public final class DivisionUI extends javax.swing.JFrame {
    int updateid;
    
    
    /**
     * Creates new form DivisionUI
     */
    public DivisionUI() {
        initComponents();
        bedit.setEnabled(false);
        loadTable(Division.getDivisions());
        //loadTable(null);
        
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
        tdivision = new javax.swing.JTextField();
        badd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldivision = new javax.swing.JTable();
        bedit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Division Window");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(225, 225, 225));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Division");

        tdivision.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tdivision.setForeground(new java.awt.Color(0, 153, 255));

        badd.setText("Add+");
        badd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baddActionPerformed(evt);
            }
        });

        tbldivision.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        tbldivision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "No", "Designation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbldivision.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbldivision.setRowHeight(21);
        tbldivision.setSelectionBackground(new java.awt.Color(0, 51, 255));
        tbldivision.setSelectionForeground(new java.awt.Color(255, 255, 204));
        tbldivision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbldivisionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbldivision);
        tbldivision.getColumnModel().getColumn(0).setMinWidth(50);
        tbldivision.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbldivision.getColumnModel().getColumn(0).setMaxWidth(50);

        bedit.setText("Edit");
        bedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tdivision))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(badd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tdivision, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(badd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bedit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-382)/2, (screenSize.height-525)/2, 382, 525);
    }// </editor-fold>//GEN-END:initComponents

    private void baddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baddActionPerformed
        if (!tdivision.getText().trim().equals("")) {
            Division division = new Division();
            division.setDivision(tdivision.getText());
            if (division.addDivision()) {
                JOptionPane.showMessageDialog(null, "Done! Added Successfully!", "Done!", 1);
                loadTable(Division.getDivisions());
                clearAll();
            } else {
                JOptionPane.showMessageDialog(null, "Error While Adding Division!", "Error!", 0);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Type the Division to Add!", "Warning!", 2);
        }
    }//GEN-LAST:event_baddActionPerformed

    private void tbldivisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbldivisionMouseClicked
        badd.setEnabled(false);
        bedit.setEnabled(true);
        

        try {
            updateid = Integer.parseInt(tbldivision.getValueAt(tbldivision.getSelectedRow(), 0).toString());
            tdivision.setText(tbldivision.getValueAt(tbldivision.getSelectedRow(), 1).toString());
        } catch (Exception e) {
            System.out.println("No Record Selected! " + e);
        }

        

    }//GEN-LAST:event_tbldivisionMouseClicked

    private void beditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beditActionPerformed
        if (updateid != 0) {
            if (!tdivision.getText().trim().equals("")) {
                Division division = new Division();
                division.setDivision(tdivision.getText());
                if (division.updateDivision(updateid)) {
                    JOptionPane.showMessageDialog(null, "Done! Updated Successfully!", "Done!", 1);
                    loadTable(Division.getDivisions());
                    clearAll();
                } else {
                    JOptionPane.showMessageDialog(null, "Error While Updating Division!", "Error!", 0);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Type the Division to Update!", "Warning!", 2);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Select an Division From Table!", "Warning!", 2);
        }
    }//GEN-LAST:event_beditActionPerformed
    
    void loadTable(ResultSet rs){
        
        DefaultTableModel dt = (DefaultTableModel) tbldivision.getModel();
       dt.setRowCount(0);
        try {
            while(rs.next()){
                ArrayList vec = new ArrayList();
                vec.add(rs.getInt("Div_id"));
                vec.add(rs.getString("division_name"));
                
                dt.addRow(vec.toArray()); 
            }
                    
        } catch (Exception e) {
            System.out.println("Error From loadTable Method: " + e);
        }
        
    }
    
    
    
    void clearAll(){
        tdivision.setText("");
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
            java.util.logging.Logger.getLogger(DivisionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DivisionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DivisionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DivisionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new DivisionUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton badd;
    private javax.swing.JButton bedit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbldivision;
    private javax.swing.JTextField tdivision;
    // End of variables declaration//GEN-END:variables
}
