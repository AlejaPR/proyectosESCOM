/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;

import java.util.Date;

/**
 *
 * @author aleja
 */
public class ReportesPOJO {
    
    private int idBusqueda;
    private String palabraBusqueda;
    private Date fechaInicio;
    private Date fechaFin;

    public ReportesPOJO(int idBusqueda, String palabraBusqueda, Date fechaInicio, Date fechaFin) {
        this.idBusqueda = idBusqueda;
        this.palabraBusqueda = palabraBusqueda;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    } 

    public int getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(int idBusqueda) {
        this.idBusqueda = idBusqueda;
    }

    public String getPalabraBusqueda() {
        return palabraBusqueda;
    }

    public void setPalabraBusqueda(String palabraBusqueda) {
        this.palabraBusqueda = palabraBusqueda;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
}
