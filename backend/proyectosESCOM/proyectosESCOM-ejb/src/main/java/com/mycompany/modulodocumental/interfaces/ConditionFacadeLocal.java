/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Condition;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
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
