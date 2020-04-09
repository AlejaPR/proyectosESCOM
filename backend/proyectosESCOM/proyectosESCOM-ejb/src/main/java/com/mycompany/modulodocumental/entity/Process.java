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
 * @author HASHY
 */
@Entity
@Table(name = "TBL_PROCESS")
public class Process implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PRC_ID")
    private int id;
    @Column(name = "PRC_NAME")
    private String name;
    @Column(name = "PRC_DESCRIPTION")
    private String description;
    @Column(name = "PRC_STATE")
    private int state;

    @OneToMany(mappedBy = "fkConProcess", cascade = CascadeType.ALL)
    private List<Condition> listCondition;

    @JoinColumn(name = "FK_PRC_DOCUMENT", referencedColumnName = "PK_DOC_ID")
    @ManyToOne
    private Document fkPrcDocument;

    public Process() {
    }

    public Process(String name, String description, int state) {
        this.name = name;
        this.description = description;
        this.state = state;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Condition> getListCondition() {
        return listCondition;
    }

    public void setListCondition(List<Condition> listCondition) {
        this.listCondition = listCondition;
    }

    public Document getFkPrcDocument() {
        return fkPrcDocument;
    }

    public void setFkPrcDocument(Document fkPrcDocument) {
        this.fkPrcDocument = fkPrcDocument;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
