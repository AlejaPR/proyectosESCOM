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
 * This is the class of the entity of general competence. Contains all fields
 * for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_COMPETITION_GENERAL")
public class CompetitionGeneral implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CG_ID")
    private int id;

    /**
     * variable name competition general
     */
    @Column(name = "CG_NAME")
    private String name;

    /**
     * Variable for the relationship with the competition entity
     */
    @JoinColumn(name = "FK_CG_COMPETITION", referencedColumnName = "PK_CT_ID")
    @ManyToOne
    private Competition fkCgCompetition;

    /**
     * Variable for the relationship with the entity general program, general
     * competence
     */
    @OneToMany(mappedBy = "fkPtcCompetitionG", cascade = CascadeType.ALL)
    private List<PtCompetitionG> listPtCompetitionG;

    /**
     * constructor method
     */
    public CompetitionGeneral() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param fkCgCompetition
     */
    public CompetitionGeneral(String name, Competition fkCgCompetition) {
        this.name = name;
        this.fkCgCompetition = fkCgCompetition;
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

    public Competition getFkCgCompetition() {
        return fkCgCompetition;
    }

    public void setFkCgCompetition(Competition fkCgCompetition) {
        this.fkCgCompetition = fkCgCompetition;
    }

    public List<PtCompetitionG> getListPtCompetitionG() {
        return listPtCompetitionG;
    }

    public void setListPtCompetitionG(List<PtCompetitionG> listPtCompetitionG) {
        this.listPtCompetitionG = listPtCompetitionG;
    }

}
