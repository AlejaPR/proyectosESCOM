package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the general class entity. contains the variables of
 * the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class GeneralClassP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable name general class
     */
    private String name;

    /**
     * variable id general
     */
    private int idGeneral;

    /**
     * variable table general class
     */
    private String table;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public GeneralClassP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     */
    public GeneralClassP(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getIdGeneral() {
        return idGeneral;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setIdGeneral(int idGeneral) {
        this.idGeneral = idGeneral;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
