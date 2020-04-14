package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical program class. Contains all the methods
 * required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProgramLogicLocal {

    List<ProgramP> getList() throws GenericException;

    ProgramP get(int idProgram) throws GenericException;

    void add(ProgramP program) throws GenericException;

    void edit(ProgramP program) throws GenericException;

    void disable(int id, DatosSolicitudPOJO dataS) throws GenericException;

}
