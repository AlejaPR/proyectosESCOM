/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.DocumentLogicFacadeLocal;
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
 *
 * @author HASHY
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("document")
public class DocumentS {

    @EJB
    DocumentLogicFacadeLocal documentLogicFacade;

    @GET
    @Path("/getIdDocument/{id}")
    public Response getIdDocument(@PathParam("id") int id) {
        try {
            int data = documentLogicFacade.getIdDocument(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @GET
    @Path("/getDocument/{id}")
    public Response getDocumentId(@PathParam("id") int id) {
        try {
            DocumentP data = documentLogicFacade.getDocumentId(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @GET
    @Path("/list")
    public Response listDocument() {
        try {
            List<DocumentP> data = documentLogicFacade.listDocument();
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @GET
    @Path("/documentIdEdit/{id}")
    public Response documentIdEdit(@PathParam("id") int id) {
        try {
            DocumentP data = documentLogicFacade.documentIdEdit(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @POST
    @Path("/add")
    public Response addDocument(DocumentP doc) {
        try {
            documentLogicFacade.addDocument(doc);
            JsonObject rest = Json.createObjectBuilder().add("data", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/edit")
    public Response editDocument(DocumentP doc) {
        try {
            documentLogicFacade.editDocument(doc);
            JsonObject rest = Json.createObjectBuilder().add("data", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    @PUT
    @Path("/disable/{id}")
    public Response disableDocument(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            documentLogicFacade.disableDocument(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("data", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
