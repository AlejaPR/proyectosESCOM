package com.mycompany.modulodocumental.pojo;

import java.io.Serializable;

/**
 * This is the POJO class of the search annex entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class SearchAnnP implements Serializable {

    /**
     * variable id program
     */
    private int idProgram;

    /**
     * variable name
     */
    private String name;

    /**
     * constructor method
     */
    public SearchAnnP() {
    }

    /**
     * constructor method
     *
     * @param idProgram
     * @param name
     */
    public SearchAnnP(int idProgram, String name) {
        this.idProgram = idProgram;
        this.name = name;
    }

    //getter and setter
    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
