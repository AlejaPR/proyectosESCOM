package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.TrainingArea;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the training area class. Contains all the methods
 * required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
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
