package com.mycompany.superadministrador.ejb;
import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.interfaces.ConfiguracionFacadeLocal;
import com.mycompany.superadministrador.entity.Configuracion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * Este es el bean de la entidad configuracion
 * Contiene todos los metodos para la persistencia y consultas a la base de datos
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
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
     * @param idConfiguracion
     * @param configuracion
     *
     */
    @Override
    public void registrarConfiguracion(int idConfiguracion, ConfiguracionPOJO configuracion) {

        Configuracion configuracionE = em.find(Configuracion.class, idConfiguracion);
        configuracionE.setBarraLateral(configuracion.getBarraLateral());
        configuracionE.setBarraSuperior(configuracion.getBarraSuperior());
        configuracionE.setBotones(configuracion.getBotones());
        configuracionE.setImagenLogin(configuracion.getImagenLogin());
        configuracionE.setLogo(configuracion.getLogo());
        em.merge(configuracionE);

    }
}
