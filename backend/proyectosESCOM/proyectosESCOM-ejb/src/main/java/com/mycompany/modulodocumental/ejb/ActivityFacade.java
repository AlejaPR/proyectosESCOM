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
    public List<Activity> listActivities(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.id = ?1 AND a.state = 1");
        query.setParameter(1, id);
        List<Activity> list = query.getResultList();
        return list;
    }

    @Override
    public String allInformation(int id) {
        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.fkActCondition.fkConProcess.id = ?1 AND a.type = 1");
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

}
