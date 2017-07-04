/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pckConexion.classConexionFacturacion;
import pckEntidad.DetalleVenta;
import pckEntidad.DetalleVentaConsulta;
/**
 *
 * @author HOME
 */
public class classDetalleVentaDao {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
    
    /*
    select * from detalleVenta dv 
inner join producto p on p.idProducto=dv.nombreProducto
where dv.idVenta=?

select * from detallePago dp 
inner join producto p on p.idProducto=dp.nombreProducto
where dp.idVenta=?

    */
    
    public ArrayList listarDetalleVenta(int codVenta){
        ArrayList<DetalleVentaConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("select dv.idVenta,dv.cantidad,p.nombreProducto,dv.precioUnidad from detalleVenta dv \n" +
"inner join producto p on p.idProducto=dv.nombreProducto\n" +
"where dv.idVenta=?");
            pst.setInt(1, codVenta);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new DetalleVentaConsulta(rs.getInt("idVenta"),rs.getInt("cantidad"),rs.getDouble("precioUnidad"),rs.getString("cancelado"),rs.getString("nombreProducto")));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("listar Venta: "+e.getMessage());
        }return  lista;
    }
    
    public String agregarDetalleVenta(DetalleVenta unDetalleVenta){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert detalleVenta values(?,?,?,?,?)");
            pst.setInt(1, unDetalleVenta.getIdVenta());
            pst.setInt(2, unDetalleVenta.getCantidad());
            pst.setInt(3,unDetalleVenta.getIdProducto());
            pst.setDouble(4,unDetalleVenta.getPrecioUnitario());
            pst.setString(5, unDetalleVenta.getCancelado());
            pst.executeUpdate();
            men="DETALLE VENTA AGREGADO";
        } catch (Exception e) {
           men=("agregarDetalleVenta: "+e.getMessage());
        }return men;
    }
    
    public String eliminarDetalleVenta(int cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("UPdate DetalleVenta cancelado='Si' where idVenta=?");
            pst.setInt(1, cod);
            pst.executeUpdate();
            men="DETALLE VENTA ELIMINADO";
        } catch (Exception e) {
            System.out.println("eliminarDetalleVenta: "+e.getMessage());
        }return  men;
    }
     public void guardarArchivo(int cant,String nombre,double precioUnitario,double Stotal){
            FileWriter archivo=null;
            PrintWriter molde=null;
         try {
                archivo=new FileWriter(System.getProperty("user.dir")+ "\\src\\Texto.txt",true);
                BufferedWriter bw=new BufferedWriter(archivo);
                molde=new PrintWriter(bw);
                molde.print(cant);
                molde.print("|"+nombre);
                molde.print("|"+precioUnitario);
                molde.print("|"+Stotal+"\r\n");
                molde.close();
                System.out.println("HECHO: ");
            } catch (Exception e) {
                System.out.println("Guardar Archivo: "+e.toString());
            }
        }
     
     public void leerArchivo(){
         
         FileReader archivo=null;
         BufferedReader br=null;
         
         try {
             archivo=new FileReader(System.getProperty("user.dir")+"\\src\\Texto.txt");
             br=new BufferedReader(archivo);
             
             String linea;
             
             while ((linea=br.readLine())!=null) {                 
                 System.out.println("LECTURA CORRECTA"+linea);
             } archivo.close();
         } catch (Exception e) {
             System.out.println("Error Lectura: "+e.getMessage());
         }
     }
}
