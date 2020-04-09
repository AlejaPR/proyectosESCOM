/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtOccupational;
import com.mycompany.modulodocumental.interfaces.PtOccupationalFacadeLocal;
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
public class PtOccupationalFacade extends AbstractFacade<PtOccupational> implements PtOccupationalFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtOccupationalFacade() {
        super(PtOccupational.class);
    }

    @Override
    public List<PtOccupational> getList(int programT) {
        Query query = em.createQuery("SELECT o FROM PtOccupational o WHERE o.fkPtoProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtOccupational> list = query.getResultList();
        return list;
    }
    
}
