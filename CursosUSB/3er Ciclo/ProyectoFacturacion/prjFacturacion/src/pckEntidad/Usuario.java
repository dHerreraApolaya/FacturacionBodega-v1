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
public class Usuario {
    private String codUser,nombre,apellido,contraseña,estado,nivel,cancelado;
    
    
    public String getCodUser() {
        return codUser;
    }

    public void setCodUser(String codUser) {
        this.codUser = codUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

   

    public Usuario() {
    }

    public Usuario(String codUser, String nombre, String apellido, String contraseña, String estado, String nivel, String cancelado) {
        this.codUser = codUser;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.estado = estado;
        this.nivel = nivel;
        this.cancelado = cancelado;
    }

    

    
}
