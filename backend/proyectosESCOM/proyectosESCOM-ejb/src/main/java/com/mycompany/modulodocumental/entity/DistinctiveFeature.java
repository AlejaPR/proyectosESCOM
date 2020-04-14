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
 * This is the class of the distinctive feature entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_DISTINCTIVE_FEATURE")
public class DistinctiveFeature implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_DF_ID")
    private int id;

    /**
     * variable name distinctive feature
     */
    @Column(name = "DF_NAME")
    private String name;

    /**
     * Variable para la relación con la entidad programa general
     */
    @JoinColumn(name = "FK_DF_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkDfGeneral;

    /**
     * Variable for the relationship with the general program entity,
     * distinctive feature
     */
    @OneToMany(mappedBy = "fkPtdDistinctive", cascade = CascadeType.ALL)
    private List<PtDistinctive> listPtDistinctive;

    /**
     * constructor method.
     */
    public DistinctiveFeature() {
    }

    /**
     * constructor method.
     *
     * @param name
     */
    public DistinctiveFeature(String name) {
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

    public List<PtDistinctive> getListPtDistinctive() {
        return listPtDistinctive;
    }

    public void setListPtDistinctive(List<PtDistinctive> listPtDistinctive) {
        this.listPtDistinctive = listPtDistinctive;
    }

    public GeneralProgram getFkDfGeneral() {
        return fkDfGeneral;
    }

    public void setFkDfGeneral(GeneralProgram fkDfGeneral) {
        this.fkDfGeneral = fkDfGeneral;
    }

}
