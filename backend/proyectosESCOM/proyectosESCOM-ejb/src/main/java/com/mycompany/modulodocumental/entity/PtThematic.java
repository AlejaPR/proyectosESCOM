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
@Table(name = "TBL_PT_THEMATIC")
public class PtThematic implements Serializable{
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_PTT_ID")
    private int id;

    private Thematic fkPttThematic;

    private ProgramThematicCore fkPttProgramThematic;

    public PtThematic() {
    }

    public PtThematic(int id, Thematic fkPttThematic, ProgramThematicCore fkPttProgramThematic) {
        this.id = id;
        this.fkPttThematic = fkPttThematic;
        this.fkPttProgramThematic = fkPttProgramThematic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Thematic getFkPttThematic() {
        return fkPttThematic;
    }

    public void setFkPttThematic(Thematic fkPttThematic) {
        this.fkPttThematic = fkPttThematic;
    }

    public ProgramThematicCore getFkPttProgramThematic() {
        return fkPttProgramThematic;
    }

    public void setFkPttProgramThematic(ProgramThematicCore fkPttProgramThematic) {
        this.fkPttProgramThematic = fkPttProgramThematic;
    }

    
}
