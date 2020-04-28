package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.DocumentVersionLogicLocal;
import com.mycompany.modulodocumental.pojo.DocumentVersionP;
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
 * Class in charge of managing all the services related to the document version
 * entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("documentVersion")
public class DocumentVersionS {

    /**
     * Document version logical interface injection
     */
    @EJB
    private DocumentVersionLogicLocal documentVersionLogicFacade;

    /**
     * Service to list current versions
     *
     * @param id
     * @return
     */
    @GET
    @Path("/listCurrent/{id}")
    public Response getListCurrent(@PathParam("id") int id) {
        try {
            List<DocumentVersionP> data = documentVersionLogicFacade.getListCurrent(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to list old versions
     *
     * @param id
     * @return
     */
    @GET
    @Path("/listOld/{id}")
    public Response getListOld(@PathParam("id") int id) {
        try {
            List<DocumentVersionP> data = documentVersionLogicFacade.getListOld(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to add a version of the document
     *
     * @param version
     * @return
     */
    @POST
    @Path("/add")
    public Response add(DocumentVersionP version) {
        try {
            documentVersionLogicFacade.add(version);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.CREATED).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

}
