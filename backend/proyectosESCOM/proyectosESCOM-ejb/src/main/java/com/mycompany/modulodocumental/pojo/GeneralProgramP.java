package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the general program entity. contains the variables
 * of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class GeneralProgramP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable name general program
     */
    private String name;

    /**
     * variable description general program
     */
    private String description;

    /**
     * variable state general program
     */
    private int state;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public GeneralProgramP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param description
     * @param state
     */
    public GeneralProgramP(int id, String name, String description, int state) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
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

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
