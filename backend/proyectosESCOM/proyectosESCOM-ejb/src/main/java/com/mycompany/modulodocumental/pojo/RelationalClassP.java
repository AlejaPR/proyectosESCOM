package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the relational class entity. contains the variables
 * of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class RelationalClassP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable id relation
     */
    private int idRelation;

    /**
     * variable name relation
     */
    private String nameRelation;

    /**
     * variable table relation
     */
    private String table;

    /**
     * variable id program thematic
     */
    private int idProgramaThematic;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public RelationalClassP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param idRelation
     * @param nameRelation
     * @param idProgramaThematic
     */
    public RelationalClassP(int id, int idRelation, String nameRelation, int idProgramaThematic) {
        this.id = id;
        this.idRelation = idRelation;
        this.nameRelation = nameRelation;
        this.idProgramaThematic = idProgramaThematic;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRelation() {
        return idRelation;
    }

    public void setIdRelation(int idRelation) {
        this.idRelation = idRelation;
    }

    public String getNameRelation() {
        return nameRelation;
    }

    public void setNameRelation(String nameRelation) {
        this.nameRelation = nameRelation;
    }

    public int getIdProgramaThematic() {
        return idProgramaThematic;
    }

    public void setIdProgramaThematic(int idProgramaThematic) {
        this.idProgramaThematic = idProgramaThematic;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
