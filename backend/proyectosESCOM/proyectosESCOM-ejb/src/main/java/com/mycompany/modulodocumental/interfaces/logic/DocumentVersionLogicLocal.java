package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.DocumentVersionP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical document version class. Contains all
 * the methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface DocumentVersionLogicLocal {

    List<DocumentVersionP> getListCurrent(int idDocument) throws GenericException;

    List<DocumentVersionP> getListOld(int idProgram) throws GenericException;

    void add(DocumentVersionP version) throws GenericException;

}
