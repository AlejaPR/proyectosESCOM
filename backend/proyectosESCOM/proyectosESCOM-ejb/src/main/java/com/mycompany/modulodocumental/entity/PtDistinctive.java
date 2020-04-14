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
 * This is the class of the programa tematica - distinctive feature entity.
 * Contains all fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PT_DISTINCTIVE")
public class PtDistinctive implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTD_ID")
    private int id;

    /**
     * Variable for the relationship with the distinctive feature entity
     */
    @JoinColumn(name = "FK_PTD_DISTINCTIVE", referencedColumnName = "PK_DF_ID")
    @ManyToOne
    private DistinctiveFeature fkPtdDistinctive;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @JoinColumn(name = "FK_PTD_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtdProgramThematic;

    /**
     * constructor method
     */
    public PtDistinctive() {
    }

    /**
     * constructor method
     *
     * @param fkPtdDistinctive
     * @param fkPtdProgramThematic
     */
    public PtDistinctive(DistinctiveFeature fkPtdDistinctive, ProgramThematicCore fkPtdProgramThematic) {
        this.fkPtdDistinctive = fkPtdDistinctive;
        this.fkPtdProgramThematic = fkPtdProgramThematic;
    }

    //getter and setter
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
