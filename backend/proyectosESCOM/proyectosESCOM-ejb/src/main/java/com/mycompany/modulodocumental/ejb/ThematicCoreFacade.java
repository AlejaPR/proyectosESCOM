package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ThematicCore;
import com.mycompany.modulodocumental.interfaces.ThematicCoreFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the thematic core entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ThematicCoreFacade extends AbstractFacade<ThematicCore> implements ThematicCoreFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ThematicCoreFacade() {
        super(ThematicCore.class);
    }

    /**
     * This method returns the list of thematic nuclei of the general program.
     *
     * @param general
     * @return
     */
    @Override
    public List<ThematicCore> getList(int general) {
        Query query = em.createQuery("SELECT t FROM ThematicCore t WHERE t.fkTcTrainingArea.fkTaGeneral.id =?1 ");
        query.setParameter(1, general);
        List<ThematicCore> data = query.getResultList();
        return data;
    }

}
