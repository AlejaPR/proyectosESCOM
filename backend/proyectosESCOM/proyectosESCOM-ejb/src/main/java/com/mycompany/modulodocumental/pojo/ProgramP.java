package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 * This is the POJO class of the program entity. contains the variables of the
 * table without annotations
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
public class ProgramP implements Serializable {

    /**
     * id variable
     */
    private int id;

    /**
     * variable name program
     */
    private String name;

    /**
     * variable leven education program
     */
    private String levelEducation;

    /**
     * variable institution program
     */
    private String institution;

    /**
     * variable academic credits program
     */
    private int academicCredits;

    /**
     * variable duration program
     */
    private int duration;

    /**
     * variable methodology program
     */
    private String methodology;

    /**
     * variable campus program
     */
    private String campus;

    /**
     * variable state program
     */
    private int state;

    /**
     * variable id general program
     */
    private int idGeneral;

    /**
     * request data variable
     */
    private DatosSolicitudPOJO requestData;

    /**
     * constructor method
     */
    public ProgramP() {
    }

    /**
     * constructor method
     *
     * @param id
     * @param name
     * @param levelEducation
     * @param institution
     * @param academicCredits
     * @param duration
     * @param methodology
     * @param campus
     * @param state
     */
    public ProgramP(int id, String name, String levelEducation, String institution, int academicCredits, int duration, String methodology, String campus, int state) {
        this.id = id;
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

    public int getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(int idGeneral) {
        this.idGeneral = idGeneral;
    }

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
