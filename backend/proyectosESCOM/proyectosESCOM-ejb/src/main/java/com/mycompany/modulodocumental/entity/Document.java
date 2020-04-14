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
 * This is the class of the document entity. Contains all fields for
 * persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_DOCUMENT")
public class Document implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_DOC_ID")
    private int id;

    /**
     * variable description document
     */
    @Column(name = "DOC_DESCRIPTION")
    private String description;

    /**
     * variable type document
     */
    @Column(name = "DOC_TYPE")
    private String type;

    /**
     * variable state document
     */
    @Column(name = "DOC_STATE")
    private int state;

    /**
     * Variable for the relationship with the process entity
     */
    @OneToMany(mappedBy = "fkPrcDocument", cascade = CascadeType.ALL)
    private List<Process> listProcess;

    /**
     * Variable for the relationship with the document version entity
     */
    @OneToMany(mappedBy = "fkDvDocument", cascade = CascadeType.ALL)
    private List<DocumentVersion> listDocumentVersion;

    /**
     * Variable for the relationship with the program entity
     */
    @JoinColumn(name = "FK_DOC_PROGRAM", referencedColumnName = "PK_PRO_ID")
    @ManyToOne
    private Program fkDocProgram;

    /**
     * Variable for the relationship with the user entity
     */
    @Column(name = "FK_DOC_USER")
    private int idUser;

    /**
     * constructor method.
     */
    public Document() {
    }

    /**
     * constructor method.
     *
     * @param description
     * @param type
     * @param state
     */
    public Document(String description, String type, int state) {
        this.description = description;
        this.type = type;
        this.state = state;
    }

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Process> getListProcess() {
        return listProcess;
    }

    public void setListProcess(List<Process> listProcess) {
        this.listProcess = listProcess;
    }

    public Program getFkDocProgram() {
        return fkDocProgram;
    }

    public void setFkDocProgram(Program fkDocProgram) {
        this.fkDocProgram = fkDocProgram;
    }

    public List<DocumentVersion> getListDocumentVersion() {
        return listDocumentVersion;
    }

    public void setListDocumentVersion(List<DocumentVersion> listDocumentVersion) {
        this.listDocumentVersion = listDocumentVersion;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

}
