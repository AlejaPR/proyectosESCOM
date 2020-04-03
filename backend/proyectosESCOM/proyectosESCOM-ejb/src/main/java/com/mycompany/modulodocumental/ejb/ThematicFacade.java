/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Thematic;
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
public class ThematicFacade extends AbstractFacade<Thematic> implements ThematicFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematicFacade() {
        super(Thematic.class);
    }

    @Override
    public List<Thematic> getList(int general) {
        Query query = em.createQuery("SELECT t FROM Thematic t WHERE t.fkThGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<Thematic> data = query.getResultList();
        return data;
    }
    
}
