package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.DocumentLogicLocal;
import com.mycompany.modulodocumental.pojo.DocumentP;
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
 * Class in charge of managing all the services related to the document entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("document")
public class DocumentS {

    /**
     * Document logical interface injection
     */
    @EJB
    DocumentLogicLocal documentLogicFacade;

    /**
     * Service to obtain the document id
     *
     * @param id
     * @return
     */
    @GET
    @Path("/getIdDocument/{id}")
    public Response getIdDocument(@PathParam("id") int id) {
        try {
            int data = documentLogicFacade.getIdDocument(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to obtain a document
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            DocumentP data = documentLogicFacade.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to list documents
     *
     * @return
     */
    @GET
    @Path("/list")
    public Response getList() {
        try {
            List<DocumentP> data = documentLogicFacade.getList();
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to obtain the document to edit
     *
     * @param id
     * @return
     */
    @GET
    @Path("/documentIdEdit/{id}")
    public Response documentIdEdit(@PathParam("id") int id) {
        try {
            DocumentP data = documentLogicFacade.documentIdEdit(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to add a document
     *
     * @param doc
     * @return
     */
    @POST
    @Path("/add")
    public Response add(DocumentP doc) {
        try {
            documentLogicFacade.add(doc);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit a document
     *
     * @param doc
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(DocumentP doc) {
        try {
            documentLogicFacade.edit(doc);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to disable a document
     *
     * @param id
     * @param dataR
     * @return
     */
    @PUT
    @Path("/disable/{id}")
    public Response disable(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            documentLogicFacade.disable(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
