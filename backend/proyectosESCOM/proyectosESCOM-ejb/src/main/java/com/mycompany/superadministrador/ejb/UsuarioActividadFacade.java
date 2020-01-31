package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.interfaces.UsuarioActividadFacadeLocal;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeison gaona - alejandra pabon
 */
@Stateless
public class UsuarioActividadFacade extends AbstractFacade<UsuarioActividad> implements UsuarioActividadFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioActividadFacade() {
        super(UsuarioActividad.class);
    }   
}
