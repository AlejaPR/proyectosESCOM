package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Condition;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the condition class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ConditionFacadeLocal {

    void create(Condition condition);

    void edit(Condition condition);

    void remove(Condition condition);

    Condition find(Object id);

    List<Condition> findAll();

    List<Condition> findRange(int[] range);

    int count();

    List<Condition> listConditionPro(int idProcess);

    List<Condition> listConditionDoc(int idDocument);
}
