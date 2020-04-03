/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtCompetitionG;
import com.mycompany.modulodocumental.interfaces.PtCompetitionGFacadeLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
