package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.OccupationalProfile;
import com.mycompany.modulodocumental.interfaces.OccupationalProfileFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the occupational profile entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class OccupationalProfileFacade extends AbstractFacade<OccupationalProfile> implements OccupationalProfileFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OccupationalProfileFacade() {
        super(OccupationalProfile.class);
    }

    /**
     * This method returns the list of occupational profiles of a general
     * program
     *
     * @param general
     * @return
     */
    @Override
    public List<OccupationalProfile> getList(int general) {
        Query query = em.createQuery("SELECT o FROM OccupationalProfile o WHERE o.fkOpGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<OccupationalProfile> data = query.getResultList();
        return data;
    }

}
