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
public class ConditionP implements Serializable {

    private int id;
    private String name;
    private String description;
    private int state;
    private Date startDate;
    private Date finalDate;
    private int process;
    private DatosSolicitudPOJO requestData;

    public ConditionP() {
    }

    public ConditionP(int id, String name, String description, int state, Date startDate, Date finalDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.startDate = startDate;
        this.finalDate = finalDate;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
