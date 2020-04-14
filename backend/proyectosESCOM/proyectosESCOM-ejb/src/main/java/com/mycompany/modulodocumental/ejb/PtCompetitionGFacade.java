package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtCompetitionG;
import com.mycompany.modulodocumental.interfaces.PtCompetitionGFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the entity thematic core program, general competence.
 * Contains all methods for persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class PtCompetitionGFacade extends AbstractFacade<PtCompetitionG> implements PtCompetitionGFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtCompetitionGFacade() {
        super(PtCompetitionG.class);
    }

    /**
     * This method returns the list of relationships of general competence and
     * thematic core of the program.
     *
     * @param programT
     * @return
     */
    @Override
    public List<PtCompetitionG> getList(int programT) {
        Query query = em.createQuery("SELECT c FROM PtCompetitionG c WHERE c.fkPtcProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtCompetitionG> list = query.getResultList();
        return list;
    }

}
