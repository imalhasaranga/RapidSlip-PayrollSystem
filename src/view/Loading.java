/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import model.DB;

/**
 *
 * @author Gihan
 */
public class Loading extends javax.swing.JFrame {

    MainUI1 mainui;

    /**
     * Creates new form Loading
     */
    public Loading() {
        initComponents();

        new Thread(new Runnable() {

            public void run() {

                for (int i = 0; i <= 100; i++) {
                    try {
                        pbloading.setValue(i);
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                try {
                    Login login = new Login(mainui);
                        login.setVisible(true);
                    ResultSet rs = DB.getData("select * from comp_parameters where compPara_id = '1' OR compPara_id = '2'");
                    rs.next();
                    if (rs.getString("lcode").equals("kdaweera123")) {
                        login.setVisible(true);
                        mainui = null;
                        dispose();
                    } else {
                        String dt[] = rs.getString("dt").split("-");

                        Calendar cal = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String today = df.format(cal.getTime());
                        String thisdt[] = today.split("-");
//                        System.out.println("TODAY1: " + thisdt[0]);
//                        System.out.println("TODAY1: " + thisdt[1]);
//                        System.out.println("TODAY1: " + thisdt[2]);

                        if (thisdt[0].equals("2012")) {
                            if (Integer.parseInt(thisdt[1]) != 10) {
                                JOptionPane.showMessageDialog(null, "Please Complete Final Payments! (101): Contact Deen Technologies", "Inform!", 0);
                                System.exit(EXIT_ON_CLOSE);
                            } else {
//                                if(Integer.parseInt(thisdt[2]) > 7){
//                                    JOptionPane.showMessageDialog(null, "Please Complete Final Payments! (101): Contact Deen Technologies", "Error!", 0);
//                                    System.exit(EXIT_ON_CLOSE);
//                                }else{
//                                    login.setVisible(true);
//                                    mainui = null;
//                                    dispose();
//                                }
//                                login.setVisible(true);
//                                mainui = null;
//                                dispose();
                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please Complete Final Payments! (101): Contact Deen Technologies", "Inform!", 0);
                            System.exit(EXIT_ON_CLOSE);
                        }



                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Configuration Error (100): Contact Deen Technologies", "Error!", 0);
                    dispose();
                }



            }
        }).start();

    }

    public void LoadValues() {
        mainui = new MainUI1();

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
        pbloading = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pbloading.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(pbloading, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 550, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loadingimage.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 370));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-574)/2, (screenSize.height-362)/2, 574, 362);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loading.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Loading().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar pbloading;
    // End of variables declaration//GEN-END:variables
}
