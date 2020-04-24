package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the competition general entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class CompetitionGeneralP implements Serializable {

    /***
     * id variable
     */
    private int id;
    
    /**
     * variable name competition general
     */
    private String name;
    
    /**
     * variable id competition
     */
    private int idCompetition;
    
    /**
     * variable name competition
     */
    private String nameCompetition;
    
    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public CompetitionGeneralP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param idCompetition
     */
    public CompetitionGeneralP(int id, String name, int idCompetition) {
        this.id = id;
        this.name = name;
        this.idCompetition = idCompetition;
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

    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNameCompetition() {
        return nameCompetition;
    }

    public void setNameCompetition(String nameCompetition) {
        this.nameCompetition = nameCompetition;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
