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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_GENERAL_PROGRAM")
public class GeneralProgram implements Serializable{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_GP_ID")
    private int id;
    @Column(name = "GP_NAME")
    private String name;
    @Column(name = "GP_DESCRIPTION")
    private String description;
    
    @OneToMany(mappedBy = "fkProGeneral")
    List<Program> listProgram;
    
    @OneToMany(mappedBy = "fkThGeneral")
    List<Thematic> listThematic;
    
    @OneToMany(mappedBy = "fkTaGeneral")
    List<TrainingArea> listTrainingArea;
    
    @OneToMany(mappedBy = "fkCtGeneral")
    List<Competition> listCompetition;
    
    @OneToMany(mappedBy = "fkDfGeneral")
    List<DistinctiveFeature> listDistinctiveFeature;
    
    @OneToMany(mappedBy = "fkOpGeneral")
    List<OccupationalProfile> listOccupationalProfile;
    
    @OneToMany(mappedBy = "fkPpGeneral")
    List<ProfessionalProfile> listProfessionalProfile;
    
    @OneToMany(mappedBy = "fkTcGeneral")
    List<ThematicCore> listThematicCore;

    public GeneralProgram() {
    }

    public GeneralProgram(String name, String description) {
        this.name = name;
        this.description = description;
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

    public List<Program> getListProgram() {
        return listProgram;
    }

    public void setListProgram(List<Program> listProgram) {
        this.listProgram = listProgram;
    }

    public List<Thematic> getListThematic() {
        return listThematic;
    }

    public void setListThematic(List<Thematic> listThematic) {
        this.listThematic = listThematic;
    }

    public List<TrainingArea> getListTrainingArea() {
        return listTrainingArea;
    }

    public void setListTrainingArea(List<TrainingArea> listTrainingArea) {
        this.listTrainingArea = listTrainingArea;
    }

    public List<Competition> getListCompetition() {
        return listCompetition;
    }

    public void setListCompetition(List<Competition> listCompetition) {
        this.listCompetition = listCompetition;
    }

    public List<DistinctiveFeature> getListDistinctiveFeature() {
        return listDistinctiveFeature;
    }

    public void setListDistinctiveFeature(List<DistinctiveFeature> listDistinctiveFeature) {
        this.listDistinctiveFeature = listDistinctiveFeature;
    }

    public List<OccupationalProfile> getListOccupationalProfile() {
        return listOccupationalProfile;
    }

    public void setListOccupationalProfile(List<OccupationalProfile> listOccupationalProfile) {
        this.listOccupationalProfile = listOccupationalProfile;
    }

    public List<ProfessionalProfile> getListProfessionalProfile() {
        return listProfessionalProfile;
    }

    public void setListProfessionalProfile(List<ProfessionalProfile> listProfessionalProfile) {
        this.listProfessionalProfile = listProfessionalProfile;
    }

    public List<ThematicCore> getListThematicCore() {
        return listThematicCore;
    }

    public void setListThematicCore(List<ThematicCore> listThematicCore) {
        this.listThematicCore = listThematicCore;
    }
    
    
    
}
