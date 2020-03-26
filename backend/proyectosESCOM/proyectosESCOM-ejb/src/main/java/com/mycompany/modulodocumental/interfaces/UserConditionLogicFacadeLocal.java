/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface UserConditionLogicFacadeLocal {

    public List<ConditionView> listUserCondition(int idU, int idP) throws GenericException;

}
