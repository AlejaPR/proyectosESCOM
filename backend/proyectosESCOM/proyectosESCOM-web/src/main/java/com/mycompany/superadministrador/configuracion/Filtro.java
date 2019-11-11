package com.mycompany.superadministrador.configuracion;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.Token;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.SeguridadFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
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
    UsuarioFacadeLocal usuarioFacade;

    @EJB
    SesionesFacadeLocal sesionesFacade;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String url = requestContext.getUriInfo().getAbsolutePath().toString();
        if (url.contains("api/login")) {
            return;
        }
        String token = requestContext.getHeaderString("TokenAuto");
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

                    } else {
                        Respuesta respuesta = new Respuesta("token vencido");
                        requestContext.abortWith(Response.status(Response.Status.NOT_ACCEPTABLE)
                                .entity(respuesta)
                                .type(MediaType.APPLICATION_JSON)
                                .build());
                    }
                }else{
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
