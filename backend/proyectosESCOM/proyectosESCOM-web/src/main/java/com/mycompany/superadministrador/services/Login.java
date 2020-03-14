package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase que administra el servicio para login
 *
 * @author jeison gaona - alejandra pabon
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
            if (usuario != null) {
                return Response.status(Response.Status.OK).entity(usuario).build();
            } else {
                respuesta.setRespuesta("No se encontro ninguna credencial que coincida");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
            }

        } catch (ExcepcionGenerica e) {
            System.out.println("excep es" + e.getMessage());
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
            respuesta.setRespuesta("Ocurrio un error al eliminar el token");
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }

    }

}
