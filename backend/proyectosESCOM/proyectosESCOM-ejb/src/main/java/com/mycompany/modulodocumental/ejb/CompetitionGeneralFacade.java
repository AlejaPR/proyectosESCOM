/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.CompetitionGeneral;
import com.mycompany.modulodocumental.interfaces.CompetitionGeneralFacadeLocal;
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
public class CompetitionGeneralFacade extends AbstractFacade<CompetitionGeneral> implements CompetitionGeneralFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetitionGeneralFacade() {
        super(CompetitionGeneral.class);
    }

    @Override
    public List<CompetitionGeneral> getList(int general) {        
        Query query = em.createQuery("SELECT c FROM CompetitionGeneral c WHERE c.fkCgCompetition.fkCtGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<CompetitionGeneral> data = query.getResultList();
        return data;
    }
    
    
}
