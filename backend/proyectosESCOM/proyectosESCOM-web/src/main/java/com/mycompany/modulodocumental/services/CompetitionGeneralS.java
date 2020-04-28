package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.CompetitionGeneralLogicLocal;
import com.mycompany.modulodocumental.pojo.CompetitionGeneralP;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
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
 * Class in charge of managing all the services related to the competition
 * general entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("competitionGeneral")
public class CompetitionGeneralS {

    /**
     * Competition general logical interface injection
     */
    @EJB
    private CompetitionGeneralLogicLocal competitionGeneralLogic;

    /**
     * Service to list general competences
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<CompetitionGeneralP> data = competitionGeneralLogic.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to obtain general competence
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            CompetitionGeneralP data = competitionGeneralLogic.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to add general competence
     *
     * @param comG
     * @return
     */
    @POST
    @Path("/add")
    public Response add(CompetitionGeneralP comG) {
        try {
            competitionGeneralLogic.add(comG);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "addG").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit a general competence
     *
     * @param comG
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(CompetitionGeneralP comG) {
        try {
            competitionGeneralLogic.edit(comG);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "editG").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to eliminate general competition
     *
     * @param comG
     * @return
     */
    @PUT
    @Path("/delete")
    public Response delete(CompetitionGeneralP comG) {
        try {
            competitionGeneralLogic.delete(comG);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "deleteG").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
