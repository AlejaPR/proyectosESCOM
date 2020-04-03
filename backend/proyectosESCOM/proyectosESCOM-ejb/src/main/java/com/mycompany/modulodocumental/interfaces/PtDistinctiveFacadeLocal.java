/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.PtDistinctive;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
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
    
}
