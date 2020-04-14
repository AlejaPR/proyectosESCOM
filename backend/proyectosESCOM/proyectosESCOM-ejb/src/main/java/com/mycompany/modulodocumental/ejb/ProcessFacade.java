package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ProcessFacadeLocal;
import com.mycompany.modulodocumental.entity.Process;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the process entity. Contains all methods for persistence
 * and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ProcessFacade extends AbstractFacade<Process> implements ProcessFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessFacade() {
        super(Process.class);
    }

    /**
     * This method returns the list of processes of a specific document
     *
     * @param idDocument
     * @return
     */
    @Override
    public List<Process> listProcess(int idDocument) {
        Query query = em.createQuery("SELECT p FROM Process p WHERE p.fkPrcDocument.id = ?1 AND p.state > 0");
        query.setParameter(1, idDocument);
        List<Process> list = query.getResultList();
        return list;
    }

}
