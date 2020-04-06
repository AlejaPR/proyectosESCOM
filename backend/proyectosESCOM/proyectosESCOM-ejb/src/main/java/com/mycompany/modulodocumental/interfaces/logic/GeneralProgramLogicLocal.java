/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.GeneralProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface GeneralProgramLogicLocal {

    public List<GeneralProgramP> getList() throws GenericException;

    public GeneralProgramP get(int idGeneralP) throws GenericException;

    public void add(GeneralProgramP generalP) throws GenericException;

    public void edit(GeneralProgramP generalP) throws GenericException;    

}
