package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the annex entity class. Contains all fields for persistence
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_ANNEX")
public class Annex implements Serializable {
    
    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_AX_ID")
    private int id;
    
    /**
     * variable keywords annex
     */
    @Column(name = "AX_KEYWORDS")
    private String keywords;
    
     /**
      * variable description annex
      */
    @Column(name = "AX_DESCRIPTION")
    private String description;
    
    /**
     * variable name annex
     */
    @Column(name = "AX_NAME")
    private String name;
    
    /**
     * variable state annex
     */
    @Column(name = "AX_STATE")
    private int state;

    /**
     * Variable for the relationship with the program entity
     */
    @JoinColumn(name = "FK_AX_PROGRAM", referencedColumnName = "PK_PRO_ID")
    @ManyToOne
    private Program fkAxProgram;

    /**
     * Variable for the relationship with the entity version annex
     */
    @OneToMany(mappedBy = "fkAvAnnex", cascade = CascadeType.ALL)
    private List<AnnexVersion> listAnnexVersion;

    /**
     * Variable for the relationship with the activity entity
     */
    @OneToMany(mappedBy = "fkActAnnex", cascade = CascadeType.ALL)
    private List<Activity> listActivity;

    /**
     * constructor method
     */
    public Annex() {
    }
    
    /**
     * constructor method
     * @param keywords
     * @param description
     * @param name 
     */
    public Annex(String keywords, String description, String name) {
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

    public Program getFkAxProgram() {
        return fkAxProgram;
    }

    public void setFkAxProgram(Program fkAxProgram) {
        this.fkAxProgram = fkAxProgram;
    }

    public List<AnnexVersion> getListAnnexVersion() {
        return listAnnexVersion;
    }

    public void setListAnnexVersion(List<AnnexVersion> listAnnexVersion) {
        this.listAnnexVersion = listAnnexVersion;
    }

    public List<Activity> getListActivity() {
        return listActivity;
    }

    public void setListActivity(List<Activity> listActivity) {
        this.listActivity = listActivity;
    }

}
