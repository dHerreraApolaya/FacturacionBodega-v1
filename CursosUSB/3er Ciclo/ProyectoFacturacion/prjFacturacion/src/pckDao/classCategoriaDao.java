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
import pckEntidad.Categoria;

/**
 *
 * @author Alumno
 */
public class classCategoriaDao {
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
    
    public int codCategoria(){
        ArrayList<Categoria> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from categoria");
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
            lista.add(new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("CodCategoria: "+e.getMessage());
        }return lista.size();
    }
    
    public ArrayList listarCategoria(){
        ArrayList<Categoria> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from categoria where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Cliente: "+e.getMessage());
        }return  lista;
    }
    
    public Categoria buscarCategoria(int Cod){
        Categoria ocategoria=null;
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from categoria where idCategoria=?");
            pst.setInt(1, Cod);
            ResultSet rs=pst.executeQuery();
            if (rs.next()) {
                ocategoria=new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Buscar Categoria: "+e.getMessage());
        }return ocategoria;
    }
    
    public String agregarCategoria(Categoria unaCategoria){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Insert Categoria  values(?,?,?)");
           pst.setString(1, unaCategoria.getNombreCategoria());
            pst.setString(2, unaCategoria.getAbreviatura());
            pst.setString(3, unaCategoria.getCancelado());
            pst.executeUpdate();
            men="CATEGORIA AGREGADO";
        } catch (Exception e) {
            men="agregar Categoria: "+e.getMessage();
        }return men;
    }
    
    public String actualizarCategoria(Categoria unaCategoria){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("update categoria set nombrecategoria=?,abreviatura=? where idcategoria=?");
            pst.setInt(3, unaCategoria.getIdCategoria());
            pst.setString(1, unaCategoria.getNombreCategoria());
            pst.setString(2,unaCategoria.getAbreviatura());
            pst.executeUpdate();
            men="CATEGORIA ACTUALIZADO";
        } catch (Exception e) {
            men="actualizar Categoria: "+e.getMessage();
        }return men;
    }
   
    public String eliminarCategoria(int Cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update categoria set cancelado='Si' where idcategoria=?");
            pst.setInt(1, Cod);
            pst.executeUpdate();
            men="CATEGORIA ELIMINADO";
        } catch (Exception e) {
            men="eliminar Categoria: "+e.getMessage();
        }return men;
    }
    
}
