/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.GeneralClassLogicLocal;
import com.mycompany.modulodocumental.pojo.GeneralClassP;
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
 * @author hashy
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("generalClass")
public class GeneralClassS {

    @EJB
    private GeneralClassLogicLocal generalClassLogic;

    @GET
    @Path("/list/{id}/{table}")
    public Response getList(@PathParam("id") int id, @PathParam("id") String table) {
        try {
            List<GeneralClassP> data = generalClassLogic.getList(id, table);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @GET
    @Path("/get/{id}/{table}")
    public Response get(@PathParam("id") int id, @PathParam("table") String table) {
        try {
            GeneralClassP data = generalClassLogic.get(id, table);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @POST
    @Path("/add")
    public Response add(GeneralClassP com) {
        try {
            String data = generalClassLogic.add(com);
            JsonObject rest = Json.createObjectBuilder().add("data", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/edit")
    public Response edit(GeneralClassP com) {
        try {
            String data = generalClassLogic.edit(com);
            JsonObject rest = Json.createObjectBuilder().add("data", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/delete")
    public Response delete(GeneralClassP com) {
        try {
            String data = generalClassLogic.delete(com);
            JsonObject rest = Json.createObjectBuilder().add("data", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
}
