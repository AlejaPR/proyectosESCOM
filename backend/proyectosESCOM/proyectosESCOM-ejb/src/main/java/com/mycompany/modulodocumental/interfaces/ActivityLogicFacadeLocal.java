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

    public ActivityP getActivityId(int id) throws GenericException;

    public void addActivity(ActivityP activitiy) throws GenericException;

    public void editActivity(ActivityP activity) throws GenericException;

    public List<ActivityP> listActivities(int id) throws GenericException;

    public void addInformation(ActivityP activity) throws GenericException;

    public String allInformation(int id) throws GenericException;

    public void disableActivity(int id, DatosSolicitudPOJO dataR) throws GenericException;

}
