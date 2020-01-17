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
import com.mycompany.superadministrador.entity.TipoDocumento;
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

        List<ActividadPOJO> listaFinalActividades = new ArrayList();
        listaFinalActividades = em.createNativeQuery("SELECT tbl_actividad.pk_act_idactividad,TBL_ACTIVIDAD.ACT_NOMBREACTIVIDAD FROM TBL_USUARIOACTIVIDAD,TBL_ACTIVIDAD, TBL_USUARIO \n"
                + "WHERE tbl_actividad.pk_act_idactividad = tbl_usuarioactividad.fk_uac_idactividad \n"
                + "AND tbl_usuarioactividad.fk_uac_idusuario= tbl_usuario.pk_usr_idusuario \n"
                + "AND tbl_usuario.pk_usr_idusuario=?")
                .setParameter(1, idUsuario)
                .getResultList();
        return listaFinalActividades;
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
