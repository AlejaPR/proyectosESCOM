/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.ejb.UsuarioFacade;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jeiso
 */
@javax.enterprise.context.RequestScoped
@Path("usuario")
public class ServiciosUsuario {

    @EJB
    UsuarioFacadeLocal usuarioBD;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response iniciarSesion() {
        try {
            return Response.status(Response.Status.OK).entity(usuarioBD.findAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Error "+e).build();
        }
    }
}
