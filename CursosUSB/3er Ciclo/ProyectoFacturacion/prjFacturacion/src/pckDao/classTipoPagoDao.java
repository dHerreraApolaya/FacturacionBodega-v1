/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pckConexion.classConexionFacturacion;
import pckEntidad.TipoPago;

/**
 *
 * @author HOME
 */
public class classTipoPagoDao {
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
    
    void mensaje(String men){
        JOptionPane.showMessageDialog( null,men,"Aviso",1);
    }
    
    public int codigoTipoPago(){
        ArrayList<TipoPago> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from opcionPago");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new TipoPago(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }rs.close();pst.close();
        } catch (Exception e) {
            mensaje("Listar TipoPago: "+e.toString());
        }return lista.size();
    }
    
    public ArrayList listarTipoPago(){
        ArrayList<TipoPago> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from opcionPago where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new TipoPago(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }rs.close();pst.close();
        } catch (Exception e) {
            mensaje("Listar TipoPago: "+e.toString());
        }return lista;
    }
    
    public TipoPago buscarTipoPago(int codigo){
        TipoPago otipoPago=null;
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from opcionPago where idOpcionPago=(?) ");
            pst.setInt(1, codigo);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                otipoPago=new TipoPago(rs.getInt(1),rs.getString(2), rs.getString(3));
            }rs.close();pst.close();
        } catch (Exception e) {
            mensaje("Buscar Tipo Pago: "+e.toString());
        }return otipoPago;
    }
    
    public String agregarTipoPago(TipoPago unTipoPago){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert opcionPago values(?,?) ");
         //   pst.setInt(1, unTipoPago.getIdOpcionPago());
            pst.setString(1, unTipoPago.getNombreOpcionPago());
            pst.setString(2, unTipoPago.getCancelado());
            pst.executeUpdate();
            men="Tipo de Pago Agregado";
        } catch (Exception e) {
            men="Agregar Tipo de Pago: "+e.getMessage();
        }return men;
    }
    
    public String actualizarTipoPago(TipoPago unTipoPago){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("update opcionPago set nombreOpcionPago=? where idOpcionPago=(?)");
            pst.setString(1, unTipoPago.getNombreOpcionPago());
            pst.setInt(2,unTipoPago.getIdOpcionPago());
            pst.executeUpdate();
            men="Tipo de Pago Actualizado";
        } catch (Exception e) {
            men="Actualizar Tipo de Pago: "+e.getMessage();
        }return men;
    }
    
    public String eliminadoLogicoTipoPago(int codigo){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("update opcionPago set cancelado='Si' where idOpcionPago=(?)");
            pst.setInt(1, codigo);
            pst.executeUpdate();
            men="Tipo de Pago Eliminado";
        } catch (Exception e) {
            men="Eliminado Logico Tipo de Pago: "+e.getMessage();
        }return men;
    }
    
}
