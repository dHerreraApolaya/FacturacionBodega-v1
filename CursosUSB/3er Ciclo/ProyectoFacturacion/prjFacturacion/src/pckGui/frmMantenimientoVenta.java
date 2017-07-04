/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckGui;


import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pckDao.classClienteDao;
import pckDao.classDetallePago;
import pckDao.classDetalleVentaDao;
import pckDao.classEventoDao;
import pckDao.classFecha;
import pckDao.classHoraSistema;
import pckDao.classProductoDao;
import pckDao.classTipoPagoDao;
import pckDao.classVentaDao;
import pckEntidad.Cliente;
import pckEntidad.DetallePago;
import pckEntidad.DetalleVenta;
import pckEntidad.Evento;
import pckEntidad.ProductoConsulta;
import pckEntidad.TipoPago;
import pckEntidad.Venta;
/**
 *
 * @author HOME
 */
public class frmMantenimientoVenta extends javax.swing.JFrame {
    classProductoDao oproductoDao=new classProductoDao();
    classVentaDao oVentaDao=new classVentaDao();
    classClienteDao oClienteDao=new classClienteDao();
    
    DefaultTableModel mod=new DefaultTableModel();
        classDetalleVentaDao oDetalleVentaDao=new classDetalleVentaDao();
         double Total_Pagar=0;
         String Tipo_Doc="";
    String idLogin="";
    int par=0;
            /**
     * Creates new form frmMantenimientoVenta
     */
   
    void buscarRazonSocial(String codCliente){
       Cliente ocliente=oClienteDao.buscarCliente(codCliente);
       if(ocliente==null){
           mensaje("No Existe");
       }else {
           txtRazonSocial.setText(ocliente.getNombreCliente());
       }
        
    }
    
    double consultarPrecio(int codigoProd){
        double precio=0.0;
        ProductoConsulta oproducto=oproductoDao.buscarProducto(codigoProd);
        if(oproducto==null){
            JOptionPane.showMessageDialog(this,"oproducto: Null","Alerta",1);
        }else {
            precio=oproducto.getPrecioUnitario();
        }return precio;
    }
  
    void calcularImporte(){
        int x=mod.getRowCount();
        double oStotal=0,unIgv=0,unImporte=0;
        for (int i = 0; i < x; i++) {
            oStotal=Double.parseDouble(""+mod.getValueAt(i, 3))+oStotal;
        }
     
        
        unIgv=oStotal*0.18;
        unImporte=oStotal+unIgv;
//        String.format("%.2f", unIgv);
        Total_Pagar=unImporte;
        txtSubTotal.setText(""+String.format("%.2f",oStotal));
        txtIgv.setText(""+String.format("%.2f", unIgv));
        txtImporte.setText(""+String.format("%.2f", unImporte));
    }
    
    void calcularDiferencia(double efectivo){
        double diferencia=0.0;
//        mensaje(diferencia+"");
//        mensaje(Total_Pagar+"");
        diferencia=efectivo-Total_Pagar;
//        mensaje(diferencia+"");
        txtVuelto.setText(""+String.format("%.2f", diferencia));
    }
    
    
     void agregar(){
         String  columnas[]={
          "Cantidad","Nombre Producto","Precio Unitario","SubTotal"  
        };mod.setColumnIdentifiers(columnas);
        tblListaProductos.setModel(mod);
         
        String nom=cboNombreProducto.getSelectedItem().toString();
        int cant=Integer.parseInt(txtCantidad.getText());
        double precio,sTotal;
        precio=consultarPrecio(cboNombreProducto.getSelectedIndex());
        sTotal=precio*cant;
        Object data[]={
          cant,nom,precio,sTotal
        };
       
        mod.addRow(data);
    }
     
