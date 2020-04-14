package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Activity;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the activity class. Contains all the methods
 * required for the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface ActivityFacadeLocal {

    void create(Activity activity);

    void edit(Activity activity);

    void remove(Activity activity);

    Activity find(Object id);

    List<Activity> findAll();

    List<Activity> findRange(int[] range);

    int count();

    List<Activity> listDaughters(int id, int condition);

    List<Activity> listActivitiesInfo(int id);

    List<Activity> listActivitiesAnnex(int id);

    int Percentage(int id);

    int totalActivities(int id);

    String allInformation(int id);
}
