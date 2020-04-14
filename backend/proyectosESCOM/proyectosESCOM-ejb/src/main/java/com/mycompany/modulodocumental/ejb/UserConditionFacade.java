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
 * This is the bean of the entity user condition. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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

    /**
     * This method returns the list of conditions associated with a user
     *
     * @param user
     * @param document
     * @return
     */
    @Override
    public List<Condition> listCondition(int user, int document) {
        List<Condition> list = new ArrayList<>();
        Query queryUser = em.createQuery("SELECT u FROM UserCondition u WHERE u.fkUcUser = ?1 AND u.fkUcCondition.fkConProcess.fkPrcDocument.id = ?2 ORDER BY u.fkUcCondition.id ASC");
        queryUser.setParameter(1, user);
        queryUser.setParameter(2, document);
        List<UserCondition> auxUser = queryUser.getResultList();
        for (UserCondition con : auxUser) {
            list.add(con.getFkUcCondition());
        }
        return list;
    }

    /**
     * This method returns the list of users associated with a condition
     *
     * @param id
     * @return
     */
    @Override
    public List<UserCondition> listUsersCondition(int id) {
        Query query = em.createQuery("SELECT u FROM UserCondition u WHERE u.fkUcCondition.id =?1");
        query.setParameter(1, id);
        List<UserCondition> data = query.getResultList();
        return data;
    }

}
