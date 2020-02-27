/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;

/**
 *
 * @author jeiso
 */
public class ActividadPOJO {

    private int idActividad;
    private String nombre;
    private String descripcionActividad;
    private String moduloActividad;
    private String estado;

    private int idModulo;

    public ActividadPOJO() {
    }

    public ActividadPOJO(int idActividad, String nombre) {
        this.idActividad = idActividad;
        this.nombre = nombre;

    }

    public ActividadPOJO(int idActividad, String nombre, String descripcionActividad, String moduloActividad) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcionActividad = descripcionActividad;
        this.moduloActividad = moduloActividad;
    }
    
    public ActividadPOJO(int idActividad, String nombre,int idModulo) {
        this.idActividad = idActividad;
        this.nombre = nombre;
       this.idModulo = idModulo;
    }
    
    public ActividadPOJO(int idActividad, String nombre, int idModulo, String estado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.idModulo=idModulo;
        this.estado=estado;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getModuloActividad() {
        return moduloActividad;
    }

    public void setModuloActividad(String moduloActividad) {
        this.moduloActividad = moduloActividad;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
