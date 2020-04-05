/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ThematicCore;
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
public class ThematicCoreFacade extends AbstractFacade<ThematicCore> implements ThematicCoreFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematicCoreFacade() {
        super(ThematicCore.class);
    }

    @Override
    public List<ThematicCore> getList(int general) {
        Query query = em.createQuery("SELECT t FROM ThematicCore t WHERE t.fkTcTrainingArea.fkTaGeneral =?1 ");
        query.setParameter(1, general);
        List<ThematicCore> data = query.getResultList();
        return data;
    }

}
