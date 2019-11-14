package com.mycompany.superadministrador.services;

import com.mycompany.modulodocumental.PruebaEJB;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de manejar todos los servicios referente al usuario
 *
 * @author jeison gaona - alejandra pabon
 */
@javax.enterprise.context.RequestScoped
@Path("usuario")
public class UsuarioServicio {
    
    
    @EJB
    UsuarioFacadeLocal usuarioFacade;

    @EJB(name = "Sesiones")
    SesionesFacadeLocal sesiones;

    @EJB
    PruebaEJB prueba;

    @GET
    @Path("/prueba/{token}")
    public Response prueba(@PathParam("token") String token) {
//        System.out.println("size "+usuarioFacade.consultarActividadesUsuario(8).size());
//        for(Actividad ac :usuarioFacade.consultarActividadesUsuario(8)){
//            System.out.println("Actividad "+ac.getDescripcionActividad());
//        }
        return Response.status(Response.Status.OK).entity(sesiones.getMapaSesiones()).build();

    }
    

    @DELETE
    @Path("/{token}")
    public Response cerrarSesion(@PathParam("token") String token) {
        try{
            usuarioFacade.cerrarSesion(token);
            Respuesta respuesta = new Respuesta("Sesion cerrada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        }catch(Exception e){
            Respuesta respuesta = new Respuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
       

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(UsuarioPOJO usuario) {
        try {
            usuarioFacade.registrarUsuario(usuario);
            Respuesta respuesta = new Respuesta("Usuario registrado");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (Exception e) {
            Respuesta respuesta = new Respuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

}
