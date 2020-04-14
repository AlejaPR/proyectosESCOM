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
 * This is the class of the thematic entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_THEMATIC")
public class Thematic implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_TH_ID")
    private int id;

    /**
     * variable name thematic
     */
    @Column(name = "TH_NAME")
    private String name;

    /**
     * Variable for the relationship with the general program entity
     */
    @JoinColumn(name = "FK_TH_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkThGeneral;

    /**
     * Variable for the relationship with the program thematic core entity
     */
    @OneToMany(mappedBy = "fkPttThematic", cascade = CascadeType.ALL)
    private List<PtThematic> listPtThematic;

    /**
     * constructor method
     */
    public Thematic() {
    }

    /**
     * constructor method
     *
     * @param name
     */
    public Thematic(String name) {
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

    public List<PtThematic> getListPtThematic() {
        return listPtThematic;
    }

    public void setListPtThematic(List<PtThematic> listPtThematic) {
        this.listPtThematic = listPtThematic;
    }

    public GeneralProgram getFkThGeneral() {
        return fkThGeneral;
    }

    public void setFkThGeneral(GeneralProgram fkThGeneral) {
        this.fkThGeneral = fkThGeneral;
    }

}
