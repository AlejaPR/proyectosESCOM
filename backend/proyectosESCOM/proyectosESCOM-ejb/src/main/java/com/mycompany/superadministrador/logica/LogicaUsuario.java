package com.mycompany.superadministrador.logica;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ClavePOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.TipoDocumentoFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import com.mycompany.superadministrador.seguridad.Sesiones;
import com.mycompany.superadministrador.utilitarios.EnvioCorreo;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.ejb.TransactionRolledbackLocalException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.NoResultException;

/**
 * Esta es la clase encargada de la logica de usuario
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class LogicaUsuario implements LogicaUsuarioFacadeLocal {

    /**
     * Inyeccion de la interfaz usuario*
     */
    @EJB
    UsuarioFacadeLocal usuarioDB;

    /**
     * Inyeccion de la interfaz de tipo documento*
     */
    @EJB
    TipoDocumentoFacadeLocal tipoDocumentoDB;

    /**
     * Inyeccion de la interfaz de actividad*
     */
    @EJB
    ActividadFacadeLocal actividadDB;

    /**
     * Inyeccion de la interfaz de usuario-actividad*
     */
    @EJB
    UsuarioActividadFacadeLocal usuarioActividadDB;

    /**
     * Inyeccion de la interfaz de modulo*
     */
    @EJB
    ModuloFacadeLocal moduloDB;

    /**
     * Inyeccion de la interfaz de sesiones*
     */
    @EJB
    SesionesFacadeLocal sesiones;

    /**
     * Inyeccion de la interfaz de bitacora*
     */
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**
     * Variable para el registro en bitacora*
     */
    private static final String TABLA = "TBL_USUARIO";

    /**
     * Variable para el correo del superAdmin
     */
    private static final String CORREOSUPERADMIN = "admistrativosis6@gmail.com";

    /**
     * Metodo encargado de la logica del login de usuario
     *
     * @param correo
     * @param contrasena
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     */
    @Override
    public UsuarioPOJO loginUsuario(String correo, String contrasena) throws ExcepcionGenerica {
        try {
            String contrasenaEncriptada = Seguridad.generarHash(contrasena);
            List<Usuario> listaUsuario = usuarioDB.consultaLogin(correo, contrasenaEncriptada);

            if (listaUsuario.isEmpty()) {
                //Credenciales incorrectas 
                List<UsuarioPOJO> listaUsuarioCorreo = usuarioDB.buscarCorreoUsuario(correo);

                if (!listaUsuarioCorreo.isEmpty()) {
                    for (UsuarioPOJO usuarioCorreo : listaUsuarioCorreo) {
                        if (usuarioCorreo.getNumeroIntentos() == 3) {
                            Date fechaIngreso = new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(fechaIngreso);
                            calendar.add(Calendar.MINUTE, 60);
                            usuarioDB.cambiarFechaIngreso(calendar.getTime(), usuarioCorreo);
                            return null;
                        } else {
                            int intentos = usuarioCorreo.getNumeroIntentos() + 1;
                            usuarioDB.cambiarNumeroIntentos(intentos, usuarioCorreo.getId());
                            if (intentos == 3) {
                                usuarioDB.cambiarNumeroIntentos(intentos, usuarioCorreo.getId());
                                Date fechaIngreso = new Date();
                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(fechaIngreso);
                                calendar.add(Calendar.MINUTE, 60);
                                usuarioDB.cambiarFechaIngreso(calendar.getTime(), usuarioCorreo);
                            }
                            return null;
                        }
                    }
                } else {
                    throw new ExcepcionGenerica("No se encontro ninguna credencial que coincida");
                }

            } else {
                //Credenciales correctas
                UsuarioPOJO usuarioRespuesta = new UsuarioPOJO();
                for (Usuario usuario : listaUsuario) {
                    if (usuario.getEstado().equals("Suspendido")) {
                        throw new ExcepcionGenerica("Cuenta suspendida temporalmente");
                    } else {
                        if (usuario.getFechaIngreso() != null) {
                            //valida si la fecha ingreso esta antes de la actual
                            if (usuario.getFechaIngreso().after(new Date())) {
                                throw new ExcepcionGenerica("Cuenta suspendida temporalmente por intentos erroneos");
                            } else {
                                usuarioDB.cambiarIntentosConFecha(usuario);
                                Seguridad token = new Seguridad();
                                String[] actividad;
                                if (usuario.getCorreoElectronico().equals(CORREOSUPERADMIN)) {
                                    actividad = usuarioDB.consultarTodasActividades(usuario.getIdUsuario());
                                } else {
                                    actividad = usuarioDB.consultarActividadesUsuario(usuario.getIdUsuario());
                                }
                                Gson gson = new Gson();
                                String actividades = gson.toJson(actividad);
                                String tokencin = token.generarToken(usuario, actividades);
                                usuario.setToken(Seguridad.desencriptar(tokencin).getFirma());
                                usuarioDB.editarToken(usuario.getToken(), usuario.getIdUsuario());
                                usuarioRespuesta.setToken(tokencin);
                                usuarioRespuesta.setNombre(usuario.getNombre());
                                usuarioRespuesta.setApellido(usuario.getApellido());
                                validarTokens(tokencin);
                                return usuarioRespuesta;
                            }
                        } else {
                            usuarioDB.cambiarIntentosConFecha(usuario);
                            Seguridad token = new Seguridad();
                            String[] actividad;
                            if (usuario.getCorreoElectronico().equals(CORREOSUPERADMIN)) {
                                actividad = usuarioDB.consultarTodasActividades(usuario.getIdUsuario());
                            } else {
                                actividad = usuarioDB.consultarActividadesUsuario(usuario.getIdUsuario());
                            }
                            Gson gson = new Gson();
                            String actividades = gson.toJson(actividad);
                            String tokencin = token.generarToken(usuario, actividades);
                            usuario.setToken(Seguridad.desencriptar(tokencin).getFirma());
                            usuarioDB.editarToken(usuario.getToken(), usuario.getIdUsuario());
                            usuarioRespuesta.setToken(tokencin);
                            usuarioRespuesta.setNombre(usuario.getNombre());
                            usuarioRespuesta.setApellido(usuario.getApellido());
                            validarTokens(tokencin);
                        }

                    }
                }
                return usuarioRespuesta;
            }

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el login del usuario");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ninguna credencial que coincida");
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (EJBTransactionRolledbackException ex) {
            throw new ExcepcionGenerica("No se encontro ninguna credencial que coincida");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor ");
        }
        return null;
    }

    /**
     * Metodo encargado de la logica de cerrar sesion
     *
     * @param token
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cerrarSesion(String token) throws ExcepcionGenerica {
        try {
            Token tokenDesencriptado = Seguridad.desencriptar(token);
            usuarioDB.editarTokenCerrarSesion(" ", tokenDesencriptado.getIssuer());
            sesiones.getMapaSesiones().remove(token);
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de cerrar la sesion");
        }
    }

    /**
     * Metodo encargado del registro de usuario
     *
     * @param usuario
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     */
    @Override
    public void registrarUsuario(UsuarioPOJO usuario) throws ExcepcionGenerica {
        try {
            List<Usuario> usuarioResultado = usuarioDB.consultaDatosExistentes(usuario.getCorreoElectronico(), usuario.getNumeroDocumento());
            if (usuarioResultado.isEmpty()) {
                usuarioDB.registrarUsuario(usuario);
                usuario.getDatosSolicitud().setTablaInvolucrada(TABLA);
                bitacora.registrarEnBitacora(usuario.getDatosSolicitud());
            } else {
                throw new ExcepcionGenerica("El correo o numero de documento ya esta registrado");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el registro del usuario");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo encargado de validar token
     *
     * @param tokencin
     */
    public void validarTokens(String tokencin) {
        Token token = Seguridad.desencriptar(tokencin);
        Calendar FechaHoy = new GregorianCalendar();
        Calendar fecha = Sesiones.sumarRestarDiasFecha(FechaHoy.getTime(), 30);
        String keyEncontrada = "";
        if (sesiones.getMapaSesiones().isEmpty()) {
            sesiones.getMapaSesiones().put(tokencin, fecha);
        }
        for (Map.Entry<String, Calendar> entry : sesiones.getMapaSesiones().entrySet()) {
            if (Seguridad.desencriptar(entry.getKey()).getIssuer().equals(token.getIssuer())) {
                keyEncontrada = entry.getKey();
            }
        }
        if (!keyEncontrada.equals("")) {
            sesiones.getMapaSesiones().remove(keyEncontrada);
            sesiones.getMapaSesiones().put(tokencin, fecha);
        } else {
            sesiones.getMapaSesiones().put(tokencin, fecha);
        }
    }

    /**
     * Metodo encargado de devolver los datos de usuario
     *
     * @param token
     * @return
     */
    @Override
    public UsuarioPOJO devolverDatosUsuario(String token) {
        try {
            if (validarToken(token)) {
                Token tokenDesencriptado = Seguridad.desencriptar(token);
                return usuarioDB.busquedaToken(tokenDesencriptado.getFirma());
            } else {
                //vencio el token o no esta registrado
                return null;
            }
        } catch (Exception e) {
            //ocurrio una error   
            return null;
        }
    }

    /**
     * Funcion encargada de validar la vigencia y si el token esta registrado
     *
     * @param token
     * @return
     */
    public boolean validarToken(String token) {
        try {
            if (sesiones.getMapaSesiones().containsKey(token)) {
                if (sesiones.validacionDeFecha(sesiones.getMapaSesiones().get(token)) > 0) {
                    //validacion correcta
                    return true;
                } else {
                    //el token esta vencido
                    return false;
                }
            } else {
                //el token no esta registrado
                return false;
            }
        } catch (Exception e) {
            //ocurrio una error   
            return false;
        }
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de usuarios
     *
     * @param token
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<UsuarioPOJO> devolverUsuarios(String token) throws ExcepcionGenerica {
        try {
            String correoSolicitud = Seguridad.desencriptar(token).getIssuer();
            List<UsuarioPOJO> usuariosResultado = new ArrayList<>();
            if (correoSolicitud.equals(CORREOSUPERADMIN)) {
                usuariosResultado = usuarioDB.listarUsuarios();
            } else {
                for (UsuarioPOJO usuario : usuarioDB.listarUsuarios()) {
                    if (!usuario.getCorreoElectronico().equals(CORREOSUPERADMIN)) {
                        usuariosResultado.add(usuario);
                    }
                }
            }
            if (!usuariosResultado.isEmpty()) {
                return usuariosResultado;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de tipos de
     * documento
     *
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<TipoDocumentoPOJO> devolverDocumentos() throws ExcepcionGenerica {
        try {
            List<TipoDocumentoPOJO> tipoDocumentoResultado = tipoDocumentoDB.consultaTipoDocumento();
            if (!tipoDocumentoResultado.isEmpty()) {
                return tipoDocumentoResultado;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    /**
     * Metodo que llama a la consulta que devuelve los datos del usuario
     * recibiendo la cedula
     *
     * @param cedula
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public UsuarioPOJO traerUsuarioCedula(int cedula) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuarioResultado = usuarioDB.buscarUsuarioEspecifico(cedula);
            if (usuarioResultado != null) {
                return usuarioResultado;
            } else {
                throw new NoResultException("No se encontraron datos del usuario");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que llama a la consulta para editar usuario recibiendo como
     * parametro la cedula
     *
     * @param cedula
     * @param usuarioEditar
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void editarUsuario(int cedula, UsuarioPOJO usuarioEditar) throws ExcepcionGenerica {
        try {
            List<Usuario> usuariosFiltro = new ArrayList<>();
            for (Usuario usuario : usuarioDB.findAll()) {
                if (usuario.getNumeroDocumento() != cedula) {
                    usuariosFiltro.add(usuario);
                }
            }
            boolean datosExistentes = false;
            for (Usuario usuario : usuariosFiltro) {
                if (usuario.getCorreoElectronico().equals(usuarioEditar.getCorreoElectronico()) | usuario.getNumeroDocumento() == usuarioEditar.getNumeroDocumento()) {
                    datosExistentes = true;
                    break;
                }
            }
            if (!datosExistentes) {
                if (usuarioDB.editarUsuario(cedula, usuarioEditar, tipoDocumentoDB.find(usuarioEditar.getTipoDocumento())) != 1) {
                    throw new Exception("Usuario no modificado");
                } else {
                    usuarioEditar.getDatosSolicitud().setTablaInvolucrada(TABLA);
                    bitacora.registrarEnBitacora(usuarioEditar.getDatosSolicitud());
                }
            } else {
                throw new ExcepcionGenerica("El correo o numero de documento ya esta registrado");
            }

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion del usuario");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    /**
     * Metodo que llama a la consulta para cambiar el estado del usuario
     * recibiendo como parametro la cedula
     *
     * @param cedula
     * @param datosSolicitud
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoUsuario(int cedula, DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuarioResultado = usuarioDB.buscarUsuarioEspecifico(cedula);
            datosSolicitud.setTablaInvolucrada(TABLA);
            if (usuarioResultado != null) {
                if (usuarioResultado.getEstado().equals("Activo")) {
                    usuarioDB.cambiarEstadoUsuario(usuarioResultado.getId(), "Suspendido");
                    bitacora.registrarEnBitacora(datosSolicitud);
                } else if (usuarioResultado.getEstado().equals("Suspendido")) {
                    usuarioDB.cambiarEstadoUsuario(usuarioResultado.getId(), "Activo");
                    bitacora.registrarEnBitacora(datosSolicitud);
                }
            } else {
                throw new NoResultException("No se encontraron datos del usuario");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que trae los datos del usuario con el parametro cedula para llamar
     * la consulta que busca la lista de todas las actividades del usuario
     *
     * @param numeroDocumento
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesUsuario(int numeroDocumento) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> listaActividades = new ArrayList();
            listaActividades = actividadDB.listarActividadesUsuario(numeroDocumento);
            if (listaActividades.size() >= 0) {
                return listaActividades;
            } else {
                throw new NoResultException("Error en la consulta");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que trae los datos del usuario con el parametro cedula, llama la
     * consulta que busca la lista de actividades no asociadas del usuario
     *
     * @param numeroDocumento
     * @param idModulo
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int numeroDocumento, int idModulo) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> listaActividades = new ArrayList();
            listaActividades = actividadDB.listarActividadesNoAsociadasUsuario(numeroDocumento, idModulo);
            if (listaActividades.size() >= 0) {
                return listaActividades;
            } else {
                throw new NoResultException("Error en la consulta");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que trae los datos del usuario con el parametro cedula para llamar
     * la consulta que eliminar la actividades del usuario recibiendo como
     * parametro el id de la actividad
     *
     * @param cedula
     * @param listaActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void eliminarActividadUsuario(int cedula, List<ActividadPOJO> listaActividad) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuarioResultado = usuarioDB.buscarUsuarioEspecifico(cedula);
            if (usuarioResultado != null) {
                for (int i = 0; i < listaActividad.size(); i++) {
                    usuarioActividadDB.eliminarActividadUsuario(usuarioDB.find(usuarioResultado.getId()), actividadDB.find(listaActividad.get(i).getIdActividad()));
                }
                listaActividad.get(0).getDatosSolicitud().setTablaInvolucrada(TABLA);
                bitacora.registrarEnBitacora(listaActividad.get(0).getDatosSolicitud());
            } else {
                throw new NoResultException("No se encontraron datos del usuario");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que asigna actividades a los usuarios
     *
     * @param numeroDocumento
     * @param actividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void asignarActividadAUsuario(int numeroDocumento, ActividadPOJO actividad) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuario = usuarioDB.buscarUsuarioEspecifico(numeroDocumento);
            Usuario usu = usuarioDB.find(usuario.getId());
            Actividad act = actividadDB.find(actividad.getIdActividad());
            UsuarioActividad usuarioActividad = new UsuarioActividad();
            usuarioActividad.setUsuario(usu);
            usuarioActividad.setActividad(act);
            usuarioActividad.setUltimaModificacion(new Date());
            usuarioActividadDB.create(usuarioActividad);
            actividad.getDatosSolicitud().setTablaInvolucrada(TABLA);
            bitacora.registrarEnBitacora(actividad.getDatosSolicitud());
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de asignar actividad al usuario");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que trae los datos del usuario con el parametro cedula para llamar
     * la consulta que busca la lista de actividades del usuario activas
     *
     * @param numeroDocumento
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesUsuarioActivas(int numeroDocumento) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> listaActividades = new ArrayList();
            listaActividades = actividadDB.listarActividadesUsuarioActivas(numeroDocumento);
            if (listaActividades.size() >= 0) {
                return listaActividades;
            } else {
                throw new NoResultException("Error en la consulta");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**
     * Metodo que realiza la logica para la redireccion de modulos
     *
     * @param token
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ModuloPOJO> redireccionUsuario(String token) throws ExcepcionGenerica {
        try {
            String correo = Seguridad.desencriptar(token).getIssuer();
            List<ModuloPOJO> retorno = new ArrayList<>();
            if (correo.equals(CORREOSUPERADMIN)) {
                List<ModuloPOJO> modulosResultado = new ArrayList();
                int id = 0;
                Token tokenDevuelto = Seguridad.desencriptar(token);
                String firma = tokenDevuelto.getFirma();
                UsuarioPOJO usuario = usuarioDB.busquedaToken(firma);
                List<ActividadPOJO> listaActividad = actividadDB.listarActividadesUsuarioSuper(usuario.getNumeroDocumento());
                for (int i = 0; i < listaActividad.size(); i++) {
                    id = listaActividad.get(i).getIdModulo();
                    ModuloPOJO moduloE = moduloDB.buscarModuloEspecifico(id);
                    if (moduloE.getEstadoModulo().equals("Activo") | moduloE.getEstadoModulo().equals("Suspendido")) {
                        modulosResultado.add(moduloE);
                    }
                }
                for (ModuloPOJO m : modulosResultado) {
                    if (!comprobarExistencia(retorno, m.getIdModulo())) {
                        retorno.add(m);
                    }
                }
            } else {
                List<ModuloPOJO> modulosResultado = new ArrayList();
                int id = 0;
                Token tokenDevuelto = Seguridad.desencriptar(token);
                String firma = tokenDevuelto.getFirma();
                UsuarioPOJO usuario = usuarioDB.busquedaToken(firma);
                List<ActividadPOJO> listaActividad = listarActividadesUsuarioActivas(usuario.getNumeroDocumento());
                for (int i = 0; i < listaActividad.size(); i++) {
                    id = listaActividad.get(i).getIdModulo();
                    ModuloPOJO moduloE = moduloDB.buscarModuloEspecifico(id);
                    if (moduloE.getEstadoModulo().equals("Activo")) {
                        modulosResultado.add(moduloE);
                    }
                }
                for (ModuloPOJO m : modulosResultado) {
                    if (!comprobarExistencia(retorno, m.getIdModulo())) {
                        retorno.add(m);
                    }
                }
            }
            if (!retorno.isEmpty()) {
                return retorno;
            } else {
                throw new ExcepcionGenerica("No hay permisos asociados");
            }
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    /**
     * Metodo que comprueba la existencia del modulo en la lista de redireccion
     *
     * @param modulos
     * @param id
     * @return
     */
    public boolean comprobarExistencia(List<ModuloPOJO> modulos, int id) {
        for (ModuloPOJO m : modulos) {
            if (id == m.getIdModulo()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de usuarios para el
     * modulo documental
     *
     * @return
     *
     */
    @Override
    public List<UsuarioPOJO> devolverUsuariosModuloDocumental() {
        try {
            List<UsuarioPOJO> usuariosResultado = usuarioDB.listarUsuariosModuloDocumental();
            if (!usuariosResultado.isEmpty()) {
                return usuariosResultado;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Metodo que llama a la consulta para cambiar contraseña interna del
     * usuario
     *
     * @param clavePOJO
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarClaveInterna(ClavePOJO clavePOJO) throws ExcepcionGenerica {
        try {
            Token tokenDevuelto = Seguridad.desencriptar(clavePOJO.getToken());
            String firma = tokenDevuelto.getFirma();
            UsuarioPOJO usuario = usuarioDB.busquedaToken(firma);
            clavePOJO.setAntiguaClave(Seguridad.generarHash(clavePOJO.getAntiguaClave()));
            clavePOJO.setNuevaClave(Seguridad.generarHash(clavePOJO.getNuevaClave()));
            if (usuario != null) {
                if (!clavePOJO.getAntiguaClave().equals(clavePOJO.getNuevaClave())) {
                    if (clavePOJO.getAntiguaClave().equals(usuario.getContrasena())) {
                        usuarioDB.cambiarClaveInterna(clavePOJO.getNuevaClave(), usuario);
                        clavePOJO.getDatosSolicitud().setTablaInvolucrada(TABLA);
                        bitacora.registrarEnBitacora(clavePOJO.getDatosSolicitud());
                    } else {
                        throw new ExcepcionGenerica("La contraseña ingresada no es la correcta");
                    }
                } else {
                    throw new ExcepcionGenerica("La contraseña nueva no puede ser igual a la antigua");
                }
            } else {
                throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    /**
     * Metodo que llama a la consulta para devolver el correo del usuario
     *
     * @param token
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public String devolverCorreo(String token) throws ExcepcionGenerica {
        try {
            String correo = Seguridad.desencriptar(token).getIssuer();
            if (!correo.equals("")) {
                return correo;
            } else {
                throw new ExcepcionGenerica("No se encontro el usuario");
            }
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta de usuario");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }

    }

    @Override
    public String generarTokenRecuperarContrasena(String correoElectronico) throws ExcepcionGenerica {
        try {
            List<UsuarioPOJO> usuario = usuarioDB.buscarCorreoUsuario(correoElectronico);
            if (!usuario.isEmpty()) {
                Seguridad seguridad = new Seguridad();
                String token = seguridad.generarTokenRecuperar(correoElectronico);
                Token tokenDevuelto = Seguridad.desencriptar(token);
                String firma = tokenDevuelto.getFirma();
                if (usuarioDB.editarTokenRecuperarContrasena(firma, usuario.get(0).getId()) != 1) {
                    throw new ExcepcionGenerica("Ocurrio un error en el servidor");
                } else {
                    EnvioCorreo e = new EnvioCorreo();
                    String link = "http://localhost:3000/recuperarContrasena/" + token;
                    e.enviarCorreo(e.devolverEstructuraHTML(usuario.get(0).getNombre(), link), correoElectronico, "Recuperar contraseña");
                    return "Correo enviado";
                }
            } else {
                throw new ExcepcionGenerica("Correo electronico no registrado");
            }
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (AddressException ex) {
            throw new ExcepcionGenerica("El correo esta en formato incorrecto");
        } catch (MessagingException ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    @Override
    public String validarTokenRecuperarContrasena(String token) throws ExcepcionGenerica {
        try {
            UsuarioPOJO resultado = usuarioDB.busquedaTokenRecuperar(Seguridad.desencriptar(token).getFirma());
            return Seguridad.desencriptar(token).getIssuer();
        } catch (MalformedJwtException | UnsupportedJwtException | SignatureException ex) {
            throw new ExcepcionGenerica("Token en formato incorrecto");
        } catch (ExpiredJwtException ex) {
            throw new ExcepcionGenerica("Token vencido");
        } catch (EJBTransactionRolledbackException ex) {
            throw new ExcepcionGenerica("Enlace no valido");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("Enlace no valido");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

    @Override
    public String cambiarContrasenaExterna(String nuevaContrasena, String token, DatosSolicitudPOJO datos) throws ExcepcionGenerica {
        try {
            String firma = Seguridad.desencriptar(token).getFirma();
            UsuarioPOJO resultado = usuarioDB.busquedaTokenRecuperar(firma);
            if (resultado == null) {
                throw new ExcepcionGenerica("Usuario no registrado");
            } else {
                usuarioDB.cambiarClaveInterna(Seguridad.generarHash(nuevaContrasena), resultado);
                usuarioDB.editarTokenRecuperarContrasena(null, resultado.getId());
                datos.setTablaInvolucrada(TABLA);
                datos.setIdUsuario(resultado.getId());
                bitacora.registrarEnBitacora(datos);
                return "cambiada";
            }
        } catch (ExcepcionGenerica ex) {
            throw new ExcepcionGenerica(ex.getMessage());
        } catch (MalformedJwtException | UnsupportedJwtException | SignatureException ex) {
            throw new ExcepcionGenerica("Token en formato incorrecto");
        } catch (ExpiredJwtException ex) {
            throw new ExcepcionGenerica("Token vencido");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro un correo asociado");
        } catch (EJBTransactionRolledbackException ex) {
            throw new ExcepcionGenerica("Enlace no valido");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio un error en el servidor");
        }
    }

}
