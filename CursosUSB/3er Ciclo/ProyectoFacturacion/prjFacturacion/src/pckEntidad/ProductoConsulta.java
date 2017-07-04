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
public class ProductoConsulta {
    private int idProducto;
    private String nombreProd,nombreUnidadMedida;
    private double precioUnitario;
    private String nombreCategoria,cancelado;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }

    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public ProductoConsulta() {
    }

    public ProductoConsulta(int idProducto, String nombreProd, String nombreUnidadMedida, double precioUnitario, String nombreCategoria, String cancelado) {
        this.idProducto = idProducto;
        this.nombreProd = nombreProd;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.precioUnitario = precioUnitario;
        this.nombreCategoria = nombreCategoria;
        this.cancelado = cancelado;
    }
    
    
}
