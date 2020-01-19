/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aleja
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
        listaActividades=listaAct.getResultList();
        List<ActividadPOJO> respuesta= new ArrayList<>();
        for(Actividad act : listaActividades){
            respuesta.add(new ActividadPOJO(act.getIdActividad(), act.getNombreActividad()));
        }
        return respuesta;
    }
    
    @Override
    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int idUsuario,int idModulo) {
        List<Actividad> listaActividades = new ArrayList<>();
        TypedQuery<Actividad> listaAct = em.createNamedQuery("consultaActividadesNoAsociadasUsuario", Actividad.class);
        listaAct.setParameter("idModulo", idModulo);
        listaAct.setParameter("numeroDocumento", idUsuario);
        listaActividades=listaAct.getResultList();
        List<ActividadPOJO> respuesta= new ArrayList<>();
        for(Actividad act : listaActividades){
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
            listaActividadesM.add(actividad);
        }
        return listaActividadesM;
    }

}
