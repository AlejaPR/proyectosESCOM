package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.GeneralProgramP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical general program class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface GeneralProgramLogicLocal {

    List<GeneralProgramP> getList() throws GenericException;

    GeneralProgramP get(int idGeneralP) throws GenericException;

    void add(GeneralProgramP generalP) throws GenericException;

    void edit(GeneralProgramP generalP) throws GenericException;

    void disable(GeneralProgramP generalP) throws GenericException;

}
