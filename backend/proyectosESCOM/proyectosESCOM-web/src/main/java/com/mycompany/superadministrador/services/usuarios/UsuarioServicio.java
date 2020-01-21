package com.mycompany.superadministrador.services.usuarios;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

    private final Respuesta respuesta = new Respuesta();

    public UsuarioServicio() {
    }

    /**
     * Servicio que registra usuarios
     *
     * @param usuario
     * @return  *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrarUsuario")
    public Response registrarUsuario(UsuarioPOJO usuario) {
        try {
            usuarioLogica.registrarUsuario(usuario);
            respuesta.setRespuesta("Usuario registrado");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los usuarios registrados
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarUsuarios")
    public Response listarUsuarios() {
        try {
            List<UsuarioPOJO> listaUsuarios = usuarioLogica.devolverUsuarios();
            return Response.status(Response.Status.OK).entity(listaUsuarios).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los tipos de documento
     *
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoDocumento")
    public Response listarTipoDocumento() {
        try {
            List<TipoDocumentoPOJO> listaDocumentos = usuarioLogica.devolverDocumentos();
            return Response.status(Response.Status.OK).entity(listaDocumentos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los datos de un usuario especifico consultado con la
     * cedula
     *
     * @param cedula
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/datosUsuario/{cedula}")
    public Response listarUsuarioCedula(@PathParam("cedula") int cedula) {
        try {
            UsuarioPOJO usuarioDatos = usuarioLogica.traerUsuarioCedula(cedula);
            return Response.status(Response.Status.OK).entity(usuarioDatos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que permite la edicion de usuarios
     *
     * @param cedula
     * @param usuarioEditar
     * @return  *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/editarUsuario/{cedula}")
    public Response editarUsuario(@PathParam("cedula") int cedula, UsuarioPOJO usuarioEditar) {
        try {
            usuarioLogica.editarUsuario(cedula, usuarioEditar);
            respuesta.setRespuesta("Usuario modificado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("No se ha podido modificar el usuario");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que habilita o deshabilita un usuario recibe como parametro la
     * cedula
     *
     * @param cedula
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstadoUsuario/{cedula}")
    public Response cambiarEstadoUsuario(@PathParam("cedula") int cedula) {
        try {
            usuarioLogica.cambiarEstadoUsuario(cedula);
            respuesta.setRespuesta("Estado cambiado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista las actividades de un usuario especifico, recibe como
     * parametro la cedula
     *
     * @param cedula
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividadesUsuario/{cedula}")
    public Response listarActividadesUsuario(@PathParam("cedula") int cedula) {
        try {
            List<ActividadPOJO> listaActividades = usuarioLogica.listarActividadesUsuario(cedula);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * Servicio que lista las actividades no asosciadas de un usuario especifico, recibe como
     * parametro la cedula y el codigo del modulo 
     *
     * @param numeroDocumento
     * @param idModulo
     * 
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividadesNoAsociadasUsuario/{numeroDocumento}/{codigoModulo}")
    public Response listarActividadesNoAsociadasUsuario(@PathParam("numeroDocumento") int numeroDocumento,@PathParam("codigoModulo") int idModulo) {
        try {
            List<ActividadPOJO> listaActividades = usuarioLogica.listarActividadesNoAsociadasUsuario(numeroDocumento,idModulo);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que elimina actividad a un usuario, recibe como parametro la
     * cedula y el id de la actividad
     *
     * @param cedula
     * @param idActividad
     * @return  *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eliminarActividadUsuario/{cedula}/{idActividad}")
    public Response eliminarActividadUsuario(@PathParam("cedula") int cedula, @PathParam("idActividad") int idActividad) {
        try {
            usuarioLogica.eliminarActividadUsuario(cedula, idActividad);
            respuesta.setRespuesta("Actividad eliminada correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Sin acceso al servicio");
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor ");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * Servicio que asigna actividades a usuarios 
     *
     * @param numeroDocumento
     * @param codigoActividad
     * 
     * @return  *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/asignarActividad/{numeroDocumento}/{codigoActividad}")
    public Response asignarActividad(@PathParam("numeroDocumento") int numeroDocumento,@PathParam("codigoActividad") int codigoActividad) {
        try {
            usuarioLogica.asignarActividadAUsuario(numeroDocumento, codigoActividad);
            respuesta.setRespuesta("Actividad asignada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta("Ya existen los datos registrados previamente");
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }


}
