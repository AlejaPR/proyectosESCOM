/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ActivityFacadeLocal;
import com.mycompany.modulodocumental.entity.Activity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HASHY
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

    @Override
    public int Percentage(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.state = 2 ");
        query.setParameter(1, id);
        int cont = query.getResultList().size();
        return cont;
    }

    @Override
    public int totalActivities(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 ");
        query.setParameter(1, id);
        int cont = query.getResultList().size();
        return cont;
    }

    @Override
    public List<Activity> listActivitiesInfo(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.type = 1 ORDER BY a.number ASC, a.state ASC");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        return list;
    }

    @Override
    public List<Activity> listActivitiesAnnex(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.type = 2 ORDER BY a.state ASC ");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        return list;
    }
    
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

    @Override
    public List<Activity> listDaughters(int id, int condition) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.parentActivity = ?1 AND a.type = 1 AND a.fkActCondition.id = ?2 ORDER BY a.number ASC");
        query.setParameter(1, id);
        query.setParameter(2, condition);
        List<Activity> list = query.getResultList();
        return list;
    }

}
