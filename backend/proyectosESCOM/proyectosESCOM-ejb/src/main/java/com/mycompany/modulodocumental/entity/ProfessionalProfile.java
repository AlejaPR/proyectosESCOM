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
@Table(name = "TBL_PROFESSIONAL_PROFILE")
public class ProfessionalProfile implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PP_ID")
    private int id;
    @Column(name = "PP_NAME")
    private String name;

    @JoinColumn(name = "FK_PP_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkPpGeneral;

    @OneToMany(mappedBy = "fkPtpProfessional")
    private List<PtProfessional> listPtProfessional;

    public ProfessionalProfile() {
    }

    public ProfessionalProfile(String name) {
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

    public List<PtProfessional> getListPtProfessional() {
        return listPtProfessional;
    }

    public void setListPtProfessional(List<PtProfessional> listPtProfessional) {
        this.listPtProfessional = listPtProfessional;
    }

    public GeneralProgram getFkPpGeneral() {
        return fkPpGeneral;
    }

    public void setFkPpGeneral(GeneralProgram fkPpGeneral) {
        this.fkPpGeneral = fkPpGeneral;
    }

}
