/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.AnnexVersionLogicFacadeLocal;
import com.mycompany.modulodocumental.pojo.AnnexVersionP;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("annexVersion")
public class AnnexVersionS {

    @EJB
    private AnnexVersionLogicFacadeLocal annexVersionLogicFacade;

    @GET
    @Path("/list/{id}")
    public Response listAnnexVersion(@PathParam("id") int id) {
        try {
            List<AnnexVersionP> data = annexVersionLogicFacade.listAnnexVersion(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    @POST
    @Path("/add")
    public Response addAnnexVersion(AnnexVersionP annexV) {
        try {
            annexVersionLogicFacade.addAnnexVersion(annexV);
            JsonObject rest = Json.createObjectBuilder().add("data", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    @DELETE
    @Path("/delete/{id}")
    public Response deleteVersion(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            annexVersionLogicFacade.deleteVersion(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("data", "delete").build();
            return Response.status(Response.Status.NO_CONTENT).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
