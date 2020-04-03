/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ConditionFacadeLocal;
import com.mycompany.modulodocumental.entity.Condition;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HASHY
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

    @Override
    public List<Condition> listConditionPro(int idProcess) {
        Query query = em.createQuery("SELECT c FROM Condition c WHERE c.fkConProcess.id = ?1 AND c.state <> -1 ORDER BY c.id ASC");
        query.setParameter(1, idProcess);
        List<Condition> list = query.getResultList();
        return list;
    }

    @Override
    public List<Condition> listConditionDoc(int idD) {
        Query query = em.createQuery("SELECT c FROM Condition c WHERE c.fkConProcess.fkPrcDocument.id = ?1  AND c.state <> -1 ORDER BY c.id DESC, c.fkConProcess.id ASC");
        query.setParameter(1, idD);
        List<Condition> list = query.getResultList();
        return list;
    }

}
