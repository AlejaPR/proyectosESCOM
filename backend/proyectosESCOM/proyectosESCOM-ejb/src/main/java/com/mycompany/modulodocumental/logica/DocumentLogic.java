package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Document;
import com.mycompany.modulodocumental.entity.Program;
import com.mycompany.modulodocumental.interfaces.DocumentFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.DocumentLogicLocal;
import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.pojo.DocumentP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the document logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class DocumentLogic implements DocumentLogicLocal {

    /**
     * document interface injection
     */
    @EJB
    DocumentFacadeLocal documentFacade;

    /**
     * program interface injection
     */
    @EJB
    ProgramFacadeLocal programFacade;

    /**
     * bitacora interface injection
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_DOCUMENT";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica documento";

    /**
     * method to get the id of a document
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public int getIdDocument(int id) throws GenericException {
        try {
            int data = documentFacade.documentIdR(id);
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtiene id documento", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of get a document
     *
     * @param idDocument
     * @return
     * @throws GenericException
     */
    @Override
    public DocumentP get(int idDocument) throws GenericException {
        try {
            Document doc = documentFacade.find(idDocument);
            DocumentP data = new DocumentP(doc.getId(), doc.getDescription(), doc.getType(), doc.getState());
            data.setProgram(doc.getFkDocProgram().getName());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Obtener documento id", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to get list of documents
     *
     * @return
     * @throws GenericException
     */
    @Override
    public List<DocumentP> getList() throws GenericException {
        try {
            List<Document> list = documentFacade.findAll();
            List<DocumentP> data = new ArrayList<>();
            for (Document doc : list) {
                if (doc.getState() == 1) {
                    DocumentP aux = new DocumentP(doc.getId(), doc.getDescription(), doc.getType(), doc.getState());
                    aux.setProgram(doc.getFkDocProgram().getName()+"-"+doc.getFkDocProgram().getCampus());
                    data.add(aux);
                }
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista documentos", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to get the document to edit
     *
     * @param id
     * @return
     * @throws GenericException
     */
    @Override
    public DocumentP documentIdEdit(int id) throws GenericException {
        try {
            Document doc = documentFacade.find(id);
            DocumentP data = new DocumentP(doc.getId(), doc.getDescription(), doc.getType(), doc.getState());
            data.setProgram(doc.getFkDocProgram().getId() + "");
            data.setIdUser(doc.getIdUser());
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Documento id editar", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add a document
     *
     * @param document
     * @throws GenericException
     */
    @Override
    public void add(DocumentP document) throws GenericException {
        try {
            List<Document> list = documentFacade.documentsProgram(Integer.parseInt(document.getProgram()));
            for (Document list1 : list) {
                if (list1.getState() == 1) {
                    list1.setState(-1);
                    documentFacade.edit(list1);
                }
            }
            Document data = new Document(document.getDescription(), document.getType(), document.getState());
            Program pro = programFacade.find(Integer.parseInt(document.getProgram()));
            data.setFkDocProgram(pro);
            documentFacade.create(data);
            document.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(document.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Agregar documento", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method of editing a document
     *
     * @param document
     * @throws GenericException
     */
    @Override
    public void edit(DocumentP document) throws GenericException {
        try {
            Document data = documentFacade.find(document.getId());
            data.setDescription(document.getDescription());
            data.setIdUser(document.getIdUser());
            data.setType(document.getType());
            Program pro = programFacade.find(Integer.parseInt(document.getProgram()));
            data.setFkDocProgram(pro);
            documentFacade.edit(data);
            document.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(document.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Editar documento", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to disable a document
     *
     * @param idDocument
     * @param dataR
     * @throws GenericException
     */
    @Override
    public void disable(int idDocument, DatosSolicitudPOJO dataR) throws GenericException {
        try {
            Document data = documentFacade.find(idDocument);
            data.setState(-1);
            documentFacade.edit(data);
            dataR.setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(dataR);
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Inhabilitar documento", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
