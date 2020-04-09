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
@Table(name = "TBL_PT_DISTINCTIVE")
public class PtDistinctive implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTD_ID")
    private int id;

    @JoinColumn(name = "FK_PTD_DISTINCTIVE", referencedColumnName = "PK_DF_ID")
    @ManyToOne
    private DistinctiveFeature fkPtdDistinctive;

    @JoinColumn(name = "FK_PTD_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtdProgramThematic;

    public PtDistinctive() {
    }

    public PtDistinctive(DistinctiveFeature fkPtdDistinctive, ProgramThematicCore fkPtdProgramThematic) {
        this.fkPtdDistinctive = fkPtdDistinctive;
        this.fkPtdProgramThematic = fkPtdProgramThematic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DistinctiveFeature getFkPtdDistinctive() {
        return fkPtdDistinctive;
    }

    public void setFkPtdDistinctive(DistinctiveFeature fkPtdDistinctive) {
        this.fkPtdDistinctive = fkPtdDistinctive;
    }

    public ProgramThematicCore getFkPtdProgramThematic() {
        return fkPtdProgramThematic;
    }

    public void setFkPtdProgramThematic(ProgramThematicCore fkPtdProgramThematic) {
        this.fkPtdProgramThematic = fkPtdProgramThematic;
    }

}
