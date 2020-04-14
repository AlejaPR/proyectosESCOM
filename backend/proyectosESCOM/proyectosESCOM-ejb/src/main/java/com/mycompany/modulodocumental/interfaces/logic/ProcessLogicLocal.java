package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProcessP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical process class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProcessLogicLocal {

    List<ProcessP> getList(int idDocument) throws GenericException;

    ProcessP get(int idProcess) throws GenericException;

    void add(ProcessP process) throws GenericException;

    void edit(ProcessP process) throws GenericException;

    void disable(int idProcess, DatosSolicitudPOJO dataR) throws GenericException;

}
