/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.io.Serializable;

/**
 *
 * @author HASHY
 */
public class ProgramP implements Serializable {

    private int id;
    private String name;
    private String levelEducation;
    private String institution;
    private int academicCredits;
    private int duration;
    private String methodology;
    private String campus;
    private DatosSolicitudPOJO requestData;

    public ProgramP() {
    }

    public ProgramP(int id, String name, String levelEducation, String institution, int academicCredits, int duration, String methodology, String campus) {
        this.id = id;
        this.name = name;
        this.levelEducation = levelEducation;
        this.institution = institution;
        this.academicCredits = academicCredits;
        this.duration = duration;
        this.methodology = methodology;
        this.campus = campus;
    }

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

    public DatosSolicitudPOJO getRequestData() {
        return requestData;
    }

    public void setRequestData(DatosSolicitudPOJO requestData) {
        this.requestData = requestData;
    }

}
