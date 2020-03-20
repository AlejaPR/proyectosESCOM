package com.mycompany.superadministrador.services;
import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaConfiguracionFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Clase encargada de manejar todos los servicios referente a la entidad configuracion
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("configuracion")
public class ConfiguracionServicio {
    
    /**Inyeccion de la interfaz logica configuracion**/
    @EJB
    LogicaConfiguracionFacadeLocal configuracionLogica;

    /**Variable para manejo de mensajes**/
    private final Respuesta respuesta = new Respuesta();
    
    /**Constructor vacio de la clase**/
    public ConfiguracionServicio() {
    }
    
    
    /**
     * Servicio que registra la configuracion
     *
     * @param configuracion
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editarConfiguracion")
    public Response registrarConfiguracion(ConfiguracionPOJO configuracion) {
        try {
            configuracionLogica.registrarConfiguracion(configuracion);
            respuesta.setRespuesta("Configuracion registrada");
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
     * Servicio que lista la configuracion de entorno
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarEntorno")
    public Response listarEntorno() {
        try {
            List<ConfiguracionPOJO> listaEntorno = configuracionLogica.listarEntorno();
            return Response.status(Response.Status.OK).entity(listaEntorno).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * Servicio que lista la configuracion de inicio
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarInicio")
    public Response listarInicio() {
        try {
            List<ConfiguracionPOJO> listaInicio = configuracionLogica.listarInicio();
            return Response.status(Response.Status.OK).entity(listaInicio).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * Servicio que lista la configuracion completa
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarCompleta")
    public Response configuracionCompleta() {
        try {
            List<ConfiguracionPOJO> listaEntorno = configuracionLogica.listarConfiguracionCompleta();
            return Response.status(Response.Status.OK).entity(listaEntorno).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
