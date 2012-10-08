/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Gihan
 */
public class Datecontrol {
    String selecteddate;
    String currentdate;
    
    public String getSelectedDate(JDateChooser dt){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        selecteddate = df.format(dt.getDate());
        
        return selecteddate;
    }
    
    public String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        currentdate = df.format(cal.getTime());

        return currentdate;
    }
    
    public static String getCurrentDateString(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       

        return  df.format(cal.getTime());
    }
    
}
