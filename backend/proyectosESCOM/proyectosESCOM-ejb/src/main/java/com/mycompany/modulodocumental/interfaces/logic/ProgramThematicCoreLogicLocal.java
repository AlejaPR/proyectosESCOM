package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ProgramThematicCoreP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical program thematic core class. Contains
 * all the methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProgramThematicCoreLogicLocal {

    List<ProgramThematicCoreP> getList(int program) throws GenericException;

    ProgramThematicCoreP get(int id) throws GenericException;

    void add(ProgramThematicCoreP programT) throws GenericException;

    void edit(ProgramThematicCoreP programT) throws GenericException;

    void delete(int id, DatosSolicitudPOJO dataS) throws GenericException;

}
