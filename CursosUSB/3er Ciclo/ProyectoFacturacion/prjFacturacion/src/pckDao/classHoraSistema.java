/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckDao;

import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author HOME
 */
public class classHoraSistema extends Thread{
    
    JLabel lblHora;

    public classHoraSistema(JLabel lblHora) {
        this.lblHora = lblHora;
    }
    
    @Override
    public void run(){
        while (true) {            
             Date horasistema=new Date();
            
            SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
            lblHora.setText(sdf.format(horasistema));
            
            try {
                sleep(1000);
            } catch (Exception e) {
                System.out.println("Error Rin: "+e.getMessage());
            }
            
        }
    }
    
}
