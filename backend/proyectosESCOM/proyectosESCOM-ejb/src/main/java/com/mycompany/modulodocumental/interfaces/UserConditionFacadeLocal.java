package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.entity.UserCondition;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the user condition class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface UserConditionFacadeLocal {

    void create(UserCondition userCondition);

    void edit(UserCondition userCondition);

    void remove(UserCondition userCondition);

    UserCondition find(Object id);

    List<UserCondition> findAll();

    List<UserCondition> findRange(int[] range);

    int count();

    List<Condition> listCondition(int user, int document);

    List<UserCondition> listUsersCondition(int id);

}
