package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.ConditionLogicLocal;
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
 * Class in charge of managing all the services related to the codition entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("condition")
public class ConditionS {

    /**
     * Condition logical interface injection
     */
    @EJB
    private ConditionLogicLocal conditionLogicFacade;

    /**
     * Service to list the conditions
     *
     * @param idD
     * @return
     */
    @GET
    @Path("/list/{idD}")
    public Response getList(@PathParam("idD") int idD) {
        try {
            List<ConditionP> data = conditionLogicFacade.getList(idD);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to obtain a condition
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response getConditionId(@PathParam("id") int id) {
        try {
            ConditionP data = conditionLogicFacade.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to list the conditions with their percentage
     *
     * @param idP
     * @return
     */
    @GET
    @Path("/listPercentage/{idP}")
    public Response getListPercentage(@PathParam("idP") int idP) {
        try {
            List<ConditionView> data = conditionLogicFacade.getListPercentage(idP);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to add a condition
     *
     * @param con
     * @return
     */
    @POST
    @Path("/add")
    public Response add(ConditionP con) {
        try {
            conditionLogicFacade.add(con);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to edit a condition
     *
     * @param con
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(ConditionP con) {
        try {
            conditionLogicFacade.edit(con);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to disable a condition
     *
     * @param id
     * @param dataR
     * @return
     */
    @PUT
    @Path("/disable/{id}")
    public Response disable(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            conditionLogicFacade.disable(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to approve a condition
     *
     * @param id
     * @param dataR
     * @return
     */
    @PUT
    @Path("/approve/{id}")
    public Response approve(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            conditionLogicFacade.approve(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "approve").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }
}
