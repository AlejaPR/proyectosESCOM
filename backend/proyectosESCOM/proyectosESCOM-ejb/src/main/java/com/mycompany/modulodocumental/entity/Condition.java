package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the class of the condition entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_CONDITION")
public class Condition implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_CON_ID")
    private int id;

    /**
     * variable name condition
     */
    @Column(name = "CON_NAME")
    private String name;

    /**
     * variable description condition
     */
    @Column(name = "CON_DESCRIPTION")
    private String description;

    /**
     * variable state condition
     */
    @Column(name = "CON_STATE")
    private int state;

    /**
     * variable start date condition
     */
    @Column(name = "CON_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    /**
     * variable final date condition
     */
    @Column(name = "CON_FINAL_DATE")
    @Temporal(TemporalType.DATE)
    private Date finalDate;

    /**
     * Variable for the relationship with the activity entity
     */
    @OneToMany(mappedBy = "fkActCondition", cascade = CascadeType.ALL)
    private List<Activity> listActivity;

    /**
     * Variable for the relationship with the user condition entity
     */
    @OneToMany(mappedBy = "fkUcCondition", cascade = CascadeType.ALL)
    private List<UserCondition> listUserCondition;

    /**
     * Variable for the relationship with the process entity
     */
    @JoinColumn(name = "FK_CON_PROCESS", referencedColumnName = "PK_PRC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Process fkConProcess;

    /**
     * constructor method
     */
    public Condition() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param description
     * @param state
     * @param startDate
     * @param finalDate
     */
    public Condition(String name, String description, int state, Date startDate, Date finalDate) {
        this.name = name;
        this.description = description;
        this.state = state;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    //getter and setter
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Activity> getListActivity() {
        return listActivity;
    }

    public void setListActivity(List<Activity> listActivity) {
        this.listActivity = listActivity;
    }

    public Process getFkConProcess() {
        return fkConProcess;
    }

    public void setFkConProcess(Process fkConProcess) {
        this.fkConProcess = fkConProcess;
    }

    public List<UserCondition> getListUserCondition() {
        return listUserCondition;
    }

    public void setListUserCondition(List<UserCondition> listUserCondition) {
        this.listUserCondition = listUserCondition;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

}
