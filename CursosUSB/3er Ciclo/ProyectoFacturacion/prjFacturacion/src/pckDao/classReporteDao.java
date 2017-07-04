/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckDao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import pckConexion.classConexionFacturacion;

/**
 *
 * @author Alumno
 */
public class classReporteDao {
     classConexionFacturacion ocn=new classConexionFacturacion();
    Connection cn=ocn.abriConexion();
    
    public void startReporteVentasTrimestre(int tri,int año){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasTrimestreAño.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@trimestre", tri);
            param.put("@año", año);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public void startReporteVentasSemestre(int sem,int año){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasSemestreAño.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@semestre", sem);
            param.put("@año", año);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public void startReporteVentasBimestre(int bi,int año){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasporBimestreAño.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@bimestre", bi);
             
            param.put("@año", año);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
           visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public void startReporteVentasMesAño(int mes,int año){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasporMesAño.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@año", año);
             param.put("@mes", mes);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
           visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public void startReporteVentasFecha(int dia,int mes,int año){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasporFecha.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("año", año);
            param.put("mes", mes);
            param.put("dia", dia);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
           visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
    
    public void startReporteVentasCliente(String Cliente){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptVentasCliente.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@cliente", Cliente);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Reporte de Ventas");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
      public void startReporteProductosUnidadMedida(int  codUnidaMedad){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptProductosporUnidadMedidad.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@codUnidadMedida", codUnidaMedad);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Reporte de Productos");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
    
        public void startReporteProductosporCategoria(int codCategoria){
        try{
            String template=System.getProperty("user.dir")+"\\src\\pckReporte\\rptProductoporCategoria.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            
            //Valores para los parametros
            Map param=new HashMap();
            param.put("@codCategoria", codCategoria);
             
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,cn);
            
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Reporte de Productos");
            visor.setVisible(true);



        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
      
    
}
