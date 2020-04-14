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
 * This is the class of the thematic core entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_THEMATIC_CORE")
public class ThematicCore implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TC_ID")
    private int id;

    /**
     * variable name thematic core
     */
    @Column(name = "TC_NAME")
    private String name;

    /**
     * variable credits thematic core
     */
    @Column(name = "TC_CREDITS")
    private int credits;

    /**
     * Variable for the relationship with the program themaic core entity
     */
    @OneToMany(mappedBy = "fkPtThematicCore", cascade = CascadeType.ALL)
    private List<ProgramThematicCore> listProgramThematicCore;

    /**
     * Variable for the relationship with the training area entity
     */
    @JoinColumn(name = "FK_TC_TRAINING_AREA", referencedColumnName = "PK_TA_ID")
    @ManyToOne
    private TrainingArea fkTcTrainingArea;

    /**
     * constructor method
     */
    public ThematicCore() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param credits
     */
    public ThematicCore(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<ProgramThematicCore> getListProgramThematicCore() {
        return listProgramThematicCore;
    }

    public void setListProgramThematicCore(List<ProgramThematicCore> listProgramThematicCore) {
        this.listProgramThematicCore = listProgramThematicCore;
    }

    public TrainingArea getFkTcTrainingArea() {
        return fkTcTrainingArea;
    }

    public void setFkTcTrainingArea(TrainingArea fkTcTrainingArea) {
        this.fkTcTrainingArea = fkTcTrainingArea;
    }

}
