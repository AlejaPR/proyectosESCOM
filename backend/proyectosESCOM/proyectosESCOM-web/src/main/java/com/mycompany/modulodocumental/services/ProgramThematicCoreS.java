package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.ProgramThematicCoreLogicLocal;
import com.mycompany.modulodocumental.pojo.ProgramThematicCoreP;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
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
 * Class in charge of managing all the services related to the program thematic
 * core entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("programThematicCore")
public class ProgramThematicCoreS {

    /**
     * program thematic core logical interface injection
     */
    @EJB
    private ProgramThematicCoreLogicLocal programThematicCoreLogic;

    /**
     * Service to list the thematic core of the program
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<ProgramThematicCoreP> data = programThematicCoreLogic.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to obtain a thematic core of the program
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            ProgramThematicCoreP data = programThematicCoreLogic.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to add a thematic core of the program
     *
     * @param pro
     * @return
     */
    @POST
    @Path("/add")
    public Response add(ProgramThematicCoreP pro) {
        try {
            programThematicCoreLogic.add(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit a thematic core of the program
     *
     * @param pro
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(ProgramThematicCoreP pro) {
        try {
            programThematicCoreLogic.edit(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to remove a thematic core from the program
     *
     * @param id
     * @param dataS
     * @return
     */
    @PUT
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id, DatosSolicitudPOJO dataS) {
        try {
            programThematicCoreLogic.delete(id, dataS);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "delete").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
}
