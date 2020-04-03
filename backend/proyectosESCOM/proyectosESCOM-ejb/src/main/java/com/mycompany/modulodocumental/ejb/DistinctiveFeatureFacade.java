/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.DistinctiveFeature;
import com.mycompany.modulodocumental.interfaces.DistinctiveFeatureFacadeLocal;
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
public class DistinctiveFeatureFacade extends AbstractFacade<DistinctiveFeature> implements DistinctiveFeatureFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DistinctiveFeatureFacade() {
        super(DistinctiveFeature.class);
    }

    @Override
    public List<DistinctiveFeature> getList(int general) {
        Query query = em.createQuery("SELECT d FROM DistinctiveFeature d WHERE d.fkDfGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<DistinctiveFeature> data = query.getResultList();
        return data;
    }
    
}
