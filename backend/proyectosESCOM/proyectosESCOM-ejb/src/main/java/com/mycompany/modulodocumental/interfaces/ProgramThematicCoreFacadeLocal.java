package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.ProgramThematicCore;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic core class. Contains all the
 * methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProgramThematicCoreFacadeLocal {

    void create(ProgramThematicCore programThematicCore);

    void edit(ProgramThematicCore programThematicCore);

    void remove(ProgramThematicCore programThematicCore);

    ProgramThematicCore find(Object id);

    List<ProgramThematicCore> findAll();

    List<ProgramThematicCore> findRange(int[] range);

    int count();

    List<ProgramThematicCore> getList(int program);

}
