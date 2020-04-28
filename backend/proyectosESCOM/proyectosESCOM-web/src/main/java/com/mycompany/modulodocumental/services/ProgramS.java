package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.ProgramLogicLocal;
import com.mycompany.modulodocumental.pojo.ProgramP;
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
 * Class in charge of managing all the services related to the program entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("program")
public class ProgramS {

    /**
     * Program logical interface injection
     */
    @EJB
    ProgramLogicLocal programLogicFacade;

    /**
     * Service to list programs
     *
     * @return
     */
    @GET
    @Path("/list")
    public Response getList() {
        try {
            List<ProgramP> data = programLogicFacade.getList();
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to obtain a program
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            ProgramP data = programLogicFacade.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service to add a program
     *
     * @param pro
     * @return
     */
    @POST
    @Path("/add")
    public Response add(ProgramP pro) {
        try {
            programLogicFacade.add(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit a program
     *
     * @param pro
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(ProgramP pro) {
        try {
            programLogicFacade.edit(pro);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to disable a program
     *
     * @param id
     * @param dataS
     * @return
     */
    @PUT
    @Path("/disable/{id}")
    public Response disable(@PathParam("id") int id, DatosSolicitudPOJO dataS) {
        try {
            programLogicFacade.disable(id, dataS);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

}
