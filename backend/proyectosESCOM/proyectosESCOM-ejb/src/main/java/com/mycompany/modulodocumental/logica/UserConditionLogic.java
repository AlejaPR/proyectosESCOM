/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.UserConditionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.UserConditionLogicFacadeLocal;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
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
public class UserConditionLogic implements UserConditionLogicFacadeLocal {

    @EJB
    private ActivityFacadeLocal activityFacade;
    @EJB
    private UserConditionFacadeLocal userConditionFacade;
    @EJB 
    private UtilitarioFacadeLocal bitacora;
    
    private static final String TABLE = "TBL_USER_CONDITION";
    
    private static final String CLASS = "Clase logica usuario condicion";

    @Override
    public List<ConditionView> listUserCondition(int idU, int idP) throws GenericException {
        try {
            List<Condition> list = userConditionFacade.listCondition(idU, idP);
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
            bitacora.registroLogger(CLASS, "Lista usuarios condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
