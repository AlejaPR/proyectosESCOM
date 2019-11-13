/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.google.gson.Gson;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import com.mycompany.superadministrador.seguridad.Sesiones;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aleja
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    SesionesFacadeLocal sesiones;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public UsuarioPOJO busquedaToken(String firma) {
        TypedQuery<Usuario> consulta = em.createNamedQuery("busquedaToken", Usuario.class);
        consulta.setParameter("token", firma);
        Usuario usuarioDB = consulta.getSingleResult();
        UsuarioPOJO usuarioRespuesta = new UsuarioPOJO();
        usuarioRespuesta.setId(usuarioDB.getIdUsuario());
        usuarioRespuesta.setCorreoElectronico(usuarioDB.getCorreoElectronico());
        return usuarioRespuesta;
    }

    public Usuario consultaLogin(String correo, String contrasena) {
        TypedQuery<Usuario> consultaLogin = em.createNamedQuery("consultaLogin", Usuario.class);
        consultaLogin.setParameter("correo", correo);
        consultaLogin.setParameter("contrasena", contrasena);
        return consultaLogin.getSingleResult();
    }

    public int editarToken(String token, int idUsuario) {
        TypedQuery<Usuario> editarToken = em.createNamedQuery("editarToken", Usuario.class);
        editarToken.setParameter("token", token);
        editarToken.setParameter("idUsuario", idUsuario);
        return editarToken.executeUpdate();
    }
    
    public int editarTokenCerrarSesion(String firma, String correo) {
        TypedQuery<Usuario> editarToken = em.createNamedQuery("editarTokenCerrar", Usuario.class);
        editarToken.setParameter("token", firma);
        editarToken.setParameter("correo", correo);
        return editarToken.executeUpdate();
    }
    

    public List<ActividadPOJO> consultarActividadesUsuario(int idUsuario){
        TypedQuery<Actividad> consultaActividadesUsuario = em.createNamedQuery("consultaActividades", Actividad.class);
        consultaActividadesUsuario.setParameter("idUsuario", idUsuario);
        List<ActividadPOJO> actividadesPOJO=new ArrayList<>();
        for(Actividad a : consultaActividadesUsuario.getResultList()){
            ActividadPOJO ap=new ActividadPOJO();
            ap.setNombre(a.getNombreActividad());
            actividadesPOJO.add(ap);
        }
        return actividadesPOJO;
    }

    @Override
    public UsuarioPOJO loginUsuario(String correo, String contrasena) {
        try {
            String contrasenaEncriptada = Seguridad.generarHash(contrasena);
            Usuario usuario = consultaLogin(correo, contrasenaEncriptada);
            if (usuario == null) {
                return null;
            }
            Seguridad token = new Seguridad();
            List<ActividadPOJO> actividad=consultarActividadesUsuario(usuario.getIdUsuario());
            Gson gson=new Gson();
            String actividades=gson.toJson(actividad);
            String tokencin = token.generarToken(usuario,actividades);
            usuario.setToken(Seguridad.desencriptar(tokencin).getFirma());
            editarToken(usuario.getToken(), usuario.getIdUsuario());
            UsuarioPOJO usuarioRespuesta = new UsuarioPOJO();
            usuarioRespuesta.setToken(tokencin);
            validarTokens(tokencin);
            return usuarioRespuesta;
        } catch (Exception e) {
            return null;
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
    
    @Override
    public void cerrarSesion(String token){
        Token tokenDesencriptado=Seguridad.desencriptar(token);
        editarTokenCerrarSesion(" ", tokenDesencriptado.getIssuer());
        sesiones.getMapaSesiones().remove(token);
    }

    @Override
    public void registrarUsuario(UsuarioPOJO usuario) {
        System.out.println("usuario fecha nac" + usuario.getFechaNacimiento());
        String contrasena=Seguridad.generarHash(usuario.getContrasena());
        usuario.setContrasena(contrasena);
        try {
            em.createNativeQuery("INSERT INTO TBL_USUARIO (USR_TOKEN,USR_NUMERODOCUMENTO,USR_APELLIDO,USR_ESTADO,USR_FECHANACIMIENTO,USR_NUMEROINTENTOS,USR_NOMBRE,USR_ULTIMAMODIFICACION,USR_CORREOELECTRONICO,"
                    + "FK_USR_IDTIPODOCUMENTO,USR_CONTRASENA) VALUES (?,?,?,?,?,?,?,?,?,?,?)")
                    .setParameter(1, " ")
                    .setParameter(2, usuario.getNumeroDocumento())
                    .setParameter(3, usuario.getApellido())
                    .setParameter(4, "Activo")
                    .setParameter(5, usuario.getFechaNacimiento())
                    .setParameter(6, 0)
                    .setParameter(7, usuario.getNombre())
                    .setParameter(8, new Date())
                    .setParameter(9, usuario.getCorreoElectronico())
                    .setParameter(10, usuario.getTipoDocumento())
                    .setParameter(11, usuario.getContrasena())
                    .executeUpdate();
        } catch (Exception e) {
            System.out.println("Exception insertando fila usuario " + e);
        }
    }

    /**
     * Funcion encargada de devolver los datos de un usuario segun un token dado
     * @param token
     * @return 
     */
    @Override
    public UsuarioPOJO devolverDatosUsuario(String token) {
        try {
            if (validarToken(token)) {
                Token tokenDesencriptado = Seguridad.desencriptar(token);
                return busquedaToken(tokenDesencriptado.getFirma());
            }else{
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
}
