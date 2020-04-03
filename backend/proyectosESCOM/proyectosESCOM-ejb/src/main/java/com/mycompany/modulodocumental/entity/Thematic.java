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
@Table(name = "TBL_THEMATIC")
public class Thematic implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TH_ID")
    private int id;
    @Column(name = "TH_NAME")
    private String name;
    
    @JoinColumn(name = "FK_TH_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkThGeneral;
    
    @OneToMany(mappedBy = "fkPttThematic")
    private List<PtThematic> listPtThematic;

    public Thematic() {
    }

    public Thematic(String name) {
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

    public List<PtThematic> getListPtThematic() {
        return listPtThematic;
    }

    public void setListPtThematic(List<PtThematic> listPtThematic) {
        this.listPtThematic = listPtThematic;
    }

    public GeneralProgram getFkThGeneral() {
        return fkThGeneral;
    }

    public void setFkThGeneral(GeneralProgram fkThGeneral) {
        this.fkThGeneral = fkThGeneral;
    }
    
}
