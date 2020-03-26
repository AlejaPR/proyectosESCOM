/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.view;

/**
 *
 * @author HASHY
 */

public class ConditionView {
    
    private int id;
    private String name;
    private String description;
    private int state;
    private int percentage;

    public ConditionView() {
    }

    public ConditionView(int id,String name, String description, int state, int percentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.percentage = percentage;
    }

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

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
    
    
}
