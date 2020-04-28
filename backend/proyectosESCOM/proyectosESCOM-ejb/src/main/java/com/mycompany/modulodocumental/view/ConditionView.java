package com.mycompany.modulodocumental.view;

import java.io.Serializable;

/**
 * This is the view class of the condition entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ConditionView implements Serializable {

    /**
     * variable id condition
     */
    private int id;

    /**
     * variable name condition
     */
    private String name;

    /**
     * variable description condition
     */
    private String description;

    /**
     * variabe state condition
     */
    private int state;

    /**
     * variable percentage condition
     */
    private int percentage;

    /**
     * constructor method
     */
    public ConditionView() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param description
     * @param state
     * @param percentage
     */
    public ConditionView(int id, String name, String description, int state, int percentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.percentage = percentage;
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
