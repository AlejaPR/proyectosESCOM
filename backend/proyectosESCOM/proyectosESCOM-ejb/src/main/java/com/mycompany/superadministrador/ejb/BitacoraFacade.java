/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.entity.Bitacora;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aleja
 */
@Stateless
public class BitacoraFacade extends AbstractFacade<Bitacora> implements BitacoraFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }
    
    @Override
    public void create(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
