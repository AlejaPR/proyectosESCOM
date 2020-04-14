/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 *
 * @author hashy
 */
public class ThematicCoreP implements Serializable {

    private int id;
    private String name;
    private int credits;
    private int idTrainingArea;
    private DatosSolicitudPOJO requestData;

    public ThematicCoreP() {
    }

    public ThematicCoreP(int id, String name, int credits, int idTrainingArea) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.idTrainingArea = idTrainingArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getIdTrainingArea() {
        return idTrainingArea;
    }

    public void setIdTrainingArea(int idTrainingArea) {
        this.idTrainingArea = idTrainingArea;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }
    
}
