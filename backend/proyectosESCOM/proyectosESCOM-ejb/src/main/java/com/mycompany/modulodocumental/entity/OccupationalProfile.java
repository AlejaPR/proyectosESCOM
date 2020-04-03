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
@Table(name = "TBL_OCCUPATIONAL_PROFILE")
public class OccupationalProfile implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_OP_ID")
    private int id;
    @Column(name = "OP_NAME")
    private String name;

    @JoinColumn(name = "FK_OP_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkOpGeneral;

    @OneToMany(mappedBy = "fkPtoOccupational")
    private List<PtOccupational> listPtOccupational;

    public OccupationalProfile() {
    }

    public OccupationalProfile(String name) {
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
