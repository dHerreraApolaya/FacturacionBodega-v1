/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckConexion;
import java.sql.*;
/**
 *
 * @author Alumno
 */
public class classConexionFacturacion {
    
    Connection cn=null;
    String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url="jdbc:sqlserver://HOME-PC\\COMPUTO11;databaseName=Facturacion";
    String driverMysql="com.mysql.jdbc.Driver";
    String urlMysql="jdbc:mysql://localhost/FACTURACION";
    
    public Connection abriConexion(){
        try {
           Class.forName(driver);
           cn=DriverManager.getConnection(url,"sa","root");
            
//           Class.forName(driverMysql);
//           cn=DriverManager.getConnection(urlMysql,"root","root");
          //  System.out.println("Conexion Correctamente");
        } catch (Exception e) {
            System.out.println("Error Sql: "+e.getMessage());
        }return  cn;
    }
    void cerrarConexion(){
        try {
            cn.close();
        } catch (Exception e) {
            System.out.println("Error Cerrar Conexion: "+e.getMessage());
        }
    }
}
