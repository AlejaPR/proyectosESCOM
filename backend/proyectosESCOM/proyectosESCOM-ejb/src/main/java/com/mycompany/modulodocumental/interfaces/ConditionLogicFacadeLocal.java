/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface ConditionLogicFacadeLocal {

    public List<ConditionP> listCondition(int idD) throws GenericException;

    public ConditionP getConditionId(int id) throws GenericException;

    public List<ConditionView> listConditionPercentage(int idP) throws GenericException;

    public void addCondition(ConditionP condition) throws GenericException;

    public void editCondition(ConditionP condition) throws GenericException;

    public void disableCondition(int id, DatosSolicitudPOJO dataR) throws GenericException;

    public void approveCondition(int id, DatosSolicitudPOJO dataR) throws GenericException;

}
