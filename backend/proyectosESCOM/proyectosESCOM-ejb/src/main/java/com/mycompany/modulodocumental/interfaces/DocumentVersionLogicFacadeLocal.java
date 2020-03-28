/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.pojo.DocumentVersionP;
import com.mycompany.modulodocumental.utility.GenericException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hashy
 */
@Local
public interface DocumentVersionLogicFacadeLocal {

    List<DocumentVersionP> listCurrentVersions(int idDocument) throws GenericException;
    
    List<DocumentVersionP> listOldVersions(int idProgram) throws GenericException;
    
    void addVersion(DocumentVersionP version) throws GenericException;
    
}
