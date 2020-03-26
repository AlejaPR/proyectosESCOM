/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.entity.UserCondition;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
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
    
}
