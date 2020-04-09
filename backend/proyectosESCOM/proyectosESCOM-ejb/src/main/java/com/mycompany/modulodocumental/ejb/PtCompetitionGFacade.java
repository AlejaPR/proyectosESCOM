/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtCompetitionG;
import com.mycompany.modulodocumental.interfaces.PtCompetitionGFacadeLocal;
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
public class PtCompetitionGFacade extends AbstractFacade<PtCompetitionG> implements PtCompetitionGFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtCompetitionGFacade() {
        super(PtCompetitionG.class);
    }

    @Override
    public List<PtCompetitionG> getList(int programT) {
        Query query = em.createQuery("SELECT c FROM PtCompetitionG c WHERE c.fkPtcProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtCompetitionG> list = query.getResultList();
        return list;
    }

}
