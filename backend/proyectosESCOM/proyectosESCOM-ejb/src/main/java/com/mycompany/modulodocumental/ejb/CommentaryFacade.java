/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.CommentaryFacadeLocal;
import com.mycompany.modulodocumental.entity.Commentary;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HASHY
 */
@Stateless
public class CommentaryFacade extends AbstractFacade<Commentary> implements CommentaryFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentaryFacade() {
        super(Commentary.class);
    }
    
}
