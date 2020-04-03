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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_PT_COMPETITION_G")
public class PtCompetitionG implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTC_ID")
    private int id;

    @JoinColumn(name = "FK_PTC_COMPETITIION_G", referencedColumnName = "PK_CG_ID")
    @ManyToOne
    private CompetitionGeneral fkPtcCompetitionG;

    @JoinColumn(name = "FK_PTC_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtcProgramThematic;

    public PtCompetitionG() {
    }

    public PtCompetitionG(CompetitionGeneral fkPtcCompetitionG, ProgramThematicCore fkPtcProgramThematic) {
        this.fkPtcCompetitionG = fkPtcCompetitionG;
        this.fkPtcProgramThematic = fkPtcProgramThematic;
    }

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
