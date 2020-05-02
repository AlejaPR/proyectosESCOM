package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Activity;
import com.mycompany.modulodocumental.entity.Annex;
import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ActivityLogicLocal;
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
 * This is the class in charge of the activity logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ActivityLogic implements ActivityLogicLocal {

    /**
     * Activity interface injection
     */
    @EJB
    private ActivityFacadeLocal activityFacade;

    /**
     * Annex interface injection
     */
    @EJB
    private AnnexFacadeLocal annexFacade;

    /**
     * Annex version interface injection
     */
    @EJB
    private AnnexVersionFacadeLocal annexVersionFacade;

    /**
     * Condition interface injection
     */
    @EJB
    private ConditionFacadeLocal conditionFacade;

    /**
     * Bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_ACTIVITY";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica actividad";

    /**
     * method that gets activity
     *
     * @param idActivity
     * @return
     * @throws GenericException
     */
    @Override
    public ActivityP get(int idActivity) throws GenericException {
        try {
            Activity act = activityFacade.find(idActivity);
            ActivityP data = new ActivityP(act.getId(), act.getName(), act.getDescription(), act.getInformation(), act.getState(), act.getType(), act.getNumber());
            data.setIdCondition(act.getFkActCondition().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener actividad id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method that adds an activity
     *
     * @param activity
     * @throws GenericException
     */
    @Override
    public void add(ActivityP activity) throws GenericException {
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
            activity.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(activity.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method that edits an activity
     *
     * @param activity
     * @throws GenericException
     */
    @Override
    public void edit(ActivityP activity) throws GenericException {
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

    /**
     * method that lists informative activities
     *
     * @param idCondition
     * @return
     * @throws GenericException
     */
    @Override
    public List<ActivityP> listInfo(int idCondition) throws GenericException {
        try {
            List<Activity> list = activityFacade.listActivitiesInfo(idCondition);
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

    /**
     * method that adds information to the activity
     *
     * @param activity
     * @throws GenericException
     */
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

    /**
     * method that obtains all the information of the activities
     *
     * @param idCondition
     * @return
     * @throws GenericException
     */
    @Override
    public String allInformation(int idCondition) throws GenericException {
        try {
            List<Condition> conditions = conditionFacade.listConditionPro(idCondition);
            int cont = 1;
            String data = "";

            for (Condition condition : conditions) {
                if ("General".equals(condition.getName())) {
                    List<ActivityP> info = listInfo(condition.getId());
                    for (ActivityP inf : info) {
                        if (inf.getInformation() != null) {
                            data = data + inf.getInformation() + "<br/>";
                        }
                    }
                }
            }

            for (Condition condition : conditions) {
                data = data + "<p style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + condition.getName() + "</p>";
                List<ActivityP> info = listInfo(condition.getId());
                for (ActivityP inf : info) {
                    data = data + "<p style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + inf.getNumber() + ". " + inf.getName() + "</p>";
                }
                cont++;
            }

            cont = 1;

            data = "<br/>" + data + "<h3 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + conditions.get(0).getFkConProcess().getName() + "</h3>";
            for (Condition condition : conditions) {
                data = data + "<h3>" + cont + "." + condition.getName() + "</h3>";
                List<ActivityP> info = listInfo(condition.getId());
                for (ActivityP inf : info) {
                    data = data + "<h4 style=\"font-size: 14px; font-family: arial, helvetica, sans-serif;\">" + cont + "." + inf.getNumber() + ". " + inf.getName() + "</h4>" + "<br/>";
                    if (inf.getInformation() != null) {
                        data = data + inf.getInformation() + "<br/>";
                    } else {
                        data = data + "" + "<br/>";
                    }
                }
                cont++;
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method that removes an activity
     *
     * @param idActivity
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void disable(int idActivity, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Activity del = activityFacade.find(idActivity);
            activityFacade.remove(del);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar actividad", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method that modifies the state of the activity
     *
     * @param activity
     * @throws GenericException
     */
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

    /**
     * method that associates an attachment with an activity
     *
     * @param activity
     * @param annex
     * @param dataS
     * @throws GenericException
     */
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

    /**
     * method that gets an annex type activity
     *
     * @param activity
     * @return
     * @throws GenericException
     */
    @Override
    public ActivityAnnexView getAnnex(int activity) throws GenericException {
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

    /**
     * method that lists the attached type activities
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public List<ActivityP> listAnnex(int id) throws GenericException {
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
