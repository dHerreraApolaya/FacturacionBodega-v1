/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckGui;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pckDao.classReporteDao;
import pckDao.classVentaDao;
import pckEntidad.Venta;

/**
 *
 * @author HOME
 */
public class frmReporteVentasporSemestre extends javax.swing.JFrame {
    classVentaDao oVentaDao=new classVentaDao();
    
      void cargarItemComboBox(int cant){
        cboSemestre.removeAllItems();
        for (int i = 0; i <= cant; i++) {
            if(i==0){
                cboSemestre.addItem("--Todos--");
            }else if (i>0){
                cboSemestre.addItem(i);
            }
        }
    }
       void listarAño(){
        ArrayList lista=oVentaDao.listarAño();
        for (Object object : lista) {
            cboAño.addItem(object);
        }
    }
      void listarVentas(){
          DefaultTableModel mod=new DefaultTableModel();
        String columnas[]={
            "Codigo","Tipo Documento","Fecha","Hora","Empleado","Cliente"
        };mod.setColumnIdentifiers(columnas);
        
        ArrayList<Venta> lista=oVentaDao.listarVenta();
        for (Venta oventa : lista) {
            Object data[]={
              oventa.getIdVenta(),oventa.getTipoDocumento(),oventa.getFecha(),oventa.getHora(),oventa.getEmpleado(),oventa.getNombrecliente()
            };mod.addRow(data);
        }tblVentasporSemestre.setModel(mod);lblFilas.setText("Filas Encontradas: "+tblVentasporSemestre.getRowCount());
      }
      
      void listarVentasporSemestre(int sem,int año){
          DefaultTableModel mod=new DefaultTableModel();
        String columnas[]={
            "Codigo","Tipo Documento","Fecha","Hora","Empleado","Cliente"
        };mod.setColumnIdentifiers(columnas);
        
        ArrayList<Venta> lista=oVentaDao.ventasporSemestreAño(sem, año);
        for (Venta oventa : lista) {
            Object data[]={
              oventa.getIdVenta(),oventa.getTipoDocumento(),oventa.getFecha(),oventa.getHora(),oventa.getEmpleado(),oventa.getNombrecliente()
            };mod.addRow(data);
        }tblVentasporSemestre.setModel(mod);lblFilas.setText("Filas Encontradas: "+tblVentasporSemestre.getRowCount());
      }
    
      
    
    /**
     * Creates new form frmReporteVentasporSemestre
     */
    public frmReporteVentasporSemestre() {
        initComponents();
        cargarItemComboBox(2);
        listarAño();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentasporSemestre = new javax.swing.JTable();
        pnlControles = new javax.swing.JPanel();
        btnConsultar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnIraMenu = new javax.swing.JButton();
        pnlCuadroBusqueda = new javax.swing.JPanel();
        cboSemestre = new javax.swing.JComboBox();
        cboAño = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFilas = new javax.swing.JLabel();
        btnGenerarReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Usuario:"));

        tblVentasporSemestre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblVentasporSemestre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVentasporSemestreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVentasporSemestre);

        pnlControles.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Controles:"));

        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnIraMenu.setText("Ir a Menú");
        btnIraMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIraMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlesLayout = new javax.swing.GroupLayout(pnlControles);
        pnlControles.setLayout(pnlControlesLayout);
        pnlControlesLayout.setHorizontalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIraMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlControlesLayout.setVerticalGroup(
            pnlControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnConsultar)
                .addGap(18, 18, 18)
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnIraMenu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCuadroBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cuadro de Búsqueda"));

        cboSemestre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Todos--" }));

        cboAño.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Todos--" }));

        jLabel1.setText("Año:");

        jLabel2.setText("Semestre:");

        javax.swing.GroupLayout pnlCuadroBusquedaLayout = new javax.swing.GroupLayout(pnlCuadroBusqueda);
        pnlCuadroBusqueda.setLayout(pnlCuadroBusquedaLayout);
        pnlCuadroBusquedaLayout.setHorizontalGroup(
            pnlCuadroBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuadroBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(cboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlCuadroBusquedaLayout.setVerticalGroup(
            pnlCuadroBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuadroBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCuadroBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        lblFilas.setText("Filas Encontradas: xx");

        btnGenerarReporte.setText("GENERAR REPORTE");
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlCuadroBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblFilas))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlControles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGenerarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCuadroBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnGenerarReporte))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFilas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIraMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIraMenuActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnIraMenuActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        listarVentas();
        cboSemestre.setSelectedIndex(0);
        cboAño.setSelectedIndex(0);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        int sem=cboSemestre.getSelectedIndex();
        int año=Integer.parseInt(""+cboAño.getSelectedItem());
        if(sem==0 || cboAño.getSelectedIndex()==0){
            
        }else {
        listarVentasporSemestre(sem,año);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
        // TODO add your handling code here:
          classReporteDao report=new classReporteDao();
        
        int sem=cboSemestre.getSelectedIndex();
        int año=cboAño.getSelectedIndex();
        
        if(sem==0 || año==0){
            JOptionPane.showMessageDialog(this, "Seleccione un semestre y un Año");
        }else {
            report.startReporteVentasSemestre(sem, año);
        }
        
        
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

    private void tblVentasporSemestreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVentasporSemestreMouseClicked
        // TODO add your handling code here:
          int pos=tblVentasporSemestre.getSelectedRow();
        int idVenta=Integer.parseInt( ""+tblVentasporSemestre.getValueAt(pos, 0));
       
        frmListarDetalleVentaDetallePago vent=new frmListarDetalleVentaDetallePago();
        vent.setLocationRelativeTo(null);vent.setResizable(false);
        vent.listarDetallePago(idVenta);vent.listarDetalleVenta(idVenta);
        vent.setVisible(true);vent.txtCodVenta.setText(""+idVenta);
    }//GEN-LAST:event_tblVentasporSemestreMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmReporteVentasporSemestre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReporteVentasporSemestre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReporteVentasporSemestre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReporteVentasporSemestre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmReporteVentasporSemestre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.JButton btnIraMenu;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox cboAño;
    private javax.swing.JComboBox cboSemestre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilas;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlCuadroBusqueda;
    private javax.swing.JTable tblVentasporSemestre;
    // End of variables declaration//GEN-END:variables
}
