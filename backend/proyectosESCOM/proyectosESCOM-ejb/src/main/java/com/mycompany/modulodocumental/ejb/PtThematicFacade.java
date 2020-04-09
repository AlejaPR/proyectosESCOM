/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtThematic;
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
public class PtThematicFacade extends AbstractFacade<PtThematic> implements PtThematicFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtThematicFacade() {
        super(PtThematic.class);
    }

    @Override
    public List<PtThematic> getList(int programT) {
        Query query = em.createQuery("SELECT t FROM PtCompetitionG t WHERE t.fkPtcProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtThematic> list = query.getResultList();
        return list;
    }

}
