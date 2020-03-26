/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.entity.AnnexVersion;
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
public class AnnexVersionFacade extends AbstractFacade<AnnexVersion> implements AnnexVersionFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnnexVersionFacade() {
        super(AnnexVersion.class);
    }

    @Override
    public List<AnnexVersion> listAnnexVersion(int id) {
        Query query = em.createQuery("SELECT v FROM AnnexVersion v WHERE v.fkAvAnnex.id = ?1 ORDER BY v.state ASC");
        query.setParameter(1, id);
        List<AnnexVersion> list = query.getResultList();
        return list;
    }
    
}
