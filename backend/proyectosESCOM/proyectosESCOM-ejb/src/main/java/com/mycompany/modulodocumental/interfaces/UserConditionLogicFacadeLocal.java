/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.UserConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface UserConditionLogicFacadeLocal {

    List<ConditionView> listUserCondition(String token, int idP) throws GenericException;

    List<UsuarioPOJO> listUsers() throws GenericException ;
    
    List<UsuarioPOJO> listUsersCondition(int id) throws GenericException ;
    
    void associateUserCondition(UserConditionP userCondition) throws GenericException;  
    
    void deleteUserCondition(UserConditionP userCondition) throws GenericException;
    
}
