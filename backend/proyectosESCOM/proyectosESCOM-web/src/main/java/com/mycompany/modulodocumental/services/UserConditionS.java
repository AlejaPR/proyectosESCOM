/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental.services;


import com.mycompany.modulodocumental.interfaces.UserConditionLogicFacadeLocal;
import com.mycompany.modulodocumental.view.ConditionView;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
    private UserConditionLogicFacadeLocal userConditionLogicFacade;

    @GET
    @Path("/list/{idU}/{idP}")
    public Response listUserCondition(@PathParam("idU") int idU, @PathParam("idP") int idP) {
        try {
            List<ConditionView> data = userConditionLogicFacade.listUserCondition(idU, idP);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("data", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

}
