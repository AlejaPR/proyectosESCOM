/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces.logic;

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
public interface DocumentLogicLocal {

    public int getIdDocument(int id) throws GenericException;

    public DocumentP get(int idDocument) throws GenericException;

    public List<DocumentP> getList() throws GenericException;

    public DocumentP documentIdEdit(int id) throws GenericException;

    public void add(DocumentP document) throws GenericException;

    public void edit(DocumentP document) throws GenericException;

    public void disable(int idDocument, DatosSolicitudPOJO dataR) throws GenericException;

}
