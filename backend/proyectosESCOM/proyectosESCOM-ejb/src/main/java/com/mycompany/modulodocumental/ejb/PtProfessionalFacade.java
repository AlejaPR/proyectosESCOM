package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.PtProfessional;
import com.mycompany.modulodocumental.interfaces.PtProfessionalFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the bean of the entity thematic core program, professional profile.
 * Contains all methods for persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class PtProfessionalFacade extends AbstractFacade<PtProfessional> implements PtProfessionalFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtProfessionalFacade() {
        super(PtProfessional.class);
    }

    /**
     * This method returns the list of professional profile relationships and
     * thematic core of the program.
     *
     * @param programT
     * @return
     */
    @Override
    public List<PtProfessional> getList(int programT) {
        Query query = em.createQuery("SELECT p FROM PtProfessional p WHERE p.fkPtpProgramThematic.id = ?1");
        query.setParameter(1, programT);
        List<PtProfessional> list = query.getResultList();
        return list;
    }

}
