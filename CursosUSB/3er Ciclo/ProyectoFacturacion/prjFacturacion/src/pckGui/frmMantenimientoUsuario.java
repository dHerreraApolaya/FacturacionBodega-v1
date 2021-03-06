/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckGui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pckDao.classEventoDao;
import pckDao.classUsuario;
import pckEntidad.Evento;
import pckEntidad.Usuario;

/**
 *
 * @author Katherine
 */
public class frmMantenimientoUsuario extends javax.swing.JFrame {
    String estado="";
    classUsuario ousuario =new classUsuario();
    
    void mensaje(String men){
        JOptionPane.showMessageDialog(this, men,"AVISO",1);
    }
    
   String idLogin="";
      void obtenerIdLogin(String cod){
      idLogin=cod;
    }
    void agregarEvento(String evento){
        
        classEventoDao oEventoDao=new classEventoDao();
     //  lblUsuario.setText("aRios");
        Evento unEvento=new Evento(1,idLogin, evento, "Usuario", frmMenu.lblFecha.getText()+" "+frmMenu.lblHora.getText()+" "+frmMenu.lblAmPm.getText(), ""+frmMenu.lblFecha.getText(), "No");
        mensaje(oEventoDao.agregarEvento(unEvento));
    }
    
    void limpiar(){
        txtApellido.setText("");
        txtContraseña.setText("");
        txtLogin.setText("");
        txtNombre.setText("");
        cboNivel.setSelectedIndex(0);
        chkActivo.setSelected(false);
        chkActivo.setText("Desactivado");
    }
    void listarUsuario(){
        DefaultTableModel  mod=new DefaultTableModel();
        String columnas[]={"Codigo","Nombres","Apellidos","Contraseña","Estado","Nivel"};
        mod.setColumnIdentifiers(columnas);
        ArrayList<Usuario> lista=ousuario.listarUser();
        for (Usuario ousuario : lista) {
            Object data[]={
              ousuario.getCodUser(),ousuario.getNombre(),ousuario.getApellido(),ousuario.getContraseña(),ousuario.getEstado(),ousuario.getNivel()
            };mod.addRow(data);
        }tblLIsta.setModel(mod);lblFilas.setText("Filas Encontradas: "+tblLIsta.getRowCount());
    }
    
    void desBLqBtn(boolean valor){
        btnActualizar.setEnabled(valor);
        btnEliminar.setEnabled(valor);
    }
    
    void desBlqTxt(boolean valor){
        txtApellido.setEditable(valor);
        txtContraseña.setEditable(valor);
        txtNombre.setEditable(valor);
        chkActivo.setEnabled(valor);
        cboNivel.setEnabled(valor);
    }
    
    void consultarEstado(){
          if(chkActivo.isSelected()==true){
            chkActivo.setText("Activo");estado="Activo";
        }else if(chkActivo.isSelected()==false){
            chkActivo.setText("Desactivado");estado="Desactivado";
        }
    }
     void soloNumeros(KeyEvent evt,String cad){
        int longitud=0;
        char x=evt.getKeyChar();
        
        longitud=cad.length();
        
        if(Character.isLetter(x)){
            getToolkit().beep();
            evt.consume();
       //     JOptionPane.showMessageDialog(this, "INGRESAR SOLO NUMEROS....");
        }
        
        if(longitud>=50){
            evt.consume();
        }
    }
    
   
    void soloTexto(KeyEvent evt,String cad){
         char x=evt.getKeyChar();
         
         if(Character.isDigit(x)){
             getToolkit().beep();
             evt.consume();
             JOptionPane.showMessageDialog(this, "INGRESAR SOLO TEXTO....");
         }
    }
    
