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
public class DetalleVenta {
    private int idVenta, cantidad,idProducto;
    private double precioUnitario;
    private String cancelado;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    
    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

   

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public DetalleVenta() {
    }

    public DetalleVenta(int idVenta, int cantidad, int idProducto, double precioUnitario, String cancelado) {
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.idProducto = idProducto;
        this.precioUnitario = precioUnitario;
        this.cancelado = cancelado;
    }

   

   
    
    
    
}
