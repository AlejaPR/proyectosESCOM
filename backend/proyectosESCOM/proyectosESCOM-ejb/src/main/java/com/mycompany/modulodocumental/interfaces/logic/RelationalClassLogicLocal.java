package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.RelationalClassP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical relational class. Contains all
 * the methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface RelationalClassLogicLocal {

    List<RelationalClassP> getList(int programT, String table) throws GenericException;

    String add(RelationalClassP relation) throws GenericException;

    String delete(RelationalClassP relation) throws GenericException;

}
