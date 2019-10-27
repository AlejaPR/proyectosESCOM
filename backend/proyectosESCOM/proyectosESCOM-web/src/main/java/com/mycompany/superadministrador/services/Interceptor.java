/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

/**
 *
 * @author jeiso
 */
//@Provider
//@PreMatching
//public class Interceptor implements ContainerRequestFilter {
public class Interceptor {
//
//    
//    @Override
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        String url= requestContext.getUriInfo().getAbsolutePath().toString();
//        if(url.contains("api/usuario"))
//            return;
//        String token = requestContext.getHeaderString("token-auto");
//        if(token==null){
//            JsonObject json=Json.createObjectBuilder().add("mensaje", "token requerido").build();
//            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
//        }else{
//            String resultado=controllerUsuario.seguridadToken(token);
//            if(resultado.equals(" ")){ 
//            }
//            if(resultado.equals("token incorrecto")| resultado.equals("token vencido")){
//                JsonObject json = Json.createObjectBuilder().add("mensaje", resultado).build();
//                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(json).type(MediaType.APPLICATION_JSON).build());
//            }
//        }
//    }
}