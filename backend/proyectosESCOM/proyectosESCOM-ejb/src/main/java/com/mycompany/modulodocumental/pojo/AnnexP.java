package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the annex entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class AnnexP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable keywords annex
     */
    private String keywords;

    /**
     * variable description annex
     */
    private String description;

    /**
     * variable name annex
     */
    private String name;

    /**
     * variable id program annex
     */
    private int program;

    /**
     * variable name program annex
     */
    private String nameProgram;

    /**
     * variable state annex
     */
    private int state;

    /**
     * variable link annex
     */
    private String link;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public AnnexP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param keywords
     * @param description
     * @param name
     */
    public AnnexP(int id, String keywords, String description, String name) {
        this.id = id;
        this.keywords = keywords;
        this.description = description;
        this.name = name;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

    public String getNameProgram() {
        return nameProgram;
    }

    public void setNameProgram(String nameProgram) {
        this.nameProgram = nameProgram;
    }

}
