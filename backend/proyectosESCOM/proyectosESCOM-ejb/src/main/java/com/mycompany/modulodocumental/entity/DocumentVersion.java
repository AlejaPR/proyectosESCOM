/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HASHY
 */
@Entity
@Table(name = "TBL_DOCUMENT_VERSION")
public class DocumentVersion implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_DV_ID")
    private int id;
    @Column(name = "DV_DESCRIPTION")
    private String description;
    @Column(name = "DV_VERSION")
    private int version;
    @Column(name = "DV_LOCATION")
    private String location;
    @Column(name = "DV_STATE")
    private int state;
    @Column(name = "DV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @JoinColumn(name = "FK_DV_DOCUMENT", referencedColumnName = "PK_DOC_ID")
    @ManyToOne
    private Document fkDvDocument;

    public DocumentVersion() {
    }

    public DocumentVersion(String description, int version, String location, int state, Date date) {
        this.description = description;
        this.version = version;
        this.location = location;
        this.state = state;
        this.date = date;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Document getFkDvDocument() {
        return fkDvDocument;
    }

    public void setFkDvDocument(Document fkDvDocument) {
        this.fkDvDocument = fkDvDocument;
    }
    
    
}
