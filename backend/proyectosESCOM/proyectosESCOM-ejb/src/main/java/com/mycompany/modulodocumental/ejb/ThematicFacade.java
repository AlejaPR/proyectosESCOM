package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Thematic;
import com.mycompany.modulodocumental.interfaces.ThematicFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the themetic entity. Contains all methods for persistence
 * and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ThematicFacade extends AbstractFacade<Thematic> implements ThematicFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematicFacade() {
        super(Thematic.class);
    }

    /**
     * This method returns the list of thematic of the general program.
     *
     * @param general
     * @return
     */
    @Override
    public List<Thematic> getList(int general) {
        Query query = em.createQuery("SELECT t FROM Thematic t WHERE t.fkThGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<Thematic> data = query.getResultList();
        return data;
    }

}
