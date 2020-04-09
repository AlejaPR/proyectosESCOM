/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtDistinctive;
import com.mycompany.modulodocumental.interfaces.PtDistinctiveFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hashy
 */
@Stateless
public class PtDistinctiveFacade extends AbstractFacade<PtDistinctive> implements PtDistinctiveFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtDistinctiveFacade() {
        super(PtDistinctive.class);
    }

    @Override
    public List<PtDistinctive> getList(int programT) {
        Query query = em.createQuery("SELECT d FROM PtDistinctive d WHERE d.fkPtdProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtDistinctive> list = query.getResultList();
        return list;
    }
    
}
