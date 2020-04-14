package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Program;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProgramFacadeLocal {

    void create(Program program);

    void edit(Program program);

    void remove(Program program);

    Program find(Object id);

    List<Program> findAll();

    List<Program> findRange(int[] range);

    int count();

}
