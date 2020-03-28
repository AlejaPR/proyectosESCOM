/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ActivityLogicFacadeLocal {

    ActivityP getActivityId(int id) throws GenericException;

    void addActivity(ActivityP activitiy) throws GenericException;

    void editActivity(ActivityP activity) throws GenericException;

    List<ActivityP> listActivities(int id) throws GenericException;

    void addInformation(ActivityP activity) throws GenericException;

    String allInformation(int id) throws GenericException;

    void disableActivity(int id, DatosSolicitudPOJO dataR) throws GenericException;

    void changeStatus(ActivityP activity) throws GenericException;

    void associateAnnex(int activity, int annex, DatosSolicitudPOJO dataS) throws GenericException;

}
