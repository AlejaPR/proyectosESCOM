/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.ConditionLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.ConditionP;
import com.mycompany.modulodocumental.view.ConditionView;
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
@Path("condition")
public class ConditionS {

    @EJB
    private ConditionLogicFacadeLocal conditionLogicFacade;

    /**
     * this method returns the list of conditions with active status
     *
     * @param idD
     * @return
     */
    @GET
    @Path("/list/{idD}")
    public Response listCondition(@PathParam("idD") int idD) {
        try {
            List<ConditionP> data = conditionLogicFacade.listCondition(idD);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * this method returns a condition specified by the id
     *
     * @param id
     * @return
     */
    @GET
    @Path("/getCondition/{id}")
    public Response getConditionId(@PathParam("id") int id) {
        try {
            ConditionP data = conditionLogicFacade.getConditionId(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * this method returns a list of conditions with the current progress
     *
     * @param idP
     * @return
     */
    @GET
    @Path("/listPercentage/{idP}")
    public Response listConditionPercentage(@PathParam("idP") int idP) {
        try {
            List<ConditionView> data = conditionLogicFacade.listConditionPercentage(idP);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * this method adds a new condition to database
     *
     * @param con
     * @return
     */
    @POST
    @Path("/add")
    public Response addCondition(ConditionP con) {
        try {
            conditionLogicFacade.addCondition(con);
            JsonObject rest = Json.createObjectBuilder().add("data", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    @PUT
    @Path("/edit")
    public Response editCondition(ConditionP con) {
        try {
            conditionLogicFacade.editCondition(con);
            JsonObject rest = Json.createObjectBuilder().add("data", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/disable/{id}")
    public Response disableCondition(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            conditionLogicFacade.disableCondition(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("data", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @PUT
    @Path("/approve/{id}")
    public Response approveCondition(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            conditionLogicFacade.approveCondition(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("data", "approve").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }
}
