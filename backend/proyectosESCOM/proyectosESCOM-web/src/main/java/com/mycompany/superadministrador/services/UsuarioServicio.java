package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ClavePOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
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
 * Clase encargada de manejar de servicios de la entidad usuario
 *
 * @author Alejandra Pabon- Jeison Gaona
 */
@javax.enterprise.context.RequestScoped
@Path("usuarios")
public class UsuarioServicio {

    /**
     * Inyeccion de la interfaz logica usuario*
     */
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    /**
     * Variable para manejo de mensajes*
     */
    private final Respuesta respuesta = new Respuesta();

    /**
     * Constructor vacio de la clase*
     */
    public UsuarioServicio() {
    }

    /**
     * Servicio que registra usuarios
     *
     * @param usuario
     * @return *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/registrar")
    public Response registrar(UsuarioPOJO usuario) {
        try {
            usuarioLogica.registrarUsuario(usuario);
            respuesta.setRespuesta("Usuario registrado");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los usuarios registrados
     *
     * @param token
     * @param cantidadDatos
     * @param paginaActual
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/listar/{token}/{cantidadDatos}/{paginaActual}")
    public Response listar(@PathParam("token") String token,@PathParam("cantidadDatos") int cantidadDatos,@PathParam("paginaActual") int paginaActual) {
        try {
            List<UsuarioPOJO> listaUsuarios = usuarioLogica.devolverUsuarios(token,cantidadDatos,paginaActual);
            return Response.status(Response.Status.OK).entity(listaUsuarios).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * 
     * @param palabraBusqueda
     * @param token
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/filtrar/{token}/{palabraBusqueda}")
    public Response filtrarCantidad(@PathParam("palabraBusqueda") String palabraBusqueda,@PathParam("token") String token) {
        try {
            int cantidad= usuarioLogica.cantidadDeDatosFiltrados(token,palabraBusqueda);
            return Response.status(Response.Status.OK).entity(cantidad).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    /**
     * 
     * @param palabraBusqueda
     * @param token
     * @param cantidadDatos
     * @param paginaActual
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/filtrar/{token}/{palabraBusqueda}/{cantidadDatos}/{paginaActual}")
    public Response filtrar(@PathParam("palabraBusqueda") String palabraBusqueda,@PathParam("token") String token,@PathParam("cantidadDatos") int cantidadDatos,@PathParam("paginaActual") int paginaActual) {
        try {
            List<UsuarioPOJO> listaUsuarios = usuarioLogica.devolverUsuariosFiltrados(palabraBusqueda,token,cantidadDatos,paginaActual);
            return Response.status(Response.Status.OK).entity(listaUsuarios).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
    
    
    /**
     * 
     * @param token
     * @return 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cantidadDatos/{token}")
    public Response cantidad(@PathParam("token") String token) {
        try {
            return Response.status(Response.Status.OK).entity(usuarioLogica.cantidadDeDatos(token)).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error interno del servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista los tipos de documento
     *
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tipoDocumento")
    public Response listarTipoDocumento() {
        try {
            List<TipoDocumentoPOJO> listaDocumentos = usuarioLogica.devolverDocumentos();
            return Response.status(Response.Status.OK).entity(listaDocumentos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/datos/{cedula}")
    public Response listarUsuarioCedula(@PathParam("cedula") int cedula) {
        try {
            UsuarioPOJO usuarioDatos = usuarioLogica.traerUsuarioCedula(cedula);
            return Response.status(Response.Status.OK).entity(usuarioDatos).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @return *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/editar/{cedula}")
    public Response editar(@PathParam("cedula") int cedula, UsuarioPOJO usuarioEditar) {
        try {
            usuarioLogica.editarUsuario(cedula, usuarioEditar);
            respuesta.setRespuesta("Usuario modificado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @param datosSolicitud
     * @return
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cambiarEstado/{cedula}")
    public Response cambiarEstado(@PathParam("cedula") int cedula, DatosSolicitudPOJO datosSolicitud) {
        try {
            usuarioLogica.cambiarEstadoUsuario(cedula, datosSolicitud);
            respuesta.setRespuesta("Estado cambiado correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividades/{cedula}")
    public Response listarActividades(@PathParam("cedula") int cedula) {
        try {
            List<ActividadPOJO> listaActividades = usuarioLogica.listarActividadesUsuario(cedula);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que lista las actividades no asociadas de un usuario especifico,
     * recibe como parametro la cedula y el codigo del modulo
     *
     * @param numeroDocumento
     * @param idModulo
     *
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listarActividadesNoAsociadas/{numeroDocumento}/{codigoModulo}")
    public Response listarActividadesNoAsociadas(@PathParam("numeroDocumento") int numeroDocumento, @PathParam("codigoModulo") int idModulo) {
        try {
            List<ActividadPOJO> listaActividades = usuarioLogica.listarActividadesNoAsociadasUsuario(numeroDocumento, idModulo);
            return Response.status(Response.Status.OK).entity(listaActividades).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que elimina actividad a un usuario, recibe como parametro la
     * cedula y el id de la actividad
     *
     * @param cedula
     * @param listaActividad
     * @return *
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eliminarActividad/{cedula}")
    public Response eliminarActividad(@PathParam("cedula") int cedula, List<ActividadPOJO> listaActividad) {
        try {
            usuarioLogica.eliminarActividadUsuario(cedula, listaActividad);
            respuesta.setRespuesta("Actividad eliminada correctamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
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
     * @param actividad
     *
     * @return *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/asignarActividad/{numeroDocumento}")
    public Response asignarActividad(@PathParam("numeroDocumento") int numeroDocumento, ActividadPOJO actividad) {
        try {
            usuarioLogica.asignarActividadAUsuario(numeroDocumento, actividad);
            respuesta.setRespuesta("Actividad asignada");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que sirve para la redireccion de permisos, devuelve lista de
     * modulos asignados a usuario y reciben token
     *
     * @param token
     *
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/redireccion/{token}")
    public Response redireccion(@PathParam("token") String token) {
        try {
            List<ModuloPOJO> listaModulo = usuarioLogica.redireccionUsuario(token);
            return Response.status(Response.Status.OK).entity(listaModulo).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio para el cambio de contraseña interna
     *
     * @param clavePOJO
     * @return *
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/cambiarClaveInterna")
    public Response cambiarClaveInterna(ClavePOJO clavePOJO) {
        try {
            usuarioLogica.cambiarClaveInterna(clavePOJO);
            respuesta.setRespuesta("Contraseña modificada exitosamente");
            return Response.status(Response.Status.OK).entity(respuesta).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }

    /**
     * Servicio que devuelve correo y recibe token
     *
     * @param token
     *
     * @return *
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/devolverCorreo/{token}")
    public Response devolverCorreo(@PathParam("token") String token) {
        try {
            String correoUsuario = usuarioLogica.devolverCorreo(token);
            return Response.status(Response.Status.OK).entity(correoUsuario).build();
        } catch (ExcepcionGenerica e) {
            respuesta.setRespuesta(e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(respuesta).build();
        } catch (Exception e) {
            respuesta.setRespuesta("Ocurrio un error en el servidor");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta).build();
        }
    }
}
