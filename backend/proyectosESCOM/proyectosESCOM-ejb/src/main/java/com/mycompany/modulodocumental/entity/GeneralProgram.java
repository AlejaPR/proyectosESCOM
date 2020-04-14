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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the class of the general program entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_GENERAL_PROGRAM")
public class GeneralProgram implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_GP_ID")
    private int id;

    /**
     * variable name general program
     */
    @Column(name = "GP_NAME")
    private String name;

    /**
     * variable description general program
     */
    @Column(name = "GP_DESCRIPTION")
    private String description;

    /**
     * variable state general program
     */
    @Column(name = "GP_STATE")
    private int state;

    /**
     * Variable for the relationship with the program entity
     */
    @OneToMany(mappedBy = "fkProGeneral", cascade = CascadeType.ALL)
    List<Program> listProgram;

    /**
     * Variable for the relationship with the thematic entity
     */
    @OneToMany(mappedBy = "fkThGeneral", cascade = CascadeType.ALL)
    List<Thematic> listThematic;

    /**
     * Variable for the relationship with the training area entity
     */
    @OneToMany(mappedBy = "fkTaGeneral", cascade = CascadeType.ALL)
    List<TrainingArea> listTrainingArea;

    /**
     * Variable for the relationship with the competition entity
     */
    @OneToMany(mappedBy = "fkCtGeneral", cascade = CascadeType.ALL)
    List<Competition> listCompetition;

    /**
     * Variable for the relationship with the distinctive feature entity
     */
    @OneToMany(mappedBy = "fkDfGeneral", cascade = CascadeType.ALL)
    List<DistinctiveFeature> listDistinctiveFeature;

    /**
     * Variable for the relationship with the occupational profile entity
     */
    @OneToMany(mappedBy = "fkOpGeneral", cascade = CascadeType.ALL)
    List<OccupationalProfile> listOccupationalProfile;

    /**
     * Variable for the relationship with the professional profile entity
     */
    @OneToMany(mappedBy = "fkPpGeneral", cascade = CascadeType.ALL)
    List<ProfessionalProfile> listProfessionalProfile;

    /**
     * constructor method
     */
    public GeneralProgram() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param description
     * @param state
     */
    public GeneralProgram(String name, String description, int state) {
        this.name = name;
        this.description = description;
        this.state = state;
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

}
