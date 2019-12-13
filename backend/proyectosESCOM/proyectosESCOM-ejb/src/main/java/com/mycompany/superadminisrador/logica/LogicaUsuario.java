package com.mycompany.superadminisrador.logica;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.TipoDocumento;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.TipoDocumentoFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import com.mycompany.superadministrador.seguridad.Sesiones;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
    public void registrarUsuario(UsuarioPOJO usuario)throws ExcepcionGenerica {
        try{
            List<Usuario> usuarioResultado=usuarioDB.consultaDatosExistentes(usuario.getCorreoElectronico(),usuario.getNumeroDocumento());
            if(usuarioResultado.isEmpty()){
                usuarioDB.registrarUsuario(usuario);
            }else{
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
    
     /**Metodo que llama a la consulta para obtener la lista de usuarios
     * @return 
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     **/
    @Override
    public List<UsuarioPOJO> devolverUsuarios() throws ExcepcionGenerica {
        try{
          List<UsuarioPOJO> usuariosResultado=usuarioDB.listarUsuarios();
            if(!usuariosResultado.isEmpty()){
                return usuariosResultado;
            }else{
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }
        
        
    /**Metodo que llama a la consulta para obtener la lista de tipos de documento
     * @return 
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     **/
    @Override
    public List<TipoDocumentoPOJO> devolverDocumentos() throws ExcepcionGenerica {
        try{
          List<TipoDocumentoPOJO> tipoDocumentoResultado=tipoDocumentoDB.consultaTipoDocumento();
            if(!tipoDocumentoResultado.isEmpty()){
                return tipoDocumentoResultado;
            }else{
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
          
    }

    @Override
    public UsuarioPOJO traerUsuarioCedula(int cedula) throws ExcepcionGenerica {
        try{
          UsuarioPOJO usuarioResultado=usuarioDB.buscarUsuarioEspecifico(cedula);
            if(usuarioResultado != null){
                return usuarioResultado;
            }
            else{
                throw new NoResultException("No se encontraron datos del usuario");
            }
        }catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    @Override
    public void editarUsuario(UsuarioPOJO usuarioEditar) throws ExcepcionGenerica {
         try{
                usuarioDB.editarUsuario(usuarioEditar); 
                
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
        
    }

   
}


