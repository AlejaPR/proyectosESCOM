/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HASHY
 */
public class AnnexVersionP implements Serializable {

    private int id;
    private Date date;
    private String location;
    private int state;
    private int version;
    private String description;
    private int idUser;
    private int annex;
    private DatosSolicitudPOJO requestData;
    
    
    public AnnexVersionP() {
    }
    
    public AnnexVersionP(int id, Date date, String location, int state, int version, String description) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.state = state;
        this.version = version;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getAnnex() {
        return annex;
    }

    public void setAnnex(int annex) {
        this.annex = annex;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }
    
}
