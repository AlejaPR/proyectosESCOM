/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.view;

import java.io.Serializable;

/**
 *
 * @author HASHY
 */
public class AnnexView implements Serializable{
    
    private String name;
    private String description;
    private String condition;
    private String keywords;

    public AnnexView() {
    }

    public AnnexView(String name, String description, String condition, String keywords) {
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.keywords = keywords;
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
