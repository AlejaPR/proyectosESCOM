package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.AnnexFacadeLocal;
import com.mycompany.modulodocumental.entity.Annex;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the annex entity bean. Contains all methods for persistence and
 * queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class AnnexFacade extends AbstractFacade<Annex> implements AnnexFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnnexFacade() {
        super(Annex.class);
    }

    /**
     * This method returns the list of annexes specifying the program or the
     * name
     *
     * @param idProgram
     * @param name
     * @return
     */
    @Override
    public List<Annex> searchAnnex(int idProgram, String name) {
        List<Annex> list = new ArrayList<>();
        List<Annex> result = new ArrayList<>();
        if (idProgram != 0 && name != null) {
            Query query = em.createQuery("SELECT a FROM Annex a WHERE a.fkAxProgram.id = ?1 AND a.state > 0");
            query.setParameter(1, idProgram);
            result = query.getResultList();
            for (Annex res : result) {
                if (res.getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(res);
                }
            }

        } else if (idProgram == 0 && name != null) {
            Query query = em.createQuery("SELECT a FROM Annex a WHERE a.state > 0");
            result = query.getResultList();
            for (Annex res : result) {
                if (res.getName().toLowerCase().contains(name.toLowerCase())) {
                    list.add(res);
                }
            }
        } else if (idProgram != 0 && name == null) {
            Query query = em.createQuery("SELECT a FROM Annex a WHERE a.fkAxProgram.id = ?1 AND a.state > 0");
            query.setParameter(1, idProgram);
            list = query.getResultList();
        } else {
            Query query = em.createQuery("SELECT a FROM Annex a WHERE a.state > 0");
            list = query.getResultList();
        }
        return list;
    }

}
