/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ActivityAnnexView;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ActivityLogicLocal {

    ActivityP get(int idActivity) throws GenericException;

    void add(ActivityP activitiy) throws GenericException;

    void edit(ActivityP activity) throws GenericException;

    List<ActivityP> listInfo(int idCondition) throws GenericException;
    
    List<ActivityP> listAnnex(int idCondition) throws GenericException;

    void addInformation(ActivityP activity) throws GenericException;

    String allInformation(int id) throws GenericException;

    void disable(int idActivity, DatosSolicitudPOJO dataR) throws GenericException;

    void changeStatus(ActivityP activity) throws GenericException;

    void associateAnnex(int activity, int annex, DatosSolicitudPOJO dataS) throws GenericException;
    
    ActivityAnnexView getAnnex(int activity) throws GenericException;

}
