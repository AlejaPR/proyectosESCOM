package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtThematic;
import com.mycompany.modulodocumental.interfaces.PtThematicFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the entity thematic, thematic program. Contains all
 * methods for persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class PtThematicFacade extends AbstractFacade<PtThematic> implements PtThematicFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtThematicFacade() {
        super(PtThematic.class);
    }

    /**
     * This method returns the list of thematic relations and thematic nucleus
     * of the program.
     *
     * @param programT
     * @return
     */
    @Override
    public List<PtThematic> getList(int programT) {
        Query query = em.createQuery("SELECT t FROM PtThematic t WHERE t.fkPttProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtThematic> list = query.getResultList();
        return list;
    }

}
