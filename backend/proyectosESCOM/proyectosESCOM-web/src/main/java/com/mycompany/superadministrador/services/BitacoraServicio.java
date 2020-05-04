package com.mycompany.superadministrador.services;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Clase encargada de manejar todos los servicios referente a la entidad bitacora
 *
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("bitacora")
public class BitacoraServicio {

    /**Inyeccion de la interfaz logica bitacora**/
    @EJB
    LogicaBitacoraFacadeLocal bitacoraLogica;

    /**Variable para el manejo de mensajes**/
    private final Respuesta respuesta = new Respuesta();

    /**Constructor vacio de la clase*/    
    public BitacoraServicio() {

    }

    
    /**
     * 
     * @param reporte
     * @param cantidadDeDatos
     * @param paginaActual
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consultar/{cantidadDeDatos}/{paginaActual}")
    public Response consultar(ReportePOJO reporte,@PathParam("cantidadDeDatos") int cantidadDeDatos,@PathParam("paginaActual") int paginaActual) {
        try {
            List<DatosSolicitudPOJO> consultaBitacora = new ArrayList();
            consultaBitacora = bitacoraLogica.consultar(reporte,cantidadDeDatos,paginaActual);
            if (!consultaBitacora.isEmpty()) {
                return Response.status(Response.Status.OK).entity(consultaBitacora).build();
            }else{
                respuesta.setRespuesta("No se encontraron reportes");
                return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
            }
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consultar")
    public Response consultarBitacoraCantidadDatos(ReportePOJO reporte) {
        try {
            int cantidad = bitacoraLogica.cantidadDeDatos(reporte);
            if (cantidad!=0) {
                return Response.status(Response.Status.OK).entity(cantidad).build();
            }else{
                respuesta.setRespuesta("No se encontraron reportes");
                return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
            }
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consultarTotal/")
    public Response consultarBitacoraTotal(ReportePOJO reporte) {
        try {
            List<DatosSolicitudPOJO> consultaBitacora = new ArrayList();
            consultaBitacora = bitacoraLogica.consultarBitacoraCompleta(reporte);
            if (!consultaBitacora.isEmpty()) {
                return Response.status(Response.Status.OK).entity(consultaBitacora).build();
            }else{
                respuesta.setRespuesta("No se encontraron reportes");
                return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
            }
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

}
