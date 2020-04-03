/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.TrainingArea;
import com.mycompany.modulodocumental.interfaces.TrainingAreaFacadeLocal;
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
public class TrainingAreaFacade extends AbstractFacade<TrainingArea> implements TrainingAreaFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrainingAreaFacade() {
        super(TrainingArea.class);
    }

    @Override
    public List<TrainingArea> getList(int general) {
        Query query = em.createQuery("SELECT t FROM TrainingArea t WHERE t.fkTaGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<TrainingArea> data = query.getResultList();
        return data;
    }
    
}
