/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Activity;
import com.mycompany.modulodocumental.entity.Annex;
import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ActivityLogicFacadeLocal;
import com.mycompany.modulodocumental.interfaces.AnnexFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author hashy
 */
@Stateless
public class ActivityLogic implements ActivityLogicFacadeLocal {

    @EJB
    private ActivityFacadeLocal activityFacade;
    @EJB
    private AnnexFacadeLocal annexFacade;
    @EJB
    private ConditionFacadeLocal conditionFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_ACTIVITY";

    private static final String CLASS = "Clase logica actividad";

    @Override
    public ActivityP getActivityId(int id) throws GenericException {
        try {
            Activity act = activityFacade.find(id);
            ActivityP data = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType());
            data.setIdCondition(act.getFkActCondition().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener actividad id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public void addActivity(ActivityP activity) throws GenericException {
        try {
            Activity data = new Activity(activity.getName(), activity.getDescription(), "", activity.getState(), activity.getType());
            Condition condition = conditionFacade.find(activity.getIdCondition());
            data.setFkActCondition(condition);
            activityFacade.create(data);
            activity.getRequestData().setOperacion(TABLE);
            bitacora.registrarEnBitacora(activity.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public void editActivity(ActivityP activity) throws GenericException {
        try {
            Activity data = activityFacade.find(activity.getId());
            data.setDescription(activity.getDescription());
            data.setName(activity.getName());
            data.setType(activity.getType());
            activityFacade.edit(data);
            activity.getRequestData().setOperacion(TABLE);
            bitacora.registrarEnBitacora(activity.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public List<ActivityP> listActivities(int id) throws GenericException {
        try {
            List<Activity> list = activityFacade.listActivities(id);
            List<ActivityP> data = new ArrayList<>();
            for (Activity act : list) {
                ActivityP aux = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType());
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista actividades", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void addInformation(ActivityP activity) throws GenericException {
        try {
            Activity data = activityFacade.find(activity.getId());
            data.setInformation(activity.getInformation());
            activityFacade.edit(data);
            activity.getRequestData().setOperacion(TABLE);
            bitacora.registrarEnBitacora(activity.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar informacion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public String allInformation(int id) throws GenericException {
        try {
            String data = activityFacade.allInformation(id);
            return data;
        } catch (Exception e) {
            throw new GenericException("error server");
        }

    }

    @Override
    public void disableActivity(int id, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Activity del = activityFacade.find(id);
            activityFacade.remove(del);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void changeStatus(ActivityP activity) throws GenericException {
        try {
            Activity data = activityFacade.find(activity.getId());
            data.setState(activity.getState());
            activityFacade.edit(data);
            activity.getRequestData().setOperacion(TABLE);
            bitacora.registrarEnBitacora(activity.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Cambiar estado", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void associateAnnex(int activity, int annex, DatosSolicitudPOJO dataS) throws GenericException {
        try {
            Activity data = activityFacade.find(activity);
            Annex ann = annexFacade.find(annex);
            data.setFkActAnnex(ann);
            activityFacade.edit(data);
            dataS.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataS);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "asociar anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
