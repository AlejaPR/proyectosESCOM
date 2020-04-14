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
 * This is the class of the programa tematica - professional profile entity.
 * Contains all fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PT_PROFESSIONAL")
public class PtProfessional implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTP_ID")
    private int id;

    /**
     * Variable for the relationship with the professional profile entity
     */
    @JoinColumn(name = "FK_PTP_PROFESSIONAL", referencedColumnName = "PK_PP_ID")
    @ManyToOne
    private ProfessionalProfile fkPtpProfessional;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @JoinColumn(name = "FK_PTP_PROGRAM_THEMATIC", referencedColumnName = "PK_PT_ID")
    @ManyToOne
    private ProgramThematicCore fkPtpProgramThematic;

    /**
     * constructor method
     */
    public PtProfessional() {
    }

    /**
     * constructor method
     *
     * @param fkPtpProfessional
     * @param fkPtpProgramThematic
     */
    public PtProfessional(ProfessionalProfile fkPtpProfessional, ProgramThematicCore fkPtpProgramThematic) {
        this.fkPtpProfessional = fkPtpProfessional;
        this.fkPtpProgramThematic = fkPtpProgramThematic;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProfessionalProfile getFkPtpProfessional() {
        return fkPtpProfessional;
    }

    public void setFkPtpProfessional(ProfessionalProfile fkPtpProfessional) {
        this.fkPtpProfessional = fkPtpProfessional;
    }

    public ProgramThematicCore getFkPtpProgramThematic() {
        return fkPtpProgramThematic;
    }

    public void setFkPtpProgramThematic(ProgramThematicCore fkPtpProgramThematic) {
        this.fkPtpProgramThematic = fkPtpProgramThematic;
    }

}
