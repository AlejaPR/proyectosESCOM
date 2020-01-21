package com.mycompany.superadminisrador.logica;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.TipoDocumentoFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import com.mycompany.superadministrador.seguridad.Sesiones;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jeiso
 */
@Stateless
public class LogicaUsuario implements LogicaUsuarioFacadeLocal {

    @EJB
    UsuarioFacadeLocal usuarioDB;

    @EJB
    TipoDocumentoFacadeLocal tipoDocumentoDB;

    @EJB
    ActividadFacadeLocal actividadDB;
    
    @EJB
    UsuarioActividadFacadeLocal usuarioActividadDB;

    @EJB
    SesionesFacadeLocal sesiones;

    @Override
    public UsuarioPOJO loginUsuario(String correo, String contrasena) throws ExcepcionGenerica {
        try {
            String contrasenaEncriptada = Seguridad.generarHash(contrasena);
            Usuario usuario = usuarioDB.consultaLogin(correo, contrasenaEncriptada);
            Seguridad token = new Seguridad();
            List<ActividadPOJO> actividad = usuarioDB.consultarActividadesUsuario(usuario.getIdUsuario());
            Gson gson = new Gson();
            String actividades = gson.toJson(actividad);
            String tokencin = token.generarToken(usuario, actividades);
            usuario.setToken(Seguridad.desencriptar(tokencin).getFirma());
            usuarioDB.editarToken(usuario.getToken(), usuario.getIdUsuario());
            UsuarioPOJO usuarioRespuesta = new UsuarioPOJO();
            usuarioRespuesta.setToken(tokencin);
            validarTokens(tokencin);
            return usuarioRespuesta;

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el login del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ninguna credencial que coincida");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

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

    @Override
    public void registrarUsuario(UsuarioPOJO usuario) throws ExcepcionGenerica {
        try {
            List<Usuario> usuarioResultado = usuarioDB.consultaDatosExistentes(usuario.getCorreoElectronico(), usuario.getNumeroDocumento());
            if (usuarioResultado.isEmpty()) {
                usuarioDB.registrarUsuario(usuario);
            } else {
                throw new NoResultException("El correo o numero de documento ya esta registrado");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el login del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

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
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<UsuarioPOJO> devolverUsuarios() throws ExcepcionGenerica {
        try {
            List<UsuarioPOJO> usuariosResultado = usuarioDB.listarUsuarios();
            if (!usuariosResultado.isEmpty()) {
                return usuariosResultado;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
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
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
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
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
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
                }
            } else {
                throw new NoResultException("El correo o numero de documento ya esta registrado");
            }

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }

    }

    /**
     * Metodo que llama a la consulta para cambiar el estado del usuario
     * recibiendo como parametro la cedula
     *
     * @param cedula
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoUsuario(int cedula) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuarioResultado = usuarioDB.buscarUsuarioEspecifico(cedula);
            if (usuarioResultado != null) {
                if (usuarioResultado.getEstado().equals("Activo")) {
                    usuarioDB.cambiarEstadoUsuario(usuarioResultado.getId(), "Suspendido");
                } else if (usuarioResultado.getEstado().equals("Suspendido")) {
                    usuarioDB.cambiarEstadoUsuario(usuarioResultado.getId(), "Activo");
                }
            } else {
                throw new NoResultException("No se encontraron datos del usuario");
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
     * Metodo que trae los datos del usuario con el parametro cedula para llamar
     * la consulta que busca la lista de actividades del usuario
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
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

     /**
     * Metodo que trae los datos del usuario con el parametro cedula, llama
     * la consulta que busca la lista de actividades no asociadas del usuario
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
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**
     * Metodo que trae los datos del usuario con el parametro cedula para llamar
     * la consulta que eliminar la actividades del usuario recibiendo como
     * parametro el id de la actividad
     *
     * @param cedula
     * @param idActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void eliminarActividadUsuario(int cedula, int idActividad) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuarioResultado = usuarioDB.buscarUsuarioEspecifico(cedula);
            if (usuarioResultado != null) {
                actividadDB.eliminarActividadUsuario(usuarioResultado.getId(), idActividad);

            } else {
                throw new NoResultException("No se encontraron datos del usuario");
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
     * Metodo que asigna actividades a los usuarios 
     *
     * @param numeroDocumento
     * @param idActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void asignarActividadAUsuario(int numeroDocumento,int idActividad) throws ExcepcionGenerica {
        try {
            UsuarioPOJO usuario=usuarioDB.buscarUsuarioEspecifico(numeroDocumento);
            Usuario usu=usuarioDB.find(usuario.getId());
            Actividad act= actividadDB.find(idActividad);
            UsuarioActividad usuarioActividad=new UsuarioActividad();
            usuarioActividad.setFkUacIdusuario(usu);
            usuarioActividad.setFkUacIdactividad(act);
            usuarioActividad.setUltimaModificacion(new Date());
            usuarioActividadDB.create(usuarioActividad);
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el login del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

}
