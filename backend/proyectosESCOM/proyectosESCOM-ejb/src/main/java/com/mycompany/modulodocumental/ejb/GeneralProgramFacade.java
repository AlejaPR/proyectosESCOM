package com.mycompany.modulodocumental.ejb;

import com.mycompany.modulodocumental.interfaces.GeneralProgramFacadeLocal;
import com.mycompany.modulodocumental.entity.GeneralProgram;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the bean of the general program entity. Contains all methods for
 * persistence and queries to the database
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
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
