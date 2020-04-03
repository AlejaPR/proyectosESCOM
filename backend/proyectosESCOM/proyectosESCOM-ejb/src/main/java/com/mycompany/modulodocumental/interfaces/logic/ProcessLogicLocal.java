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

    public List<ProcessP> getList(int idDocument) throws GenericException;

    public ProcessP get(int idProcess) throws GenericException;

    public void add(ProcessP process) throws GenericException;

    public void edit(ProcessP process) throws GenericException;

    public void disable(int idProcess, DatosSolicitudPOJO dataR) throws GenericException;
    
}
