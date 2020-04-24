package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the POJO class of the condition entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ConditionP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * varible name condition
     */
    private String name;

    /**
     * varible description condition
     */
    private String description;

    /**
     * varible state condition
     */
    private int state;

    /**
     * variable start Date condition
     */
    private Date startDate;

    /**
     * varible final date condition
     */
    private Date finalDate;

    /**
     * varible start date string condition
     */
    private String startDateS;

    /**
     * varible final date string condition
     */
    private String finalDateS;

    /**
     * varible id process condition
     */
    private int process;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public ConditionP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param description
     * @param state
     * @param startDate
     * @param finalDate
     */
    public ConditionP(int id, String name, String description, int state, Date startDate, Date finalDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param description
     * @param state
     * @param startDateS
     * @param finalDateS
     * @param ol
     */
    public ConditionP(int id, String name, String description, int state, String startDateS, String finalDateS, int ol) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.startDateS = startDateS;
        this.finalDateS = finalDateS;
    }

    //getter and setter
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

    public String getStartDateS() {
        return startDateS;
    }

    public void setStartDateS(String startDateS) {
        this.startDateS = startDateS;
    }

    public String getFinalDateS() {
        return finalDateS;
    }

    public void setFinalDateS(String finalDateS) {
        this.finalDateS = finalDateS;
    }

}
