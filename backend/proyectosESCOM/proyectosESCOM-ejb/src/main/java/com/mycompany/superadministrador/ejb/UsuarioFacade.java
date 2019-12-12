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
    
    public List<Usuario> consultaDatosExistentes(String correo, int idDocumento) {
        TypedQuery<Usuario> consultaDatos = em.createNamedQuery("consultarExistencia", Usuario.class);
        consultaDatos.setParameter("correo", correo);
        consultaDatos.setParameter("numeroDocumento", idDocumento);
        return consultaDatos.getResultList();
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

    public List<ActividadPOJO> consultarActividadesUsuario(int idUsuario) {
        TypedQuery<Actividad> consultaActividadesUsuario = em.createNamedQuery("consultaActividades", Actividad.class);
        consultaActividadesUsuario.setParameter("idUsuario", idUsuario);
        List<ActividadPOJO> actividadesPOJO = new ArrayList<>();
        for (Actividad a : consultaActividadesUsuario.getResultList()) {
            ActividadPOJO ap = new ActividadPOJO();
            ap.setNombre(a.getNombreActividad());
            actividadesPOJO.add(ap);
        }
        return actividadesPOJO;
    }

    @Override
    public void registrarUsuario(UsuarioPOJO usuario) {
        String contrasena = Seguridad.generarHash(usuario.getContrasena());
        usuario.setContrasena(contrasena);
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

    }
}
