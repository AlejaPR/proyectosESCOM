package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.GeneralClassP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical general class class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface GeneralClassLogicLocal {

    List<GeneralClassP> getList(int program, String table) throws GenericException;

    GeneralClassP get(int id, String table) throws GenericException;

    String add(GeneralClassP generalC) throws GenericException;

    String edit(GeneralClassP generalC) throws GenericException;

    String delete(GeneralClassP generalC) throws GenericException;

}
