package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the POJO class of the annex version entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class AnnexVersionP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable annex version
     */
    private Date date;

    /**
     * variable location annex version
     */
    private String location;

    /**
     * variable state annex version
     */
    private int state;

    /**
     * variable version annex version
     */
    private int version;

    /**
     * variable description annex version
     */
    private String description;

    /**
     * variable id user annex version
     */
    private int idUser;

    /**
     * variable id annex version
     */
    private int annex;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public AnnexVersionP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param date
     * @param location
     * @param state
     * @param version
     * @param description
     */
    public AnnexVersionP(int id, Date date, String location, int state, int version, String description) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.state = state;
        this.version = version;
        this.description = description;
    }

    //getter and setter
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
