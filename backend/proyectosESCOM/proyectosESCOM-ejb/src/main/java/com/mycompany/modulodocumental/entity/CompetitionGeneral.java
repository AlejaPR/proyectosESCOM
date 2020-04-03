/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_COMPETITION_GENERAL")
public class CompetitionGeneral  implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CG_ID")
    private int id;
    @Column(name = "CG_NAME")
    private String name;
    
    @JoinColumn(name = "FK_CG_COMPETITION", referencedColumnName = "PK_CT_ID")
    @ManyToOne
    private Competition fkCgCompetition;

    @OneToMany(mappedBy = "fkPtcCompetitionG")
    private List<PtCompetitionG> listPtCompetitionG;
    
    public CompetitionGeneral() {
    }

    public CompetitionGeneral(String name, Competition fkCgCompetition) {
        this.name = name;
        this.fkCgCompetition = fkCgCompetition;
    }

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
