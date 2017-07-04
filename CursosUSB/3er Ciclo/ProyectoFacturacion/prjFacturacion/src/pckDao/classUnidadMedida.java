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
import pckConexion.classConexionFacturacion;
import pckEntidad.UnidadMedida;

/**
 *
 * @author Alumno
 */
public class classUnidadMedida {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    ArrayList<UnidadMedida> lista=new ArrayList<>();
        
    public int cantUnidMed(){
         ArrayList<UnidadMedida> cantlista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from unidadMedida");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                cantlista.add(new UnidadMedida(rs.getInt(1), rs.getString(2),rs.getString(3)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar cant medida: "+e.getMessage());
        } return cantlista.size();
    }
    
    public ArrayList listarUnidadMedida(){
        ArrayList<UnidadMedida> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select idUnidadMedida,nombreMedida,cancelado from unidadMedida where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                  lista.add(new UnidadMedida(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Medida: "+e.getMessage());
        }return lista;
    }
    
    public UnidadMedida buscarUnidadMedida(int cod){
        UnidadMedida ounidmedida=null;
        try {
            PreparedStatement pst=cn.prepareStatement("Select idUnidadMedida,nombreMedida,cancelado from unidadMedida where idunidadMedida=?");
            pst.setInt(1, cod);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                ounidmedida= new UnidadMedida(rs.getInt(1),rs.getString(2),rs.getString(3));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("buscar Unidad Medida: "+e.getMessage());
        }return ounidmedida;
    }
    
    public String agregarUnidadMedida(UnidadMedida unaUnidadMedida){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert unidadMedida values(?,?)");
         //   pst.setInt(1, unaUnidadMedida.getIdUnidadMedidad());
            pst.setString(1, unaUnidadMedida.getNombreMedida());
            pst.setString(2, unaUnidadMedida.getCancelado());
            pst.executeUpdate();
            men="UNIDAD DE MEDIDA AGREGADO";
        } catch (Exception e) {
            System.out.println("agregar Unidad Medida: "+e.getMessage());
        }return men;
    }
    
    public String actualizarUnidadMedida(UnidadMedida unaUnidadMedida){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("update unidadMedida set nombreMedida=? where idUnidadMedida=?");
            pst.setInt(2, unaUnidadMedida.getIdUnidadMedidad());
            pst.setString(1, unaUnidadMedida.getNombreMedida());
            pst.executeUpdate();
            men="UNIDAD DE MEDIDA ACTUALIZADO";
        } catch (Exception e) {
            men="actualizar Unidad Medida: "+e.getMessage();
        }return men;
    }
    
    public String eliminarUnidadMedida(int cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update UnidadMedida set cancelado='Si' where idunidadMedida=?");
            pst.setInt(1, cod);
            pst.executeUpdate();
            men="UNIDAD DE MEDIDA ELIMINADO";
        } catch (Exception e) {
            men="eliminar UnidadMedida: "+e.getMessage();
        } return men;
    }
    
    
    
}
