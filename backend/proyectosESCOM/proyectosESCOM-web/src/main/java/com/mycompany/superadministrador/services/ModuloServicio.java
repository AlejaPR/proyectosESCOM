package com.mycompany.superadministrador.services;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaModuloFacadeLocal;
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
 * Clase encargada de manejar todos los servicios referente a la entidad modulo
 *
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("modulos")
public class ModuloServicio {

    /**Inyeccion de la interfaz logica modulo**/
    @EJB
    LogicaModuloFacadeLocal moduloLogica;

    /**Variable para manejo de mensajes**/
    private final Respuesta respuesta = new Respuesta();

    /**Constructor vacio de la clase**/
    public ModuloServicio() {
    }

    /**
     * Servicio que registra modulos
     *
     * @param modulo
     * @return *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrar")
    public Response registrar(ModuloPOJO modulo) {
        try {
            moduloLogica.registrarModulo(modulo);
            respuesta.setRespuesta("Modulo registrado");
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
     * Servicio que lista los modulos registrados
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public Response listar() {
        try {
            List<ModuloPOJO> listaModulos = moduloLogica.devolverModulos();
            return Response.status(Response.Status.OK).entity(listaModulos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los datos de un modulo especifico consultado con el id
     *
     * @param idModulo
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/datos/{idModulo}")
    public Response listarModuloId(@PathParam("idModulo") int idModulo) {
        try {
            ModuloPOJO moduloDatos = moduloLogica.traerModuloId(idModulo);
            return Response.status(Response.Status.OK).entity(moduloDatos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que permite la edicion de modulos
     *
     * @param idModulo
     * @param moduloEditar
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/editar/{idModulo}")
    public Response editar(@PathParam("idModulo") int idModulo, ModuloPOJO moduloEditar) {
        try {
            moduloLogica.editarModulo(idModulo, moduloEditar);
            respuesta.setRespuesta("Modulo modificado correctamente");
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
     * Servicio que habilita o deshabilita un modulo recibe como parametro el id
     *
     * @param idModulo
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstado/{idModulo}")
    public Response cambiarEstado(@PathParam("idModulo") int idModulo,DatosSolicitudPOJO datosSolicitud) {
        try {
            moduloLogica.cambiarEstadoModulo(idModulo,datosSolicitud);
            respuesta.setRespuesta("Estado de modulo cambiado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista las actividades de un modulo especifico, recibe como
     * parametro el id
     *
     * @param idModulo
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividades/{idModulo}")
    public Response listarActividades(@PathParam("idModulo") int idModulo) {
        try {
            List<ActividadPOJO> listaActividadesM = moduloLogica.listarActividadesModulo(idModulo);
            return Response.status(Response.Status.OK).entity(listaActividadesM).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que permite la suspension/habilitacion de una actividad de un
     * modulo en especifico
     *
     *
     * @param listaActividad
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstadoActividad")
    public Response cambiarEstadoActividad(List<ActividadPOJO> listaActividad) {
        try {
            moduloLogica.cambiarEstadoActividadModulo(listaActividad);
            respuesta.setRespuesta("Estado de actividad del modulo cambiado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
