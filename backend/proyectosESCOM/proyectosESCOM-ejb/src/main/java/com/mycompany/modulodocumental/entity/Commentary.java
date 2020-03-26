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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author HASHY
 */

@Entity
@Table(name = "TBL_COMMENTARY")
public class Commentary  implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_COM_ID")
    private int id;
    @Column(name = "COM_MESSAGE")
    private String message;
    @Column(name = "COM_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "COM_STATE")
    private int state;    
    @Column(name = "FK_COM_USER")
    private int idUser;
    
    public Commentary() {
    }

    public Commentary(String message, Date date, int state) {
        this.message = message;
        this.date = date;
        this.state = state;
    }

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
