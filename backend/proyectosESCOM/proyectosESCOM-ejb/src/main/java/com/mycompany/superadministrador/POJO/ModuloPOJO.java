/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;

/**
 *
 * @author aleja
 */
public class ModuloPOJO {
    
    private int idModulo;
    
    private String estadoModulo;
    
    private String imagenModulo;
    
    private String nombreModulo;
    
    private String descripcionModulo;
    
    private String acronimo;

    public ModuloPOJO() {
    }

    public ModuloPOJO(int idModulo, String estadoModulo, String imagenModulo, String nombreModulo, String descripcionModulo, String acronimo) {
        this.idModulo = idModulo;
        this.estadoModulo = estadoModulo;
        this.imagenModulo = imagenModulo;
        this.nombreModulo = nombreModulo;
        this.descripcionModulo = descripcionModulo;
        this.acronimo = acronimo;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getEstadoModulo() {
        return estadoModulo;
    }

    public void setEstadoModulo(String estadoModulo) {
        this.estadoModulo = estadoModulo;
    }

    public String getImagenModulo() {
        return imagenModulo;
    }

    public void setImagenModulo(String imagenModulo) {
        this.imagenModulo = imagenModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getDescripcionModulo() {
        return descripcionModulo;
    }

    public void setDescripcionModulo(String descripcionModulo) {
        this.descripcionModulo = descripcionModulo;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }
    
    
    
}
