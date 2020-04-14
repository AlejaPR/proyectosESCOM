package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ThematicCoreP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical thematic core class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ThematicCoreLogicLocal {

    List<ThematicCoreP> getList(int program) throws GenericException;

    ThematicCoreP get(int id) throws GenericException;

    void add(ThematicCoreP thematic) throws GenericException;

    void edit(ThematicCoreP thematic) throws GenericException;

    void delete(ThematicCoreP thematic) throws GenericException;

}
