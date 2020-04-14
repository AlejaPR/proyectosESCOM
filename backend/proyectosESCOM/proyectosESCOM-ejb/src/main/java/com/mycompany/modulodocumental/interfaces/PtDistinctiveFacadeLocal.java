package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtDistinctive;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the program thematic - distinctive feature class.
 * Contains all the methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface PtDistinctiveFacadeLocal {

    void create(PtDistinctive ptDistinctive);

    void edit(PtDistinctive ptDistinctive);

    void remove(PtDistinctive ptDistinctive);

    PtDistinctive find(Object id);

    List<PtDistinctive> findAll();

    List<PtDistinctive> findRange(int[] range);

    int count();

    List<PtDistinctive> getList(int programT);

}
