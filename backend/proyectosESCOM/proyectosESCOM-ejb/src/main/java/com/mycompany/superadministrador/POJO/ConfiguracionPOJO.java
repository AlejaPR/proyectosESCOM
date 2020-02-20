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
public class ConfiguracionPOJO {
    
    private int idConfiguracion;
    private byte[] logo;
    private byte[] imagenLogin;
    private String barraSuperior;
    private String barraLateral;
    private String botones;

    public ConfiguracionPOJO() {
    }

    public ConfiguracionPOJO(int idConfiguracion,byte[] logo, byte[] imagenLogin, String barraSuperior, String barraLateral, String botones) {
        this.idConfiguracion = idConfiguracion;
        this.logo = logo;
        this.imagenLogin = imagenLogin;
        this.barraSuperior = barraSuperior;
        this.barraLateral = barraLateral;
        this.botones = botones;
    }

    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
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
