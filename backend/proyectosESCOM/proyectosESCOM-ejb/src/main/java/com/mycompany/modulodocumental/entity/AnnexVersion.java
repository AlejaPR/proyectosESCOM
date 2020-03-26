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
@Table(name = "TBL_ANNEX_VERSION")
public class AnnexVersion implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_AV_ID")
    private int id;
    @Column(name = "AV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "AV_LOCATION")
    private String location;
    @Column(name = "AV_STATE")
    private int state;    
    @Column(name = "AV_VERSION")
    private int version;
    @Column(name = "AV_DESCRIPTION")
    private String description;

    @Column(name = "FK_AV_USER")
    private int idUser;
    
    @JoinColumn(name = "FK_AV_ANNEX", referencedColumnName = "PK_AX_ID")
    @ManyToOne
    private Annex fkAvAnnex; 
    
          
    public AnnexVersion() {
    }
    
    public AnnexVersion(Date date, String location, int state,int version, String description) {
        this.date = date;
        this.location = location;
        this.state = state;
        this.version = version;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Annex getFkAvAnnex() {
        return fkAvAnnex;
    }

    public void setFkAvAnnex(Annex fkAvAnnex) {
        this.fkAvAnnex = fkAvAnnex;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
        
}
