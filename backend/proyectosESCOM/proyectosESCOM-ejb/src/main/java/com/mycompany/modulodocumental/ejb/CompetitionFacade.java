package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Competition;
import com.mycompany.modulodocumental.interfaces.CompetitionFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the competition entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class CompetitionFacade extends AbstractFacade<Competition> implements CompetitionFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompetitionFacade() {
        super(Competition.class);
    }

    /**
     * This method returns the list of competencies of a general program
     *
     * @param general
     * @return
     */
    @Override
    public List<Competition> getList(int general) {
        Query query = em.createQuery("SELECT c FROM Competition c WHERE c.fkCtGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<Competition> data = query.getResultList();
        return data;
    }

}
