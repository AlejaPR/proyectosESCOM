/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ProcessFacadeLocal;
import com.mycompany.modulodocumental.entity.Process;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HASHY
 */
@Stateless
public class ProcessFacade extends AbstractFacade<Process> implements ProcessFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcessFacade() {
        super(Process.class);
    }

    @Override
    public List<Process> listProcess(int idDocument) {
        Query query = em.createQuery("SELECT p FROM Process p WHERE p.fkPrcDocument.id = ?1 AND p.state > 0");
        query.setParameter(1, idDocument);
        List<Process> list = query.getResultList();
        return list;
    }
    
}
