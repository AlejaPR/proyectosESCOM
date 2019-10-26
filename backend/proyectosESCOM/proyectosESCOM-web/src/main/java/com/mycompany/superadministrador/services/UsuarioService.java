/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author aleja
 */
@javax.enterprise.context.RequestScoped
@Path("usuario")
public class UsuarioService {

    @EJB
    UsuarioFacadeLocal usuarioFacade;

    @GET
    @Path("/prueba")
    public Response prueba(){
       System.out.println("hola");
       List<Usuario> u =usuarioFacade.findAll();
       for(int i=0; i<u.size(); i++){
                System.out.println(u.get(i).getNombre());
       }
            
        return Response.status(Response.Status.OK).build();
            
    }
    
    /**servicio que devuelve los datos del usuario**/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("servicio/{token}")
    public Response devolverUsuario(@PathParam("token") String token){
        try {
            System.out.println(token);
            List<Usuario> u =usuarioFacade.busquedaToken(token);
            for(int i=0; i<u.size(); i++){
                System.out.println(u.get(i).getNombre());
            }
            
            return Response.status(Response.Status.OK).entity(u).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }     
    }

}
