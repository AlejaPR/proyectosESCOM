/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.entity.Condition;
import com.mycompany.modulodocumental.interfaces.UserConditionFacadeLocal;
import com.mycompany.modulodocumental.entity.UserCondition;
import java.util.ArrayList;
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
public class UserConditionFacade extends AbstractFacade<UserCondition> implements UserConditionFacadeLocal {

    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserConditionFacade() {
        super(UserCondition.class);
    }

    @Override
    public List<Condition> listCondition(int user, int document) {
        List<Condition> list = new ArrayList<>();
        Query queryUser = em.createQuery("SELECT u FROM UserCondition u WHERE u.fkUcUser = ?1 AND u.fkUcCondition.fkConProcess.fkPrcDocument.id = ?2");
        queryUser.setParameter(1, user);
        queryUser.setParameter(2, document);
        List<UserCondition> auxUser = queryUser.getResultList();
        for (UserCondition con : auxUser) {           
            list.add(con.getFkUcCondition());
        }
        return list;
    }

}
