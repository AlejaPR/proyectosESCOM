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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the class of the activity entity. It contains all the fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_ACTIVITY")
public class Activity implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ACT_ID")
    private int id;

    /**
     * variable name activity
     */
    @Column(name = "ACT_NAME")
    private String name;

    /**
     * variable description activity
     */
    @Column(name = "ACT_DESCRIPTION")
    private String description;

    /**
     * variable information activity
     */
    @Lob
    @Column(name = "ACT_INFORMATION")
    private String information;

    /**
     * variable state activity
     */
    @Column(name = "ACT_STATE")
    private int state;

    /**
     * activity type variable
     */
    @Column(name = "ACT_TYPE")
    private int type;

    /**
     * variable number activity
     */
    @Column(name = "ACT_NUMBER")
    private String number;

    /**
     * variable id parent activity
     */
    @Column(name = "ACT_PARENT_ACTIVITY")
    private int parentActivity;

    /**
     * Variable for the relationship with the condition entity
     */
    @JoinColumn(name = "FK_ACT_CONDITION", referencedColumnName = "PK_CON_ID")
    @ManyToOne
    private Condition fkActCondition;

    /**
     * Variable for the relationship with the annex entity
     */
    @JoinColumn(name = "FK_ACT_ANNEX", referencedColumnName = "PK_AX_ID")
    @ManyToOne
    private Annex fkActAnnex;

    /**
     * Variable for the relationship with the comment entity
     */
    @OneToMany(mappedBy = "fkComActivity", cascade = CascadeType.ALL)
    private List<Commentary> listCommentary;

    /**
     * constructor method
     */
    public Activity() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param description
     * @param information
     * @param state
     * @param type
     * @param number
     */
    public Activity(String name, String description, String information, int state, int type, String number) {
        this.name = name;
        this.description = description;
        this.information = information;
        this.state = state;
        this.type = type;
        this.number = number;
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

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNumber() {
        return number;
    }

    public int getParentActivity() {
        return parentActivity;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setParentActivity(int parentActivity) {
        this.parentActivity = parentActivity;
    }

    public Condition getFkActCondition() {
        return fkActCondition;
    }

    public void setFkActCondition(Condition fkActCondition) {
        this.fkActCondition = fkActCondition;
    }

    public Annex getFkActAnnex() {
        return fkActAnnex;
    }

    public void setFkActAnnex(Annex fkActAnnex) {
        this.fkActAnnex = fkActAnnex;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Commentary> getListCommentary() {
        return listCommentary;
    }

    public void setListCommentary(List<Commentary> listCommentary) {
        this.listCommentary = listCommentary;
    }

}
