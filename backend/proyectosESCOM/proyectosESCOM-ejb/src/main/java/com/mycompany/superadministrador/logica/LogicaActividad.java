package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Esta es la clase encargada de la logica de usuario
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class LogicaActividad implements LogicaActividadFacadeLocal {

    /**
     * Inyeccion de la interfaz de actividad*
     */
    @EJB
    ActividadFacadeLocal actividadDB;

    /**
     * Inyeccion de la interfaz de modulo*
     */
    @EJB
    ModuloFacadeLocal moduloDB;

    /**
     * Inyeccion de la interfaz de utilitario*
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable para registro en bitacora*
     */
    private static final String TABLA = "TBL_ACTIVIDAD";

    /**
     * Variable para el registro de logger*
     */
    private static final String CLASE = "Clase Logica Actividad";

    /**
     * Metodo que llama a la consulta para obtener la lista de actividades
     *
     * @param cantidadDatos
     * @param paginaActual
     * @return
     * @throws ExcepcionGenerica
     */
    @Override
    public List<ActividadPOJO> devolverActividades(int cantidadDatos, int paginaActual) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> actividadesResultado = actividadDB.listarActividades(cantidadDatos, paginaActual);
            if (!actividadesResultado.isEmpty()) {
                return actividadesResultado;
            } else {
                return new ArrayList<>();
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Devolver actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Devolver actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }
    
    @Override
    public List<ActividadPOJO> filtrarActividades(String palabraBusqueda,int cantidadDatos, int paginaActual) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> actividadesResultado = actividadDB.filtrarActividades(palabraBusqueda.toLowerCase(),cantidadDatos, paginaActual);
            if (!actividadesResultado.isEmpty()) {
                return actividadesResultado;
            } else {
                return new ArrayList<>();
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Filtrar actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Filtrar actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }
    
    @Override
    public int filtrarActividadesCantidad(String palabraBusqueda) throws ExcepcionGenerica {
        try {
            int cantidad = actividadDB.filtrarActividadesCantidad(palabraBusqueda.toLowerCase());
            if (cantidad!=0) {
                return cantidad;
            } else {
                return 0;
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Filtrar actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Filtrar actividades", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }
    
    /**
     * Funcion encargada de obtener el numero de registro de actividades
     * @return
     * @throws ExcepcionGenerica 
     */
    @Override
    public int cantidadActividades() throws ExcepcionGenerica {
        return actividadDB.count();
    }

    /**
     * Metodo que llama a la consulta para registrar la actividad
     *
     *
     * @param actividad
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public ActividadPOJO registrarActividad(ActividadPOJO actividad) throws ExcepcionGenerica {
        try {
            Modulo moduloResultado = moduloDB.find(actividad.getIdModulo());
            if (moduloResultado != null) {
                String acronimo = moduloResultado.getAcronimo();
                String nombreActividadN = acronimo + "_" + actividad.getNombre();

                List<Actividad> actividadResultado = actividadDB.buscarActividadPorNombre(nombreActividadN);
                if (actividadResultado.isEmpty()) {
                    ActividadPOJO actividadR = new ActividadPOJO();
                    actividadR = actividadDB.registrarActividad(actividad, nombreActividadN, moduloResultado);
                    actividad.getDatosSolicitud().setTablaInvolucrada(TABLA);
                    bitacora.registrarEnBitacora(actividad.getDatosSolicitud());
                    return actividadR;
                } else {
                    throw new NoResultException("La actividad ya se encuentra registrada");
                }
            } else {
                throw new NoResultException("El modulo no se encuentra registrado");
            }
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Registrar actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (NoResultException ex) {
            bitacora.registroLogger(CLASE, "Registrar actividad", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Registrar actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que llama a la consulta para editar la actividad
     *
     * @param actividadEditar
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void editarActividad(ActividadPOJO actividadEditar) throws ExcepcionGenerica {
        try {
            actividadDB.editarActividad(actividadEditar);
            actividadEditar.getDatosSolicitud().setTablaInvolucrada(TABLA);
            bitacora.registrarEnBitacora(actividadEditar.getDatosSolicitud());

        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Editar actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion de la actividad");
        } catch (NoResultException ex) {
            bitacora.registroLogger(CLASE, "Editar actividad", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("La actividad no existe");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Editar actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    /**
     * Metodo que llama a la consulta para editar el estado de la actividad
     *
     * @param idActividad
     * @param datosSolicitud
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoActividad(int idActividad, DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica {
        try {
            Actividad actividadResultado = actividadDB.find(idActividad);
            datosSolicitud.setTablaInvolucrada(TABLA);
            if (actividadResultado != null) {
                if (actividadResultado.getEstado().equals("Activo")) {
                    actividadDB.cambiarEstadoActividad(idActividad, "Suspendido");
                    bitacora.registrarEnBitacora(datosSolicitud);
                } else if (actividadResultado.getEstado().equals("Suspendido")) {
                    actividadDB.cambiarEstadoActividad(idActividad, "Activo");
                    bitacora.registrarEnBitacora(datosSolicitud);
                }
            } else {
                throw new NoResultException("No se encontraron datos de la actividad");
            }
        } catch (NoResultException ex) {
            bitacora.registroLogger(CLASE, "Cambiar estado actividad", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontraron datos de la actividad");
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Cambiar estado actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Cambiar estado actividad", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    /**
     * Metodo que llama a la consulta que devuelve los datos de la actividad
     * especifica recibe el id
     *
     * @param idActividad
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public ActividadPOJO traerActividadEspecifica(int idActividad) throws ExcepcionGenerica {
        try {
            ActividadPOJO actividadResultado = actividadDB.buscarActividadEspecifica(idActividad);
            if (actividadResultado != null) {
                return actividadResultado;
            } else {
                throw new NoResultException("No se encontraron datos de la actividad");
            }
        } catch (NoResultException ex) {
            bitacora.registroLogger(CLASE, "Traer actividad especifica", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontraron datos de la actividad");
        } catch (NullPointerException ex) {
            bitacora.registroLogger(CLASE, "Traer actividad especifica", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            bitacora.registroLogger(CLASE, "Traer actividad especifica", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }
}