    /**
     * Creates new form frmMantenimientoUsuario
     */
    public frmMantenimientoUsuario() {
        initComponents();
        listarUsuario();
        limpiar();
        desBLqBtn(false);
        desBlqTxt(false);
        consultarEstado();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnVerificar = new javax.swing.JButton();
        pnlControl = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnBUscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnIraMenu = new javax.swing.JButton();
        pnlOpcionesCuenta = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        chkActivo = new javax.swing.JCheckBox();
        cboNivel = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLIsta = new javax.swing.JTable();
        lblFilas = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Datos:"));

        jLabel3.setText("Apellidos:");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel2.setText("Nombres:");

        btnVerificar.setText("VERIFICAR");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellido)
                            .addComponent(txtNombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVerificar)))
                .addContainerGap())
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerificar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlControl.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Controles:"));

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnNuevo.setText("NUEVO");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnBUscar.setText("BUSCAR");
        btnBUscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUscarActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnIraMenu.setText("Ir a Menu");
        btnIraMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIraMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlControlLayout = new javax.swing.GroupLayout(pnlControl);
        pnlControl.setLayout(pnlControlLayout);
        pnlControlLayout.setHorizontalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(btnBUscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlControlLayout.createSequentialGroup()
                        .addGroup(pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnIraMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlControlLayout.setVerticalGroup(
            pnlControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlControlLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addGap(18, 18, 18)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnBUscar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(18, 18, 18)
                .addComponent(btnIraMenu)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pnlOpcionesCuenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Opciones de la Cuenta:"));

        jLabel4.setText("Login:");

        jLabel1.setText("Contraseña:");

        txtLogin.setEditable(false);
        txtLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkActivo.setText("ACTIVADO");
        chkActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkActivoActionPerformed(evt);
            }
        });

        cboNivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Seleccionar-", "Empleado", "SuperVisor" }));

        javax.swing.GroupLayout pnlOpcionesCuentaLayout = new javax.swing.GroupLayout(pnlOpcionesCuenta);
        pnlOpcionesCuenta.setLayout(pnlOpcionesCuentaLayout);
        pnlOpcionesCuentaLayout.setHorizontalGroup(
            pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesCuentaLayout.createSequentialGroup()
                .addGroup(pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLogin)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkActivo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboNivel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlOpcionesCuentaLayout.setVerticalGroup(
            pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesCuentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionesCuentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblLIsta.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblLIsta);

        lblFilas.setText("Filas Encontradas: xx");

        lblUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Usuario:"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblFilas))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                            .addComponent(pnlOpcionesCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlOpcionesCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pnlControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFilas)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkActivoActionPerformed
        // TODO add your handling code here:
      consultarEstado();
    }//GEN-LAST:event_chkActivoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if(btnAgregar.getText().equalsIgnoreCase("AGREGAR")){
            limpiar();
            desBlqTxt(true);
            txtNombre.requestFocus();
            btnAgregar.setText("ACEPTAR");btnNuevo.setText("CANCELAR");
        }
        else if(btnAgregar.getText().equalsIgnoreCase("ACEPTAR")){
            if(txtApellido.getText().isEmpty() || txtNombre.getText().isEmpty()){
            mensaje("No dejar Campos Vacios");
        }else {
                mensaje(ousuario.agregarUser(new Usuario(txtLogin.getText(), txtNombre.getText(), txtApellido.getText(), txtContraseña.getText(), estado, cboNivel.getSelectedItem().toString(), "No")));
                agregarEvento("Insertar");
                listarUsuario();
                limpiar();
        }btnAgregar.setText("AGREGAR");btnNuevo.setText("NUEVO");
        }
        
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
       if(btnActualizar.getText().equalsIgnoreCase("ACTUALIZAR")){
           desBlqTxt(true);
           txtNombre.requestFocus();
           btnActualizar.setText("ACEPTAR");btnNuevo.setText("CANCELAR");
       }else if(btnActualizar.getText().equalsIgnoreCase("ACEPTAR")){
            if(txtLogin.getText().isEmpty() || txtContraseña.getText().isEmpty()){
            mensaje("No dejar Campos Vacios");txtContraseña.requestFocus();
        }else {
                mensaje(ousuario.actualizarUser(new Usuario(txtLogin.getText(), txtNombre.getText(), txtApellido.getText(), txtContraseña.getText(), estado, cboNivel.getSelectedItem().toString(),"No")));
           agregarEvento("Modificar");
                listarUsuario();
            desBLqBtn(false);
            limpiar();
        }
            btnActualizar.setText("ACTUALIZAR");btnNuevo.setText("NUEVO");
       }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed
        // TODO add your handling code here:
        String IdLogin="";
        if(txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()){
            mensaje("COMPLETAR CAMPOS VACIOS");
        }else {
            int espacio=txtApellido.getText().indexOf(" ");
            IdLogin=""+txtNombre.getText().substring(0,1).toLowerCase()+txtApellido.getText().substring(0, espacio).toUpperCase();
            mensaje(IdLogin);
            
            Usuario obtUsuario=ousuario.buscarUser(IdLogin);
            if(obtUsuario==null){
                mensaje("CUMPLE PRIMER IF");
               txtContraseña.requestFocus();
               txtLogin.setText(IdLogin);txtLogin.setEditable(true);
            }else 
                
                if(obtUsuario.getApellido().trim().equalsIgnoreCase(txtApellido.getText().trim()) && obtUsuario.getNombre().trim().equalsIgnoreCase(txtNombre.getText().trim())) {
                
                mensaje("El Usuario a Registrar Ya EXISTE: "+"Se Evaluo por Apellidos y Nombre");
                txtLogin.setText(obtUsuario.getCodUser());
                txtContraseña.setText(obtUsuario.getContraseña());
                cboNivel.setSelectedItem(obtUsuario.getNivel());
                if(obtUsuario.getEstado().equalsIgnoreCase("Activo")){
                    chkActivo.setSelected(true);chkActivo.setText("Activo");
                }else if(obtUsuario.getEstado().equalsIgnoreCase("Desactivado"))
                {chkActivo.setSelected(false);chkActivo.setText("Desactivado");
                }
            }else if(obtUsuario.getApellido().trim().equalsIgnoreCase(txtApellido.getText().trim())) {
                    mensaje("Consultar si tiene un familiar por los Apellidos que tienen en comun; Obedecer y Hacer un cambio en el Id Login");
                    txtLogin.setText(IdLogin);
                    txtLogin.setEditable(true);txtLogin.requestFocus();
                    
                }
        }
    }//GEN-LAST:event_btnVerificarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
          if(btnNuevo.getText().equalsIgnoreCase("NUEVO")){
            limpiar();
           desBlqTxt(false);desBLqBtn(false);
        }else if(btnNuevo.getText().equalsIgnoreCase("CANCELAR")){
        desBlqTxt(false);desBLqBtn(false);
            btnNuevo.setText("NUEVO");
            limpiar();
            btnBUscar.setText("BUSCAR");btnAgregar.setText("AGREGAR");btnActualizar.setText("ACTUALIZAR");
         }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnIraMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIraMenuActionPerformed
        // TODO add your handling code here:
