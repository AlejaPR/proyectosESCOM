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
 * Esta es la clase de la entidad de competencias. Contiene todos los campos
 * para persistencia.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_COMPETITION")
public class Competition implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CT_ID")
    private int id;
    
    /**
     * variable name competition
     */
    @Column(name = "CT_NAME")
    private String name;

    /**
     * Variable for the relationship with the entity general competence
     */
    @OneToMany(mappedBy = "fkCgCompetition", cascade = CascadeType.ALL)
    private List<CompetitionGeneral> listCompetitionGeneral;

    /**
     * Variable for the relationship with the entity general program
     */
    @JoinColumn(name = "FK_CT_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkCtGeneral;

    /**
     * constructor method
     */
    public Competition() {
    }

    /**
     * constructor method
     * @param name 
     */
    public Competition(String name) {
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

    public GeneralProgram getFkCtGeneral() {
        return fkCtGeneral;
    }

    public void setFkCtGeneral(GeneralProgram fkCtGeneral) {
        this.fkCtGeneral = fkCtGeneral;
    }

    public List<CompetitionGeneral> getListCompetitionGeneral() {
        return listCompetitionGeneral;
    }

    public void setListCompetitionGeneral(List<CompetitionGeneral> listCompetitionGeneral) {
        this.listCompetitionGeneral = listCompetitionGeneral;
    }

}
