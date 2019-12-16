/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    /**Metodo que la consulta de actividades del usuario especifico
     * 
     * @param idUsuario
     * @return 
       **/
    @Override
    public List<ActividadPOJO> listarActividadesUsuario(Integer idUsuario) {
        
        List<ActividadPOJO> listaFinalActividades= new ArrayList();
        listaFinalActividades = em.createNativeQuery("SELECT TBL_ACTIVIDAD.ACT_NOMBREACTIVIDAD FROM TBL_USUARIOACTIVIDAD,TBL_ACTIVIDAD, TBL_USUARIO \n" +
                                "WHERE tbl_actividad.pk_act_idactividad = tbl_usuarioactividad.fk_uac_idactividad \n" +
                                "AND tbl_usuarioactividad.fk_uac_idusuario= tbl_usuario.pk_usr_idusuario \n" +
                                "AND tbl_usuario.pk_usr_idusuario=?")
                                .setParameter(1, idUsuario)
                                .getResultList();
        return listaFinalActividades;
    }

    
}
