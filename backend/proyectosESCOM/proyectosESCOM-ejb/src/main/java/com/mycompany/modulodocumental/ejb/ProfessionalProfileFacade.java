package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.ProfessionalProfile;
import com.mycompany.modulodocumental.interfaces.ProfessionalProfileFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the professional profile entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ProfessionalProfileFacade extends AbstractFacade<ProfessionalProfile> implements ProfessionalProfileFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfessionalProfileFacade() {
        super(ProfessionalProfile.class);
    }

    /**
     * This method returns the list of professional profiles of a general
     * program
     *
     * @param general
     * @return
     */
    @Override
    public List<ProfessionalProfile> getList(int general) {
        Query query = em.createQuery("SELECT p FROM ProfessionalProfile p WHERE p.fkPpGeneral.id = ?1 ");
        query.setParameter(1, general);
        List<ProfessionalProfile> data = query.getResultList();
        return data;
    }

}
