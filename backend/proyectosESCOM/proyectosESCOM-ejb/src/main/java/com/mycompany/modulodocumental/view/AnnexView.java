package com.mycompany.modulodocumental.view;

import java.io.Serializable;

/**
 * This is the view class of the annex entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class AnnexView implements Serializable {

    /**
     * variable name annex
     */
    private String name;

    /**
     * variable description annex
     */
    private String description;

    /**
     * variable name condition
     */
    private String condition;

    /**
     * variable keywords annex
     */
    private String keywords;

    /**
     * constructor method
     */
    public AnnexView() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param description
     * @param condition
     * @param keywords
     */
    public AnnexView(String name, String description, String condition, String keywords) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.keywords = keywords;
    }

    //getter and setter
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

}
