package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the document entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class DocumentP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable description document
     */
    private String description;

    /**
     * variable type document
     */
    private String type;

    /**
     * variable state document
     */
    private int state;

    /**
     * variable id user document
     */
    private int idUser;

    /**
     * variable name programa document
     */
    private String program;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public DocumentP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param description
     * @param type
     * @param state
     */
    public DocumentP(int id, String description, String type, int state) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.state = state;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
