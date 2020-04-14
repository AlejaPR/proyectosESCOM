package com.mycompany.modulodocumental.interfaces.logic;

import com.mycompany.modulodocumental.pojo.DocumentP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the logical document class. Contains all the
 * methods required for connecting the logic with the entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Local
public interface DocumentLogicLocal {

    public int getIdDocument(int id) throws GenericException;

    public DocumentP get(int idDocument) throws GenericException;

    public List<DocumentP> getList() throws GenericException;

    public DocumentP documentIdEdit(int id) throws GenericException;

    public void add(DocumentP document) throws GenericException;

    public void edit(DocumentP document) throws GenericException;

    public void disable(int idDocument, DatosSolicitudPOJO dataR) throws GenericException;

}
