/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
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
public class ModuloFacade extends AbstractFacade<Modulo> implements ModuloFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuloFacade() {
        super(Modulo.class);
    }

    /**Metodo que realiza la consulta a la tabla modulo
       Devuelve una lista con los modulos registrados
     * @return 
       **/
    @Override
    public List<ModuloPOJO> listarModulos() {
        
        TypedQuery<Modulo> consultaModulosRegistrados = em.createNamedQuery("consultaModulos", Modulo.class);
        List<ModuloPOJO> listaModulos = new ArrayList<>();
        for (Modulo m : consultaModulosRegistrados.getResultList()) {
            ModuloPOJO modulo = new ModuloPOJO();
            modulo.setIdModulo(m.getIdModulo());
            modulo.setEstadoModulo(m.getEstado());
            modulo.setImagenModulo(m.getImagen());
            modulo.setNombreModulo(m.getNombreModulo());
            modulo.setDescripcionModulo(m.getDescripcionModulo());
            modulo.setAcronimo(m.getAcronimo());
            listaModulos.add(modulo);
        }
        return listaModulos;
    }
    
    /**Metodo que realiza la consulta a la tabla modulo
       Devuelve los datos de un modulo registrado con el id enviado
     * @param idModulo
     * @return 
       **/
    @Override
    public ModuloPOJO buscarModuloEspecifico(int idModulo) {
        TypedQuery<Modulo> consultaModuloEsp = em.createNamedQuery("consultaModuloEsp", Modulo.class);
        consultaModuloEsp.setParameter("idModulo", idModulo);
        Modulo moduloEspDB = consultaModuloEsp.getSingleResult();
        ModuloPOJO moduloRespuesta = new ModuloPOJO();
        moduloRespuesta.setIdModulo(moduloEspDB.getIdModulo());
        moduloRespuesta.setImagenModulo(moduloEspDB.getImagen());
        moduloRespuesta.setNombreModulo(moduloEspDB.getNombreModulo());
        moduloRespuesta.setDescripcionModulo(moduloEspDB.getDescripcionModulo());
        moduloRespuesta.setAcronimo(moduloEspDB.getAcronimo());
        moduloRespuesta.setEstadoModulo(moduloEspDB.getEstado());
        
        return moduloRespuesta;
    }
    
     /**Metodo que realiza la modificacion de un modulo
       Recibe id para filtrar la busqueda
     * @param idModulo
     * @param moduloEditar
     * 
       **/
    @Override
    public void editarModulo(int idModulo, ModuloPOJO moduloEditar) {
        em.createNativeQuery("UPDATE TBL_MODULO SET MOD_ESTADO=?, MOD_IMAGEN=?, MOD_NOMBREMODULO=?, MOD_DESCRIPCIONMODULO=?,MOD_ACRONIMO=? WHERE PK_MOD_IDMODULO=?")
                .setParameter(1, moduloEditar.getEstadoModulo())
                .setParameter(2, moduloEditar.getImagenModulo())
                .setParameter(3, moduloEditar.getNombreModulo())
                .setParameter(4, moduloEditar.getDescripcionModulo())
                .setParameter(5, moduloEditar.getAcronimo())
                .setParameter(6, idModulo)
                .executeUpdate();
      
    }

}
