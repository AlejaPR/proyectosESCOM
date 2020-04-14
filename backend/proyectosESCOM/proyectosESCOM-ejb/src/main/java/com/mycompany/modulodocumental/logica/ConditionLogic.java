package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.entity.Process;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.ConditionLogicLocal;
import com.mycompany.modulodocumental.interfaces.ProcessFacadeLocal;
import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the condition logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ConditionLogic implements ConditionLogicLocal {

    /**
     * Condition interface injection
     */
    @EJB
    private ConditionFacadeLocal conditionFacade;

    /**
     * Activity interface injection
     */
    @EJB
    private ActivityFacadeLocal activityFacade;

    /**
     * Process interface injection
     */
    @EJB
    private ProcessFacadeLocal processFacade;

    /**
     * Bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_CONDITION";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica condicion";

    /**
     * method to get list of conditions
     *
     * @param idDocument
     * @return
     * @throws GenericException
     */
    @Override
    public List<ConditionP> getList(int idDocument) throws GenericException {
        try {
            List<Condition> list = conditionFacade.listConditionDoc(idDocument);
            List<ConditionP> data = new ArrayList<>();
            for (Condition con : list) {
                SimpleDateFormat formatoUsuario = new SimpleDateFormat("yyyy-MM-dd");
                Date startData = null;
                String startDataN = null;
                if (con.getStartDate() == null) {
                    startData = null;
                } else {
                    startData = con.getStartDate();
                    startDataN = formatoUsuario.format(startData);
                }
                Date finalData = null;
                String finalDataN = null;
                if (con.getFinalDate() == null) {
                    finalData = null;
                } else {
                    finalData = con.getFinalDate();
                    finalDataN = formatoUsuario.format(finalData);
                }

                ConditionP conP = new ConditionP(con.getId(), con.getName(), con.getDescription(), con.getState(), startDataN, finalDataN, 1);
                data.add(conP);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista condiciones", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method to get a condition
     *
     * @param idCondition
     * @return
     * @throws GenericException
     */
    @Override
    public ConditionP get(int idCondition) throws GenericException {
        try {
            Condition con = conditionFacade.find(idCondition);
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("yyyy-MM-dd");
            Date startData = null;
            String startDataN = null;
            if (con.getStartDate() == null) {
                startData = null;
            } else {
                startData = con.getStartDate();
                startDataN = formatoUsuario.format(startData);
            }
            Date finalData = null;
            String finalDataN = null;
            if (con.getFinalDate() == null) {
                finalData = null;
            } else {
                finalData = con.getFinalDate();
                finalDataN = formatoUsuario.format(finalData);
            }
            ConditionP data = new ConditionP(con.getId(),
                    con.getName(),
                    con.getDescription(),
                    con.getState(),
                    startDataN,
                    finalDataN, 1);
            data.setProcess(con.getFkConProcess().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtiene condicion id ", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method to get the list of conditions with the percentage of progress
     *
     * @param idProcess
     * @return
     * @throws GenericException
     */
    @Override
    public List<ConditionView> getListPercentage(int idProcess) throws GenericException {
        try {
            List<Condition> list = conditionFacade.listConditionPro(idProcess);
            List<ConditionView> data = new ArrayList<>();
            for (Condition con : list) {
                int aux = activityFacade.Percentage(con.getId());
                int total = activityFacade.totalActivities(con.getId());
                int resul = 0;
                if (total != 0) {
                    resul = (int) (aux * 100) / total;
                }
                ConditionView conV = new ConditionView(con.getId(), con.getName(), con.getDescription(), con.getState(), resul);
                data.add(conV);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista condiciones porcentaje", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    /**
     * method of adding a condition
     *
     * @param condition
     * @throws GenericException
     */
    @Override
    public void add(ConditionP condition) throws GenericException {
        try {
            Process proce = processFacade.find(condition.getProcess());
            Condition data = new Condition(condition.getName(), condition.getDescription(), condition.getState(), condition.getStartDate(), condition.getFinalDate());
            data.setFkConProcess(proce);
            conditionFacade.create(data);
            condition.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(condition.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of editing a condition
     *
     * @param condition
     * @throws GenericException
     */
    @Override
    public void edit(ConditionP condition) throws GenericException {
        try {
            Condition data = conditionFacade.find(condition.getId());
            data.setDescription(condition.getDescription());
            data.setName(condition.getName());
            com.mycompany.modulodocumental.entity.Process process = processFacade.find(condition.getProcess());
            data.setFkConProcess(process);
            conditionFacade.edit(data);
            condition.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(condition.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to disable a condition
     *
     * @param idCondition
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void disable(int idCondition, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Condition con = conditionFacade.find(idCondition);
            con.setState(-1);
            conditionFacade.edit(con);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of approving a condition
     *
     * @param idCondition
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void approve(int idCondition, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Condition condition = conditionFacade.find(idCondition);
            condition.setState(2);
            conditionFacade.edit(condition);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Aprobar condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
