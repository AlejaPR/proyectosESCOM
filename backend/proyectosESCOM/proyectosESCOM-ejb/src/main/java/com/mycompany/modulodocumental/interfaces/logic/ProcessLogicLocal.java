/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProcessP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ProcessLogicLocal {

    List<ProcessP> getList(int idDocument) throws GenericException;

    ProcessP get(int idProcess) throws GenericException;

    void add(ProcessP process) throws GenericException;

    void edit(ProcessP process) throws GenericException;

    void disable(int idProcess, DatosSolicitudPOJO dataR) throws GenericException;
    
}
