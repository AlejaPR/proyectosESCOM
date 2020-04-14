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
 * This is the class of the programa tematica - occupational profile entity.
 * Contains all fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PT_OCCUPATIONAL")
public class PtOccupational implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTO_ID")
    private int id;

    /**
     * Variable for the relationship with the occupational profile entity
     */
    @JoinColumn(name = "FK_PTO_OCCUPATIONAL", referencedColumnName = "PK_OP_ID")
    @ManyToOne
    private OccupationalProfile fkPtoOccupational;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @JoinColumn(name = "FK_PTO_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtoProgramThematic;

    /**
     * constructor method
     */
    public PtOccupational() {
    }

    /**
     * constructor method
     *
     * @param fkPtoOccupational
     * @param fkPtoProgramThematic
     */
    public PtOccupational(OccupationalProfile fkPtoOccupational, ProgramThematicCore fkPtoProgramThematic) {
        this.fkPtoOccupational = fkPtoOccupational;
        this.fkPtoProgramThematic = fkPtoProgramThematic;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OccupationalProfile getFkPtoOccupational() {
        return fkPtoOccupational;
    }

    public void setFkPtoOccupational(OccupationalProfile fkPtoOccupational) {
        this.fkPtoOccupational = fkPtoOccupational;
    }

    public ProgramThematicCore getFkPtoProgramThematic() {
        return fkPtoProgramThematic;
    }

    public void setFkPtoProgramThematic(ProgramThematicCore fkPtoProgramThematic) {
        this.fkPtoProgramThematic = fkPtoProgramThematic;
    }

}
