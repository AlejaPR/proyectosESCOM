package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import javax.ejb.EJB;
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
    UsuarioFacadeLocal usuarioFacade;
    
    public Login() {
    }

    /**
     * Servicio que realiza la autenticacion del usuario
     * @param correo
     * @param contrasena
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{correo}/{contrasena}")
    public Response login(@PathParam("correo") String correo,@PathParam("contrasena") String contrasena) {
        //        sesionesBean.suma();
        UsuarioPOJO usuario = usuarioFacade.loginUsuario(correo,contrasena);
        Respuesta respuesta=new Respuesta();
        try {
            if (usuario != null) {
                return Response.status(Response.Status.OK).entity(usuario).build();
            } else {
                respuesta.setRespuesta("Credenciales incorrectas");
                return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
            }
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    } 
}
