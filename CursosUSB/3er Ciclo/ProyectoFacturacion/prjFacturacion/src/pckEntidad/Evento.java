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
public class Evento {
    private int idEvento;
    private String loginUser,evento,nombretabla;
    private String hora;
    private String fecha,cantelado;

    public String getCantelado() {
        return cantelado;
    }

    public void setCantelado(String cantelado) {
        this.cantelado = cantelado;
    }

    
    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getNombretabla() {
        return nombretabla;
    }

    public void setNombretabla(String nombretabla) {
        this.nombretabla = nombretabla;
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

    
    public Evento() {
    }

    public Evento(int idEvento, String loginUser, String evento, String nombretabla, String hora, String fecha, String cantelado) {
        this.idEvento = idEvento;
        this.loginUser = loginUser;
        this.evento = evento;
        this.nombretabla = nombretabla;
        this.hora = hora;
        this.fecha = fecha;
        this.cantelado = cantelado;
    }

    

    

    
}
