package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.DocumentFacadeLocal;
import com.mycompany.modulodocumental.entity.Document;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the document entity. Contains all methods for persistence
 * and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class DocumentFacade extends AbstractFacade<Document> implements DocumentFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentFacade() {
        super(Document.class);
    }

    /**
     * This method returns the id of the document that is activated from a
     * specific program
     *
     * @param id
     * @return
     */
    @Override
    public int documentIdR(int id) {
        Query query = em.createQuery("SELECT d FROM Document d WHERE d.fkDocProgram.id = ?1 AND d.state = 1");
        query.setParameter(1, id);
        List<Document> list = query.getResultList();
        if (list.size() > 0) {
            return list.get(0).getId();
        } else {
            return -1;
        }

    }

    /**
     * This method returns the list of documents of a specific program
     *
     * @param id
     * @return
     */
    @Override
    public List<Document> documentsProgram(int id) {
        Query query = em.createQuery("SELECT d FROM Document d WHERE d.fkDocProgram.id = ?1");
        query.setParameter(1, id);
        List<Document> list = query.getResultList();
        return list;
    }

}
