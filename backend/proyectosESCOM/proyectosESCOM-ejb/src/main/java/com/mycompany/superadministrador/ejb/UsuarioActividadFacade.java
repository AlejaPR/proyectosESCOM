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
 * Este es el bean de la entidad usuario-actividad
 * Contiene todos los metodos para la persistencia y consultas a la base de datos
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
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
    
    /**Metodo que elimina actividades asociadas a los usuarios
     * @param usuario
     * @param actividad
     **/
    @Override
    public void eliminarActividadUsuario(Usuario usuario, Actividad actividad) {
        TypedQuery<UsuarioActividad> listaAct = em.createNamedQuery("eliminarActividad", UsuarioActividad.class);
        listaAct.setParameter("numeroDocumento", usuario.getIdUsuario());
        listaAct.setParameter("codigoActividad", actividad.getIdActividad());
        listaAct.executeUpdate();
    }
}
