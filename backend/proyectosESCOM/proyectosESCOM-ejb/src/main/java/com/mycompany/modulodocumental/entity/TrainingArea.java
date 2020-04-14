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
 * This is the class of the training area entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_TRAINING_AREA")
public class TrainingArea implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TA_ID")
    private int id;

    /**
     * variable name training area
     */
    @Column(name = "TA_NAME")
    private String name;

    /**
     * Variable for the relationship with the general program entity
     */
    @JoinColumn(name = "FK_TA_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkTaGeneral;

    /**
     * Variable for the relationship with the thematic core entity
     */
    @OneToMany(mappedBy = "fkTcTrainingArea", cascade = CascadeType.ALL)
    private List<ThematicCore> listThematicCore;

    /**
     * constructor method
     */
    public TrainingArea() {
    }

    /**
     * constructor method
     *
     * @param name
     */
    public TrainingArea(String name) {
        this.name = name;
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

    public List<ThematicCore> getListThematicCore() {
        return listThematicCore;
    }

    public void setListThematicCore(List<ThematicCore> listThematicCore) {
        this.listThematicCore = listThematicCore;
    }

    public GeneralProgram getFkTaGeneral() {
        return fkTaGeneral;
    }

    public void setFkTaGeneral(GeneralProgram fkTaGeneral) {
        this.fkTaGeneral = fkTaGeneral;
    }

}
