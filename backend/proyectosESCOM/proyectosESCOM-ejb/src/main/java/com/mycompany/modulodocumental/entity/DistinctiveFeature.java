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
@Table(name = "TBL_DISTINCTIVE_FEATURE") 
public class DistinctiveFeature implements Serializable{
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_DF_ID")
    private int id;
    @Column(name = "DF_NAME")
    private String name;
    
    @JoinColumn(name = "FK_DF_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkDfGeneral;
    
    @OneToMany(mappedBy = "fkPtdDistinctive", cascade = CascadeType.ALL)
    private List<PtDistinctive> listPtDistinctive;
    
    public DistinctiveFeature() {
    }

    public DistinctiveFeature(String name) {
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

    public List<PtDistinctive> getListPtDistinctive() {
        return listPtDistinctive;
    }

    public void setListPtDistinctive(List<PtDistinctive> listPtDistinctive) {
        this.listPtDistinctive = listPtDistinctive;
    }

    public GeneralProgram getFkDfGeneral() {
        return fkDfGeneral;
    }

    public void setFkDfGeneral(GeneralProgram fkDfGeneral) {
        this.fkDfGeneral = fkDfGeneral;
    }
       
}
