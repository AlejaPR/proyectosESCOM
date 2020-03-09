package com.mycompany.superadministrador.configuracion;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Alejandra Pabon Rodriguez 461 215 234 Clase que genera el filtro para
 * el token
 */
@Provider
@PreMatching
public class Filtro implements ContainerRequestFilter {

    @EJB
    SesionesFacadeLocal sesionesFacade;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String url = requestContext.getUriInfo().getAbsolutePath().toString();
        if (url.contains("api/login")|url.contains("api/configuracion/listarInicio")|url.contains("api/bitacora/consultar")) {
            return;
        }
        String token = requestContext.getHeaderString("TokenAuto");
        String permiso = requestContext.getHeaderString("Permiso");
        if (token == null) {
            Respuesta respuesta = new Respuesta("Token requerido");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(respuesta)
                    .type(MediaType.APPLICATION_JSON)
                    .build());
        } else {
            try {
                if (sesionesFacade.getMapaSesiones().containsKey(token)) {
                    if (sesionesFacade.modificarVencimiento(token)) {
                        if(url.contains("api/login/cerrarSesion")|url.contains("api/usuarios/redireccion")|url.contains("api/configuracion/listarEntorno")){
                            return;
                        }
                        if (!sesionesFacade.validarPermiso(token, permiso)) {
                            Respuesta respuesta = new Respuesta("Sin permiso");
                            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                    .entity(respuesta)
                                    .type(MediaType.APPLICATION_JSON)
                                    .build());
                        }
                    } else {
                        Respuesta respuesta = new Respuesta("token vencido");
                        requestContext.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE)
                                .entity(respuesta)
                                .type(MediaType.APPLICATION_JSON)
                                .build());
                    }
                } else {
                    Respuesta respuesta = new Respuesta("token no registrado");
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity(respuesta)
                            .type(MediaType.APPLICATION_JSON)
                            .build());
                }
            } catch (MalformedJwtException me) {
                Respuesta respuesta = new Respuesta("token incorrecto");
                requestContext.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta)
                        .type(MediaType.APPLICATION_JSON)
                        .build());
            } catch (Exception e) {
                Respuesta respuesta = new Respuesta("token incorrecto");
                requestContext.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta)
                        .type(MediaType.APPLICATION_JSON)
                        .build());
            }
        }
    }

}
