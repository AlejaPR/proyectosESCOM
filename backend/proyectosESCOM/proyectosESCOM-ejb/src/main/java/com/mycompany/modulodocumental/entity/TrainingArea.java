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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_TRAINING_AREA")
public class TrainingArea implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TA_ID")
    private int id;
    @Column(name = "TA_NAME")
    private String name;
    
    @JoinColumn(name = "FK_TA_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkTaGeneral;
    
    @OneToMany(mappedBy = "fkTcTrainingArea")
    private List<ThematicCore> listThematicCore;

    public TrainingArea() {
    }

    public TrainingArea(String name) {
        this.name = name;
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

    public List<ThematicCore> getListThematicCore() {
        return listThematicCore;
    }

    public void setListThematicCore(List<ThematicCore> listThematicCore) {
        this.listThematicCore = listThematicCore;
    }

    public GeneralProgram getFkTaGeneral() {
        return fkTaGeneral;
    }

    public void setFkTaGeneral(GeneralProgram fkTaGeneral) {
        this.fkTaGeneral = fkTaGeneral;
    }
    
}
