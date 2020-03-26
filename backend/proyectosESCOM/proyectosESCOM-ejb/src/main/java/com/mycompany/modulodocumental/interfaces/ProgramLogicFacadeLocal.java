/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.ProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProgramLogicFacadeLocal {

    public List<ProgramP> listProgram() throws GenericException;

    public ProgramP getProgramId(int id) throws GenericException;

    public void addProgram(ProgramP program) throws GenericException;

    public void editProgram(ProgramP program) throws GenericException;

}
