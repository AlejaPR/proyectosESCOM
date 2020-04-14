package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.OccupationalProfile;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the occupational profile class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface OccupationalProfileFacadeLocal {

    void create(OccupationalProfile occupationalProfile);

    void edit(OccupationalProfile occupationalProfile);

    void remove(OccupationalProfile occupationalProfile);

    OccupationalProfile find(Object id);

    List<OccupationalProfile> findAll();

    List<OccupationalProfile> findRange(int[] range);

    int count();

    List<OccupationalProfile> getList(int general);
}
