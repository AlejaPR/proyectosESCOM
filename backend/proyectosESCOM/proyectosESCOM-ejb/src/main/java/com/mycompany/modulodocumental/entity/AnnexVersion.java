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
 * This is the class of the version annex entity. Contains all fields for
 * persistence
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_ANNEX_VERSION")
public class AnnexVersion implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_AV_ID")
    private int id;

    /**
     * change date variable
     */
    @Column(name = "AV_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /**
     * variable location annex
     */
    @Column(name = "AV_LOCATION")
    private String location;

    /**
     * variable state annex
     */
    @Column(name = "AV_STATE")
    private int state;

    /**
     * variable version annex
     */
    @Column(name = "AV_VERSION")
    private int version;

    /**
     * variable description annex
     */
    @Column(name = "AV_DESCRIPTION")
    private String description;

    /**
     * Variable for the relationship with the user entity
     */
    @Column(name = "FK_AV_USER")
    private int idUser;

    /**
     * Variable for the relationship with the annex entity
     */
    @JoinColumn(name = "FK_AV_ANNEX", referencedColumnName = "PK_AX_ID")
    @ManyToOne
    private Annex fkAvAnnex;

    /**
     * constructor method
     */
    public AnnexVersion() {
    }

    /**
     * constructor method
     *
     * @param date
     * @param location
     * @param state
     * @param version
     * @param description
     */
    public AnnexVersion(Date date, String location, int state, int version, String description) {
        this.date = date;
        this.location = location;
        this.state = state;
        this.version = version;
        this.description = description;
    }

    //getter and setter
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