    void agregarProducto(String nuevoProd,int nuevaCant){
       int tamaño= mod.getRowCount();
//       mensaje("TAmaño modelo: "+tamaño);
       String oprod="";
       String prodRepetido="";
       int x=0;
       
       // POR CADA I ESTA AGREGANDO .... 
       if(tamaño==0){
           agregar();lblFilas.setText("Cantidad de Productos: "+tblListaProductos.getRowCount());
         }else if(tamaño>0){
//             mensaje("INicio FOr: "+tamaño);
           for (int i = 0; i < tamaño; i++) {
             oprod=""+mod.getValueAt(i,1);
//             mensaje("OBTENER PRODUCTO:"+i+mod.getValueAt(i, 1));
             if(oprod.equalsIgnoreCase(nuevoProd)){
                 x=i;
                 prodRepetido=""+mod.getValueAt(x, 1);
             }
           }
             if(prodRepetido.equalsIgnoreCase(nuevoProd)){
//                 mensaje("IF == TRUE :"+x+mod.getValueAt(x, 1)+" : "+prodRepetido);
                 int ocant=Integer.parseInt(""+ mod.getValueAt(x, 0));
                 int ncant=nuevaCant+ocant;
                 double nprecio=consultarPrecio(cboNombreProducto.getSelectedIndex());
                 mod.setValueAt(ncant, x, 0);
                 mod.setValueAt((nprecio*ncant), x, 3);
              
//              mensaje("ACTUALIZAR CANTIDAD: "+ncant);
//                 break;
             } else {
//                mensaje("NO ENCONTRO :"+oprod);
                agregar();
                lblFilas.setText("Cantidad de Productos: "+tblListaProductos.getRowCount());

             } 
           
       }tblListaProductos.setModel(mod);
    }
    
    int CantVenta(){
        //mensaje(""+oVentaDao.CantLista());
        int x=oVentaDao.CantLista();
        return x;
    }
    int codProd(int pos){
         int x=0;
        String nomProd=""+tblListaProductos.getValueAt(pos,1);
        cboNombreProducto.setSelectedItem(nomProd);
        x=cboNombreProducto.getSelectedIndex();
        return x;
    }
    
  
    
    void agregarDetalleVenta(){
        int x=tblListaProductos.getRowCount();
      //  mensaje("Empieza "+x);
        for (int i = 0; i < x; i++) {
            int idProd=codProd(i);
//            mensaje(""+idProd);
            DetalleVenta nDetalleVenta =new DetalleVenta(CantVenta(),Integer.parseInt(""+ tblListaProductos.getValueAt(i, 0)), idProd,Double.parseDouble(""+ tblListaProductos.getValueAt(i, 2)), "No");
            mensaje(oDetalleVentaDao.agregarDetalleVenta(nDetalleVenta));
//            System.out.println(""+idProd);
        }
        cboNombreProducto.setSelectedIndex(0);
    }
    //
    void agregarVenta(){
       
            Venta unaVenta=new Venta(1, Tipo_Doc, idLogin, txtRuc.getText(), lblFechaEmision.getText()+" "+lblHora.getText()+" "+lblAmPm.getText(),lblFechaEmision.getText()+"","No");
            mensaje(oVentaDao.agregarVenta(unaVenta));
//      
    }
      void obtenerIdLogin(String cod){
      idLogin=cod;
    }
    void agregarEvento(String evento){
        
        classEventoDao oEventoDao=new classEventoDao();
     //  lblUsuario.setText("aRios");
        Evento unEvento=new Evento(1,idLogin, evento, "Venta", lblFechaEmision.getText()+" "+lblHora.getText()+" "+lblAmPm.getText(), ""+lblFechaEmision.getText(), "No");
        mensaje(oEventoDao.agregarEvento(unEvento));
    }
    
    void agregarDetallePago(int codPago){
        int cantLetras=0;
        if(codPago==2){
            cantLetras=Integer.parseInt(lblCantLetraDif.getText());
        }
        classDetallePago oDetallePago=new classDetallePago();
     //  lblUsuario.setText("aRios");
        DetallePago unDetallePago=new DetallePago(CantVenta(), codPago,cantLetras,txtRuc.getText() ,"No", "Ninguno", Total_Pagar);
        mensaje(oDetallePago.agregarDetallePago(unDetallePago));
      //  mensaje(oEventoDao.agregarEvento(unEvento));
    }
    
    void cargarFecha(){
        classFecha ofecha=new classFecha();
        lblFechaEmision.setText(ofecha.fechaActual());
    }
    
