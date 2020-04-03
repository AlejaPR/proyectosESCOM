/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.TrainingArea;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface TrainingAreaFacadeLocal {

    void create(TrainingArea trainingArea);

    void edit(TrainingArea trainingArea);

    void remove(TrainingArea trainingArea);

    TrainingArea find(Object id);

    List<TrainingArea> findAll();

    List<TrainingArea> findRange(int[] range);

    int count();
    
    List<TrainingArea> getList(int general);
    
}
