package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.entity.Condition;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the condition entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ConditionFacade extends AbstractFacade<Condition> implements ConditionFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConditionFacade() {
        super(Condition.class);
    }

    /**
     * This method returns the list of the conditions of a specific process
     *
     * @param idProcess
     * @return
     */
    @Override
    public List<Condition> listConditionPro(int idProcess) {
        Query query = em.createQuery("SELECT c FROM Condition c WHERE c.fkConProcess.id = ?1 AND c.state <> -1 ORDER BY c.id ASC");
        query.setParameter(1, idProcess);
        List<Condition> list = query.getResultList();
        return list;
    }

    /**
     * This method returns the list of the conditions of a specific document
     *
     * @param idDocument
     * @return
     */
    @Override
    public List<Condition> listConditionDoc(int idDocument) {
        Query query = em.createQuery("SELECT c FROM Condition c WHERE c.fkConProcess.fkPrcDocument.id = ?1  AND c.state <> -1 ORDER BY c.id DESC, c.fkConProcess.id ASC");
        query.setParameter(1, idDocument);
        List<Condition> list = query.getResultList();
        return list;
    }

}
