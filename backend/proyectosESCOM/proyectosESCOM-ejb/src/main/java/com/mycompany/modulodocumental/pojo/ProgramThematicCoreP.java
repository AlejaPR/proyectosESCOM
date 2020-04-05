/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 *
 * @author hashy
 */
public class ProgramThematicCoreP implements Serializable {

    private int id;
    private String contributeObjetive;
    private String contributeProfessional;
    private String contributeOccupational;
    private String objectiveOutput;
    private String teamContribution;
    private String observationFinal;
    private int idProgram;
    private int idThematicCore;
    private String nameThematicCore;
    private String objetive;
    private DatosSolicitudPOJO requestData;

    public ProgramThematicCoreP() {
        
    }

    public ProgramThematicCoreP(int id, String nameThematicCore, String objetive) {
        this.id = id;
        this.nameThematicCore = nameThematicCore;
        this.objetive = objetive;
    }
    
    public ProgramThematicCoreP(int id, String contributeObjetive, String contributeProfessional, String contributeOccupational, String objectiveOutput, String teamContribution, String observationFinal, int idProgram, int idThematicCore) {
        this.id = id;
        this.contributeObjetive = contributeObjetive;
        this.contributeProfessional = contributeProfessional;
        this.contributeOccupational = contributeOccupational;
        this.objectiveOutput = objectiveOutput;
        this.teamContribution = teamContribution;
        this.observationFinal = observationFinal;
        this.idProgram = idProgram;
        this.idThematicCore = idThematicCore;
    }

    public ProgramThematicCoreP(int id, int idProgram, int idThematicCore) {
        this.id = id;
        this.idProgram = idProgram;
        this.idThematicCore = idThematicCore;
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

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public int getIdThematicCore() {
        return idThematicCore;
    }

    public void setIdThematicCore(int idThematicCore) {
        this.idThematicCore = idThematicCore;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
