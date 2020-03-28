/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.DocumentVersionLogicFacadeLocal;
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
 *
 * @author HASHY
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("documentVersion")
public class DocumentVersionS {

    @EJB
    private DocumentVersionLogicFacadeLocal documentVersionLogicFacade;

    @GET
    @Path("/listCurrent/{id}")
    public Response listCurrentVersions(@PathParam("id") int id) {
        try {
            List<DocumentVersionP> data = documentVersionLogicFacade.listCurrentVersions(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    @GET
    @Path("/listOld/{id}")
    public Response listOldVersions(@PathParam("id") int id) {
        try {
            List<DocumentVersionP> data = documentVersionLogicFacade.listOldVersions(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    @POST
    @Path("/add")
    public Response addVersion(DocumentVersionP version) {
        try {
            documentVersionLogicFacade.addVersion(version);
            JsonObject rest = Json.createObjectBuilder().add("data", "add").build();
            return Response.status(Response.Status.CREATED).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }
    
    
}
