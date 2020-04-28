package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.CommentaryLogicLocal;
import com.mycompany.modulodocumental.pojo.CommentaryP;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class in charge of managing all the services related to the commentary entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("commentary")
public class CommentaryS {

    /**
     * Commentary logical interface injection
     */
    @EJB
    private CommentaryLogicLocal commentaryLogicFacade;

    /**
     * Service to add a comment
     *
     * @param commentary
     * @return
     */
    @POST
    @Path("/add")
    public Response add(CommentaryP commentary) {
        try {
            commentaryLogicFacade.add(commentary);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.CREATED).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to list comments
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<CommentaryP> data = commentaryLogicFacade.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to delete a comment
     *
     * @param id
     * @param datosS
     * @return
     */
    @POST
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id, DatosSolicitudPOJO datosS) {
        try {
            commentaryLogicFacade.delete(id, datosS);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "delete").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
