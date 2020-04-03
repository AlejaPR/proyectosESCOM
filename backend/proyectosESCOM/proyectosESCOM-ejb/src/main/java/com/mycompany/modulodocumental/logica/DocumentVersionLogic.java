/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.logica;

import com.mycompany.modulodocumental.entity.DocumentVersion;
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
 *
 * @author hashy
 */
@Stateless
public class DocumentVersionLogic implements DocumentVersionLogicLocal {

    @EJB
    private DocumentVersionFacadeLocal documentVersionFacade;
    @EJB
    private UtilitarioFacadeLocal bitacora;

    private static final String TABLE = "TBL_DOCUMENT_VERSION";

    private static final String CLASS = "Clase logica version documento";

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

    @Override
    public void add(DocumentVersionP version) throws GenericException {
        try {
            List<DocumentVersion> list = documentVersionFacade.listCurrentVersions(version.getDocument());
            int value = 0;
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
            DocumentVersion data = new DocumentVersion(version.getDescription(),value , version.getLocation(), version.getState(), version.getDate());        
            documentVersionFacade.create(data);
            version.getRequestData().setTablaInvolucrada(TABLE);
            bitacora.registrarEnBitacora(version.getRequestData());
        } catch (Exception ex) {
            bitacora.registroLogger(CLASS, "Lista versiones anteriores", Level.SEVERE, ex.getMessage());
            throw new GenericException("error server");
        }
    }

}
