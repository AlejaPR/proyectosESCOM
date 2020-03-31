/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_PROGRAM_THEMATIC_CORE")
public class ProgramThematicCore implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PT_ID")
    private int id;
    @Column(name = "PT_CONTRIBUTE_OBJECTIVE")
    private String contributeObjetive;
    @Column(name = "PT_CONTRIBUTE_PROFESSIONAL")
    private String contributeProfessional;
    @Column(name = "PT_CONTRIBUTE_OCCUPATIONAL")
    private String contributeOccupational;
    @Column(name = "PT_OBJECTIVE_OUTPUT")
    private String objectiveOutput;
    @Column(name = "PT_TEAM_CONTRIBUTION")
    private String teamContribution;
    @Column(name = "PT_OBSERVATION_FINAL")
    private String observationFinal;
    
    private Program fkPtProgram;
    private ThematicCore fkPtThematicCore;

    public ProgramThematicCore() {
    }

    public ProgramThematicCore(int id, String contributeObjetive, String contributeProfessional, String contributeOccupational, String objectiveOutput, String teamContribution, String observationFinal) {
        this.id = id;
        this.contributeObjetive = contributeObjetive;
        this.contributeProfessional = contributeProfessional;
        this.contributeOccupational = contributeOccupational;
        this.objectiveOutput = objectiveOutput;
        this.teamContribution = teamContribution;
        this.observationFinal = observationFinal;
        
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContributeObjetive() {
        return contributeObjetive;
    }

    public void setContributeObjetive(String contributeObjetive) {
        this.contributeObjetive = contributeObjetive;
    }

    public String getContributeProfessional() {
        return contributeProfessional;
    }

    public void setContributeProfessional(String contributeProfessional) {
        this.contributeProfessional = contributeProfessional;
    }

    public String getContributeOccupational() {
        return contributeOccupational;
    }

    public void setContributeOccupational(String contributeOccupational) {
        this.contributeOccupational = contributeOccupational;
    }

    public String getObjectiveOutput() {
        return objectiveOutput;
    }

    public void setObjectiveOutput(String objectiveOutput) {
        this.objectiveOutput = objectiveOutput;
    }

    public String getTeamContribution() {
        return teamContribution;
    }

    public void setTeamContribution(String teamContribution) {
        this.teamContribution = teamContribution;
    }

    public String getObservationFinal() {
        return observationFinal;
    }

    public void setObservationFinal(String observationFinal) {
        this.observationFinal = observationFinal;
    }

    public Program getFkPtProgram() {
        return fkPtProgram;
    }

    public void setFkPtProgram(Program fkPtProgram) {
        this.fkPtProgram = fkPtProgram;
    }

    public ThematicCore getFkPtThematicCore() {
        return fkPtThematicCore;
    }

    public void setFkPtThematicCore(ThematicCore fkPtThematicCore) {
        this.fkPtThematicCore = fkPtThematicCore;
    }
    
    
    
    
}