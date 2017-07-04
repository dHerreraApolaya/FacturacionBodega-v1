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
import pckEntidad.DetallePago;
import pckEntidad.DetallePagoConsulta;

/**
 *
 * @author HOME
 */
public class classDetallePago {
    
     classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
      public ArrayList listarDetallePago(int codVenta){
        ArrayList<DetallePagoConsulta> lista=new ArrayList<>();
        try {
            PreparedStatement pst=cn.prepareStatement("select dp.idVenta,c.nombreCliente,op.nombreOpcionPago,dp.montoPagar,dp.cantLetras,dp.comentario from detallePago dp \n" +
"inner join cliente c on c.idCliente=dp.idCliente inner join opcionPago op on op.idOpcionPago=dp.idOpcionPago  where dp.idVenta=?");
            pst.setInt(1, codVenta);
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new DetallePagoConsulta(rs.getInt("idVenta"),rs.getInt("cantLetras"),rs.getString("idCliente"),rs.getString("cancelado"),rs.getString("comentario"),rs.getString("nombreOpcionPago"),rs.getDouble("montoPagar")));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("listar detalle Pago: "+e.getMessage());
        }return  lista;
    }
    
    public String agregarDetallePago(DetallePago unDetallePago){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert detallePago values(?,?,?,?,?,?,?)");
            pst.setInt(1, unDetallePago.getIdVenta());
            pst.setString(2, unDetallePago.getIdCliente());
            pst.setInt(3,unDetallePago.getIdOpcionPago());
            pst.setDouble(4,unDetallePago.getMontoPagar());
            pst.setInt(5, unDetallePago.getCantLetras());
            pst.setString(6, unDetallePago.getComentario());
            pst.setString(7, unDetallePago.getCancelado());
            pst.executeUpdate();
            men="DETALLE DEL PAGO AGREGADO";
        } catch (Exception e) {
           men=("agregar DetallePago: "+e.getMessage());
        }return men;
    }
    
    public String eliminarDetallePago(int cod){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("UPdate Detallepago cancelado='Si' where idVenta=?");
            pst.setInt(1, cod);
            pst.executeUpdate();
            men="DETALLE DEL PAGO ELIMINADO";
        } catch (Exception e) {
            System.out.println("eliminar DetallePago: "+e.getMessage());
        }return  men;
    }
    
}
