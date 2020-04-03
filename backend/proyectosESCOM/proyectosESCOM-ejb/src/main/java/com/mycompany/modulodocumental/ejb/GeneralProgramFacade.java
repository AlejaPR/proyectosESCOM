/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.GeneralProgramFacadeLocal;
import com.mycompany.modulodocumental.entity.GeneralProgram;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hashy
 */
@Stateless
public class GeneralProgramFacade extends AbstractFacade<GeneralProgram> implements GeneralProgramFacadeLocal {
    @PersistenceContext(unitName = "documentaryUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneralProgramFacade() {
        super(GeneralProgram.class);
    }
    
}
