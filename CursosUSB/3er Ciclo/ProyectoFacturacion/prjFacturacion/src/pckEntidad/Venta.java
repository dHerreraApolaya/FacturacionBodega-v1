/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pckEntidad;


/**
 *
 * @author HOME
 */
public class Venta {
    private int idVenta;
    private String tipoDocumento,empleado,nombrecliente;
    private String hora;
    private String fecha;
    private String cancelado;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    
    public Venta() {
    }

    public Venta(int idVenta, String tipoDocumento, String empleado, String nombrecliente, String hora, String fecha, String cancelado) {
        this.idVenta = idVenta;
        this.tipoDocumento = tipoDocumento;
        this.empleado = empleado;
        this.nombrecliente = nombrecliente;
        this.hora = hora;
        this.fecha = fecha;
        this.cancelado = cancelado;
    }

    

    
  
    
}
