package com.mycompany.modulodocumental.services;

import com.mycompany.modulodocumental.interfaces.logic.ActivityLogicLocal;
import com.mycompany.modulodocumental.pojo.ActivityP;
import com.mycompany.modulodocumental.view.ActivityAnnexView;
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
 * Class in charge of managing all the services related to the activity entity
 *
 * @author Cristian Estevez - Anggy - University of Cundinamarca
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("activity")
public class ActivityS {

    /**
     * Activity logical interface injection
     */
    @EJB
    private ActivityLogicLocal activityLogicFacade;

    /**
     * Service that adds an activity
     *
     * @param act
     * @return
     */
    @POST
    @Path("/add")
    public Response add(ActivityP act) {
        try {
            activityLogicFacade.add(act);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service that edits an activity
     *
     * @param act
     * @return
     */
    @PUT
    @Path("/edit")
    public Response edit(ActivityP act) {
        try {
            activityLogicFacade.edit(act);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "edit").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service that information type activities
     *
     * @param id
     * @return
     */
    @GET
    @Path("/listInfo/{id}")
    public Response listInfo(@PathParam("id") int id) {
        try {
            List<ActivityP> data = activityLogicFacade.listInfo(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service than annex type activities
     *
     * @param id
     * @return
     */
    @GET
    @Path("/listAnnex/{id}")
    public Response listAnnex(@PathParam("id") int id) {
        try {
            List<ActivityP> data = activityLogicFacade.listAnnex(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that add information to the activity
     *
     * @param act
     * @return
     */
    @PUT
    @Path("/addInformation")
    public Response addInformation(ActivityP act) {
        try {
            activityLogicFacade.addInformation(act);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "add").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service that gets an activity
     *
     * @param id
     * @return
     */
    @GET
    @Path("/get/{id}")
    public Response get(@PathParam("id") int id) {
        try {
            ActivityP data = activityLogicFacade.get(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that obtains all the information of the activities
     *
     * @param id
     * @return
     */
    @GET
    @Path("/allInformation/{id}")
    public Response allInformation(@PathParam("id") int id) {
        try {
            String data = activityLogicFacade.allInformation(id);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", data).build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that removes an activity
     *
     * @param id
     * @param dataR
     * @return
     */
    @POST
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int id, DatosSolicitudPOJO dataR) {
        try {
            activityLogicFacade.disable(id, dataR);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "delete").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that the status of an activity
     *
     * @param activity
     * @return
     */
    @PUT
    @Path("/changeStatus")
    public Response changeStatus(ActivityP activity) {
        try {
            activityLogicFacade.changeStatus(activity);
            if (activity.getState() == 1) {
                JsonObject rest = Json.createObjectBuilder().add("respuesta", "denied").build();
                return Response.status(Response.Status.OK).entity(rest).build();
            } else if (activity.getState() == 2) {
                JsonObject rest = Json.createObjectBuilder().add("respuesta", "approved").build();
                return Response.status(Response.Status.OK).entity(rest).build();
            } else {
                JsonObject rest = Json.createObjectBuilder().add("respuesta", "notify").build();
                return Response.status(Response.Status.OK).entity(rest).build();
            }

        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }

    }

    /**
     * Service that relates an activity to an annex
     *
     * @param id
     * @param idA
     * @param dataS
     * @return
     */
    @PUT
    @Path("/associate/{id}/{idA}")
    public Response associateAnnex(@PathParam("id") int id, @PathParam("idA") int idA, DatosSolicitudPOJO dataS) {
        try {
            activityLogicFacade.associateAnnex(id, idA, dataS);
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "associate").build();
            return Response.status(Response.Status.OK).entity(rest).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }

    /**
     * Service that obtains the annex type activity
     *
     * @param id
     * @return
     */
    @GET
    @Path("/getActivityAnnex/{id}")
    public Response getAnnex(@PathParam("id") int id) {
        try {
            ActivityAnnexView data = activityLogicFacade.getAnnex(id);
            return Response.status(Response.Status.OK).entity(data).build();
        } catch (Exception e) {
            JsonObject rest = Json.createObjectBuilder().add("respuesta", "error server").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(rest).build();
        }
    }
}
