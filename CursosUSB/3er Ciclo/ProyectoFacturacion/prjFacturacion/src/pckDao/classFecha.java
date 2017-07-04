/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author HOME
 */
public class classFecha {
    
    public String fechaActual(){
         Date fecha=new Date();
        SimpleDateFormat formatFecha=new SimpleDateFormat("dd/MM/YYYY");
        return formatFecha.format(fecha);
    }
    
    public String ObtenerAmPm(){
        Calendar  mical=new GregorianCalendar();
        String ampm="";
         
        
        ampm=mical.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
        
        return ampm;
    }
    
    
}
