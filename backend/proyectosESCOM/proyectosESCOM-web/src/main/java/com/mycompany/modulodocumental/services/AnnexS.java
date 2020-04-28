package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.AnnexLogicLocal;
import com.mycompany.modulodocumental.pojo.AnnexP;
import com.mycompany.modulodocumental.pojo.SearchAnnP;
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
 * Class in charge of managing all the services related to the annex entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("annex")
public class AnnexS {

    /**
     * Annex logical interface injection
     */
    @EJB
    private AnnexLogicLocal annexLogicFacade;

    /**
     * Service that adds an annex
     *
     * @param annex
     * @return
     */
    @POST
    @Path("/add")
    public Response add(AnnexP annex) {
        try {
            annexLogicFacade.add(annex);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service to edit an attachment
     *
     * @param annex
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(AnnexP annex) {
        try {
            annexLogicFacade.edit(annex);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that disable an annex
     *
     * @param id
     * @param dataR
     * @return
     */
    @PUT
    @Path("/disable/{id}")
    public Response disableAnnex(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            annexLogicFacade.disable(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "disable").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that obtains the list of annexes
     *
     * @param id
     * @return
     */
    @GET
    @Path("/list/{id}")
    public Response getList(@PathParam("id") int id) {
        try {
            List<AnnexP> data = annexLogicFacade.getList(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that gets an annex
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            AnnexP data = annexLogicFacade.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service seeking annexes
     *
     * @param search
     * @return
     */
    @POST
    @Path("/searchAnnexS")
    public Response searchAnnexS(SearchAnnP search) {
        try {
            List<AnnexP> data = annexLogicFacade.searchAnnexS(search);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

}
