package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.UsuarioActividadFacadeLocal;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
    @Override
    public void eliminarActividadUsuario(Usuario usuario, Actividad actividad) {
        TypedQuery<UsuarioActividad> listaAct = em.createNamedQuery("eliminarActividad", UsuarioActividad.class);
        listaAct.setParameter("numeroDocumento", usuario.getIdUsuario());
        listaAct.setParameter("codigoActividad", actividad.getIdActividad());
        listaAct.executeUpdate();
    }
}
