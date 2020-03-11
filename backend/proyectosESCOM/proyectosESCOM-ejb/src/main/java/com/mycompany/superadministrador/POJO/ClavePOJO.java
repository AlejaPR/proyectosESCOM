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
public class ClavePOJO {
    
    private String token;
    private String antiguaClave;
    private String nuevaClave;

    public ClavePOJO() {
    }

    public ClavePOJO(String token, String antiguaClave, String nuevaClave) {
        this.token = token;
        this.antiguaClave = antiguaClave;
        this.nuevaClave = nuevaClave;
    }
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAntiguaClave() {
        return antiguaClave;
    }

    public void setAntiguaClave(String antiguaClave) {
        this.antiguaClave = antiguaClave;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    
    
}
