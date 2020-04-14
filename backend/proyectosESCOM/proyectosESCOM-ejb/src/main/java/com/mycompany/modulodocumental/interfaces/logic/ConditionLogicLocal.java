package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical condition class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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
