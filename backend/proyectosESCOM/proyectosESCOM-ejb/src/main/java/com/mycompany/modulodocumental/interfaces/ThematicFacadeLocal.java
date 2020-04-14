package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Thematic;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the thematic class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ThematicFacadeLocal {

    void create(Thematic thematic);

    void edit(Thematic thematic);

    void remove(Thematic thematic);

    Thematic find(Object id);

    List<Thematic> findAll();

    List<Thematic> findRange(int[] range);

    int count();

    List<Thematic> getList(int general);
}
