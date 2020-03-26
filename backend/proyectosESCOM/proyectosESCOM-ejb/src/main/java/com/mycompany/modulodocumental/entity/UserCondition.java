/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HASHY
 */
@Entity
@Table(name = "TBL_USER_CONDITION")
public class UserCondition implements Serializable {
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PK_UC_ID")
    private int id;
    
    @JoinColumn(name = "FK_UC_CONDITION", referencedColumnName = "PK_CON_ID")
    @ManyToOne
    private Condition fkUcCondition;
    
    @Column(name = "FK_UC_USER")
    private int fkUcUser;

    public UserCondition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Condition getFkUcCondition() {
        return fkUcCondition;
    }

    public void setFkUcCondition(Condition fkUcCondition) {
        this.fkUcCondition = fkUcCondition;
    }

    public int getFkUcUser() {
        return fkUcUser;
    }

    public void setFkUcUser(int fkUcUser) {
        this.fkUcUser = fkUcUser;
    }
    
}
