package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaActividadFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de manejar todos los servicios referente a las actividades
 *
 * @author jeison gaona - alejandra pabon
 */
@javax.enterprise.context.RequestScoped
@Path("actividad")
public class ActividadServicio {
    
    @EJB
    LogicaActividadFacadeLocal actividadLogica;

    private final Respuesta respuesta = new Respuesta();
    
    public ActividadServicio() {
    }

    /**
     * Servicio que lista las actividades registradas
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividades")
    public Response listarActividades() {
        try {
            List<ActividadPOJO> listaActividades = actividadLogica.devolverActividades();
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * Servicio que registra actividades
     *
     * @param actividad
     * @return  *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrarActividad")
    public Response registrarActividad(ActividadPOJO actividad) {
        try {
            actividadLogica.registrarActividad(actividad);
            respuesta.setRespuesta("Actividad registrada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
     /**
     * Servicio que permite la edicion de actividades
     *
     * 
     * @param actividadEditar
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/editarActividad")
    public Response editarUsuario(ActividadPOJO actividadEditar) {
        try {
            actividadLogica.editarActividad(actividadEditar);
            respuesta.setRespuesta("Actividad modificada correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
     /**
     * Servicio que permite la suspencion/activacion de la actividad
     *
     * 
     * @param idActividad
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstadoActividad/{idActividad}")
    public Response cambiarEstadoActividad(@PathParam("idActividad") int idActividad) {
        try {
            actividadLogica.cambiarEstadoActividad(idActividad);
            respuesta.setRespuesta("Estado de actividad modificado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