    void cargarHora(){
        classHoraSistema oHora=new classHoraSistema(lblHora);
        try {
            oHora.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void cargarCodCLiente(){
        TextAutoCompleter cargarOpc=new TextAutoCompleter(txtRuc);
        ArrayList<Cliente> lista=oClienteDao.listarCliente();
        for (Cliente ocliente : lista) {
            cargarOpc.addItem(ocliente.getIdCliente());
        }
    }
    
     void mostarAmPm(){
         classFecha ofecha=new classFecha();
        lblAmPm.setText(ofecha.ObtenerAmPm());
    }
     
    void cargarCboProd(){
         ArrayList<ProductoConsulta> lista=oproductoDao.listarProductosDetallado();
        for (ProductoConsulta producto : lista) {
            cboNombreProducto.addItem(producto.getNombreProd());
            };
        }
    
    void cargarCboOpcionPago(){
        classTipoPagoDao oTipoPagoDao=new classTipoPagoDao();
        ArrayList<TipoPago> lista=oTipoPagoDao.listarTipoPago();
        for (TipoPago otipoPago : lista) {
            cboOpcionPago.addItem(otipoPago.getNombreOpcionPago());
        }
    }
    
    void DiseñoTitulo(String TipoDoc,int cant){
        int codVenta=oVentaDao.CantLista()+1;
        Tipo_Doc=TipoDoc;
        par=cant;
        String titulo="Nro"+TipoDoc+"000"+codVenta;
        lblTituloNroDoc.setText(titulo);
    }
    
    void nuevo(){
       cboNombreProducto.setSelectedIndex(0);
        txtIgv.setText("0.0");
        txtImporte.setText("0.0");
        txtRazonSocial.setText("");
        txtRuc.setText("");
        txtSubTotal.setText("0.0");
        txtVuelto.setText("0.0");
        txtTotalPagar.setText("0.0");
        cboOpcionPago.setSelectedIndex(0);
        txtCuotas.setText("");
        txtefectivo.setText("");
        DefaultTableModel mod=new DefaultTableModel();
        tblListaProductos.setModel(mod);
        
        
        limpiar();
        lblFilas.setText("Filas Encontradas: "+tblListaProductos.getRowCount());
//        columnas();
        Total_Pagar=0;
    }
    
    void limpiar(){
        cboNombreProducto.setSelectedIndex(0);
        txtCantidad.setText("0");
    }
    
    void mensaje(String men){
        JOptionPane.showMessageDialog(this, men,"Alerta",1);
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
        
        if(longitud>=par){
            evt.consume();
        }
    }
    
      void soloNumerosCAnt(KeyEvent evt,String cad){
    //    int longitud=0;
        char x=evt.getKeyChar();
        
      //  longitud=cad.length();
        
        if(Character.isLetter(x)){
            getToolkit().beep();
            evt.consume();
       //     JOptionPane.showMessageDialog(this, "INGRESAR SOLO NUMEROS....");
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
    
//     private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {                                
//
//        soloNumeros(evt, txtDni.getText());
//    }                               
//    
    
    public frmMantenimientoVenta() {
        initComponents();
        nuevo();
        cargarCboProd();cargarCboOpcionPago();
        cargarFecha();cargarHora();
    
        Total_Pagar=0;
        Tipo_Doc="";
        cargarCodCLiente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatoEmpresa = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pnlFecha = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTituloNroDoc = new javax.swing.JLabel();
        lblFechaEmision = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        lblAmPm = new javax.swing.JLabel();
        pnlDatosCliente = new javax.swing.JPanel();
        lblNombRazonSocial = new javax.swing.JLabel();
        lblDocumento = new javax.swing.JLabel();
        txtRazonSocial = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        pnlDetalleVenta = new javax.swing.JPanel();
        cboNombreProducto = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblListaProductos = new javax.swing.JTable();
        lblFilas = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        BtnNUevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        pnlOpcionPago = new javax.swing.JPanel();
        cboOpcionPago = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtefectivo = new javax.swing.JTextField();
        lblNroCntaEfectivo = new javax.swing.JLabel();
        lblCantLetraDif = new javax.swing.JLabel();
        btnIrMenu = new javax.swing.JButton();
        txtSubTotal = new javax.swing.JTextField();
        txtIgv = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        btnAceptarPago = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        txtVuelto = new javax.swing.JTextField();
        txtCuotas = new javax.swing.JTextField();
        lblCuotas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro Venta");

        pnlDatoEmpresa.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("\"NOMBRE EMPRESA\"");

        jLabel2.setText("\"DIRECCION\"");

        jLabel3.setText("RUC EMPRESA:");

        pnlFecha.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Hora:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha Emision:");

        lblTituloNroDoc.setText("N° FACTURA:");

        javax.swing.GroupLayout pnlFechaLayout = new javax.swing.GroupLayout(pnlFecha);
        pnlFecha.setLayout(pnlFechaLayout);
        pnlFechaLayout.setHorizontalGroup(
            pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFechaLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(lblFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTituloNroDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlFechaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAmPm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlFechaLayout.setVerticalGroup(
            pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFechaLayout.createSequentialGroup()
                .addComponent(lblTituloNroDoc, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(lblFechaEmision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(pnlFechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAmPm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout pnlDatoEmpresaLayout = new javax.swing.GroupLayout(pnlDatoEmpresa);
        pnlDatoEmpresa.setLayout(pnlDatoEmpresaLayout);
        pnlDatoEmpresaLayout.setHorizontalGroup(
            pnlDatoEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatoEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlDatoEmpresaLayout.setVerticalGroup(
            pnlDatoEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatoEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatoEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatoEmpresaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlDatosCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNombRazonSocial.setText("Razon Social:");

        lblDocumento.setText("RUC:");

        txtRuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRucActionPerformed(evt);
            }
        });
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlDatosClienteLayout = new javax.swing.GroupLayout(pnlDatosCliente);
        pnlDatosCliente.setLayout(pnlDatosClienteLayout);
        pnlDatosClienteLayout.setHorizontalGroup(
            pnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDocumento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombRazonSocial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDatosClienteLayout.setVerticalGroup(
            pnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombRazonSocial)
                    .addComponent(txtRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pnlDetalleVenta.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cboNombreProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccionar--" }));
        cboNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNombreProductoActionPerformed(evt);
            }
        });

        jLabel12.setText("Nombre Producto:");

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel6.setText("Cantidad:");

        javax.swing.GroupLayout pnlDetalleVentaLayout = new javax.swing.GroupLayout(pnlDetalleVenta);
        pnlDetalleVenta.setLayout(pnlDetalleVentaLayout);
        pnlDetalleVentaLayout.setHorizontalGroup(
            pnlDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        pnlDetalleVentaLayout.setVerticalGroup(
            pnlDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleVentaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalleVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(cboNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tblListaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblListaProductos);

        lblFilas.setText("Total de Productos: xx");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Controles"));

        btnAceptar.setText("AGREGAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        BtnNUevo.setText("NUEVA VENTA");
        BtnNUevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNUevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnNUevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnNUevo)
                .addGap(18, 18, 18)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel7.setText("Usuario:");

        lblUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnlOpcionPago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Detalle Pago:"));

        cboOpcionPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccionar--" }));
        cboOpcionPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboOpcionPagoActionPerformed(evt);
            }
        });

        jLabel9.setText("Opcion Pago:");

        txtTotalPagar.setEditable(false);

        jLabel10.setText("Importe:");

        txtefectivo.setEditable(false);

        lblNroCntaEfectivo.setText("Efectivo:");

        lblCantLetraDif.setText("Diferencia:");

        btnIrMenu.setText("Ir a Menú");
        btnIrMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrMenuActionPerformed(evt);
            }
        });

        txtSubTotal.setEditable(false);

        txtIgv.setEditable(false);

        jLabel16.setText("SubTotal:");

        jLabel17.setText("Total a Pagar:");

        jLabel18.setText("IGV:");

        txtImporte.setEditable(false);

        btnAceptarPago.setText("CALCULAR");
        btnAceptarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarPagoActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setText("IMPRIMIR");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        lblCuotas.setText("Cuotas:");

        javax.swing.GroupLayout pnlOpcionPagoLayout = new javax.swing.GroupLayout(pnlOpcionPago);
        pnlOpcionPago.setLayout(pnlOpcionPagoLayout);
        pnlOpcionPagoLayout.setHorizontalGroup(
            pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionPagoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionPagoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(cboOpcionPago, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionPagoLayout.createSequentialGroup()
                        .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnIrMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAceptarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOpcionPagoLayout.createSequentialGroup()
                                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17))
                                .addGap(18, 18, 18)
                                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtImporte)
                                    .addComponent(txtIgv)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOpcionPagoLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlOpcionPagoLayout.createSequentialGroup()
                                        .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblNroCntaEfectivo)
                                            .addComponent(txtefectivo, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(txtTotalPagar, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                            .addComponent(jLabel10)
                                            .addComponent(lblCantLetraDif))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCuotas)
                                            .addGroup(pnlOpcionPagoLayout.createSequentialGroup()
                                                .addComponent(lblCuotas)
                                                .addGap(0, 0, Short.MAX_VALUE)))))))
                        .addContainerGap())
                    .addGroup(pnlOpcionPagoLayout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlOpcionPagoLayout.setVerticalGroup(
            pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionPagoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboOpcionPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNroCntaEfectivo)
                    .addComponent(lblCuotas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtefectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(btnAceptarPago)
                .addGap(18, 18, 18)
                .addComponent(lblCantLetraDif)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtVuelto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btnImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIrMenu)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlDatoEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(pnlDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGap(18, 18, 18)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblFilas)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(pnlOpcionPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlOpcionPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDatoEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(pnlDatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlDetalleVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFilas)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        int x=cboNombreProducto.getSelectedIndex();
        if(x==0){
            mensaje("Seleccione Producto");cboNombreProducto.requestFocus();
        }else if(x>0) {
       if(txtCantidad.getText().isEmpty()){
           mensaje("Ingrese Cantidad");txtCantidad.requestFocus();
       }
           else{
           agregarProducto(cboNombreProducto.getSelectedItem().toString(),Integer.parseInt(txtCantidad.getText()));
           limpiar();calcularImporte();
           txtTotalPagar.setText(txtImporte.getText());
           }
       
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnIrMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrMenuActionPerformed
        // TODO add your handling code here:
//        frmMenu vent=new frmMenu();
//        vent.setLocationRelativeTo(null);
//        vent.setResizable(false);
//        vent.setVisible(true);vent.lblUsuario.setText(lblUsuario.getText());
      this.dispose();
    }//GEN-LAST:event_btnIrMenuActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        frmVisualizarVenta vent=new frmVisualizarVenta();
        vent.setLocationRelativeTo(null);vent.setResizable(false);vent.setVisible(true);
        
        vent.impresion("NOMBRE DE LA EMPRESA");
        vent.impresion("");
        
        int x=tblListaProductos.getRowCount();
        for (int i = 0; i < x; i++) {
            vent.impresion(tblListaProductos.getValueAt(i, 0)+" "+tblListaProductos.getValueAt(i, 1)+"\t "+tblListaProductos.getValueAt(i, 2)+"\t "+tblListaProductos.getValueAt(i,3));
        }
        vent.impresion("TOTAL"+"\t"+"S/"+"\t"+"\t"+txtSubTotal.getText());
        // COMPLETAR EL MONTO DE PAGO
        vent.impresion("TOTAL PAGO EFECTIVO S/"+"\t"+"\t"+txtefectivo.getText());
        // COMPLETAR VUELTO
        vent.impresion("VUELTO"+"\t"+"\t"+"\t"+txtVuelto.getText());
        vent.impresion("\n");
        vent.impresion("OP. GRAVADA"+"\t"+"\t"+txtSubTotal.getText());
        vent.impresion("I.G.V."+"\t"+"\t"+txtIgv.getText());
        vent.impresion("IMPORTE TOTAL"+"\t"+"\t"+txtImporte.getText());
        vent.impresion("\n");
        vent.impresion("Estimado Cliente");
        vent.impresion("Conserve su comprobante, por");
        vent.impresion("regulación de SUNAT es indispensable");
        vent.impresion("presentarlo pra solicitar cambios o");
        vent.impresion("devoluciones.");
        vent.impresion("\n");
        vent.impresion("\t"+"Gracias por comprar en xxxx");
        vent.impresion("Fecha de Emisión: "+lblFechaEmision.getText()+" Hora: "+lblHora.getText());
        vent.impresion("ATENDIDO POR "+lblUsuario.getText());
        
        
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int pos=tblListaProductos.getSelectedRow();
        if(pos<0){
            mensaje("Seleccione un Producto de la Lista");
        }else if(pos>0){
            int x=Integer.parseInt(""+JOptionPane.showConfirmDialog(this, "Seguro de Eliminar el Producto "+tblListaProductos.getValueAt(pos, 1)));
            if(x==0){
        mod.removeRow(pos);
        tblListaProductos.setModel(mod);
          calcularImporte();
            }
        }
        lblFilas.setText("Filas Encontradas: "+tblListaProductos.getRowCount());
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void BtnNUevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNUevoActionPerformed
        // TODO add your handling code here:
        if(BtnNUevo.getText().equalsIgnoreCase("NUEVA VENTA")){
        nuevo();

        }else if(BtnNUevo.getText().equalsIgnoreCase("CANCELAR")){
        limpiar();
        }
    }//GEN-LAST:event_BtnNUevoActionPerformed

    private void cboNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNombreProductoActionPerformed
        // TODO add your handling code here:
        int x =cboNombreProducto.getSelectedIndex();
        if(x==0){
            txtCantidad.setEditable(false);
            txtCantidad.setText("");
        }else {
            txtCantidad.setText("");
            txtCantidad.setEditable(true);
            txtCantidad.requestFocus();
        }
    }//GEN-LAST:event_cboNombreProductoActionPerformed

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
        soloNumeros(evt, txtRuc.getText());
    }//GEN-LAST:event_txtRucKeyTyped

    private void cboOpcionPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboOpcionPagoActionPerformed
        // TODO add your handling code here:
        int x=cboOpcionPago.getSelectedIndex();
        switch (x){
            case 0:{
                //NInguno
                txtefectivo.setEditable(false);
                lblNroCntaEfectivo.setText("");
                
                txtCuotas.setEditable(false);
                btnAceptarPago.setEnabled(false);
                break;
            }
            case 1:{
                // Efectivo
                txtefectivo.setEditable(true);
                txtefectivo.requestFocus();
                lblNroCntaEfectivo.setText("Efectivo:");
                
                txtCuotas.setEditable(false);
                btnAceptarPago.setEnabled(true);
                
                break;
            }
            case 2:{
                // Credito
                txtefectivo.setEditable(true);
                txtefectivo.requestFocus();
                lblNroCntaEfectivo.setText("Nro Cuenta:");
                
                txtCuotas.setEditable(true);
                txtefectivo.setText("");
                txtefectivo.requestFocus();
                
                btnAceptarPago.setEnabled(false);
                break;
            
            }
            case 3:{
                //Debito
                txtefectivo.setEditable(true);
                txtefectivo.requestFocus();
                lblNroCntaEfectivo.setText("Nro Cuenta:");
                txtefectivo.setText("");
                txtefectivo.requestFocus();
               
                txtCuotas.setEditable(false);
                btnAceptarPago.setEnabled(false);
                break;
                
            }
            default:mensaje("Default Activo");
        }
    }//GEN-LAST:event_cboOpcionPagoActionPerformed

    private void btnAceptarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarPagoActionPerformed
        // TODO add your handling code here:
        calcularDiferencia(Double.parseDouble(txtefectivo.getText()));
      
    }//GEN-LAST:event_btnAceptarPagoActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        
        soloNumerosCAnt(evt, txtCantidad.getText());
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        
//        Insertar Modificar  Eliminar

  agregarVenta();
        int codOpcPago=cboOpcionPago.getSelectedIndex();
        switch (codOpcPago) {
            case 0:
                mensaje("Seleccionar el Modo de Pago");
                break;
            case 1:
                agregarDetallePago(codOpcPago);
                break;
            case 2:
                agregarDetallePago(codOpcPago);
                break;
            case 3:
                agregarDetallePago(codOpcPago);
                break;
            default:
                break;
        }
      
        agregarDetalleVenta();
        agregarEvento("Insertar");
       

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtRucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRucActionPerformed
        // TODO add your handling code here:
        buscarRazonSocial(txtRuc.getText());
    }//GEN-LAST:event_txtRucActionPerformed

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
            java.util.logging.Logger.getLogger(frmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMantenimientoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMantenimientoVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnNUevo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarPago;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnIrMenu;
    private javax.swing.JComboBox cboNombreProducto;
    private javax.swing.JComboBox cboOpcionPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAmPm;
    private javax.swing.JLabel lblCantLetraDif;
    private javax.swing.JLabel lblCuotas;
    public static javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblFechaEmision;
    private javax.swing.JLabel lblFilas;
    private javax.swing.JLabel lblHora;
    public static javax.swing.JLabel lblNombRazonSocial;
    private javax.swing.JLabel lblNroCntaEfectivo;
    public static javax.swing.JLabel lblTituloNroDoc;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlDatoEmpresa;
    private javax.swing.JPanel pnlDatosCliente;
    private javax.swing.JPanel pnlDetalleVenta;
    private javax.swing.JPanel pnlFecha;
    private javax.swing.JPanel pnlOpcionPago;
    private javax.swing.JTable tblListaProductos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCuotas;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtRazonSocial;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalPagar;
    private javax.swing.JTextField txtVuelto;
    private javax.swing.JTextField txtefectivo;
    // End of variables declaration//GEN-END:variables
}
