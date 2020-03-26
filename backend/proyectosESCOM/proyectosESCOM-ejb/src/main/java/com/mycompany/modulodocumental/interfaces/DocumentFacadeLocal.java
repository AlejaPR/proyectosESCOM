/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Document;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HASHY
 */
@Local
public interface DocumentFacadeLocal {

    void create(Document document);

    void edit(Document document);

    void remove(Document document);

    Document find(Object id);

    List<Document> findAll();
    
    List<Document> documentsProgram(int id);

    List<Document> findRange(int[] range);

    int count();
    
    int documentIdR(int id);
    
}
