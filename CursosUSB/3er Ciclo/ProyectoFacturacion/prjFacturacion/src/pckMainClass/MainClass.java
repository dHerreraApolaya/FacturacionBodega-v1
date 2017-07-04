/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckMainClass;

import pckConexion.classConexionFacturacion;
import pckGui.frmInicioSesion;

/**
 *
 * @author Katherine
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//       classConexionFacturacion ocn=new classConexionFacturacion();
//        ocn.abriConexion();
       
        frmInicioSesion inicio=new frmInicioSesion();
        inicio.setLocationRelativeTo(null);
        inicio.setResizable(false);
        inicio.setVisible(true);

    }
    
}
