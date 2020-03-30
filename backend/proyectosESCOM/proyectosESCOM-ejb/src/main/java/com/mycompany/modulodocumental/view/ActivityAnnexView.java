/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.view;

import java.io.Serializable;

/**
 *
 * @author hashy
 */
public class ActivityAnnexView implements Serializable{
    private int idActivity;
    private String nameActivity;
    private String descriptionActivity;
    private int idAnnex;
    private String nameAnnex;
    private String url;

    public ActivityAnnexView() {
    }

    public ActivityAnnexView(int idActivity, String nameActivity, String descriptionActivity, int idAnnex, String nameAnnex, String url) {
        this.idActivity = idActivity;
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.idAnnex = idAnnex;
        this.nameAnnex = nameAnnex;
        this.url = url;
    }

    public int getIdActivity() {
        return idActivity;
    }

    public void setIdActivity(int idActivity) {
        this.idActivity = idActivity;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public int getIdAnnex() {
        return idAnnex;
    }

    public void setIdAnnex(int idAnnex) {
        this.idAnnex = idAnnex;
    }

    public String getNameAnnex() {
        return nameAnnex;
    }

    public void setNameAnnex(String nameAnnex) {
        this.nameAnnex = nameAnnex;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
