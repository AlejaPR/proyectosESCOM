package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.interfaces.ConfiguracionFacadeLocal;
import com.mycompany.superadministrador.entity.Configuracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jeison gaona-alejandra pabon
 */
@Stateless
public class ConfiguracionFacade extends AbstractFacade<Configuracion> implements ConfiguracionFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConfiguracionFacade() {
        super(Configuracion.class);
    }
    
    @Override
    public void create(Configuracion documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Configuracion documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Configuracion documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Metodo que realiza la consulta para registrar la configuracion
     *
     * @param configuracion
     *
     */
    @Override
    public void registrarConfiguracion(ConfiguracionPOJO configuracion) {

        em.createNativeQuery("INSERT INTO TBL_CONFIGURACION (CONF_BARRASUPERIOR,CONF_BARRALATERAL,CONF_BOTONES,"
                + "CONF_LOGO,CONF_IMAGENLOGIN) VALUES (?,?,?,?,?)")
                .setParameter(1, configuracion.getBarraSuperior())
                .setParameter(2, configuracion.getBarraLateral())
                .setParameter(3, configuracion.getBotones())
                .setParameter(4, configuracion.getLogo())
                .setParameter(5, configuracion.getImagenLogin())
                .executeUpdate();
        
        
        
    }
}