//        frmMenu vent=new frmMenu();
//        vent.setVisible(true);vent.setLocationRelativeTo(null);vent.setResizable(false);
//        vent.lblUsuario.setText(lblUsuario.getText());
      this.dispose();
    }//GEN-LAST:event_btnIraMenuActionPerformed

    private void btnBUscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUscarActionPerformed
        // TODO add your handling code here:
        if(btnBUscar.getText().equalsIgnoreCase("BUSCAR")){
            limpiar();txtLogin.setEditable(true);txtLogin.requestFocus();
            btnBUscar.setText("ACEPTAR");btnNuevo.setText("CANCELAR");
        }
        else if(btnBUscar.getText().equalsIgnoreCase("ACEPTAR")){
            if(txtLogin.getText().isEmpty()){
                mensaje("INGRESE CODIGO A BUSCAR");txtLogin.requestFocus();
            }else {
                Usuario obtUsuario=ousuario.buscarUser(txtLogin.getText());
                if(obtUsuario==null){
                    mensaje("Codigo no Existe");limpiar();txtLogin.requestFocus();
                }
                else {
                    txtNombre.setText(obtUsuario.getNombre());
                    txtApellido.setText(obtUsuario.getApellido());
                    txtContraseña.setText(obtUsuario.getContraseña());
                    cboNivel.setSelectedItem(obtUsuario.getNivel());
                   if(obtUsuario.getEstado().equalsIgnoreCase("Activo")){
                    chkActivo.setSelected(true);chkActivo.setText("Activo");
                }else if(obtUsuario.getEstado().equalsIgnoreCase("Desactivado"))
                {chkActivo.setSelected(false);chkActivo.setText("Desactivado");
                }
                    txtLogin.setEditable(false);
                   
                }
            }btnBUscar.setText("BUSCAR");btnNuevo.setText("NUEVO");
            desBLqBtn(true);txtLogin.setEditable(false);
        }
        
    }//GEN-LAST:event_btnBUscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        if(txtLogin.getText().isEmpty()){
            mensaje("INGREGE CODIGO");limpiar();
            txtLogin.setEditable(true);
            txtLogin.requestFocus();
        }else {
            int x=Integer.parseInt(""+JOptionPane.showConfirmDialog(this,"Seguro de Eliminar el Usuario "+txtNombre.getText()+" ????"));
            if(x==0){
                mensaje(ousuario.eliminarUser(txtLogin.getText()));
                agregarEvento("Eliminar");
                listarUsuario();
            }limpiar();desBLqBtn(false);desBlqTxt(false);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        soloTexto(evt, txtNombre.getText());
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO add your handling code here:
        soloTexto(evt, txtApellido.getText());
    }//GEN-LAST:event_txtApellidoKeyTyped

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
            java.util.logging.Logger.getLogger(frmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMantenimientoUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBUscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnIraMenu;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnVerificar;
    private javax.swing.JComboBox cboNivel;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFilas;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlControl;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlOpcionesCuenta;
    private javax.swing.JTable tblLIsta;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
