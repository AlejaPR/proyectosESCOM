/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "TBL_CONFIGURACION")
public class Configuracion implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_CONF_IDCONFIGURACION")
    private Integer idConfiguracion;
    
    @Lob
    @Column(name = "CONF_LOGO")
    private byte[] logo;
    
    @Lob
    @Column(name = "CONF_IMAGENLOGIN")
    private byte[] imagenLogin;
    
    @Size(max = 20)
    @Column(name = "CONF_BARRASUPERIOR")
    private String barraSuperior;
    
    @Size(max = 20)
    @Column(name = "CONF_BARRALATERAL")
    private String barraLateral;
    
    @Size(max = 20)
    @Column(name = "CONF_BOTONES")
    private String botones;

    public Configuracion(){
        
    }
    
    public Configuracion(byte[] logo, byte[] imagenLogin, String barraSuperior, String barraLateral, String botones) {
        this.logo = logo;
        this.imagenLogin = imagenLogin;
        this.barraSuperior = barraSuperior;
        this.barraLateral = barraLateral;
        this.botones = botones;
    }

    public Integer getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(Integer idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public byte[] getImagenLogin() {
        return imagenLogin;
    }

    public void setImagenLogin(byte[] imagenLogin) {
        this.imagenLogin = imagenLogin;
    }

    public String getBarraSuperior() {
        return barraSuperior;
    }

    public void setBarraSuperior(String barraSuperior) {
        this.barraSuperior = barraSuperior;
    }

    public String getBarraLateral() {
        return barraLateral;
    }

    public void setBarraLateral(String barraLateral) {
        this.barraLateral = barraLateral;
    }

    public String getBotones() {
        return botones;
    }

    public void setBotones(String botones) {
        this.botones = botones;
    }

    
    
}
