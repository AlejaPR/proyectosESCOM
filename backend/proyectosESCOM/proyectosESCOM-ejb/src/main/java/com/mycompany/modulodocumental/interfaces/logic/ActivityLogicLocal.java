package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ActivityAnnexView;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical activity class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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
