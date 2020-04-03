/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 *
 * @author hashy
 */
public class RelationalClassP implements Serializable {

    private int id;
    private int idRelation;
    private String nameRelation;
    private String table;
    private int idProgramaThematic;
    private DatosSolicitudPOJO requestData;

    public RelationalClassP() {
    }

    public RelationalClassP(int id, int idRelation, String nameRelation, int idProgramaThematic) {
        this.id = id;
        this.idRelation = idRelation;
        this.nameRelation = nameRelation;
        this.idProgramaThematic = idProgramaThematic;
    }

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
