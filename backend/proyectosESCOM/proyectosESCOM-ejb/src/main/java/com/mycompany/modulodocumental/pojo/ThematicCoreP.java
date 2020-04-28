package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the thematic core entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ThematicCoreP implements Serializable {

    /**
     * if variable
     */
    private int id;

    /**
     * variable name thematic core
     */
    private String name;

    /**
     * variable credits thematic core
     */
    private int credits;

    /**
     * variable id training area
     */
    private int idTrainingArea;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public ThematicCoreP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param credits
     * @param idTrainingArea
     */
    public ThematicCoreP(int id, String name, int credits, int idTrainingArea) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.idTrainingArea = idTrainingArea;
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
