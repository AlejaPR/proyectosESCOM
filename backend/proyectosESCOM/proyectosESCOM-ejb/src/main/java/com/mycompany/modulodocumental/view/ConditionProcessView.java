package com.mycompany.modulodocumental.view;

import com.mycompany.modulodocumental.pojo.ActivityP;
import java.io.Serializable;
import java.util.List;

/**
 * This is the view class of the condition process entity. contains the
 * variables of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ConditionProcessView implements Serializable {

    /**
     * variable name condition
     */
    private String name;

    /**
     * variable description condition
     */
    private String description;

    /**
     * variable string managers
     */
    private String managers;

    /**
     * variable list activities
     */
    private List<ActivityP> listActivities;

    /**
     * constructor method
     */
    public ConditionProcessView() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param description
     * @param managers
     * @param listActivities
     */
    public ConditionProcessView(String name, String description, String managers, List<ActivityP> listActivities) {
        this.name = name;
        this.description = description;
        this.managers = managers;
        this.listActivities = listActivities;
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

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public List<ActivityP> getListActivities() {
        return listActivities;
    }

    public void setListActivities(List<ActivityP> listActivities) {
        this.listActivities = listActivities;
    }

}
