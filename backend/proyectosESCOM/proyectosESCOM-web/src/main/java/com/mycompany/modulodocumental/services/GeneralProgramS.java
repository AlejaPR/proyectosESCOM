/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.GeneralProgramLogicLocal;
import com.mycompany.modulodocumental.pojo.GeneralProgramP;
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
@Path("generalProgram")
public class GeneralProgramS {
    
    @EJB
    private GeneralProgramLogicLocal generalProgramLogic ;
    
    @GET
    @Path("/list")
    public Response getList() {
        try {
            List<GeneralProgramP> data = generalProgramLogic.getList();
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            GeneralProgramP data = generalProgramLogic.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @POST
    @Path("/add")
    public Response add(GeneralProgramP gen) {
        try {
            generalProgramLogic.add(gen);
            JsonObject rest = Json.createObjectBuilder().add("data", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/edit")
    public Response edit(GeneralProgramP gen) {
        try {
            generalProgramLogic.edit(gen);
            JsonObject rest = Json.createObjectBuilder().add("data", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }
    
    @PUT
    @Path("/disable")
    public Response disable(GeneralProgramP gen) {
        try {
            generalProgramLogic.disable(gen);
            JsonObject rest = Json.createObjectBuilder().add("data", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }
    
}
