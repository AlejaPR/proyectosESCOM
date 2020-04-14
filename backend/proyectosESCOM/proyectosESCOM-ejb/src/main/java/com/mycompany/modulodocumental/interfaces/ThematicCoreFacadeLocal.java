package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.ThematicCore;
import java.util.List;
import javax.ejb.Local;

/**
 *This is the interface for the thematic core class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ThematicCoreFacadeLocal {

    void create(ThematicCore thematicCore);

    void edit(ThematicCore thematicCore);

    void remove(ThematicCore thematicCore);

    ThematicCore find(Object id);

    List<ThematicCore> findAll();

    List<ThematicCore> findRange(int[] range);

    int count();

    List<ThematicCore> getList(int general);

}
