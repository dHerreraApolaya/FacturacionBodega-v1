/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckDao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import pckConexion.classConexionFacturacion;
import pckEntidad.Producto;
import pckEntidad.ProductoConsulta;

/**
 *
 * @author Alumno
 */
public class classProductoDao {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
        
    public int tamaño(){
            ArrayList<ProductoConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select prod.idProducto,prod.nombreProducto,unidMed.nombreMedida,prod.precioUnitario,cat.nombreCategoria,prod.cancelado from producto prod inner join categoria cat on cat.idCategoria=prod.idCategoria inner join unidadMedida unidMed on unidMed.idUnidadMedida=prod.idUnidadMedida ");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new ProductoConsulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Error Listar Productos : "+e.getMessage());
        }return lista.size();
    }
//  
//    public ArrayList listarProducto(){
//          ArrayList<ProductoConsulta> lista=new ArrayList<>();
//        try {
//            PreparedStatement pst=cn.prepareStatement("Select prod.idProducto,prod.nombreProducto,unidMed.nombreMedida,prod.precioUnitario,cat.nombreCategoria,prod.cancelado from producto prod inner join categoria cat on cat.idCategoria=prod.idCategoria inner join unidadMedida unidMed on unidMed.idUnidadMedida=prod.idUnidadMedida where prod.cancelado='No'");
//            ResultSet rs=pst.executeQuery();
//            while (rs.next()) {                
//                lista.add(new ProductoConsulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
//            }rs.close();pst.close();
//        } catch (Exception e) {
//            System.out.println("Error Listar Productos : "+e.getMessage());
//        }return lista;
//    }
    
    public ArrayList listarProductosporCategoria(int codCategoria){
        ArrayList<ProductoConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select prod.idProducto,prod.nombreProducto,unidMed.nombreMedida,prod.precioUnitario,cat.nombreCategoria,prod.cancelado from producto prod inner join categoria cat on cat.idCategoria=prod.idCategoria inner join unidadMedida unidMed on unidMed.idUnidadMedida=prod.idUnidadMedida where prod.idCategoria=(?) and prod.cancelado='No'");
            pst.setInt(1, codCategoria);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new ProductoConsulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Error Listar Productos por Categoria: "+e.getMessage());
        }return lista;
    }
    
    
    public ArrayList listarProductosporUnidadMedida(int codUnidadMedida){
        ArrayList<ProductoConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select prod.idProducto,prod.nombreProducto,unidMed.nombreMedida,prod.precioUnitario,cat.nombreCategoria,prod.cancelado from producto prod inner join categoria cat on cat.idCategoria=prod.idCategoria inner join unidadMedida unidMed on unidMed.idUnidadMedida=prod.idUnidadMedida where prod.idUnidadMedida=(?) and prod.cancelado='No'");
            pst.setInt(1, codUnidadMedida);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new ProductoConsulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Error Listar Productos por Unidad Medida: "+e.getMessage());
        }return lista;
    }
    
    public ArrayList listarProductosDetallado(){
        ArrayList<ProductoConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select prod.idProducto,prod.nombreProducto,unidMed.nombreMedida,prod.precioUnitario,cat.nombreCategoria,prod.cancelado from producto prod inner join categoria cat on cat.idCategoria=prod.idCategoria inner join unidadMedida unidMed on unidMed.idUnidadMedida=prod.idUnidadMedida where prod.cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new ProductoConsulta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6)));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Error Listar Productos Detallado: "+e.getMessage());
        }return lista;
    }
    
    public ProductoConsulta buscarProducto(int cod){
        ProductoConsulta oproducto=null;
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from producto where idProducto=?");
            pst.setInt(1, cod);
            ResultSet rs=pst.executeQuery();
            if (rs.next()) {
                oproducto=new ProductoConsulta(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getString(6));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Buscar Producto: "+e.getMessage());
        }return  oproducto;
    }
    
     public Producto buscarunProducto(int cod){
        Producto oproducto=new Producto();
        try {
            PreparedStatement pst=cn.prepareStatement("select * from producto where idProducto = ?");
            pst.setInt(1, cod);
            ResultSet rs=pst.executeQuery();
            if (rs.next()) {
                 Blob blob=rs.getBlob("imagenProd");
             //   System.out.println("BLOB: "+blob);
               // Blob blob=rs.getBlob("imagenProd");
                byte[] data=blob.getBytes(1, (int)blob.length());
                //System.out.println("DATA: "+data);
                BufferedImage img=null;
                
                try {
                    img=ImageIO.read(new ByteArrayInputStream(data));
                 //   System.out.println("ENTRO TRY: "+img);
                    } catch (Exception e) {
                        System.out.println("Error Cargar Imagen");
                    }
                oproducto.setIdProducto(rs.getInt("idProducto"));
                oproducto.setNombreProducto(rs.getString("nombreProducto"));
                oproducto.setIdUnidadMedidad(rs.getInt("idUnidadMedida"));
                oproducto.setPrecioUnitario(rs.getDouble("precioUnitario"));
                oproducto.setIdCategoria(rs.getInt("idCategoria"));
                oproducto.setCancelado("cancelado");
                oproducto.setImagenProd(img);
              }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Buscar Un Producto: "+e.getMessage());
        }return  oproducto;
    }
    
