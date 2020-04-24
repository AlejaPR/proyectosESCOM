package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the program thematic core entity. contains the
 * variables of the table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ProgramThematicCoreP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable contribute Objetive
     */
    private String contributeObjetive;

    /**
     * variable contribute Professional
     */
    private String contributeProfessional;

    /**
     * variable contribute Occupational
     */
    private String contributeOccupational;

    /**
     * variable objective Output
     */
    private String objectiveOutput;

    /**
     * variable team Contribution
     */
    private String teamContribution;

    /**
     * variable observation Final
     */
    private String observationFinal;

    /**
     * variable id program
     */
    private int idProgram;

    /**
     * variable id thematic core
     */
    private int idThematicCore;

    /**
     * variable name thematic core
     */
    private String nameThematicCore;

    /**
     * variable objetive
     */
    private String objetive;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public ProgramThematicCoreP() {

    }

    /**
     * constructor method
     *
     * @param id
     * @param nameThematicCore
     * @param objetive
     */
    public ProgramThematicCoreP(int id, String nameThematicCore, String objetive) {
        this.id = id;
        this.nameThematicCore = nameThematicCore;
        this.objetive = objetive;
    }

    /**
     * constructor method
     *
     * @param id
     * @param contributeObjetive
     * @param contributeProfessional
     * @param contributeOccupational
     * @param objectiveOutput
     * @param teamContribution
     * @param observationFinal
     * @param idProgram
     * @param idThematicCore
     */
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

    /**
     * constructor method
     *
     * @param id
     * @param idProgram
     * @param idThematicCore
     */
    public ProgramThematicCoreP(int id, int idProgram, int idThematicCore) {
        this.id = id;
        this.idProgram = idProgram;
        this.idThematicCore = idThematicCore;
    }

    //getter and setter
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

    public String getNameThematicCore() {
        return nameThematicCore;
    }

    public void setNameThematicCore(String nameThematicCore) {
        this.nameThematicCore = nameThematicCore;
    }

    public String getObjetive() {
        return objetive;
    }

    public void setObjetive(String objetive) {
        this.objetive = objetive;
    }

}
