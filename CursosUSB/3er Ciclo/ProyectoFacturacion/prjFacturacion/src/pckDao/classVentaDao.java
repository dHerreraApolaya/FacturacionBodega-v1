/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckDao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pckConexion.classConexionFacturacion;
import pckEntidad.Venta;

/**
 *
 * @author HOME
 */
public class classVentaDao {
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
        
    public int CantLista(){
       ArrayList<Venta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from Venta");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"),
                        rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Cant Venta: "+e.getMessage());
        }return  lista.size();
    }
    
    public ArrayList listarAño(){
        ArrayList lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("select year(fecha) Año from venta group by year(fecha)");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(rs.getInt("Año"));
            }pst.close();rs.close();
        } catch (Exception e) {
            System.out.println("Error Listar Año: "+e.getMessage());
        }return lista;
    }
    
    
    
    // USo de Procedimientos Almacenados
    // 
    public ArrayList ventasporFecha(int dia,int mes,int año){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
        CallableStatement cs=cn.prepareCall("{call ventasPorFecha (?,?,?)}");
        cs.setInt(3, año);
        cs.setInt(2, mes);
        cs.setInt(1,dia);
        ResultSet rs=cs.executeQuery();
            while (rs.next()) {                
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("VEntas por Fecha: "+e.getMessage());
        }return lista;
  }
//     public ArrayList ventasPorFechaMes(int mes){
//        ArrayList<Venta> lista=new ArrayList<>();
//        try {
//            CallableStatement cs=cn.prepareCall("{call ventasPorFechaMes ?}");
//            cs.setInt(1, mes);
//            ResultSet rs=cs.executeQuery();
//            while (rs.next()) {                
//                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
//            }rs.close();cs.close();
//        } catch (Exception e) {
//            System.out.println("VEntas ventasPorFechaMes: "+e.getMessage());
//        }return lista;
//    }
    
    
    
    
    
    
    
    
    
    // VEntas BIMESTRE
       public ArrayList ventasporMesAño(int mes,int año){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            //{call ventasporBi_Año(?,?)}
            CallableStatement cs=cn.prepareCall("{call ventasporMes_Año (?,?)}");
            cs.setInt(1, mes);cs.setInt(2, año);
            ResultSet rs=cs.executeQuery();
            while (rs.next()) {                
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("VEntas MES/AÑO: "+e.getMessage());
        }return lista;
    }
     
     
     
     
     
    public ArrayList ventasporBimestreAño(int bi,int año){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            CallableStatement cs=cn.prepareCall("{call ventasporBi_Año(?,?)}");
            cs.setInt(1, bi);cs.setInt(2, año);
            ResultSet rs=cs.executeQuery();
            while (rs.next()) {               
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("Ventas Bi/Año: "+e.getMessage());
        }return lista;
    }
    
    public ArrayList ventasporTrimestreAño(int tri,int año){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            CallableStatement cs=cn.prepareCall("{Call ventasporTrim_Año(?,?)}");
            cs.setInt(1, tri);cs.setInt(2, año);
            ResultSet rs=cs.executeQuery();
            while (rs.next()) { 
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("Ventas Tri/Año: "+e.getMessage());
        }return lista;
    }
    
    public ArrayList ventasporSemestreAño(int sem,int año){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            CallableStatement cs=cn.prepareCall("{call ventasporSem_Año(?,?)}");
            cs.setInt(2, año);cs.setInt(1, sem);
            ResultSet rs=cs.executeQuery();
            while (rs.next()) {                
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("Ventas Semestre/Año: "+e.getMessage());
        }return lista;
    }
    
    public ArrayList ventasporCliente(String cod){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            CallableStatement cs=cn.prepareCall("{call ventasPorCliente(?)}");
            cs.setString(1, cod);
            ResultSet rs=cs.executeQuery();
            while (rs.next()) {    
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();cs.close();
        } catch (Exception e) {
            System.out.println("Ventas por Cliente: "+e.getMessage());
        }return lista;
    }
    //  METODOS CLÄSICOS 
    public ArrayList listarVenta(){
        ArrayList<Venta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from Venta where cancelado='No'");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {
                lista.add(new Venta(rs.getInt("idVenta"), rs.getString("tipoDocumento"),rs.getString("empleado") , rs.getString("nombreCliente"), rs.getString("hora"),rs.getString("fecha"),rs.getString("cancelado")));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("Listar Venta: "+e.getMessage());
        }return  lista;
    }
    

    public String agregarVenta(Venta unaVenta){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert Venta values(?,?,?,?,?,?)");
           // pst.setInt(1, unaVenta.getIdVenta());
            pst.setString(1, unaVenta.getTipoDocumento());
            pst.setString(2, unaVenta.getFecha());
            pst.setString(3,unaVenta.getHora());
            pst.setString(4, unaVenta.getEmpleado());
            pst.setString(5, unaVenta.getNombrecliente());
            pst.setString(6, unaVenta.getCancelado());
            pst.executeUpdate();
            men="VENTA REGISTRADA";
        } catch (Exception e) {
            men="Agregar Venta: "+e.getMessage();
        }return  men;
    }
    
    public String eliminarVenta(int cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("UPdate venta set cancelado='Si' where idVenta=?");
            pst.setInt(1, cod);
            pst.executeQuery();
            men="VENTA ELIMINADO";
        } catch (Exception e) {
            men="eliminar Venta: "+e.getMessage();
        }return men;
    }
    
    
}
