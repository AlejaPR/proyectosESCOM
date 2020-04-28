package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.RelationalClassLogicLocal;
import com.mycompany.modulodocumental.pojo.RelationalClassP;
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
@Path("relationalClass")
public class RelationalClassS {

    /**
     * Relational class logical interface injection
     */
    @EJB
    private RelationalClassLogicLocal relationalClassLogic;

    /**
     * Service to list relational classes
     *
     * @param id
     * @param table
     * @return
     */
    @GET
    @Path("/list/{id}/{table}")
    public Response getList(@PathParam("id") int id, @PathParam("table") String table) {
        try {
            List<RelationalClassP> data = relationalClassLogic.getList(id, table);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to add a relational class
     *
     * @param relation
     * @return
     */
    @POST
    @Path("/add")
    public Response add(RelationalClassP relation) {
        try {
            String data = relationalClassLogic.add(relation);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to remove a relational class
     *
     * @param relation
     * @return
     */
    @PUT
    @Path("/delete")
    public Response delete(RelationalClassP relation) {
        try {
            String data = relationalClassLogic.delete(relation);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
