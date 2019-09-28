/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aleja
 */
@javax.enterprise.context.RequestScoped
@Path("actividad")
public class ActividadService {
    
    @EJB 
    ActividadFacadeLocal act;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response verActividad(){ 
            
            List<Actividad> actividad = act.findAll();
            System.out.println(".");
            for (int i = 0; i < actividad.size(); i++) {
             System.out.println(actividad.get(i).getNombreActividad());
             System.out.println(actividad.get(i).getFkActIdmodulo().getDescripcionModulo());
            }
            
            return Response.status(Response.Status.OK).build();
    }
    
    
    
    
}
