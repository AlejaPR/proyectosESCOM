package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Esta es la clase encargada de la logica de bitacora
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class LogicaBitacora implements LogicaBitacoraFacadeLocal {

    /**
     * Inyeccion de la interfaz de actividad*
     */
    @EJB
    ActividadFacadeLocal actividadDB;

    /**
     * Inyeccion de la interfaz de usuario*
     */
    @EJB
    UsuarioFacadeLocal usuarioDB;

    /**
     * Inyeccion de la interfaz de modulo*
     */
    @EJB
    ModuloFacadeLocal moduloDB;

    /**
     * Inyeccion de la interfaz de bitacora*
     */
    @EJB
    BitacoraFacadeLocal bitacoraDB;

    /**
     * Inyeccion de la interfaz de logica usuario*
     */
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    /**
     * Inyeccion de la interfaz utilitarios*
     */
    @EJB
    UtilitarioFacadeLocal utilitario;

    /**
     * Variable para el registro de logger*
     */
    private static final String CLASE = "Clase Logica Bitacora";

    /**
     * Metodo que registra los datos en bitacora
     *
     * @param solicitud
     */
    @Override
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud) {
        try {
            if (solicitud.getIdUsuario() == 0) {
                solicitud.setIdUsuario(usuarioLogica.devolverDatosUsuario(solicitud.getToken()).getId());
            }
            String actividadConAcronimo = solicitud.getOperacion();
            String actividadSinAcronimo = solicitud.getOperacion().substring(3);
            solicitud.setOperacion(actividadSinAcronimo);
            solicitud.setIdModulo(actividadDB.buscarActividadPorNombre(actividadConAcronimo).get(0).getModulo().getIdModulo());
            bitacoraDB.registrarUsuario(solicitud);
        } catch (Exception e) {
            utilitario.registroLogger(CLASE, "Registrar en bitacora", Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Metodo que llama a la consulta de bitacora
     *
     * @param reporte
     * @param cantidadDeDatos
     * @param paginaActual
     * @return
     * @throws ExcepcionGenerica
     */
    @Override
    public List<DatosSolicitudPOJO> consultar(ReportePOJO reporte, int cantidadDeDatos, int paginaActual) throws ExcepcionGenerica {
        try {
            switch (reporte.getIdBusqueda()) {
                case 1:
                    List<UsuarioPOJO> listaUsuario = new ArrayList();
                    try {
                        int documento = Integer.parseInt(reporte.getPalabraBusqueda());
                        listaUsuario = usuarioDB.buscarUsuarioBitacoraDocumento(documento);
                    } catch (NumberFormatException e) {
                        listaUsuario = usuarioDB.buscarUsuarioBitacora(reporte.getPalabraBusqueda());
                    }
                    if (!listaUsuario.isEmpty()) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioConFechaFin(listaUsuario.get(i).getId(), reporte, cantidadDeDatos, paginaActual);
                                    return listaSolicitud;
                                }
                            } else {
                                throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                            }
                        } else {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioSinFechaFin(listaUsuario.get(i).getId(), reporte, cantidadDeDatos, paginaActual);
                                    return listaSolicitud;
                                }
                            } else {
                                throw new NoResultException("No hay resultados");
                            }
                        }
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                        return listaSolicitud;
                    }
                    break;
                case 2:
                    List<ModuloPOJO> listaModulo = moduloDB.buscarModuloBitacora(reporte.getPalabraBusqueda());
                    if (!listaModulo.isEmpty()) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                                for (int i = 0; i < listaModulo.size(); i++) {
                                    List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloConFechaFin(listaModulo.get(i).getIdModulo(), reporte, cantidadDeDatos, paginaActual);
                                    return listaSolicitud;
                                }
                            } else {
                                throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                            }
                        } else {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                                for (int i = 0; i < listaModulo.size(); i++) {
                                    List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloSinFechaFin(listaModulo.get(i).getIdModulo(), reporte, cantidadDeDatos, paginaActual);
                                    return listaSolicitud;
                                }
                            } else {
                                throw new NoResultException("No hay resultados");
                            }
                        }
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                        return listaSolicitud;
                    }
                    break;
                case 3:
                    if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarActividadConFechaFin(reporte,cantidadDeDatos, paginaActual);
                            return listaSolicitud;
                        } else {
                            throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                        }
                    } else {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                            List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarActividadSinFechaFin(reporte,cantidadDeDatos, paginaActual);
                            return listaSolicitud;
                        } else {
                            throw new NoResultException("No hay resultados");
                        }
                    }
                default:
                    return null;
            }
        } catch (NullPointerException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta de bitacora");
        } catch (NoResultException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
        return null;
    }
    
    @Override
    public int cantidadDeDatos(ReportePOJO reporte)throws ExcepcionGenerica{
        try {
            switch (reporte.getIdBusqueda()) {
                case 1:
                    List<UsuarioPOJO> listaUsuario = new ArrayList();
                    try {
                        int documento = Integer.parseInt(reporte.getPalabraBusqueda());
                        listaUsuario = usuarioDB.buscarUsuarioBitacoraDocumento(documento);
                    } catch (NumberFormatException e) {
                        listaUsuario = usuarioDB.buscarUsuarioBitacora(reporte.getPalabraBusqueda());
                    }
                    if (!listaUsuario.isEmpty()) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    return bitacoraDB.buscarUsuarioConFechaFinCantidad(listaUsuario.get(i).getId(), reporte);
                                }
                            } else {
                                throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                            }
                        } else {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    return bitacoraDB.buscarUsuarioSinFechaFinCantidad(listaUsuario.get(i).getId(), reporte);
                                }
                            } else {
                                throw new NoResultException("No hay resultados");
                            }
                        }
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                        return 0;
                    }
                    break;
                case 2:
                    List<ModuloPOJO> listaModulo = moduloDB.buscarModuloBitacora(reporte.getPalabraBusqueda());
                    if (!listaModulo.isEmpty()) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                                for (int i = 0; i < listaModulo.size(); i++) {
                                    return bitacoraDB.buscarModuloConFechaFinCantidad(listaModulo.get(i).getIdModulo(), reporte);
                                }
                            } else {
                                throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                            }
                        } else {
                            if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                                for (int i = 0; i < listaModulo.size(); i++) {
                                    return bitacoraDB.buscarModuloSinFechaFinCantidad(listaModulo.get(i).getIdModulo(), reporte);
                                }
                            } else {
                                throw new NoResultException("No hay resultados");
                            }
                        }
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                        return 0;
                    }
                    break;
                case 3:
                    if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            return bitacoraDB.buscarActividadConFechaFinCantidad(reporte);
                        } else {
                            throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                        }
                    } else {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                            return bitacoraDB.buscarActividadSinFechaFinCantidad(reporte);
                        } else {
                            throw new NoResultException("No hay resultados");
                        }
                    }
                default:
                    return 0;
            }
        } catch (NullPointerException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta de bitacora");
        } catch (NoResultException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
        return 0;
    }
    
    @Override
    public List<DatosSolicitudPOJO> consultarBitacoraCompleta(ReportePOJO reporte) throws ExcepcionGenerica {
          try {
            if (reporte.getIdBusqueda() == 1) {
                List<UsuarioPOJO> listaUsuario = new ArrayList();
                try {
                    int documento = Integer.parseInt(reporte.getPalabraBusqueda());
                    listaUsuario = usuarioDB.buscarUsuarioBitacoraDocumento(documento);
                } catch (NumberFormatException e) {
                    listaUsuario = usuarioDB.buscarUsuarioBitacora(reporte.getPalabraBusqueda());
                }
                if (!listaUsuario.isEmpty()) {
                    if (reporte.getFechaFin() == null) {
                        for (int i = 0; i < listaUsuario.size(); i++) {
                            List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioSinFechaFin(listaUsuario.get(i).getId(), reporte,0,1);
                            return listaSolicitud;
                        }
                    } else  if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0){
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            for (int i = 0; i < listaUsuario.size(); i++) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioConFechaFin(listaUsuario.get(i).getId(), reporte,0,1);
                                return listaSolicitud;
                            }
                        } else {
                            throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                        }
                    }else {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                            for (int i = 0; i < listaUsuario.size(); i++) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioSinFechaFin(listaUsuario.get(i).getId(), reporte,0,1);
                                return listaSolicitud;
                            }
                        } else {
                            throw new NoResultException("No hay resultados");
                        }
                    }
                } else {
                    List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                    return listaSolicitud;
                }

            } else if (reporte.getIdBusqueda() == 2) {
                List<ModuloPOJO> listaModulo = moduloDB.buscarModuloBitacora(reporte.getPalabraBusqueda());
                if (!listaModulo.isEmpty()) {
                    if (reporte.getFechaFin() == null) {
                        for (int i = 0; i < listaModulo.size(); i++) {
                            List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloSinFechaFin(listaModulo.get(i).getIdModulo(), reporte,0,1);
                            return listaSolicitud;
                        }

                    }else  if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0){
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                            for (int i = 0; i < listaModulo.size(); i++) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloConFechaFin(listaModulo.get(i).getIdModulo(), reporte,0,1);
                                return listaSolicitud;
                            }
                        } else {
                            throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                        }
                    }else {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                            for (int i = 0; i < listaModulo.size(); i++) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloSinFechaFin(listaModulo.get(i).getIdModulo(), reporte,0,1);
                                return listaSolicitud;
                            }
                        } else {
                            throw new NoResultException("No hay resultados");
                        }
                    }
                } else {
                    List<DatosSolicitudPOJO> listaSolicitud = new ArrayList();
                    return listaSolicitud;
                }
            } else if (reporte.getIdBusqueda() == 3) {

                if (reporte.getFechaFin() == null) {
                    List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarActividadSinFechaFin(reporte,0,1);
                    return listaSolicitud;
                }else  if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0){
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) < 0) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarActividadConFechaFin(reporte,0,1);
                                return listaSolicitud;
                        } else {
                            throw new NoResultException("La fecha de inicio debe ser menor a la fecha fin");
                        }
                    }else {
                        if (reporte.getFechaInicio().compareTo(reporte.getFechaFin()) == 0) {
                                List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarActividadSinFechaFin(reporte,0,1);
                                return listaSolicitud;
                        } else {
                            throw new NoResultException("No hay resultados");
                        }
                    }
            }
        } catch (NullPointerException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta de bitacora");
        } catch (NoResultException ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.WARNING, ex.getMessage());
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            utilitario.registroLogger(CLASE, "Consultar", Level.SEVERE, ex.getMessage());
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
        return null;
     }

}
