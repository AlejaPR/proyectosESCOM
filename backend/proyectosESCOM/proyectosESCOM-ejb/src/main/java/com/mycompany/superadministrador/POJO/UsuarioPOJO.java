/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;

import java.util.Date;

/**
 *
 * @author jeiso
 */
public class UsuarioPOJO {
    
    private int id;
    
   private String correoElectronico;
   
   private String estado;
   
   private int numeroDocumento;
   
   private String nombre;
   
   private String apellido;
   
   private String contrasena;
   
   private Date fechaNacimiento;
   
   private int tipoDocumento;
   
   private String token;
   
   private String fechaDeNacimiento;
   
   private DatosSolicitudPOJO datosSolicitud;

    public UsuarioPOJO() {
    }
   
    public UsuarioPOJO(String correoElectronico, int numeroDocumento, String nombre, String apellido, String contrasena, Date fechaNacimiento, int tipoDocumento) {
        this.correoElectronico = correoElectronico;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    }

}
