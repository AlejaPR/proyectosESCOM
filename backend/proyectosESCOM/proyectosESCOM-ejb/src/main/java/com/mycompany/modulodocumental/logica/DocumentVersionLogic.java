package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.Document;
import com.mycompany.modulodocumental.entity.DocumentVersion;
import com.mycompany.modulodocumental.interfaces.DocumentFacadeLocal;
import com.mycompany.modulodocumental.interfaces.DocumentVersionFacadeLocal;
import com.mycompany.modulodocumental.interfaces.logic.DocumentVersionLogicLocal;
import com.mycompany.modulodocumental.pojo.DocumentVersionP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This is the class in charge of the document version logic
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class DocumentVersionLogic implements DocumentVersionLogicLocal {

    /**
     * Document version interface injection
     */
    @EJB
    private DocumentVersionFacadeLocal documentVersionFacade;

    /**
     * Document version interface injection
     */
    @EJB
    private DocumentFacadeLocal documentFacade;

    /**
     * Bitacora interface injection
     */
    @EJB
    private UtilitarioFacadeLocal bitacora;

    /**
     * Variable for logging
     */
    private static final String TABLE = "TBL_DOCUMENT_VERSION";

    /**
     * Variable for the logger record
     */
    private static final String CLASS = "Clase logica version documento";

    /**
     * method for current document version list
     *
     * @param idDocument
     * @return
     * @throws GenericException
     */
    @Override
    public List<DocumentVersionP> getListCurrent(int idDocument) throws GenericException {
        try {
            List<DocumentVersion> list = documentVersionFacade.listCurrentVersions(idDocument);
            List<DocumentVersionP> data = new ArrayList<>();
            for (DocumentVersion aux : list) {
                DocumentVersionP ver = new DocumentVersionP(aux.getId(), aux.getDescription(), aux.getVersion(), aux.getLocation(), aux.getState(), aux.getDate());
                data.add(ver);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista versiones actuales", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method for listing old document versions
     *
     * @param idProgram
     * @return
     * @throws GenericException
     */
    @Override
    public List<DocumentVersionP> getListOld(int idProgram) throws GenericException {
        try {
            List<DocumentVersion> list = documentVersionFacade.listOldVersions(idProgram);
            List<DocumentVersionP> data = new ArrayList<>();
            for (DocumentVersion aux : list) {
                DocumentVersionP ver = new DocumentVersionP(aux.getId(), aux.getDescription(), aux.getVersion(), aux.getLocation(), aux.getState(), aux.getDate());
                data.add(ver);
            }
            return data;
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista versiones anteriores", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

    /**
     * method to add a document version
     *
     * @param version
     * @throws GenericException
     */
    @Override
    public void add(DocumentVersionP version) throws GenericException {
        try {
            List<DocumentVersion> list = documentVersionFacade.listCurrentVersions(version.getDocument());
            int value = 0;
            if (version.getState() != 2) {
                if (list.size() > 0) {
                    value = list.get(0).getVersion() + 1;
                } else {
                    value = 1;
                }
                if (list.size() > 0) {
                    DocumentVersion fin = list.get(0);
                    fin.setState(-1);
                    documentVersionFacade.edit(fin);
                }
            } else {
                value = 1;
            }
            DocumentVersion data = new DocumentVersion(version.getDescription(), value, version.getLocation(), version.getState(), version.getDate());
            Document doc = documentFacade.find(version.getDocument());
            data.setFkDvDocument(doc);
            documentVersionFacade.create(data);
            version.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(version.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista versiones anteriores", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
