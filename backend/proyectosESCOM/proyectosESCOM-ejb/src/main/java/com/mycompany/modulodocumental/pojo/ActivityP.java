package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the activity entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ActivityP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable name activity
     */
    private String name;

    /**
     * variable description activity
     */
    private String description;

    /**
     * variable information activity
     */
    private String information;

    /**
     * variable state activity
     */
    private int state;

    /**
     * variable number activity
     */
    private String number;

    /**
     * variable parent activity
     */
    private int parentActivity;

    /**
     * variable id condition activity
     */
    private int idCondition;

    /**
     * variable id annex activity
     */
    private int idAnnex;

    /**
     * variable type activity
     */
    private int type;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public ActivityP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param description
     * @param information
     * @param state
     * @param type
     * @param number
     */
    public ActivityP(int id, String name, String description, String information, int state, int type, String number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.information = information;
        this.state = state;
        this.type = type;
        this.number = number;
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
