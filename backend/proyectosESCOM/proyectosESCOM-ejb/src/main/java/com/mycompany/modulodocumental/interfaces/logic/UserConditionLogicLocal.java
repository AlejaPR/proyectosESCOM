/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface UserConditionLogicLocal {

    List<ConditionView> getList(String token, int idP) throws GenericException;

    List<UsuarioPOJO> listUsers() throws GenericException ;
    
    List<UsuarioPOJO> listUsersCondition(int id) throws GenericException ;
    
    void associate(UserConditionP userCondition) throws GenericException;  
    
    void delete(UserConditionP userCondition) throws GenericException;
    
}
