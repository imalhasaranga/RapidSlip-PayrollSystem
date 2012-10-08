/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kdaw;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import view.Loading;

import view.MainUI1;

/**
 *
 * @author Gihan
 */
public class Kdaw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel()); 
            Loading loading = new Loading();
            loading.setVisible(true); 
            loading.LoadValues(); 
        } catch (Exception e) {
            
        }


    }
}
