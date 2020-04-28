package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.AnnexVersionLogicLocal;
import com.mycompany.modulodocumental.pojo.AnnexVersionP;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
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
 * Class in charge of managing all the services related to the annex version
 * entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("annexVersion")
public class AnnexVersionS {

    /**
     * Annex version logical interface injection
     */
    @EJB
    private AnnexVersionLogicLocal annexVersionLogicFacade;

    /**
     * Service that lists the versions of the annex
     * @param id
     * @return 
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<AnnexVersionP> data = annexVersionLogicFacade.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that adds an annex version
     * @param annexV
     * @return 
     */
    @POST
    @Path("/add")
    public Response add(AnnexVersionP annexV) {
        try {
            annexVersionLogicFacade.add(annexV);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service that removes an annex version
     * @param id
     * @param dataR
     * @return 
     */
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            annexVersionLogicFacade.delete(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "delete").build();
            return Response.status(Response.Status.NO_CONTENT).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
