package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de manejar el servicio de login
 *
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("login")
public class Login {

    /**
     * Inyeccion de la interfaz logica usuario*
     */
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    /**
     * Variable para el manejo de mensajes*
     */
    private Respuesta respuesta = new Respuesta();

    /**
     * Constructor vacio de la clase*
     */
    public Login() {
    }

    /**
     * Servicio que realiza el ingreso de usuario
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
            if (usuario != null) {
                return Response.status(Response.Status.OK).entity(usuario).build();
            } else {
                respuesta.setRespuesta("No se encontro ninguna credencial que coincida");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
            }

        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que realiza el cierre de sesion
     *
     * @param token
     * @return
     */
    @DELETE
    @Path("/cerrarSesion/{token}")
    public Response cerrarSesion(@PathParam("token") String token) {
        try {
            usuarioLogica.cerrarSesion(token);
            respuesta.setRespuesta("Sesion cerrada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarClaveExterna/{nuevaContrasena}/{token}")
    public Response recuperarContrasenaExterna(@PathParam("nuevaContrasena") String nuevaContrasena, @PathParam("token") String token, DatosSolicitudPOJO datosSolicitud) {
        try {
            usuarioLogica.cambiarContrasenaExterna(nuevaContrasena, token, datosSolicitud);
            respuesta.setRespuesta("Contrasena modificada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/recuperarContrasena/{correo}")
    public Response generarTokenLink(@PathParam("correo") String correo) {
        try {
            String enviado = usuarioLogica.generarTokenRecuperarContrasena(correo);
            return Response.status(Response.Status.OK).entity(enviado).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/validarTokenRecuperarContrasena/{token}")
    public Response validarTokenLink(@PathParam("token") String token) {
        try {
            String correoUsuario = usuarioLogica.validarTokenRecuperarContrasena(token);
            return Response.status(Response.Status.OK).entity(correoUsuario).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
                respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

}
