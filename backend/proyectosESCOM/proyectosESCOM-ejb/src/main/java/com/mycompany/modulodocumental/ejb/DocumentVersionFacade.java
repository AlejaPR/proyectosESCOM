/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.DocumentVersionFacadeLocal;
import com.mycompany.modulodocumental.entity.DocumentVersion;
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
public class DocumentVersionFacade extends AbstractFacade<DocumentVersion> implements DocumentVersionFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentVersionFacade() {
        super(DocumentVersion.class);
    }

    @Override
    public List<DocumentVersion> listVersions(int id) {
        Query query = em.createQuery("SELECT v FROM DocumentVersion v WHERE v.fkDvDocument.id = ?1");
        query.setParameter(1, id);
        List<DocumentVersion> list = query.getResultList();
        return list;
    }
    
}
