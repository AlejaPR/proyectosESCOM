package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.ProcessLogicLocal;
import com.mycompany.modulodocumental.pojo.ProcessP;
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
 * Class in charge of managing all the services related to the process entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("process")
public class ProcessS {

    /**
     * process logical interface injection
     */
    @EJB
    private ProcessLogicLocal processLogicFacadeLocal;

    /**
     * Service to list processes
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<ProcessP> data = processLogicFacadeLocal.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to obtain a process
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            ProcessP data = processLogicFacadeLocal.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to add a process
     *
     * @param pro
     * @return
     */
    @POST
    @Path("/add")
    public Response add(ProcessP pro) {
        try {
            processLogicFacadeLocal.add(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit a process
     *
     * @param pro
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(ProcessP pro) {
        try {
            processLogicFacadeLocal.edit(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to disable a process
     *
     * @param id
     * @param dataR
     * @return
     */
    @PUT
    @Path("/disable/{id}")
    public Response disable(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            processLogicFacadeLocal.disable(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
