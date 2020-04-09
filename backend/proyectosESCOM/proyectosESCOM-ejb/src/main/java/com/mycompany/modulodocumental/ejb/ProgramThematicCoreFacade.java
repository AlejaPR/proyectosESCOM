/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ProgramThematicCore;
import com.mycompany.modulodocumental.interfaces.ProgramThematicCoreFacadeLocal;
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
public class ProgramThematicCoreFacade extends AbstractFacade<ProgramThematicCore> implements ProgramThematicCoreFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramThematicCoreFacade() {
        super(ProgramThematicCore.class);
    }

    @Override
    public List<ProgramThematicCore> getList(int program) {
        Query query = em.createQuery("SELECT p FROM ProgramThematicCore p WHERE p.fkPtProgram.id = ?1");
        query.setParameter(1, program);
        List<ProgramThematicCore> list = query.getResultList();
        return list;
    }

}
