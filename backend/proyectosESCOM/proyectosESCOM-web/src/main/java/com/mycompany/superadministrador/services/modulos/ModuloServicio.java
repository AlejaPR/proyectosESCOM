/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services.modulos;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaModuloFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aleja
 */
@javax.enterprise.context.RequestScoped
@Path("modulo")
public class ModuloServicio {
    
    @EJB
    LogicaModuloFacadeLocal moduloLogica;

    private final Respuesta respuesta = new Respuesta();

    public ModuloServicio() {
    }
    
    /**Servicio que lista los modulos registrados
     * @return  **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarModulos")
    public Response listarModulos() {
        try {
            List<ModuloPOJO> listaModulos = moduloLogica.devolverModulos();
            return Response.status(Response.Status.OK).entity(listaModulos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
     /**Servicio que lista las actividades de un modulo especifico, recibe como parametro el id 
     * 
     * @param idModulo
     * @return  **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividadesModulo/{idModulo}")
    public Response listarActividadesModulo(@PathParam("idModulo") int idModulo) {
        try {
            List<ActividadPOJO> listaActividadesM = moduloLogica.listarActividadesModulo(idModulo);
            return Response.status(Response.Status.OK).entity(listaActividadesM).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
