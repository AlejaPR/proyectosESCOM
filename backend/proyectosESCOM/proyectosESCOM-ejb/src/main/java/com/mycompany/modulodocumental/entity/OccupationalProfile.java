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
 * This is the class of the occupational profile entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_OCCUPATIONAL_PROFILE")
public class OccupationalProfile implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_OP_ID")
    private int id;

    /**
     * variable name occupational profile
     */
    @Column(name = "OP_NAME")
    private String name;

    /**
     * Variable for the relationship with the general program entity
     */
    @JoinColumn(name = "FK_OP_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkOpGeneral;

    /**
     * Variable for the relationship with the program themetic core,
     * occupational profile entity
     */
    @OneToMany(mappedBy = "fkPtoOccupational", cascade = CascadeType.ALL)
    private List<PtOccupational> listPtOccupational;

    /**
     * constructor method
     */
    public OccupationalProfile() {
    }

    /**
     * constructor method
     *
     * @param name
     */
    public OccupationalProfile(String name) {
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

    public List<PtOccupational> getListPtOccupational() {
        return listPtOccupational;
    }

    public void setListPtOccupational(List<PtOccupational> listPtOccupational) {
        this.listPtOccupational = listPtOccupational;
    }

    public GeneralProgram getFkOpGeneral() {
        return fkOpGeneral;
    }

    public void setFkOpGeneral(GeneralProgram fkOpGeneral) {
        this.fkOpGeneral = fkOpGeneral;
    }

}
