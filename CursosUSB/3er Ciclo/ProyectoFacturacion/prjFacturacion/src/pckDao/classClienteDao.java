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
import pckEntidad.Cliente;

/**
 *
 * @author Alumno
 */
public class classClienteDao {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
  
    public ArrayList listarCliente(){
        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("select * from cliente where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new Cliente(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Cliente: "+e.getMessage());
        }return  lista;
    }
       public ArrayList listarCliente(String cod){
        ArrayList<Cliente> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("select idcliente,nombreCliente,direccion,telefono,correo,cancelado from cliente where idCliente=?");
            pst.setString(1, cod);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new Cliente(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Cliente: "+e.getMessage());
        }return  lista;
    }
    public Cliente buscarCliente(String cod){
        Cliente ocliente=null;
        try {
            PreparedStatement pst=cn.prepareStatement("select * from cliente where idcliente=?");
            pst.setString(1, cod);
            ResultSet rs=pst.executeQuery();
            if (rs.next()) {
                ocliente=new Cliente(rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Buscar Cliente: "+e.getMessage());
        }return ocliente;
}
    
    public String agregarCliente(Cliente uncliente){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Insert cliente values (?,?,?,?,?,?)");
            pst.setString(1, uncliente.getIdCliente());
            pst.setString(2, uncliente.getNombreCliente());
            pst.setString(3,uncliente.getDireccion());
            pst.setString(4,uncliente.getTelefono());
            pst.setString(5,uncliente.getCorreo());
            pst.setString(6, uncliente.getCancelado());
            pst.executeUpdate();
            men="CLIENTE REGISTRADO";
        } catch (Exception e) {
            men="Agregar Cliente: "+e.getMessage();
        }return  men;
    }
    
    public String actualizarCliente(Cliente uncliente){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update cliente set nombreCliente=?,direccion=?,telefono=?,correo=? where idcliente=?");
            pst.setString(5, uncliente.getIdCliente());
            pst.setString(1, uncliente.getNombreCliente());
            pst.setString(2, uncliente.getDireccion());
            pst.setString(3, uncliente.getTelefono());
            pst.setString(4, uncliente.getCorreo());
            pst.executeUpdate();
            men="CLIENTE ACTUALIZADO";
        } catch (Exception e) {
            System.out.println("Actualizar Cliente: "+e.getMessage());
        }return  men;
    }
    
    public String eliminarCliente(String cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update cliente set cancelado='Si' where idcliente=?");
            pst.setString(1, cod);
            pst.executeUpdate();
            men="CLIENTE ELIMINADO";
        } catch (Exception e) {
            System.out.println("Eliminar Cliente: "+e.getMessage());
        }return men;
    }
    
}