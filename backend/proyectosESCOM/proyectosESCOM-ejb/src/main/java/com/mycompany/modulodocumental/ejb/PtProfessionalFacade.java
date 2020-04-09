/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtProfessional;
import com.mycompany.modulodocumental.interfaces.PtProfessionalFacadeLocal;
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
public class PtProfessionalFacade extends AbstractFacade<PtProfessional> implements PtProfessionalFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtProfessionalFacade() {
        super(PtProfessional.class);
    }

    @Override
    public List<PtProfessional> getList(int programT) {
        Query query = em.createQuery("SELECT p FROM PtProfessional p WHERE p.fkPtpProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtProfessional> list = query.getResultList();
        return list;
    }

}
