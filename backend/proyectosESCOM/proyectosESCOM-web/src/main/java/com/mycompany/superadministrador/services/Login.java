package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.GestorBitacora;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alejandra Pabon Rodriguez 461 215 234 Clase servicio del login
 */
@javax.enterprise.context.RequestScoped
@Path("login")
public class Login {

    /**
     * LLamado del bean de usuario
     */
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    private Respuesta respuesta = new Respuesta();

    public Login() {
    }

    /**
     * Servicio que realiza la autenticacion del usuario
     *
     * @param correo
     * @param contrasena
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{correo}/{contrasena}")
    public Response login(@PathParam("correo") String correo, @PathParam("contrasena") String contrasena) {
        try {
            UsuarioPOJO usuario = usuarioLogica.loginUsuario(correo, contrasena);
            return Response.status(Response.Status.OK).entity(usuario).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Credenciales incorrectas");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    @DELETE
    @Path("/{token}")
    public Response cerrarSesion(@PathParam("token") String token) {
        try {
            usuarioLogica.cerrarSesion(token);
            respuesta.setRespuesta("Sesion cerrada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ocurrio un error al eliminar el token");
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }

    }

}