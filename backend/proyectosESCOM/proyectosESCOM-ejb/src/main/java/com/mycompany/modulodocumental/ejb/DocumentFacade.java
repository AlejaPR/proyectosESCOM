/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.DocumentFacadeLocal;
import com.mycompany.modulodocumental.entity.Document;
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
public class DocumentFacade extends AbstractFacade<Document> implements DocumentFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentFacade() {
        super(Document.class);
    }

    @Override
    public int documentIdR(int id) {
        Query query = em.createQuery("SELECT d FROM Document d WHERE d.fkDocProgram.id = ?1 AND d.state = 1");
        query.setParameter(1, id);
        List<Document> list = query.getResultList();
        if (list.size()>0) {
            return list.get(0).getId();
        }else{
            return -1;
        }
        
    }

    @Override
    public List<Document> documentsProgram(int id) {
        Query query = em.createQuery("SELECT d FROM Document d WHERE d.fkDocProgram.id = ?1");
        query.setParameter(1, id);
        List<Document> list = query.getResultList();
        return list;
    }
    
}
