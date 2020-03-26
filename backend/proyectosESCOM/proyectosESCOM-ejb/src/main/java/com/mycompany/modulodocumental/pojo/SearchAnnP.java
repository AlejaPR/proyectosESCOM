/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.pojo;

import java.io.Serializable;

/**
 *
 * @author HASHY
 */
public class SearchAnnP implements Serializable{
    
    private int idProgram;
    private String name;

    public SearchAnnP() {
    }

    public SearchAnnP(int idProgram, String name) {
        this.idProgram = idProgram;
        this.name = name;
    }

    public int getIdProgram() {
        return idProgram;
    }

    public void setIdProgram(int idProgram) {
        this.idProgram = idProgram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
