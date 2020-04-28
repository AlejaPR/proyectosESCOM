package com.mycompany.modulodocumental.view;

import java.io.Serializable;

/**
 * This is the view class of the activity annex entity. contains the variables
 * of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ActivityAnnexView implements Serializable {

    /**
     * variable id activity
     */
    private int idActivity;

    /**
     * variable name activity
     */
    private String nameActivity;

    /**
     * variable name description activity
     */
    private String descriptionActivity;

    /**
     * variable id annex
     */
    private int idAnnex;

    /**
     * variable name annex
     */
    private String nameAnnex;

    /**
     * variable url annex
     */
    private String url;

    /**
     * constructor method
     */
    public ActivityAnnexView() {
    }

    /**
     * constructor method
     *
     * @param idActivity
     * @param nameActivity
     * @param descriptionActivity
     * @param idAnnex
     * @param nameAnnex
     * @param url
     */
    public ActivityAnnexView(int idActivity, String nameActivity, String descriptionActivity, int idAnnex, String nameAnnex, String url) {
        this.idActivity = idActivity;
        this.nameActivity = nameActivity;
        this.descriptionActivity = descriptionActivity;
        this.idAnnex = idAnnex;
        this.nameAnnex = nameAnnex;
        this.url = url;
    }

    //getter and setter
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
