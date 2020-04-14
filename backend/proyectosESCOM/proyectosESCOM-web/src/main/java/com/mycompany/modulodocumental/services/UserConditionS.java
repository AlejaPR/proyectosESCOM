/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;


import com.mycompany.modulodocumental.interfaces.logic.UserConditionLogicLocal;
import com.mycompany.modulodocumental.pojo.UserConditionP;
import com.mycompany.modulodocumental.view.ConditionView;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
@Path("userCondition")
public class UserConditionS {

    @EJB
    private UserConditionLogicLocal userConditionLogicFacade;

    @GET
    @Path("/list/{idP}")
    public Response getList(@PathParam("idP") int idP, @HeaderParam("TokenAuto") String token) {
        try {
            List<ConditionView> data = userConditionLogicFacade.getList(token, idP);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    @GET
    @Path("/listUsers")
    public Response listUsers() {
        try {
            List<UsuarioPOJO> data = userConditionLogicFacade.listUsers();
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    @GET
    @Path("/listUsersC/{id}")
    public Response listUsersCondition(@PathParam("id") int id) {
        try {
            List<UsuarioPOJO> data = userConditionLogicFacade.listUsersCondition(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    @POST
    @Path("/associate")
    public Response associate(UserConditionP userCondition){
        try {
            userConditionLogicFacade.associate(userCondition);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "associate").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    @POST
    @Path("/delete")
    public Response delete(UserConditionP userCondition){
        try {
            userConditionLogicFacade.delete(userCondition);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "delete").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
    
    
 }
