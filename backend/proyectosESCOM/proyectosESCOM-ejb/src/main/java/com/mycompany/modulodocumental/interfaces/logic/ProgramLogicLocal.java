/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProgramLogicLocal {

    public List<ProgramP> getList() throws GenericException;

    public ProgramP get(int idProgram) throws GenericException;

    public void add(ProgramP program) throws GenericException;

    public void edit(ProgramP program) throws GenericException;

}
