/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_COMPETITION")
public class Competition implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CT_ID")
    private int id;
    @Column(name = "CT_NAME")
    private String name;

    @OneToMany(mappedBy = "fkCgCompetition",cascade = CascadeType.ALL)
    private List<CompetitionGeneral> listCompetitionGeneral;

    @JoinColumn(name = "FK_CT_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkCtGeneral;

    public Competition() {
    }

    public Competition(String name) {
        this.name = name;
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
