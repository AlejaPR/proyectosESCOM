/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.ProgramFacadeLocal;
import com.mycompany.modulodocumental.entity.Program;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HASHY
 */
@Stateless
public class ProgramFacade extends AbstractFacade<Program> implements ProgramFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramFacade() {
        super(Program.class);
    }
    
}
