/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionLogicFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ProcessFacadeLocal;
import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
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
public class ConditionLogic implements ConditionLogicFacadeLocal {

    @EJB
    private ConditionFacadeLocal conditionFacade;
    @EJB
    private ActivityFacadeLocal activityFacade;
    @EJB
    private ProcessFacadeLocal processFacade;
    @EJB
    UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_CONDITION";

    private static final String CLASS = "Clase logica condicion";

    @Override
    public List<ConditionP> listCondition(int idD) throws GenericException {
        try {
            List<Condition> list = conditionFacade.listConditionDoc(idD);
            List<ConditionP> data = new ArrayList<>();
            for (Condition con : list) {
                ConditionP conP = new ConditionP(con.getId(), con.getName(), con.getDescription(), con.getState(), con.getStartDate(), con.getFinalDate());
                data.add(conP);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista condiciones", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public ConditionP getConditionId(int id) throws GenericException {
        try {
            Condition con = conditionFacade.find(id);
            ConditionP data = new ConditionP(con.getId(),
                    con.getName(),
                    con.getDescription(),
                    con.getState(),
                    con.getStartDate(),
                    con.getFinalDate());
            data.setProcess(con.getFkConProcess().getId());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtiene condicion id ", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }

    }

    @Override
    public List<ConditionView> listConditionPercentage(int idP) throws GenericException {
        try {
            List<Condition> list = conditionFacade.listConditionPro(idP);
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

    @Override
    public void addCondition(ConditionP condition) throws GenericException {
        try {
            com.mycompany.modulodocumental.entity.Process proce = processFacade.find(condition.getProcess());
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

    @Override
    public void editCondition(ConditionP condition) throws GenericException {
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

    @Override
    public void disableCondition(int id, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Condition con = conditionFacade.find(id);
            con.setState(-1);
            conditionFacade.edit(con);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void approveCondition(int id, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Condition condition = conditionFacade.find(id);
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
