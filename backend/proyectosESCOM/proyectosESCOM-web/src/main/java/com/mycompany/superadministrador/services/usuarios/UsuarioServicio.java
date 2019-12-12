package com.mycompany.superadministrador.services.usuarios;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.interfaces.TipoDocumentoFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase encargada de manejar todos los servicios referente al usuario
 *
 * @author jeison gaona - alejandra pabon
 */
@javax.enterprise.context.RequestScoped
@Path("usuario")
public class UsuarioServicio {
    
    
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;
   
    @EJB(name = "Sesiones")
    SesionesFacadeLocal sesiones;

    
    private final Respuesta respuesta = new Respuesta();

    public UsuarioServicio() {
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrarUsuario")
    public Response registrarUsuario(UsuarioPOJO usuario) {
        try {
            usuarioLogica.registrarUsuario(usuario);
            respuesta.setRespuesta("Usuario registrado");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        }catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        }catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**Servicio que lista los usuarios registrados
     * @return  **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarUsuarios")
    public Response listarUsuarios() {
        try {
            List<UsuarioPOJO> listaUsuarios = usuarioLogica.devolverUsuarios();
            return Response.status(Response.Status.OK).entity(listaUsuarios).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Credenciales incorrectas");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**Servicio que lista los tipos de documento
     * @return  **/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoDocumento")
    public Response listarTipoDocumento() {
        try {
            List<TipoDocumentoPOJO> listaDocumentos = usuarioLogica.devolverDocumentos();
            return Response.status(Response.Status.OK).entity(listaDocumentos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Credenciales incorrectas");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

}
