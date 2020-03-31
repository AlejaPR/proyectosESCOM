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
import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ActivityAnnexView;
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
    private AnnexVersionFacadeLocal annexVersionFacade;
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
            ActivityP data = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType(), act.getNumber());
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
            String newNamber = "";
            int num = 0;
            if (activity.getType() == 1) {
                Activity parent = activityFacade.find(activity.getParentActivity());
                if (parent != null) {
                    List<Activity> listAux = activityFacade.listDaughters(parent.getId(), activity.getIdCondition());
                    if (listAux.size() > 0) {
                        String aux = listAux.get(listAux.size() - 1).getNumber();
                        String[] numbers = aux.split("\\.");
                        int finalN = Integer.parseInt(numbers[numbers.length - 1]) + 1;
                        for (int i = 0; i < numbers.length - 1; i++) {
                            if (newNamber == "") {
                                newNamber = numbers[i];
                            } else {
                                newNamber = newNamber + "." + numbers[i];
                            }
                        }
                        newNamber = newNamber + "." + finalN;

                    } else {
                        newNamber = parent.getNumber() + ".1";
                    }
                    num = parent.getId();
                } else {
                    List<Activity> listAux = activityFacade.listDaughters(0, activity.getIdCondition());
                    if (listAux.size() > 0) {
                        int aux = Integer.parseInt(listAux.get(listAux.size() - 1).getNumber()) + 1;
                        newNamber = aux + "";
                    } else {
                        newNamber = "1";
                    }
                }
            }
            Activity data = new Activity(activity.getName(), activity.getDescription(), "", activity.getState(), activity.getType(), newNamber);
            data.setParentActivity(num);
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
    public List<ActivityP> listActivitiesInfo(int id) throws GenericException {
        try {
            List<Activity> list = activityFacade.listActivitiesInfo(id);
            List<ActivityP> data = new ArrayList<>();
            for (Activity act : list) {
                ActivityP aux = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType(), act.getNumber());
                if (act.getFkActAnnex() != null) {
                    aux.setIdAnnex(act.getFkActAnnex().getId());
                }
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista actividades informacion", Level.SEVERE, ex.getMessage());
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
            List<Condition> conditions = conditionFacade.listConditionPro(id);
            int cont = 1;
            String data = "";

            for (Condition condition : conditions) {
                data = data + "<h3 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + condition.getName() + "</h3>";
                List<ActivityP> info = listActivitiesInfo(condition.getId());
                for (ActivityP inf : info) {
                    data = data + "<h4 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + inf.getNumber() + ". " + inf.getName() + "</h4>";

                }
                cont++;
            }

            cont = 1;

            data = "<br/>" + data + "<h3 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + conditions.get(0).getFkConProcess().getName() + "</h3>";
            for (Condition condition : conditions) {
                data = data + "<h3>" + cont + "." + condition.getName() + "</h3>";
                List<ActivityP> info = listActivitiesInfo(condition.getId());
                for (ActivityP inf : info) {
                    data = data + "<h4 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + inf.getNumber() + ". " + inf.getName() + "</h4>" + "<br/>";
                    data = data + inf.getInformation() + "<br/>";
                }
                cont++;
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar actividad", Level.SEVERE, ex.getMessage());
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

    @Override
    public ActivityAnnexView getActivityAnnex(int activity) throws GenericException {
        try {
            Activity act = activityFacade.find(activity);
            int idAnnex = 0;
            String url = "";
            String nameAnnex = "";
            if (act.getFkActAnnex() != null) {
                idAnnex = act.getFkActAnnex().getId();
                nameAnnex = act.getFkActAnnex().getName();
                if (annexVersionFacade.listAnnexVersion(act.getFkActAnnex().getId()).size() > 0) {
                    url = annexVersionFacade.listAnnexVersion(act.getFkActAnnex().getId()).get(0).getLocation();
                }
            }
            ActivityAnnexView data = new ActivityAnnexView(activity, act.getName(), act.getDescription(), idAnnex, nameAnnex, url);
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener actividad id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public List<ActivityP> listActivitiesAnnex(int id) throws GenericException {
        try {
            List<Activity> list = activityFacade.listActivitiesAnnex(id);
            List<ActivityP> data = new ArrayList<>();
            for (Activity act : list) {
                ActivityP aux = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType(), act.getNumber());
                if (act.getFkActAnnex() != null) {
                    aux.setIdAnnex(act.getFkActAnnex().getId());
                }
                data.add(aux);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista actividades anexo", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
