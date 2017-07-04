/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pckEntidad;

/**
 *
 * @author Alumno
 */
public class Categoria {
    
    private int idCategoria;
    private String nombreCategoria,abreviatura,cancelado;

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Categoria() {
    }

    public Categoria(int idCategoria, String nombreCategoria, String abreviatura, String cancelado) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.abreviatura = abreviatura;
        this.cancelado = cancelado;
    }

    
    
    
}
