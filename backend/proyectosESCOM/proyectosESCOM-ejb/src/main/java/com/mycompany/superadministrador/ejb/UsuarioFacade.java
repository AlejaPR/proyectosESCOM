package com.mycompany.superadministrador.ejb;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.TipoDocumento;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.SesionesFacadeLocal;
import com.mycompany.superadministrador.seguridad.Seguridad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
/**
 * Este es el bean de la entidad usuario
 * Contiene todos los metodos para la persistencia y consultas a la base de datos
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {

        return em;
    }

    /**Inyeccion a la interfaz de sesiones facade*/
    @EJB
    SesionesFacadeLocal sesiones;

    public UsuarioFacade() {
        super(Usuario.class);
    }

    /**
     * Metodo que realiza la consulta de token de usuario
     *
     * @param firma
     * @return
     *
     */
    @Override
    public UsuarioPOJO busquedaToken(String firma) {
        TypedQuery<Usuario> consulta = em.createNamedQuery("busquedaToken", Usuario.class);
        consulta.setParameter("token", firma);
        Usuario usuarioDB = consulta.getSingleResult();
        UsuarioPOJO usuarioRespuesta = new UsuarioPOJO();
        usuarioRespuesta.setId(usuarioDB.getIdUsuario());
        usuarioRespuesta.setCorreoElectronico(usuarioDB.getCorreoElectronico());
        usuarioRespuesta.setNumeroDocumento(usuarioDB.getNumeroDocumento());
        usuarioRespuesta.setContrasena(usuarioDB.getContrasena());
        return usuarioRespuesta;
    }

    /**
     * Metodo que realiza la consulta de correo y contraseña para el login
     *
     * @param correo
     * @param contrasena
     * @return
     *
     */
    @Override
    public List<Usuario> consultaLogin(String correo, String contrasena) {
        
        List<Usuario> listaUsuario = new ArrayList();
        TypedQuery<Usuario> consultaLogin = em.createNamedQuery("consultaLogin", Usuario.class);
        consultaLogin.setParameter("correo", correo);
        consultaLogin.setParameter("contrasena", contrasena);
        listaUsuario = consultaLogin.getResultList();
       
        return listaUsuario;
    }

    /**
     * Metodo que realiza la consulta de datos existentes de un usuario
     *
     * @param correo
     * @param idDocumento
     * @return
     *
     */
    @Override
    public List<Usuario> consultaDatosExistentes(String correo, int idDocumento) {
        TypedQuery<Usuario> consultaDatos = em.createNamedQuery("consultarExistencia", Usuario.class);
        consultaDatos.setParameter("correo", correo);
        consultaDatos.setParameter("numeroDocumento", idDocumento);
        return consultaDatos.getResultList();
    }

    /**
     * Metodo que realiza la consulta para editar token
     *
     * @param token
     * @param idUsuario
     * @return
     *
     */
    @Override
    public int editarToken(String token, int idUsuario) {
        TypedQuery<Usuario> editarToken = em.createNamedQuery("editarToken", Usuario.class);
        editarToken.setParameter("token", token);
        editarToken.setParameter("idUsuario", idUsuario);
        return editarToken.executeUpdate();
    }

    /**
     * Metodo que realiza la consulta para editar el token al cerrar sesion
     *
     * @param firma
     * @param correo
     * @return
     *
     */
    @Override
    public int editarTokenCerrarSesion(String firma, String correo) {
        TypedQuery<Usuario> editarToken = em.createNamedQuery("editarTokenCerrar", Usuario.class);
        editarToken.setParameter("token", firma);
        editarToken.setParameter("correo", correo);
        return editarToken.executeUpdate();
    }

    /**
     * Metodo que realiza la consulta de actividades del usuario especifico
     *
     * @param idUsuario
     * @return
     *
     */
    @Override
    public String[] consultarActividadesUsuario(int idUsuario) {
        TypedQuery<Actividad> consultaActividadesUsuario = em.createNamedQuery("consultaActividades", Actividad.class);
        consultaActividadesUsuario.setParameter("idUsuario", idUsuario);
        String[] actividades = new String[consultaActividadesUsuario.getResultList().size()];
        int control=0;
        for (Actividad a : consultaActividadesUsuario.getResultList()) {
            actividades[control]=a.getNombreActividad();
            control++;
        }
        return actividades;
    }

    /**
     * Metodo que realiza el registro de usuarios
     *
     * @param usuario
     *
     */
    @Override
    public void registrarUsuario(UsuarioPOJO usuario) {
        String contrasena = Seguridad.generarHash(usuario.getContrasena());
        usuario.setContrasena(contrasena);
        em.createNativeQuery("INSERT INTO TBL_USUARIO (USR_TOKEN,USR_NUMERODOCUMENTO,USR_APELLIDO,USR_ESTADO,USR_FECHANACIMIENTO,USR_NUMEROINTENTOS,USR_NOMBRE,USR_ULTIMAMODIFICACION,USR_CORREOELECTRONICO,"
                + "FK_USR_IDTIPODOCUMENTO,USR_CONTRASENA, USR_FECHAINGRESO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, " ")
                .setParameter(2, usuario.getNumeroDocumento())
                .setParameter(3, usuario.getApellido())
                .setParameter(4, "Activo")
                .setParameter(5, usuario.getFechaNacimiento())
                .setParameter(6, 0)
                .setParameter(7, usuario.getNombre())
                .setParameter(8, new Date())
                .setParameter(9, usuario.getCorreoElectronico())
                .setParameter(10, usuario.getTipoDocumento())
                .setParameter(11, usuario.getContrasena())
                .setParameter(12, null)
                .executeUpdate();
    }

    /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve una lista con los usuarios registrados
     *
     * @return
     *
     */
    @Override
    public List<UsuarioPOJO> listarUsuarios() {

        List<UsuarioPOJO> listaUsuarios = new ArrayList<>();
        TypedQuery<Usuario> consultaUsuariosRegistrados = em.createNamedQuery("consultaUsuarios", Usuario.class);

        for (Usuario u : consultaUsuariosRegistrados.getResultList()) {
            UsuarioPOJO usuario = new UsuarioPOJO();
            usuario.setNombre(u.getNombre());
            usuario.setApellido(u.getApellido());
            usuario.setNumeroDocumento(u.getNumeroDocumento());
            usuario.setCorreoElectronico(u.getCorreoElectronico());
            usuario.setEstado(u.getEstado());
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }

    /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve los datos de un usuario registrado con la cedula enviada
     *
     * @param cedula
     * @return
     *
     */
    @Override
    public UsuarioPOJO buscarUsuarioEspecifico(int cedula) {

        Usuario lista = new Usuario();
        TypedQuery<Usuario> usuarioEspDB = em.createQuery("select u from Usuario u where u.numeroDocumento= :cedula", Usuario.class);
        usuarioEspDB.setParameter("cedula", cedula);
        lista = usuarioEspDB.getSingleResult();
        /**
         * Conversion de fecha Oracle
         */
        SimpleDateFormat formatoUsuario = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        String fechaN = null;

        UsuarioPOJO usuario = new UsuarioPOJO();
        if (lista.getFechaNacimiento() == null) {
            fecha = null;
        } else {
            fecha = lista.getFechaNacimiento();
            fechaN = formatoUsuario.format(fecha);
        }
        usuario.setId(lista.getIdUsuario());
        usuario.setNombre(lista.getNombre());
        usuario.setApellido(lista.getApellido());
        usuario.setFechaDeNacimiento(fechaN);
        usuario.setTipoDocumento(lista.getTipoDocumento().getIdTipodocumento());
        usuario.setNumeroDocumento(lista.getNumeroDocumento());
        usuario.setCorreoElectronico(lista.getCorreoElectronico());
        usuario.setEstado(lista.getEstado());

        return usuario;
    }

    /**
     * Metodo que realiza la modificacion de un usuario 
     * Recibe cedula para filtrar la busqueda
     *
     * @param idUsuario
     * @param usuarioEditar
     *
     *
     */
    @Override
    public int editarUsuario(int idUsuario, UsuarioPOJO usuarioEditar, TipoDocumento tipo) {

        TypedQuery<Usuario> editarToken = em.createNamedQuery("editarUsuario", Usuario.class);
        editarToken.setParameter("nombre", usuarioEditar.getNombre());
        editarToken.setParameter("apellido", usuarioEditar.getApellido());
        editarToken.setParameter("numeroDocumento", usuarioEditar.getNumeroDocumento());
        editarToken.setParameter("fechaNacimiento", usuarioEditar.getFechaNacimiento());
        editarToken.setParameter("correoElectronico", usuarioEditar.getCorreoElectronico());
        editarToken.setParameter("tipoDocumento", tipo);
        editarToken.setParameter("documento", idUsuario);
        return editarToken.executeUpdate();
    }

    /**
     * Metodo que realiza el cambio de estado de un usuario 
     * Recibe cedula para filtrar la busqueda y el valor del estado
     *
     * @param idUsuario
     * @param estado
     *
     *
     */
    @Override
    public void cambiarEstadoUsuario(int idUsuario, String estado) {

        Usuario usuario = em.find(Usuario.class, idUsuario);
        usuario.setEstado(estado);
        em.merge(usuario);
    }
    
    /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve una lista con los usuarios registrados para el modulo documental 
     *
     * @return
     *
     */
    @Override
    public List<UsuarioPOJO> listarUsuariosModuloDocumental() {

        List<UsuarioPOJO> listaUsuarios = new ArrayList<>();
        TypedQuery<Usuario> consultaUsuariosRegistrados = em.createNamedQuery("consultaUsuarios", Usuario.class);

        for (Usuario u : consultaUsuariosRegistrados.getResultList()) {
            UsuarioPOJO usuario = new UsuarioPOJO();

            String nombre= u.getNombre();
            String apellido= u.getApellido();
            String nombreCompleto= nombre + " " + apellido;
            
            usuario.setNombre(nombreCompleto);
            usuario.setId(u.getIdUsuario());
            
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }
    
    /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve los datos de un usuario registrado para la bitacora recibiendo el correo
     *
     * @param palabraBusqueda
     * @return
     *
     */
    @Override
    public UsuarioPOJO buscarUsuarioBitacora(String palabraBusqueda) {

        String busqueda = palabraBusqueda.toLowerCase();
        Usuario lista = new Usuario();
        TypedQuery<Usuario> usuarioEspDB = em.createQuery("select u from Usuario u where (Lower(u.correoElectronico)  =:busqueda)", Usuario.class);
        usuarioEspDB.setParameter("busqueda", busqueda);
        lista = usuarioEspDB.getSingleResult();
        
        UsuarioPOJO usuario = new UsuarioPOJO();
        usuario.setId(lista.getIdUsuario());
        usuario.setNumeroDocumento(lista.getNumeroDocumento());
        usuario.setCorreoElectronico(lista.getCorreoElectronico());

        return usuario;
    }
    
     /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve los datos de un usuario registrado para la bitacora, recibe el numero de documento
     *
     * @param documentoBusqueda
     * @return
     *
     */
    @Override
    public UsuarioPOJO buscarUsuarioBitacoraDocumento(int documentoBusqueda) {

        Usuario lista = new Usuario();
        TypedQuery<Usuario> usuarioEspDB = em.createQuery("select u from Usuario u where u.numeroDocumento =:documentoBusqueda", Usuario.class);
        usuarioEspDB.setParameter("documentoBusqueda", documentoBusqueda);
        lista = usuarioEspDB.getSingleResult();
        
        UsuarioPOJO usuario = new UsuarioPOJO();
        usuario.setId(lista.getIdUsuario());
        usuario.setNumeroDocumento(lista.getNumeroDocumento());
        usuario.setCorreoElectronico(lista.getCorreoElectronico());

        return usuario;
    }

     /**
     * Metodo que realiza la consulta a la tabla usuario 
     * Devuelve los datos de un usuario registrado para la bitacora, recibe el id de usuario
     *
     * @param idUsuario
     * @return
     *
     */
    @Override
    public UsuarioPOJO buscarUsuarioBitacoraId(int idUsuario) {

        Usuario lista = new Usuario();
        TypedQuery<Usuario> usuarioEspDB = em.createQuery("select u from Usuario u where u.idUsuario =:idUsuario", Usuario.class);
        usuarioEspDB.setParameter("idUsuario", idUsuario);
        lista = usuarioEspDB.getSingleResult();
        
        UsuarioPOJO usuario = new UsuarioPOJO();
        usuario.setId(lista.getIdUsuario());
        usuario.setNumeroDocumento(lista.getNumeroDocumento());
        usuario.setCorreoElectronico(lista.getCorreoElectronico());
        usuario.setApellido(lista.getApellido());
        usuario.setNombre(lista.getNombre());

        return usuario;
    }

    /**Metodo que edita la clave del usuario
     * Recibe la nueva clave y el id del usuario
     * @param nuevaClave
     * @param usuario
     */
    @Override
    public void cambiarClaveInterna(String nuevaClave, UsuarioPOJO usuario) {
        
        TypedQuery<Usuario> cambiarContraseña = em.createNamedQuery("cambiarClaveInterna", Usuario.class);
        cambiarContraseña.setParameter("clave", nuevaClave);
        cambiarContraseña.setParameter("idUsuario", usuario.getId());
        cambiarContraseña.executeUpdate();
    }

    /**
     * Metodo que realiza la consulta de correo para el login
     *
     * @param correo
     * @return
     *
     */
    @Override
    public List<UsuarioPOJO> buscarCorreoUsuario(String correo) {
        List<Usuario> devolverUsuario = new ArrayList();
        TypedQuery<Usuario> consultaLogin = em.createNamedQuery("consultaCorreo", Usuario.class);
        consultaLogin.setParameter("correo", correo);
        devolverUsuario = consultaLogin.getResultList();
        
        List<UsuarioPOJO> listaUsuario = new ArrayList();
        for(Usuario u: devolverUsuario){
            UsuarioPOJO usuario = new UsuarioPOJO();
            usuario.setId(u.getIdUsuario());
            usuario.setFechaIngreso(u.getFechaIngreso());
            usuario.setNumeroIntentos(u.getNumeroIntentos());
            listaUsuario.add(usuario);
        }
        
        return listaUsuario;
    }
    
    /**Metodo que modifica el campo numero de intentos del usuario
     * @param numeroIntentos
     * @param idUsuario
     **/
    @Override
    public void cambiarNumeroIntentos(int numeroIntentos, int idUsuario) {
        
        TypedQuery<Usuario> cambiarIntentos = em.createNamedQuery("cambiarIntentos", Usuario.class);
        cambiarIntentos.setParameter("numeroIntentos", numeroIntentos);
        cambiarIntentos.setParameter("idUsuario", idUsuario);
        cambiarIntentos.executeUpdate();
    }
    
    /**Metodo que modifica el campo fecha ingreso del usuario
     * @param fechaIngreso
     * @param usuario
     **/
    @Override
    public void cambiarFechaIngreso(Date fechaIngreso, UsuarioPOJO usuario) {
        
        TypedQuery<Usuario> cambiarFecha = em.createNamedQuery("cambiarFechaIngreso", Usuario.class);
        cambiarFecha.setParameter("fechaIngreso", fechaIngreso);
        cambiarFecha.setParameter("idUsuario", usuario.getId());
        cambiarFecha.executeUpdate();
    }
    
    /**Metodo que modifica el campo fecha ingreso y numero de intentos para el login
     * @param usuario
     **/
    @Override
    public void cambiarIntentosConFecha(Usuario usuario) {
        
        TypedQuery<Usuario> cambiarFecha = em.createNamedQuery("cambiarIntentosFecha", Usuario.class);
        cambiarFecha.setParameter("fechaIngreso", null);
        cambiarFecha.setParameter("numeroIntentos", 0);
        cambiarFecha.setParameter("idUsuario", usuario.getIdUsuario());
        cambiarFecha.executeUpdate();
    }

}
