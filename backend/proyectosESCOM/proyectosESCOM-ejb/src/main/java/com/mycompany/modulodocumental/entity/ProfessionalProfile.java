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
 * This is the class of the professional profile entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - Universsity of Cundinamarca
 */
@Entity
@Table(name = "TBL_PROFESSIONAL_PROFILE")
public class ProfessionalProfile implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PP_ID")
    private int id;

    /**
     * variable name professinal profile
     */
    @Column(name = "PP_NAME")
    private String name;

    /**
     * Variable for the relationship with the general program entity
     */
    @JoinColumn(name = "FK_PP_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkPpGeneral;

    /**
     * Variable for the relationship with the program thematic core,
     * professional profile entity
     */
    @OneToMany(mappedBy = "fkPtpProfessional", cascade = CascadeType.ALL)
    private List<PtProfessional> listPtProfessional;

    /**
     * constructor method
     */
    public ProfessionalProfile() {
    }

    /**
     * constructor method
     *
     * @param name
     */
    public ProfessionalProfile(String name) {
        this.name = name;
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

    public List<PtProfessional> getListPtProfessional() {
        return listPtProfessional;
    }

    public void setListPtProfessional(List<PtProfessional> listPtProfessional) {
        this.listPtProfessional = listPtProfessional;
    }

    public GeneralProgram getFkPpGeneral() {
        return fkPpGeneral;
    }

    public void setFkPpGeneral(GeneralProgram fkPpGeneral) {
        this.fkPpGeneral = fkPpGeneral;
    }

}
