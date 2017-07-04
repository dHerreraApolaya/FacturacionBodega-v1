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
public class TipoPago {
    private int idOpcionPago;
    private String nombreOpcionPago,cancelado;

    public int getIdOpcionPago() {
        return idOpcionPago;
    }

    public void setIdOpcionPago(int idOpcionPago) {
        this.idOpcionPago = idOpcionPago;
    }

    public String getNombreOpcionPago() {
        return nombreOpcionPago;
    }

    public void setNombreOpcionPago(String nombreOpcionPago) {
        this.nombreOpcionPago = nombreOpcionPago;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public TipoPago() {
    }

    public TipoPago(int idOpcionPago, String nombreOpcionPago, String cancelado) {
        this.idOpcionPago = idOpcionPago;
        this.nombreOpcionPago = nombreOpcionPago;
        this.cancelado = cancelado;
    }
    
    
}
