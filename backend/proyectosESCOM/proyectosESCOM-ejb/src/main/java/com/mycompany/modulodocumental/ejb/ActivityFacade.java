package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.entity.Activity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * This is the activity entity bean. Contains all methods for persistence and
 * queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@Stateless
public class ActivityFacade extends AbstractFacade<Activity> implements ActivityFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActivityFacade() {
        super(Activity.class);
    }

    /**
     * This method returns the number of activities completed
     *
     * @param id
     * @return
     */
    @Override
    public int Percentage(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.state = 2 ");
        query.setParameter(1, id);
        int cont = query.getResultList().size();
        return cont;
    }

    /**
     * This method returns the number of activities of a condition
     *
     * @param id
     * @return
     */
    @Override
    public int totalActivities(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 ");
        query.setParameter(1, id);
        int cont = query.getResultList().size();
        return cont;
    }

    /**
     * This method returns the list of informative activities
     *
     * @param id
     * @return
     */
    @Override
    public List<Activity> listActivitiesInfo(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.type = 1 ORDER BY a.number ASC, a.state ASC");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        return list;
    }

    /**
     * This method returns the list of activities of type annex
     *
     * @param id
     * @return
     */
    @Override
    public List<Activity> listActivitiesAnnex(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.type = 2 ORDER BY a.state ASC ");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        return list;
    }

    /**
     * This method returns all the information registered in the activities
     *
     * @param id
     * @return
     */
    @Override
    public String allInformation(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.fkConProcess.id = ?1 AND a.type = 1 ORDER BY a.number ASC");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        String rest = "";
        for (Activity ele : list) {
            if (ele.getInformation() != null) {
                rest = rest + ele.getInformation() + "<br/>";
            }
        }
        return rest;
    }

    /**
     * This method returns the list of activities parent of an activity
     *
     * @param id
     * @param condition
     * @return
     */
    @Override
    public List<Activity> listDaughters(int id, int condition) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.parentActivity = ?1 AND a.type = 1 AND a.fkActCondition.id = ?2 ORDER BY a.number ASC");
        query.setParameter(1, id);
        query.setParameter(2, condition);
        List<Activity> list = query.getResultList();
        return list;
    }

}
