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
public class DetallePago {
    private int idVenta,idOpcionPago,CantLetras;
    private String idCliente,cancelado,comentario;
    private double montoPagar;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdOpcionPago() {
        return idOpcionPago;
    }

    public void setIdOpcionPago(int idOpcionPago) {
        this.idOpcionPago = idOpcionPago;
    }

    public int getCantLetras() {
        return CantLetras;
    }

    public void setCantLetras(int CantLetras) {
        this.CantLetras = CantLetras;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public DetallePago() {
    }

    public DetallePago(int idVenta, int idOpcionPago, int CantLetras, String idCliente, String cancelado, String comentario, double montoPagar) {
        this.idVenta = idVenta;
        this.idOpcionPago = idOpcionPago;
        this.CantLetras = CantLetras;
        this.idCliente = idCliente;
        this.cancelado = cancelado;
        this.comentario = comentario;
        this.montoPagar = montoPagar;
    }
    
    

}
