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
 * This is the class of the program entity. Contains all fields for persistence.
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Entity
@Table(name = "TBL_PROGRAM")
public class Program implements Serializable {

    /**
     * id variable
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PRO_ID")
    private int id;

    /**
     * variable name program
     */
    @Column(name = "PRO_NAME")
    private String name;

    /**
     * variable level education program
     */
    @Column(name = "PRO_LEVEL_EDUCATION")
    private String levelEducation;

    /**
     * variable institution program
     */
    @Column(name = "PRO_INSTITUTION")
    private String institution;

    /**
     * variable academic credits program
     */
    @Column(name = "PRO_ACADEMIC_CREDITS")
    private int academicCredits;

    /**
     * variable duration program
     */
    @Column(name = "PRO_DURATION")
    private int duration;

    /**
     * variable methodology program
     */
    @Column(name = "PRO_METHODOLOGY")
    private String methodology;

    /**
     * variable campus program
     */
    @Column(name = "PRO_CAMPUS")
    private String campus;

    /**
     * variable state program
     */
    @Column(name = "PRO_STATE")
    private int state;

    /**
     * Variable for the relationship with the general program entity
     */
    @JoinColumn(name = "FK_PRO_GENERAL", referencedColumnName = "PK_GP_ID")
    @ManyToOne
    private GeneralProgram fkProGeneral;

    /**
     * Variable for the relationship with the document entity
     */
    @OneToMany(mappedBy = "fkDocProgram", cascade = CascadeType.ALL)
    private List<Document> listDocument;

    /**
     * Variable for the relationship with the annex entity
     */
    @OneToMany(mappedBy = "fkAxProgram", cascade = CascadeType.ALL)
    private List<Annex> listAnnex;

    /**
     * Variable for the relationship with the programa thematic core entity
     */
    @OneToMany(mappedBy = "fkPtProgram", cascade = CascadeType.ALL)
    private List<ProgramThematicCore> listProgramThematicCore;

    /**
     * constructor method
     */
    public Program() {
    }

    /**
     * constructor method
     *
     * @param name
     * @param levelEducation
     * @param institution
     * @param academicCredits
     * @param duration
     * @param methodology
     * @param campus
     * @param state
     */
    public Program(String name, String levelEducation, String institution, int academicCredits, int duration, String methodology, String campus, int state) {
        this.name = name;
        this.levelEducation = levelEducation;
        this.institution = institution;
        this.academicCredits = academicCredits;
        this.duration = duration;
        this.methodology = methodology;
        this.campus = campus;
        this.state = state;
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

    public String getLevelEducation() {
        return levelEducation;
    }

    public void setLevelEducation(String levelEducation) {
        this.levelEducation = levelEducation;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getAcademicCredits() {
        return academicCredits;
    }

    public void setAcademicCredits(int academicCredits) {
        this.academicCredits = academicCredits;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public GeneralProgram getFkProGeneral() {
        return fkProGeneral;
    }

    public void setFkProGeneral(GeneralProgram fkProGeneral) {
        this.fkProGeneral = fkProGeneral;
    }

    public List<Document> getListDocument() {
        return listDocument;
    }

    public void setListDocument(List<Document> listDocument) {
        this.listDocument = listDocument;
    }

    public List<Annex> getListAnnex() {
        return listAnnex;
    }

    public void setListAnnex(List<Annex> listAnnex) {
        this.listAnnex = listAnnex;
    }

    public List<ProgramThematicCore> getListProgramThematicCore() {
        return listProgramThematicCore;
    }

    public void setListProgramThematicCore(List<ProgramThematicCore> listProgramThematicCore) {
        this.listProgramThematicCore = listProgramThematicCore;
    }

}
