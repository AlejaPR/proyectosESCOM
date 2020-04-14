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
 * This is the class of the program thematic core entity. Contains all fields
 * for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PROGRAM_THEMATIC_CORE")
public class ProgramThematicCore implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PT_ID")
    private int id;
    
    /**
     * variable contibute objective - program thematic core
     */
    @Column(name = "PT_CONTRIBUTE_OBJECTIVE")
    private String contributeObjetive;
    
    /**
     * variable contribute professional profile - program thematic core
     */
    @Column(name = "PT_CONTRIBUTE_PROFESSIONAL")
    private String contributeProfessional;
    
    /**
     * variable contribute occupational profile - program thematic core
     */
    @Column(name = "PT_CONTRIBUTE_OCCUPATIONAL")
    private String contributeOccupational;
    
    /**
     * variable objective output - program thematic core
     */
    @Column(name = "PT_OBJECTIVE_OUTPUT")
    private String objectiveOutput;
    
    /**
     * variable team contribution - program thematic core
     */
    @Column(name = "PT_TEAM_CONTRIBUTION")
    private String teamContribution;
    
    /**
     * variable observation final - program thematic core
     */
    @Column(name = "PT_OBSERVATION_FINAL")
    private String observationFinal;
    
    /**
     * variable objective - program thematic core
     */
    @Column(name = "PT_OBJECTIVE")
    private String objective;

    /**
     * Variable for the relationship with the program entity
     */
    @JoinColumn(name = "FK_PT_PROGRAM", referencedColumnName = "PK_PRO_ID")
    @ManyToOne
    private Program fkPtProgram;

    /**
     * Variable for the relationship with the thematic core entity
     */
    @JoinColumn(name = "FK_PT_THEMATIC_CORE", referencedColumnName = "PK_TC_ID")
    @ManyToOne
    private ThematicCore fkPtThematicCore;

    /**
     * Variable for the relationship with the competition general entity
     */
    @OneToMany(mappedBy = "fkPtcProgramThematic", cascade = CascadeType.ALL)
    private List<PtCompetitionG> listPtCompetitionG;

    /**
     * Variable for the relationship with the distinctive feature entity
     */
    @OneToMany(mappedBy = "fkPtdProgramThematic", cascade = CascadeType.ALL)
    private List<PtDistinctive> listPtDistinctive;

    /**
     * Variable for the relationship with the occupational profile entity
     */
    @OneToMany(mappedBy = "fkPtoProgramThematic", cascade = CascadeType.ALL)
    private List<PtOccupational> listPtOccupational;

    /**
     * Variable for the relationship with the professional profile entity
     */
    @OneToMany(mappedBy = "fkPtpProgramThematic", cascade = CascadeType.ALL)
    private List<PtProfessional> listPtProfessional;

    /**
     * Variable for the relationship with the thematic entity
     */
    @OneToMany(mappedBy = "fkPttProgramThematic", cascade = CascadeType.ALL)
    private List<PtThematic> listPtThematic;

    /**
     * constructor method.
     */
    public ProgramThematicCore() {
    }

    /**
     * constructor method
     * 
     * @param contributeObjetive
     * @param contributeProfessional
     * @param contributeOccupational
     * @param objectiveOutput
     * @param teamContribution
     * @param observationFinal
     * @param objective 
     */
    public ProgramThematicCore(String contributeObjetive, String contributeProfessional, String contributeOccupational, String objectiveOutput, String teamContribution, String observationFinal, String objective) {
        this.contributeObjetive = contributeObjetive;
        this.contributeProfessional = contributeProfessional;
        this.contributeOccupational = contributeOccupational;
        this.objectiveOutput = objectiveOutput;
        this.teamContribution = teamContribution;
        this.observationFinal = observationFinal;
        this.objective = objective;
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
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

    public List<PtCompetitionG> getListPtCompetitionG() {
        return listPtCompetitionG;
    }

    public void setListPtCompetitionG(List<PtCompetitionG> listPtCompetitionG) {
        this.listPtCompetitionG = listPtCompetitionG;
    }

    public List<PtDistinctive> getListPtDistinctive() {
        return listPtDistinctive;
    }

    public void setListPtDistinctive(List<PtDistinctive> listPtDistinctive) {
        this.listPtDistinctive = listPtDistinctive;
    }

    public List<PtOccupational> getListPtOccupational() {
        return listPtOccupational;
    }

    public void setListPtOccupational(List<PtOccupational> listPtOccupational) {
        this.listPtOccupational = listPtOccupational;
    }

    public List<PtProfessional> getListPtProfessional() {
        return listPtProfessional;
    }

    public void setListPtProfessional(List<PtProfessional> listPtProfessional) {
        this.listPtProfessional = listPtProfessional;
    }

    public List<PtThematic> getListPtThematic() {
        return listPtThematic;
    }

    public void setListPtThematic(List<PtThematic> listPtThematic) {
        this.listPtThematic = listPtThematic;
    }

}
