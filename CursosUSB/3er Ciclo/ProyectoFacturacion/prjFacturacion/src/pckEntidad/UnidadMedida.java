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
public class UnidadMedida {
    private int idUnidadMedidad;
    private String nombreMedida,cancelado;

    public int getIdUnidadMedidad() {
        return idUnidadMedidad;
    }

    public void setIdUnidadMedidad(int idUnidadMedidad) {
        this.idUnidadMedidad = idUnidadMedidad;
    }

    public String getNombreMedida() {
        return nombreMedida;
    }

    public void setNombreMedida(String nombreMedida) {
        this.nombreMedida = nombreMedida;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    
    public UnidadMedida() {
    }

    public UnidadMedida(int idUnidadMedidad, String nombreMedida, String cancelado) {
        this.idUnidadMedidad = idUnidadMedidad;
        this.nombreMedida = nombreMedida;
        this.cancelado = cancelado;
    }

        
    
}
