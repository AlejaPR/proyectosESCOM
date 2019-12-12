/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.interfaces.TipoDocumentoFacadeLocal;
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
public class TipoDocumentoFacade extends AbstractFacade<TipoDocumento> implements TipoDocumentoFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoFacade() {
        super(TipoDocumento.class);
    }
    
    @Override
    public void create(TipoDocumento documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(TipoDocumento documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(TipoDocumento documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**Metodo que realiza la consulta a la tabla tipo de documento 
       Devuelve una lista con los tipos de documento
     * @return 
       **/
    @Override
    public List<TipoDocumentoPOJO> consultaTipoDocumento() {
        TypedQuery<TipoDocumento> consultaTipoDocumento = em.createNamedQuery("consultaDocumentos", TipoDocumento.class);
        List<TipoDocumentoPOJO> tipoDocumentoLista = new ArrayList<>();
        for (TipoDocumento td : consultaTipoDocumento.getResultList()) {
            TipoDocumentoPOJO tipoD = new TipoDocumentoPOJO();
            tipoD.setIdTipoDocumento(td.getIdTipodocumento());
            tipoD.setTipoDocumento(td.getTipoDocumento());
            tipoDocumentoLista.add(tipoD);
        }
        return tipoDocumentoLista;
    }

    

}
