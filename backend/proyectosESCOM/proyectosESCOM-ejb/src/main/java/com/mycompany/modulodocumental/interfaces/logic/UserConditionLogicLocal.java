package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.UserConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical user condition class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface UserConditionLogicLocal {

    List<ConditionView> getList(String token, int idP) throws GenericException;

    List<UsuarioPOJO> listUsers() throws GenericException;

    List<UsuarioPOJO> listUsersCondition(int id) throws GenericException;

    void associate(UserConditionP userCondition) throws GenericException;

    void delete(UserConditionP userCondition) throws GenericException;

}
