package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtDistinctive;
import com.mycompany.modulodocumental.interfaces.PtDistinctiveFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the entity thematic core program, distinctive feature.
 * Contains all methods for persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class PtDistinctiveFacade extends AbstractFacade<PtDistinctive> implements PtDistinctiveFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtDistinctiveFacade() {
        super(PtDistinctive.class);
    }

    /**
     * This method returns the list of distinctive feature relationships and
     * thematic core of the program.
     *
     * @param programT
     * @return
     */
    @Override
    public List<PtDistinctive> getList(int programT) {
        Query query = em.createQuery("SELECT d FROM PtDistinctive d WHERE d.fkPtdProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtDistinctive> list = query.getResultList();
        return list;
    }

}
