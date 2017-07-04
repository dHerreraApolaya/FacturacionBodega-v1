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
public class DetallePagoConsulta {
    private int idVenta,CantLetras;
    private String idCliente,cancelado,comentario,idOpcionPago;
    private double montoPagar;

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
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

    public String getIdOpcionPago() {
        return idOpcionPago;
    }

    public void setIdOpcionPago(String idOpcionPago) {
        this.idOpcionPago = idOpcionPago;
    }

    public double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(double montoPagar) {
        this.montoPagar = montoPagar;
    }

    public DetallePagoConsulta() {
    }

    public DetallePagoConsulta(int idVenta, int CantLetras, String idCliente, String cancelado, String comentario, String idOpcionPago, double montoPagar) {
        this.idVenta = idVenta;
        this.CantLetras = CantLetras;
        this.idCliente = idCliente;
        this.cancelado = cancelado;
        this.comentario = comentario;
        this.idOpcionPago = idOpcionPago;
        this.montoPagar = montoPagar;
    }
    
    
}
