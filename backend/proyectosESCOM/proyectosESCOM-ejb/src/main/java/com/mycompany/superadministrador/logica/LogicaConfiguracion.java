package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.entity.Configuracion;
import com.mycompany.superadministrador.interfaces.ConfiguracionFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaConfiguracionFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Esta es la clase encargada de la logica de configuracion
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class LogicaConfiguracion implements LogicaConfiguracionFacadeLocal {

    /**
     * Inyeccion de la interfaz de configuracion*
     */
    @EJB
    ConfiguracionFacadeLocal configuracionDB;

    /**
     * Inyeccion de la interfaz de bitacora*
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable para registro en bitacora*
     */
    private static final String TABLA = "TBL_CONFIGURACION";

    /**
     * Variable para el registro de logger*
     */
    private static final String CLASE = "Clase Logica Configuracion";

    /**
     * Metodo que llama a la consulta para registrar la configuracion
     *
     *
     * @param configuracion
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void registrarConfiguracion(ConfiguracionPOJO configuracion) throws ExcepcionGenerica {
        try {
            List<ConfiguracionPOJO> listaConfiguracion = new ArrayList();
            listaConfiguracion = listarConfiguracionCompleta();
            for (int i = 0; i < listaConfiguracion.size(); i++) {
                configuracionDB.registrarConfiguracion(listaConfiguracion.get(i).getIdConfiguracion(), configuracion);
            }
            configuracion.getDatosSolicitud().setTablaInvolucrada(TABLA);
            bitacora.registrarEnBitacora(configuracion.getDatosSolicitud());
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Registrar configuracion", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de registrar la configuracion");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Registrar configuracion", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de entorno
     *
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ConfiguracionPOJO> listarEntorno() throws ExcepcionGenerica {
        try {
            List<Configuracion> entornoResultado = configuracionDB.findAll();
            if (!entornoResultado.isEmpty()) {
                List<ConfiguracionPOJO> listaEntorno = new ArrayList<>();
                for (Configuracion c : entornoResultado) {
                    ConfiguracionPOJO configuracion = new ConfiguracionPOJO();
                    configuracion.setBarraSuperior(c.getBarraSuperior());
                    configuracion.setBarraLateral(c.getBarraLateral());
                    configuracion.setBotones(c.getBotones());
                    configuracion.setLogo(c.getLogo());
                    listaEntorno.add(configuracion);
                }
                return listaEntorno;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Listar entorno", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Listar entorno", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de inicio
     *
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ConfiguracionPOJO> listarInicio() throws ExcepcionGenerica {
        try {
            List<Configuracion> inicioResultado = configuracionDB.findAll();
            if (!inicioResultado.isEmpty()) {
                List<ConfiguracionPOJO> listaInicio = new ArrayList<>();
                for (Configuracion c : inicioResultado) {
                    ConfiguracionPOJO configuracion = new ConfiguracionPOJO();
                    configuracion.setImagenLogin(c.getImagenLogin());
                    configuracion.setBotones(c.getBotones());
                    listaInicio.add(configuracion);
                }
                return listaInicio;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Listar inicio", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Listar inicio", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo para obtener la configuracion completa de aspecto de la pagina web
     *
     * @return
     * @throws ExcepcionGenerica
     */
    @Override
    public List<ConfiguracionPOJO> listarConfiguracionCompleta() throws ExcepcionGenerica {
        try {
            List<Configuracion> entornoResultado = configuracionDB.findAll();
            if (!entornoResultado.isEmpty()) {
                List<ConfiguracionPOJO> listaEntorno = new ArrayList<>();
                for (Configuracion c : entornoResultado) {
                    ConfiguracionPOJO configuracion = new ConfiguracionPOJO();
                    configuracion.setIdConfiguracion(c.getIdConfiguracion());
                    configuracion.setBarraSuperior(c.getBarraSuperior());
                    configuracion.setBarraLateral(c.getBarraLateral());
                    configuracion.setBotones(c.getBotones());
                    configuracion.setLogo(c.getLogo());
                    configuracion.setImagenLogin(c.getImagenLogin());
                    listaEntorno.add(configuracion);
                }
                return listaEntorno;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Listar configuracion completa", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Listar configuracion completa", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

}
