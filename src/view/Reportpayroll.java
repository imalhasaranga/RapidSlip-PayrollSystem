/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Gihan
 */
public class Reportpayroll extends javax.swing.JPanel {

    /**
     * Creates new form Reportpayroll
     */
    public Reportpayroll() {
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

        jPanel5 = new javax.swing.JPanel();
        cmbtype = new com.imal.practicalJcombSearchable.JComboPractical();
        cmbtype1 = new com.imal.practicalJcombSearchable.JComboPractical();
        cmbtype2 = new com.imal.practicalJcombSearchable.JComboPractical();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpayroll = new javax.swing.JTable();
        bdetails = new javax.swing.JButton();
        bviewpayroll = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel5.setBackground(new java.awt.Color(237, 237, 237));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        cmbtype.setForeground(new java.awt.Color(102, 0, 0));
        cmbtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All Employees", "Site Wise", "Designation Wise" }));
        cmbtype.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        cmbtype1.setForeground(new java.awt.Color(102, 0, 0));
        cmbtype1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        cmbtype2.setForeground(new java.awt.Color(102, 0, 0));
        cmbtype2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbtype, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbtype1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbtype2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbtype1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cmbtype, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbtype2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblpayroll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblpayroll.setForeground(new java.awt.Color(51, 51, 51));
        tblpayroll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Emp. No", "ETF. No", "Employee Name", "Designation", "Type", "Basic Sal.", "Gvt. Allow.", "Bud. Allow.", "Tot. Basic", "Tot. Allow.", "Gross Salary"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblpayroll.setColumnSelectionAllowed(true);
        tblpayroll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblpayroll.setRowHeight(18);
        tblpayroll.setSelectionBackground(new java.awt.Color(102, 102, 102));
        tblpayroll.setSelectionForeground(new java.awt.Color(255, 255, 204));
        tblpayroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpayrollMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpayroll);
        tblpayroll.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblpayroll.getColumnModel().getColumn(0).setMinWidth(60);
        tblpayroll.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblpayroll.getColumnModel().getColumn(0).setMaxWidth(60);
        tblpayroll.getColumnModel().getColumn(1).setMinWidth(60);
        tblpayroll.getColumnModel().getColumn(1).setPreferredWidth(60);
        tblpayroll.getColumnModel().getColumn(1).setMaxWidth(60);
        tblpayroll.getColumnModel().getColumn(3).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(3).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(3).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(4).setMinWidth(60);
        tblpayroll.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblpayroll.getColumnModel().getColumn(4).setMaxWidth(60);
        tblpayroll.getColumnModel().getColumn(5).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(5).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(5).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(6).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(6).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(6).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(7).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(7).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(7).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(8).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(8).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(8).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(9).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(9).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(9).setMaxWidth(80);
        tblpayroll.getColumnModel().getColumn(10).setMinWidth(80);
        tblpayroll.getColumnModel().getColumn(10).setPreferredWidth(80);
        tblpayroll.getColumnModel().getColumn(10).setMaxWidth(80);

        bdetails.setText("Print Table");
        bdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdetailsActionPerformed(evt);
            }
        });

        bviewpayroll.setText("View Salaries");
        bviewpayroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bviewpayrollActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bgtop_rep_payroll.jpg"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 38));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bviewpayroll, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bviewpayroll, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bdetails, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(36, 36, 36))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bviewpayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bviewpayrollActionPerformed



 }//GEN-LAST:event_bviewpayrollActionPerformed

    private void tblpayrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpayrollMouseClicked

   }//GEN-LAST:event_tblpayrollMouseClicked

    private void bdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdetailsActionPerformed
        //Detailed Jasper Report should come to here

   }//GEN-LAST:event_bdetailsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bdetails;
    private javax.swing.JButton bviewpayroll;
    private com.imal.practicalJcombSearchable.JComboPractical cmbtype;
    private com.imal.practicalJcombSearchable.JComboPractical cmbtype1;
    private com.imal.practicalJcombSearchable.JComboPractical cmbtype2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblpayroll;
    // End of variables declaration//GEN-END:variables
}