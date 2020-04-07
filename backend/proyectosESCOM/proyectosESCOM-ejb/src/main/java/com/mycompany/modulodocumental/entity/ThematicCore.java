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
@Table(name = "TBL_THEMATIC_CORE")
public class ThematicCore implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TC_ID")
    private int id;
    @Column(name = "TC_NAME")
    private String name;
    @Column(name = "TC_CREDITS")
    private int credits;
    @Column(name = "TC_OBJECTIVE")
    private String objective;

    @OneToMany(mappedBy = "fkPtThematicCore")
    private List<ProgramThematicCore> listProgramThematicCore;

    @JoinColumn(name = "FK_TC_TRAINING_AREA", referencedColumnName = "PK_TA_ID")
    @ManyToOne
    private TrainingArea fkTcTrainingArea;

    public ThematicCore() {
    }

    public ThematicCore(String name, int credits, String objective) {
        this.name = name;
        this.credits = credits;
        this.objective = objective;
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public List<ProgramThematicCore> getListProgramThematicCore() {
        return listProgramThematicCore;
    }

    public void setListProgramThematicCore(List<ProgramThematicCore> listProgramThematicCore) {
        this.listProgramThematicCore = listProgramThematicCore;
    }

    public TrainingArea getFkTcTrainingArea() {
        return fkTcTrainingArea;
    }

    public void setFkTcTrainingArea(TrainingArea fkTcTrainingArea) {
        this.fkTcTrainingArea = fkTcTrainingArea;
    }

}
