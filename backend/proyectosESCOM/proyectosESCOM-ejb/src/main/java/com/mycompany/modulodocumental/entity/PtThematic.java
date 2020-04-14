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
 * This is the class of the programa tematica - thematic entity. Contains all
 * fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PT_THEMATIC")
public class PtThematic implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTT_ID")
    private int id;

    /**
     * Variable for the relationship with the thematic entity
     */
    @JoinColumn(name = "FK_PTT_THEMATIC", referencedColumnName = "PK_TH_ID")
    @ManyToOne
    private Thematic fkPttThematic;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @JoinColumn(name = "FK_PTT_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPttProgramThematic;

    /**
     * constructor method
     */
    public PtThematic() {
    }

    /**
     * constructor method
     *
     * @param fkPttThematic
     * @param fkPttProgramThematic
     */
    public PtThematic(Thematic fkPttThematic, ProgramThematicCore fkPttProgramThematic) {
        this.fkPttThematic = fkPttThematic;
        this.fkPttProgramThematic = fkPttProgramThematic;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thematic getFkPttThematic() {
        return fkPttThematic;
    }

    public void setFkPttThematic(Thematic fkPttThematic) {
        this.fkPttThematic = fkPttThematic;
    }

    public ProgramThematicCore getFkPttProgramThematic() {
        return fkPttProgramThematic;
    }

    public void setFkPttProgramThematic(ProgramThematicCore fkPttProgramThematic) {
        this.fkPttProgramThematic = fkPttProgramThematic;
    }

}
