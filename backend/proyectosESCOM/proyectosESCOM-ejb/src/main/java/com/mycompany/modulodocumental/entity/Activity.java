/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HASHY
 */
@Entity
@Table(name = "TBL_ACTIVITY")
public class Activity implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_ACT_ID")
    private int id;
    @Column(name = "ACT_NAME")
    private String name;
    @Column(name = "ACT_DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "ACT_INFORMATION")
    private String information;
    @Column(name = "ACT_STATE")
    private int state;
    @Column(name = "ACT_TYPE")
    private int type;
    
    @JoinColumn(name = "FK_ACT_CONDITION", referencedColumnName = "PK_CON_ID")
    @ManyToOne
    private Condition fkActCondition;
    
    @JoinColumn(name = "FK_ACT_ANNEX", referencedColumnName = "PK_AX_ID")
    @ManyToOne
    private Annex fkActAnnex;
    
    @OneToMany(mappedBy = "fkComActivity")
    private List<Commentary> listCommentary;

    public Activity() {
    }

    public Activity(String name, String description, String information, int state,int type) {
        this.name = name;
        this.description = description;
        this.information = information;
        this.state = state;
        this.type = type;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Condition getFkActCondition() {
        return fkActCondition;
    }

    public void setFkActCondition(Condition fkActCondition) {
        this.fkActCondition = fkActCondition;
    }
    
    public Annex getFkActAnnex() {
        return fkActAnnex;
    }

    public void setFkActAnnex(Annex fkActAnnex) {
        this.fkActAnnex = fkActAnnex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Commentary> getListCommentary() {
        return listCommentary;
    }

    public void setListCommentary(List<Commentary> listCommentary) {
        this.listCommentary = listCommentary;
    }

}
