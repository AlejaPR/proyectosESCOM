/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface ConditionLogicLocal {

    public List<ConditionP> getList(int idDocument) throws GenericException;

    public ConditionP get(int idCondition) throws GenericException;

    public List<ConditionView> getListPercentage(int idProcess) throws GenericException;

    public void add(ConditionP condition) throws GenericException;

    public void edit(ConditionP condition) throws GenericException;

    public void disable(int idCondition, DatosSolicitudPOJO dataR) throws GenericException;

    public void approve(int idCondition, DatosSolicitudPOJO dataR) throws GenericException;

}
