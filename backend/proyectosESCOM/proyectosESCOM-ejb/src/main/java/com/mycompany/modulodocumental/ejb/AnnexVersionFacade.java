package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.AnnexVersionFacadeLocal;
import com.mycompany.modulodocumental.entity.AnnexVersion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the entity bean version annex. Contains all methods for persistence
 * and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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

    /**
     * This method returns the list of annex versions, from a specific annex
     *
     * @param id
     * @return
     */
    @Override
    public List<AnnexVersion> listAnnexVersion(int id) {
        Query query = em.createQuery("SELECT v FROM AnnexVersion v WHERE v.fkAvAnnex.id = ?1 ORDER BY v.state DESC");
        query.setParameter(1, id);
        List<AnnexVersion> list = query.getResultList();
        return list;
    }

}
