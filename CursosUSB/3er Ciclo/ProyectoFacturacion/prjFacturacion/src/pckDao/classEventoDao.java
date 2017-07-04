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
import pckEntidad.Evento;

/**
 *
 * @author HOME
 */
public class classEventoDao {
    
    classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    ArrayList<Evento> lista=new ArrayList<>();
    
    public int CantFila(){
        return lista.size();
    }
    
    public ArrayList listarEvento(){
        
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from Evento");
            ResultSet rs=pst.executeQuery();
            while (rs.next()) {                
                lista.add(new Evento(rs.getInt(1),rs.getString("loginUser"),rs.getString("evento"), rs.getString("nomTabla"), rs.getString("fecha"), rs.getString("hora"),rs.getString("cancelado")));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("listar Evento: "+e.getMessage());
        }return lista;
    
        
    }
    
    public String agregarEvento(Evento unevento){
        String men="";
        try {
            PreparedStatement pst=cn.prepareStatement("insert Evento values(?,?,?,?,?,?)");
         // pst.setInt(1,unevento.getIdEvento());
            pst.setString(1, unevento.getFecha());
            pst.setString(2, unevento.getHora());
            pst.setString(3, unevento.getLoginUser());
            pst.setString(4, unevento.getEvento());
            pst.setString(5, unevento.getNombretabla());
            pst.setString(6, unevento.getCantelado());
            pst.executeUpdate();
            men="EVENTO REGISTRADO";
        } catch (Exception e) {
            men="agregar Evento: "+e.getMessage();
        }return men;
    }
    
    public Evento buscarEvento(int cod){
        Evento oevento=null;
        try {
            PreparedStatement pst=cn.prepareStatement("Select * from evento where idevento=?");
            pst.setInt(1, cod);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                oevento=new Evento(rs.getInt(1),rs.getString("loginUSer"), rs.getString("Evento"), rs.getString("nomTabla"),
                        rs.getString("fecha"),rs.getString("hora"),rs.getString("cancelado"));
            }rs.close();pst.close();
        } catch (Exception e) {
            System.out.println("buscar Evento: "+e.getMessage());
        }return oevento;
    }
}
