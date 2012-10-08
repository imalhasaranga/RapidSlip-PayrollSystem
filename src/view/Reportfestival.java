/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.Printfestival;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Employee;

/**
 *
 * @author Gihan
 */
public class Reportfestival extends javax.swing.JFrame {

    Employee employee;
    Printfestival printfestival;
    int totemployees;
    double totamount;

    /**
     * Creates new form Reportfestival
     */
    public Reportfestival() {
        initComponents();
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
        ycyear = new com.toedter.calendar.JYearChooser();
        mcmonth = new com.toedter.calendar.JMonthChooser();
        bview = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblemployees = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        ltotemployees = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ltotamount = new javax.swing.JLabel();
        bprint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Festival Advance Deduction Report");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(225, 225, 225));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Year-Month");

        bview.setText("View F.Deduct Employees");
        bview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bviewActionPerformed(evt);
            }
        });

        tblemployees.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblemployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emp.No", "Name", "Site", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
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
        tblemployees.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblemployees.setRowHeight(21);
        tblemployees.setSelectionBackground(new java.awt.Color(0, 51, 255));
        tblemployees.setSelectionForeground(new java.awt.Color(255, 255, 204));
        tblemployees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblemployeesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblemployees);
        tblemployees.getColumnModel().getColumn(0).setMinWidth(60);
        tblemployees.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblemployees.getColumnModel().getColumn(0).setMaxWidth(60);
        tblemployees.getColumnModel().getColumn(2).setMinWidth(160);
        tblemployees.getColumnModel().getColumn(2).setPreferredWidth(160);
        tblemployees.getColumnModel().getColumn(2).setMaxWidth(160);
        tblemployees.getColumnModel().getColumn(3).setMinWidth(80);
        tblemployees.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblemployees.getColumnModel().getColumn(3).setMaxWidth(80);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Total Employees:");

        ltotemployees.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ltotemployees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltotemployees.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Total Amount:");

        ltotamount.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ltotamount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ltotamount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bprint.setText("Print");
        bprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bprintActionPerformed(evt);
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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ycyear, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mcmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bview))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ltotamount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ltotemployees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bprint, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mcmonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ycyear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bview, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ltotemployees, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ltotamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bprint, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-785)/2, (screenSize.height-600)/2, 785, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void bviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bviewActionPerformed
        employee = new Employee();
        loadTable(employee.getFestivalAdvance(ycyear.getYear(), mcmonth.getMonth() + 1));

    }//GEN-LAST:event_bviewActionPerformed

    private void tblemployeesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblemployeesMouseClicked
   }//GEN-LAST:event_tblemployeesMouseClicked

    private void bprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bprintActionPerformed
        if (tblemployees.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(null, "Sure to Print the Table Data?", "Question", 0, 3) == 0) {
                printfestival = new Printfestival();
                printfestival.setYear(ycyear.getYear());
                printfestival.setMonth(mcmonth.getMonth() + 1);
                printfestival.setTotrecords(totemployees);
                printfestival.setTotamount(totamount);
                printfestival.printThisInvoice(tblemployees);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Table is Empty, First Get Data to Print!", "Warning!", 2);
        }
    }//GEN-LAST:event_bprintActionPerformed

    void loadTable(ResultSet rs) {
        clearTable(tblemployees);
        DefaultTableModel dt = (DefaultTableModel) tblemployees.getModel();

        totemployees = 0;
        totamount = 0.0;

        try {
            while (rs.next()) {
                ArrayList vec = new ArrayList();
                totemployees++;
                totamount += rs.getDouble("amount");

                vec.add(rs.getInt("EmpNumber"));
                vec.add(rs.getString("FMname") + " " + rs.getString("Lname"));
                vec.add(rs.getString("Site_location"));
                vec.add(rs.getDouble("amount"));
                dt.addRow(vec.toArray());
            }
            ltotemployees.setText("" + totemployees);
            ltotamount.setText("" + totamount);

        } catch (Exception e) {
            System.out.println("Error From loadTable Method: " + e);
        }

    }

    void clearTable(JTable tbl) {
        DefaultTableModel dt = (DefaultTableModel) tbl.getModel();
        int rows = tbl.getRowCount();
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
            java.util.logging.Logger.getLogger(Reportfestival.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportfestival.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportfestival.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportfestival.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Reportfestival().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bprint;
    private javax.swing.JButton bview;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ltotamount;
    private javax.swing.JLabel ltotemployees;
    private com.toedter.calendar.JMonthChooser mcmonth;
    private javax.swing.JTable tblemployees;
    private com.toedter.calendar.JYearChooser ycyear;
    // End of variables declaration//GEN-END:variables
}