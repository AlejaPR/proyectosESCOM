package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Process;
import java.util.List;
import javax.ejb.Local;

/**
 *This is the interface for the process class. Contains all the
 * methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ProcessFacadeLocal {

    void create(Process process);

    void edit(Process process);

    void remove(Process process);

    Process find(Object id);

    List<Process> findAll();

    List<Process> findRange(int[] range);

    int count();
    
    List<Process> listProcess(int idDocument);
    
}
