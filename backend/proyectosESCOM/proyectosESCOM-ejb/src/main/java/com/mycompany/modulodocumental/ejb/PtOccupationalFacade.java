package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtOccupational;
import com.mycompany.modulodocumental.interfaces.PtOccupationalFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the entity thematic core program, occupational profile.
 * Contains all methods for persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class PtOccupationalFacade extends AbstractFacade<PtOccupational> implements PtOccupationalFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtOccupationalFacade() {
        super(PtOccupational.class);
    }

    /**
     * This method returns the list of relationships of occupational profiles
     * and thematic nucleus of the program.
     *
     * @param programT
     * @return
     */
    @Override
    public List<PtOccupational> getList(int programT) {
        Query query = em.createQuery("SELECT o FROM PtOccupational o WHERE o.fkPtoProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtOccupational> list = query.getResultList();
        return list;
    }

}
