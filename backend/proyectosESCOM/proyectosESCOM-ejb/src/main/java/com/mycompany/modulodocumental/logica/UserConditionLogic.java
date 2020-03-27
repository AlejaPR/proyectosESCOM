/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.entity.UserCondition;
import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.UserConditionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.UserConditionLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.UserConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
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
    private ConditionFacadeLocal conditionFacade;
    @EJB
    private UserConditionFacadeLocal userConditionFacade;
    @EJB
    private UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_USER_CONDITION";

    private static final String CLASS = "Clase logica usuario condicion";

    @Override
    public List<ConditionView> listUserCondition(String token, int idP) throws GenericException {
        try {
            UsuarioPOJO user = bitacora.devolverInformacionDeUsuario(token);
            List<Condition> list = userConditionFacade.listCondition(user.getId(), idP);
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

    @Override
    public List<UsuarioPOJO> listUsers() throws GenericException {
        try {
            List<UsuarioPOJO> data = bitacora.devolverUsuariosModulo();
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista usuarios", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public List<UsuarioPOJO> listUsersCondition(int id) throws GenericException {
        try {
            List<UserCondition> list = userConditionFacade.listUsersCondition(id);
            List<UsuarioPOJO> users = bitacora.devolverUsuariosModulo();
            List<UsuarioPOJO> data = new ArrayList<>();
            for (UserCondition list1 : list) {
                for (UsuarioPOJO user1 : users) {
                    if (list1.getFkUcUser() == user1.getId()) {
                        data.add(user1);
                    }
                }
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista usuarios condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void associateUserCondition(UserConditionP userCondition) throws GenericException {
        try {
            List<UserCondition> valid = userConditionFacade.listUsersCondition(userCondition.getIdCondition());
            boolean cont = false;
            for (UserCondition valid1 : valid) {
                if (valid1.getFkUcUser() == userCondition.getIdUser()) {
                    cont = true;
                }
            }
            if (!cont) {
                Condition condition = conditionFacade.find(userCondition.getIdCondition());
                UserCondition data = new UserCondition();
                data.setFkUcUser(userCondition.getIdUser());
                data.setFkUcCondition(condition);
                userConditionFacade.create(data);
                userCondition.getRequestData().setTablaInvolucrada(TABLE);
                bitacora.registrarEnBitacora(userCondition.getRequestData());
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Asociar usuario condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    @Override
    public void deleteUserCondition(UserConditionP userCondition) throws GenericException {
        try {
            List<UserCondition> valid = userConditionFacade.listUsersCondition(userCondition.getIdCondition());
            for (UserCondition valid1 : valid) {
                if (valid1.getFkUcUser() == userCondition.getIdUser()) {
                    userConditionFacade.remove(valid1);
                    break;
                }
            }
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Eliminar usuario condicion", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
