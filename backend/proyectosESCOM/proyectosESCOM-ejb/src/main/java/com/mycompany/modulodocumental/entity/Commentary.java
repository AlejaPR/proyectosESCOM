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
 * This is the class of the comment entity. Contains all fields for persistence
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_COMMENTARY")
public class Commentary implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_COM_ID")
    private int id;
    
    /**
     * variable message comentary
     */
    @Column(name = "COM_MESSAGE")
    private String message;
    
    /**
     * creation date variable
     */
    @Column(name = "COM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    /**
     * Variable for the relationship with the user entity
     */
    @Column(name = "FK_COM_USER")
    private int idUser;

    /**
     * Variable for the relationship with the activity entity
     */
    @JoinColumn(name = "FK_COM_ACTIVITY", referencedColumnName = "PK_ACT_ID")
    @ManyToOne
    private Activity fkComActivity;

    /**
     * constructor method
     */
    public Commentary() {
    }

    /**
     * constructor method
     * @param message
     * @param date
     * @param idUser 
     */
    public Commentary(String message, Date date, int idUser) {
        this.message = message;
        this.date = date;
        this.idUser = idUser;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Activity getFkComActivity() {
        return fkComActivity;
    }

    public void setFkComActivity(Activity fkComActivity) {
        this.fkComActivity = fkComActivity;
    }
}
