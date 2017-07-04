/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckDao;
import java.sql.*;
import java.util.ArrayList;
import pckConexion.classConexionFacturacion;
import pckEntidad.Usuario;
/**
 *
 * @author Alumno
 */
public class classUsuario {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
    public ArrayList listarUser(){
        ArrayList<Usuario> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select idLogin,nombreUser,apellidoUser,contraseña,estado,nivel,cancelado from usuario where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new Usuario(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Usuario: "+e.getMessage());
        }return lista;
    }
    
    public String agregarUser(Usuario unUsuario){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert usuario (idLogin,nombreUser,apellidoUser,contraseña,estado,nivel,cancelado) values (?,?,?,?,?,?,?)");
            pst.setString(1, unUsuario.getCodUser());
            pst.setString(2, unUsuario.getNombre());
            pst.setString(3, unUsuario.getApellido());
            pst.setString(4, unUsuario.getContraseña());
            pst.setString(5, unUsuario.getEstado());
            pst.setString(6, unUsuario.getNivel());
            pst.setString(7, unUsuario.getCancelado());
         //   pst.setByte(7, unUsuario.getImagen());
            // falta imagen
           pst.executeUpdate();
           men="USUARIO AGREGADO";
        } catch (Exception e) {
            men="Agregar Usuario: "+e.getMessage();
        }return men;
    }
    
    public String actualizarUser(Usuario unUsuario){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("update usuario set nombreUser=?,apellidoUser=?,contraseña=?,estado=?,nivel=? where idLogin=?");
            pst.setString(6, unUsuario.getCodUser());
            pst.setString(1, unUsuario.getNombre());
            pst.setString(2, unUsuario.getApellido());
            pst.setString(3, unUsuario.getContraseña());
            pst.setString(4, unUsuario.getEstado());
            pst.setString(5, unUsuario.getNivel());
            pst.executeUpdate();
            men="USUARIO ACTUALIZADO";
        } catch (Exception e) {
            men="Actualizar User: "+e.getMessage();
        }return men;
    }
    
    public Usuario buscarUser(String cod){
        Usuario ousuario=null;
        try {
            PreparedStatement pst=cn.prepareStatement("select idLogin,nombreUser,apellidoUser,contraseña,estado,nivel,cancelado from usuario where idLogin=?");
            pst.setString(1, cod);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                ousuario=new Usuario(rs.getString(1),
                        rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                        rs.getString(6),rs.getString(7));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("buscar User: "+e.getMessage());
        }return  ousuario;
    }
   
    
    public String eliminarUser(String cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update usuario set cancelado='Si' where idLogin=(?)");
            pst.setString(1, cod);
            pst.executeUpdate();
            men="USUARIO ELIMINADO";
        } catch (Exception e) {
            men="eliminar Usuario: "+e.getMessage();
        }return men;
        
    }
}
