package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtOccupational;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic - occupational profile class.
 * Contains all the methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface PtOccupationalFacadeLocal {

    void create(PtOccupational ptOccupational);

    void edit(PtOccupational ptOccupational);

    void remove(PtOccupational ptOccupational);

    PtOccupational find(Object id);

    List<PtOccupational> findAll();

    List<PtOccupational> findRange(int[] range);

    int count();

    List<PtOccupational> getList(int programT);

}
