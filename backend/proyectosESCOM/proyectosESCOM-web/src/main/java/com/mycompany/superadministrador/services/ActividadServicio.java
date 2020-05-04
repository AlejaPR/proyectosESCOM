package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
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
 * Clase encargada de manejar todos los servicios referente a la entidad
 * actividad
 *
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("actividades")
public class ActividadServicio {

    /**
     * Inyeccion de la interfaz logica actividad*
     */
    @EJB
    LogicaActividadFacadeLocal actividadLogica;

    /**
     * Variable para el manejo de mensajes*
     */
    private final Respuesta respuesta = new Respuesta();

    /**
     * Constructor vacio de la clase*
     */
    public ActividadServicio() {
    }

    /**
     * Servicio que lista las actividades registradas
     *
     * @param cantidadDatos
     * @param paginaActual
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar/{cantidadDatos}/{paginaActual}")
    public Response listar(@PathParam("cantidadDatos") int cantidadDatos, @PathParam("paginaActual") int paginaActual) {
        try {
            List<ActividadPOJO> listaActividades = actividadLogica.devolverActividades(cantidadDatos, paginaActual);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que filtra las actividades registradas
     * @param palabraBusqueda
     * @param cantidadDatos
     * @param paginaActual
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/filtrar/{palabraBusqueda}/{cantidadDatos}/{paginaActual}")
    public Response filtrar(@PathParam("palabraBusqueda") String palabraBusqueda, @PathParam("cantidadDatos") int cantidadDatos, @PathParam("paginaActual") int paginaActual) {
        try {
            List<ActividadPOJO> listaActividades = actividadLogica.filtrarActividades(palabraBusqueda,cantidadDatos, paginaActual);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
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
    @Path("/filtrarCantidad/{palabraBusqueda}")
    public Response filtrarCantidad(@PathParam("palabraBusqueda") String palabraBusqueda) {
        try {
            int cantidad=0;
            cantidad= actividadLogica.filtrarActividadesCantidad(palabraBusqueda);
            return Response.status(Response.Status.OK).entity(cantidad).build();
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
    @Path("/cantidad")
    public Response cantidad() {
        try {
            int cantidad=0;
            cantidad= actividadLogica.cantidadActividades();
            return Response.status(Response.Status.OK).entity(cantidad).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que registra actividades
     *
     * @param actividad
     * @return *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrar")
    public Response registrar(ActividadPOJO actividad) {
        try {
            ActividadPOJO actividadR = new ActividadPOJO();
            actividadR = actividadLogica.registrarActividad(actividad);
            respuesta.setRespuesta("Actividad registrada");
            return Response.status(Response.Status.OK).entity(actividadR).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @return *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/editar")
    public Response editar(ActividadPOJO actividadEditar) {
        try {
            actividadLogica.editarActividad(actividadEditar);
            respuesta.setRespuesta("Actividad modificada correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que permite la suspension/activacion de la actividad
     *
     *
     * @param idActividad
     * @return *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstado/{idActividad}")
    public Response cambiarEstado(@PathParam("idActividad") int idActividad, DatosSolicitudPOJO datosSolicitud) {
        try {
            actividadLogica.cambiarEstadoActividad(idActividad, datosSolicitud);
            respuesta.setRespuesta("Estado de actividad modificado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los datos de una actividad en especifico Recibe el id
     *
     * @param idActividad
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarEspecifica/{idActividad}")
    public Response listarEspecifica(@PathParam("idActividad") int idActividad) {
        try {
            ActividadPOJO actividadDatos = actividadLogica.traerActividadEspecifica(idActividad);
            return Response.status(Response.Status.OK).entity(actividadDatos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
