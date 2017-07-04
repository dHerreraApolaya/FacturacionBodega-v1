/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckEntidad;

import java.awt.Image;

/**
 *
 * @author Alumno
 */
public class Producto {
    private int idProducto,idUnidadMedidad,idCategoria;
    private String nombreProducto;
    private double precioUnitario;
    private String cancelado;
    private Image imagenProd;
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUnidadMedidad() {
        return idUnidadMedidad;
    }

    public void setIdUnidadMedidad(int idUnidadMedidad) {
        this.idUnidadMedidad = idUnidadMedidad;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public Image getImagenProd() {
        return imagenProd;
    }

    public void setImagenProd(Image imagenProd) {
        this.imagenProd = imagenProd;
    }

    
    public Producto() {
    }

    public Producto(int idProducto, int idUnidadMedidad, int idCategoria, String nombreProducto, double precioUnitario, String cancelado, Image imagenProd) {
        this.idProducto = idProducto;
        this.idUnidadMedidad = idUnidadMedidad;
        this.idCategoria = idCategoria;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cancelado = cancelado;
        this.imagenProd = imagenProd;
    }

    
    
    
}
