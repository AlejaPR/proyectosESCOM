/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.DocumentP;
import com.mycompany.modulodocumental.utility.GenericException;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface DocumentLogicFacadeLocal {

    public int getIdDocument(int id) throws GenericException;

    public DocumentP getDocumentId(int id) throws GenericException;

    public List<DocumentP> listDocument() throws GenericException;

    public DocumentP documentIdEdit(int id) throws GenericException;

    public void addDocument(DocumentP document) throws GenericException;

    public void editDocument(DocumentP document) throws GenericException;

    public void disableDocument(int id, DatosSolicitudPOJO dataR) throws GenericException;

}
