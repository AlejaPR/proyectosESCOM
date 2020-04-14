package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This is the class of the programa tematica - competition general entity.
 * Contains all fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PT_COMPETITION_G")
public class PtCompetitionG implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTC_ID")
    private int id;

    /**
     * Variable for the relationship with the competition general entity
     */
    @JoinColumn(name = "FK_PTC_COMPETITION_G", referencedColumnName = "PK_CG_ID")
    @ManyToOne
    private CompetitionGeneral fkPtcCompetitionG;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @JoinColumn(name = "FK_PTC_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtcProgramThematic;

    /**
     * constructor method
     */
    public PtCompetitionG() {
    }

    /**
     * constructor method
     *
     * @param fkPtcCompetitionG
     * @param fkPtcProgramThematic
     */
    public PtCompetitionG(CompetitionGeneral fkPtcCompetitionG, ProgramThematicCore fkPtcProgramThematic) {
        this.fkPtcCompetitionG = fkPtcCompetitionG;
        this.fkPtcProgramThematic = fkPtcProgramThematic;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompetitionGeneral getFkPtcCompetitionG() {
        return fkPtcCompetitionG;
    }

    public void setFkPtcCompetitionG(CompetitionGeneral fkPtcCompetitionG) {
        this.fkPtcCompetitionG = fkPtcCompetitionG;
    }

    public ProgramThematicCore getFkPtcProgramThematic() {
        return fkPtcProgramThematic;
    }

    public void setFkPtcProgramThematic(ProgramThematicCore fkPtcProgramThematic) {
        this.fkPtcProgramThematic = fkPtcProgramThematic;
    }

}
