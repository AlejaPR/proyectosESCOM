/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hashy
 */
@Entity
@Table(name = "TBL_PT_OCCUPATIONAL")
public class PtOccupational implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTO_ID")
    private int id;

    private OccupationalProfile fkPtoOccupational;

    private ProgramThematicCore fkPtoProgramThematic;

    public PtOccupational() {
    }

    public PtOccupational(int id, OccupationalProfile fkPtoOccupational, ProgramThematicCore fkPtoProgramThematic) {
        this.id = id;
        this.fkPtoOccupational = fkPtoOccupational;
        this.fkPtoProgramThematic = fkPtoProgramThematic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OccupationalProfile getFkPtoOccupational() {
        return fkPtoOccupational;
    }

    public void setFkPtoOccupational(OccupationalProfile fkPtoOccupational) {
        this.fkPtoOccupational = fkPtoOccupational;
    }

    public ProgramThematicCore getFkPtoProgramThematic() {
        return fkPtoProgramThematic;
    }

    public void setFkPtoProgramThematic(ProgramThematicCore fkPtoProgramThematic) {
        this.fkPtoProgramThematic = fkPtoProgramThematic;
    }

    
}
