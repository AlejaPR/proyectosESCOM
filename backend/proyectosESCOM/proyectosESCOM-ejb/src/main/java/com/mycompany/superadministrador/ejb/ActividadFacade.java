package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jeison gaona- alejandra pabon
 */
@Stateless
public class ActividadFacade extends AbstractFacade<Actividad> implements ActividadFacadeLocal {
    
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ActividadFacade() {
        super(Actividad.class);
    }
    
    @Override
    public void create(Actividad documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void edit(Actividad documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void remove(Actividad documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que realiza la consulta de actividades del usuario especifico
     *
     * @param idUsuario
     * @return
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesUsuario(Integer idUsuario) {
        List<Actividad> listaActividades = new ArrayList<>();
        TypedQuery<Actividad> listaAct = em.createNamedQuery("consultaActividadesUsuario", Actividad.class);
        listaAct.setParameter("numeroDocumento", idUsuario);
        listaActividades = listaAct.getResultList();
        List<ActividadPOJO> respuesta = new ArrayList<>();
        for (Actividad act : listaActividades) {
            respuesta.add(new ActividadPOJO(act.getIdActividad(), act.getNombreActividad(),act.getFkActIdmodulo().getIdModulo()));
        }
        return respuesta;
    }

    /**
     * Metodo que realiza la consulta de actividades no asignadas al usuario
     *
     * @param idUsuario
     * @param idModulo
     * @return
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int idUsuario, int idModulo) {
        List<Actividad> listaActividades = new ArrayList<>();
        TypedQuery<Actividad> listaAct = em.createNamedQuery("consultaActividadesNoAsociadasUsuario", Actividad.class);
        listaAct.setParameter("idModulo", idModulo);
        listaAct.setParameter("numeroDocumento", idUsuario);
        listaActividades = listaAct.getResultList();
        List<ActividadPOJO> respuesta = new ArrayList<>();
        for (Actividad act : listaActividades) {
            respuesta.add(new ActividadPOJO(act.getIdActividad(), act.getNombreActividad()));
        }
        return respuesta;
    }

    /**
     * Metodo que realiza la consulta para eliminar actividad de usuario
     * especifico
     *
     * @param idUsuario
     * @param idActividad
     *
     *
     */
    @Override
    public void eliminarActividadUsuario(Integer idUsuario, Integer idActividad) {
        
        em.createNativeQuery("DELETE FROM tbl_usuarioactividad WHERE fk_uac_idactividad = ? AND fk_uac_idusuario = ?")
                .setParameter(1, idActividad)
                .setParameter(2, idUsuario)
                .executeUpdate();
    }

    /**
     * Metodo que realiza la consulta de actividades de un modulo especifico
     *
     * @param modulo
     * @return
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesModulo(Modulo modulo) {
        TypedQuery<Actividad> consultaActividadesModulo = em.createNamedQuery("consultaActividadesModulo", Actividad.class);
        consultaActividadesModulo.setParameter("idModulo", modulo);
        
        List<ActividadPOJO> listaActividadesM = new ArrayList<>();
        for (Actividad a : consultaActividadesModulo.getResultList()) {
            ActividadPOJO actividad = new ActividadPOJO();
            actividad.setIdActividad(a.getIdActividad());
            actividad.setNombre(a.getNombreActividad());
            actividad.setEstado(a.getEstado());
            listaActividadesM.add(actividad);
        }
        return listaActividadesM;
    }

    /**
     * Metodo que realiza el cambio de estado de una actividad Recibe el valor
     * del estado y el id de la actividad
     *
     * @param idActividad
     * @param estado
     *
     *
     */
    @Override
    public void cambiarEstadoActividadModulo(int idActividad, String estado) {
        
        Actividad actividad = em.find(Actividad.class, idActividad);
        actividad.setEstado(estado);
        em.merge(actividad);
    }

    /**
     * Metodo que realiza la consulta de busqueda de actividad por nombre
     *
     * @param nombreActividad
     * @return
     *
     */
    @Override
    public List<Actividad> buscarActividadPorNombre(String nombreActividad) {
        
        List<Actividad> actividadResultado = new ArrayList();
        TypedQuery<Actividad> actividad = em.createNamedQuery("consultaActividadPorNombre", Actividad.class);
        actividad.setParameter("nombreActividad", nombreActividad);
        
        actividadResultado = actividad.getResultList();
        
        return actividadResultado;
    }

    /**
     * Metodo que realiza la consulta para registrar actividad
     *
     * @param actividad
     * @param nombreActividad
     * @param modulo
     * @return 
     *
     *
     */
    @Override
    public ActividadPOJO registrarActividad(ActividadPOJO actividad, String nombreActividad, Modulo modulo) {
        
        em.createNativeQuery("INSERT INTO TBL_ACTIVIDAD (ACT_ESTADO,ACT_DESCRIPCIONACTIVIDAD,ACT_ULTIMAMODIFICACION,"
                + "ACT_NOMBREACTIVIDAD,FK_ACT_IDMODULO) VALUES (?,?,?,?,?)")
                .setParameter(1, "Activo")
                .setParameter(2, actividad.getDescripcionActividad())
                .setParameter(3, new Date())
                .setParameter(4, nombreActividad)
                .setParameter(5, modulo.getIdModulo())
                .executeUpdate();
        
        ActividadPOJO actividadR = new ActividadPOJO(actividad.getIdActividad(), nombreActividad, modulo.getIdModulo());
        return actividadR;
    }

    /**
     * Metodo que realiza la modificacion de una actividad
     *
     * @param actividadEditar
     *
     *
     */
    @Override
    public void editarActividad(ActividadPOJO actividadEditar) {
        
        Actividad actividad = em.find(Actividad.class, actividadEditar.getIdActividad());
        actividad.setDescripcionActividad(actividadEditar.getDescripcionActividad());        
        em.merge(actividad);
    }

    /**
     * Metodo que realiza la modificacion del estado de una actividad
     *
     *
     * @param idActividad
     * @param estado
       *
     */
    @Override
    public void cambiarEstadoActividad(int idActividad, String estado) {
        
        Actividad actividad = em.find(Actividad.class, idActividad);
        actividad.setEstado(estado);
        em.merge(actividad);
    }
}
