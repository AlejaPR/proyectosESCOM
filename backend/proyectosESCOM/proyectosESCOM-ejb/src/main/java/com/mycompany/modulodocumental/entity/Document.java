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
@Table(name = "TBL_DOCUMENT")
public class Document implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_DOC_ID")
    private int id;
    @Column(name = "DOC_DESCRIPTION")
    private String description;
    @Column(name = "DOC_TYPE")
    private String type;
    @Column(name = "DOC_STATE")
    private int state;
    
    @OneToMany(mappedBy = "fkPrcDocument", cascade = CascadeType.ALL)
    private List<Process> listProcess;
    
    @OneToMany(mappedBy = "fkDvDocument",cascade = CascadeType.ALL)
    private List<DocumentVersion> listDocumentVersion;
    
    @JoinColumn(name = "FK_DOC_PROGRAM", referencedColumnName = "PK_PRO_ID")
    @ManyToOne
    private Program fkDocProgram;
    
    @Column(name = "FK_DOC_USER")
    private int idUser;

    public Document() {
    }

    public Document(String description, String type, int state) {        
        this.description = description;
        this.type = type;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Process> getListProcess() {
        return listProcess;
    }

    public void setListProcess(List<Process> listProcess) {
        this.listProcess = listProcess;
    }

    public Program getFkDocProgram() {
        return fkDocProgram;
    }

    public void setFkDocProgram(Program fkDocProgram) {
        this.fkDocProgram = fkDocProgram;
    }

    public List<DocumentVersion> getListDocumentVersion() {
        return listDocumentVersion;
    }

    public void setListDocumentVersion(List<DocumentVersion> listDocumentVersion) {
        this.listDocumentVersion = listDocumentVersion;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
