/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aleja
 */
@javax.enterprise.context.RequestScoped
@Path("bitacora")
public class BitacoraServicio {
    
    @EJB
    LogicaBitacoraFacadeLocal bitacoraLogica;

    private final Respuesta respuesta = new Respuesta();

    public BitacoraServicio() {
        
    }
    
    /**
     * Servicio que realiza consulta a la bitacora
     *
     * @param reporte
     * @return  *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/consultar")
    public Response consultar(ReportePOJO reporte) {
        try {
            List<DatosSolicitudPOJO> consultaBitacora = new ArrayList();
            consultaBitacora= bitacoraLogica.consultar(reporte);
            
            return Response.status(Response.Status.OK).entity(consultaBitacora).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("No se encontraron resultados con los parametros ingresados");
            return Response.status(Response.Status.NOT_FOUND).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    

    
}
