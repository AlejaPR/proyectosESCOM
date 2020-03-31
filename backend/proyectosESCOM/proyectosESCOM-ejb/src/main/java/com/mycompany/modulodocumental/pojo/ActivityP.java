/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * @author HASHY
 */
public class ActivityP implements Serializable {

    private int id;
    private String name;
    private String description;
    private String information;
    private int state;
    private String number;
    private int parentActivity;
    private int idCondition;
    private int idAnnex;
    private int type;
    private DatosSolicitudPOJO requestData;

    public ActivityP() {
    }

    public ActivityP(int id, String name, String description, String information, int state, int type, String number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.information = information;
        this.state = state;
        this.type = type;
        this.number = number;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public int getParentActivity() {
        return parentActivity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setParentActivity(int parentActivity) {
        this.parentActivity = parentActivity;
    }

    public int getIdCondition() {
        return idCondition;
    }

    public void setIdCondition(int idCondition) {
        this.idCondition = idCondition;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdAnnex() {
        return idAnnex;
    }

    public void setIdAnnex(int idAnnex) {
        this.idAnnex = idAnnex;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
