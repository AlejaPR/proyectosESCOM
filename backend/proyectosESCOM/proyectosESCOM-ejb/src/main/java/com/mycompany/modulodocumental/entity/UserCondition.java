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
 * This is the class of the user condition entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_USER_CONDITION")
public class UserCondition implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_UC_ID")
    private int id;

    /**
     * Variable for the relationship with the condition entity
     */
    @JoinColumn(name = "FK_UC_CONDITION", referencedColumnName = "PK_CON_ID")
    @ManyToOne
    private Condition fkUcCondition;

    /**
     * Variable for the relationship with the user entity
     */
    @Column(name = "FK_UC_USER")
    private int fkUcUser;

    /**
     * constructor method
     */
    public UserCondition() {
    }

    //getter and setter
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
