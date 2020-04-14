package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.GeneralProgram;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the general program class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface GeneralProgramFacadeLocal {

    void create(GeneralProgram generalProgram);

    void edit(GeneralProgram generalProgram);

    void remove(GeneralProgram generalProgram);

    GeneralProgram find(Object id);

    List<GeneralProgram> findAll();

    List<GeneralProgram> findRange(int[] range);

    int count();

}
