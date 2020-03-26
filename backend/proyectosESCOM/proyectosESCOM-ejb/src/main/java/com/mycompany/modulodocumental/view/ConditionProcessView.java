/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.view;

import com.mycompany.modulodocumental.pojo.ActivityP;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author HASHY
 */
public class ConditionProcessView implements Serializable{
    
    private String name;
    private String description;
    private String managers;
    private List<ActivityP> listActivities;

    public ConditionProcessView() {
    }

    public ConditionProcessView(String name, String description, String managers, List<ActivityP> listActivities) {
        this.name = name;
        this.description = description;
        this.managers = managers;
        this.listActivities = listActivities;
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
