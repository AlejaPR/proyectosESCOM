package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Competition;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the competition class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface CompetitionFacadeLocal {

    void create(Competition competition);

    void edit(Competition competition);

    void remove(Competition competition);

    Competition find(Object id);

    List<Competition> findAll();

    List<Competition> findRange(int[] range);

    int count();

    List<Competition> getList(int general);

}
