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

}
