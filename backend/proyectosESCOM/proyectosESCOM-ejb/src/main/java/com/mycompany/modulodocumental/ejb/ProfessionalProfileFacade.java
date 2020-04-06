/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import com.mycompany.modulodocumental.interfaces.ProfessionalProfileFacadeLocal;
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
public class ProfessionalProfileFacade extends AbstractFacade<ProfessionalProfile> implements ProfessionalProfileFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessionalProfileFacade() {
        super(ProfessionalProfile.class);
    }

    @Override
    public List<ProfessionalProfile> getList(int general) {
        Query query = em.createQuery("SELECT p FROM ProfessionalProfile p WHERE p.fkPpGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<ProfessionalProfile> data = query.getResultList();
        return data;
    }
    
}