//    public String agregarProducto(Producto unproducto){
//        String men="";
//        try {
//            PreparedStatement pst=cn.prepareStatement("insert producto values(?,?,?,?,?,?)");
//            pst.setInt(1, unproducto.getIdProducto());
//            pst.setString(2,unproducto.getNombreProducto());
//            pst.setInt(3,unproducto.getIdUnidadMedidad());
//            pst.setDouble(4,unproducto.getPrecioUnitario());
//            pst.setInt(5,unproducto.getIdCategoria());
//            pst.setString(6, unproducto.getCancelado());
//            pst.executeUpdate();
//            men="PRODUCTO AGREGADO";
//        } catch (Exception e) {
//            men="Agregar Producto: "+e.getMessage();
//        }return men;
//    }
    
//    public String actualizarProducto(Producto unproducto){
//        String men="";
//        try {
//            PreparedStatement pst=cn.prepareStatement("update producto set nombreproducto=?,idunidadmedida=?,preciounitario=?,idCategoria=? where idProducto=?");
//            pst.setInt(5, unproducto.getIdCategoria());
//            pst.setString(1, unproducto.getNombreProducto());
//            pst.setInt(2, unproducto.getIdUnidadMedidad());
//            pst.setDouble(3, unproducto.getPrecioUnitario());
//            pst.setInt(4,unproducto.getIdCategoria());
//            pst.executeUpdate();
//            men="PRODUCTO ACTUALIZADO";
//        } catch (Exception e) {
//            men="actualizar Producto: "+e.getMessage();
//        }return men;
//        
//        
//    }
    
    public String eliminarProducto(int cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("Update producto set cancelado='Si'  where idproducto=?");
            pst.setInt(1, cod);
            pst.executeUpdate();
            men="PRODUCTO ELIMINADO";
        } catch (Exception e) {
            men="eliminar Producto: "+e.getMessage();
        }return men;
    }
    
    public String agregarNuevoProducto(int idProd,String nombreProd,int idUnidadMed,double precioUnit,int idCategoria,String cancelado,String ruta){
        String men="";
        FileInputStream fis=null;
        
        try {
            File archivo=new File(ruta);
            System.out.println("ARCHIVO: "+ruta);
            
            fis=new FileInputStream(archivo);
            System.out.println("ARCHIVO INPUT STREAM: "+fis);
            
            PreparedStatement pst=cn.prepareStatement("insert into producto (nombreProducto,idUnidadMedida,precioUnitario,idCategoria,imagenProd,cancelado) values (?,?,?,?,?,?)");
           
            pst.setString(1,nombreProd);
            pst.setInt(2,idUnidadMed);
            pst.setDouble(3,precioUnit);
            pst.setInt(4,idCategoria);
            pst.setString(6, cancelado);
            pst.setBinaryStream(5, fis,archivo.length());
            
            System.out.println("PST:SETBINARY STREAM: "+archivo.length());
            
            pst.executeUpdate();
            
            men="Producto Agregado";
            pst.close();fis.close();
        } catch (Exception e) {
            men="Agregar NuevoProducto: "+e.getMessage();
        }return men;
    }
    
      public String actualizarNuevoProducto(int idProd,String nombreProd,int idUnidadMed,double precioUnit,int idCategoria,String cancelado,String ruta){
        String men="";
        FileInputStream fis=null;
        
        try {
            File archivo=new File(ruta);
            fis=new FileInputStream(archivo);
            
            PreparedStatement pst=cn.prepareStatement("update producto set nombreproducto=?,idunidadmedida=?,preciounitario=?,idCategoria=?,imagenProd=? where idProducto=?");
            pst.setInt(6, idProd);
            pst.setString(1,nombreProd);
            pst.setInt(2,idUnidadMed);
            pst.setDouble(3,precioUnit);
            pst.setInt(4,idCategoria);
            //pst.setString(6, cancelado);
            pst.setBinaryStream(5, fis,(int)archivo.length());
            pst.executeQuery();
            
            men="Producto Actualizado";
            pst.close();fis.close();
        } catch (Exception e) {
            men="Actualizar NuevoProducto: "+e.getMessage();
        }return men;
    }
    
}
