/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

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
public interface ProcessLogicFacadeLocal {

    public List<ProcessP> listProcess(int id) throws GenericException;

    public ProcessP getProcessId(int id) throws GenericException;

    public void addProcess(ProcessP process) throws GenericException;

    public void editProcess(ProcessP process) throws GenericException;

    public void disableProcess(int id, DatosSolicitudPOJO dataR) throws GenericException;
    
}
