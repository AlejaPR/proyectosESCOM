package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;
import java.util.Date;

/**
 * This is the POJO class of the document version entity. contains the variables
 * of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class DocumentVersionP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable description document version
     */
    private String description;

    /**
     * variable version document version
     */
    private int version;

    /**
     * variable location document version
     */
    private String location;

    /**
     * variable state document version
     */
    private int state;

    /**
     * variable date document version
     */
    private Date date;

    /**
     * variable id document
     */
    private int document;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public DocumentVersionP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param description
     * @param version
     * @param location
     * @param state
     * @param date
     */
    public DocumentVersionP(int id, String description, int version, String location, int state, Date date) {
        this.id = id;
        this.description = description;
        this.version = version;
        this.location = location;
        this.state = state;
        this.date = date;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

}
