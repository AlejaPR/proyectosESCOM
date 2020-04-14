package com.mycompany.modulodocumental.interfaces;

import com.mycompany.modulodocumental.entity.Document;
import java.util.List;
import javax.ejb.Local;

/**
 * This is the interface for the document class. Contains all the
 * methods required for the entity
 *
 * @author Cristain Estevez - Anggy - University of Cundinamarca
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
