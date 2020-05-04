package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 * Esta es la clase de la entidad usuario Contiene todos los campos para la
 * persistencia y consultas named query
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Entity
@Table(name = "TBL_USUARIO")
@NamedQueries({
    @NamedQuery(name = "filtrarUsuarios", query = "SELECT u FROM Usuario u WHERE (u.numeroDocumento LIKE CONCAT('%',:palabraBusqueda,'%') OR Lower(u.nombre) LIKE CONCAT('%',:palabraBusqueda,'%') OR Lower(u.correoElectronico) LIKE CONCAT('%',:palabraBusqueda,'%') "
            + "OR Lower(u.estado) LIKE CONCAT('%',:palabraBusqueda,'%')) AND NOT u.correoElectronico=:correoElectronico"),
    @NamedQuery(name = "filtrarUsuariosSuper", query = "SELECT u FROM Usuario u WHERE u.numeroDocumento LIKE CONCAT('%',:palabraBusqueda,'%') OR Lower(u.nombre) LIKE CONCAT('%',:palabraBusqueda,'%') OR Lower(u.correoElectronico) LIKE CONCAT('%',:palabraBusqueda,'%') "
            + " OR Lower(u.estado) LIKE CONCAT('%',:palabraBusqueda,'%')"),
    @NamedQuery(name = "consultaUsuariosNormal", query = "SELECT u FROM Usuario u WHERE NOT u.correoElectronico=:correoElectronico"),
    @NamedQuery(name = "consultaUsuarios", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "consultaLogin", query = "SELECT u FROM Usuario u WHERE u.correoElectronico =:correo AND u.contrasena=:contrasena"),
    @NamedQuery(name = "busquedaToken", query = "SELECT u FROM Usuario u WHERE u.token = :token"),
    @NamedQuery(name = "busquedaTokenRecuperar", query = "SELECT u FROM Usuario u WHERE u.recuperarContrasena = :token"),
    @NamedQuery(name = "editarToken", query = "UPDATE Usuario set token = :token WHERE idUsuario=:idUsuario"),
    @NamedQuery(name = "editarTokenRecuperar", query = "UPDATE Usuario set recuperarContrasena = :token WHERE idUsuario=:idUsuario"),
    @NamedQuery(name = "editarTokenCerrar", query = "UPDATE Usuario set token = :token WHERE correoElectronico=:correo"),
    @NamedQuery(name = "consultarExistencia", query = "SELECT u from Usuario u WHERE u.correoElectronico=:correo OR u.numeroDocumento=:numeroDocumento"),
    @NamedQuery(name = "consultaUsuarioEsp", query = "SELECT u from Usuario u WHERE u.numeroDocumento=:cedula"),
    @NamedQuery(name = "editarUsuario", query = "UPDATE Usuario u set u.nombre=:nombre,u.apellido=:apellido,"
            + "u.numeroDocumento=:numeroDocumento,u.fechaNacimiento=:fechaNacimiento,u.correoElectronico=:correoElectronico,u.tipoDocumento=:tipoDocumento"
            + " WHERE u.numeroDocumento=:documento"),
    @NamedQuery(name = "eliminarActividad", query = "DELETE FROM UsuarioActividad UA WHERE UA.usuario.idUsuario=:numeroDocumento AND UA.actividad.idActividad=:codigoActividad "),
    @NamedQuery(name = "cambiarClaveInterna", query = "UPDATE Usuario u set u.contrasena = :clave WHERE u.idUsuario=:idUsuario"),
    @NamedQuery(name = "consultaCorreo", query = "SELECT u FROM Usuario u WHERE u.correoElectronico =:correo"),
    @NamedQuery(name = "cambiarIntentos", query = "UPDATE Usuario u set u.numeroIntentos=:numeroIntentos WHERE u.idUsuario=:idUsuario"),
    @NamedQuery(name = "cambiarFechaIngreso", query = "UPDATE Usuario u set u.fechaIngreso = :fechaIngreso WHERE u.idUsuario= :idUsuario"),
    @NamedQuery(name = "cambiarIntentosFecha", query = "UPDATE Usuario u set u.fechaIngreso = :fechaIngreso, u.numeroIntentos=:numeroIntentos WHERE u.idUsuario= :idUsuario"),})
public class Usuario implements Serializable {

    /**
     * Variable id usuario*
     */
    @Id
    @Column(name = "PK_USR_IDUSUARIO")
    private Integer idUsuario;

    /**
     * Variable token*
     */
    @Size(max = 300)
    @Column(name = "USR_TOKEN")
    private String token;

    /**
     * Variable numero documento*
     */
    @Column(name = "USR_NUMERODOCUMENTO")
    private Integer numeroDocumento;

    /**
     * Variable apellido*
     */
    @Size(max = 50)
    @Column(name = "USR_APELLIDO")
    private String apellido;

    /**
     * Variable estado usuario*
     */
    @Size(max = 20)
    @Column(name = "USR_ESTADO")
    private String estado;

    /**
     * Variable fecha nacimiento*
     */
    @Column(name = "USR_FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    /**
     * Variable numero intentos*
     */
    @Column(name = "USR_NUMEROINTENTOS")
    private Integer numeroIntentos;

    /**
     * Variable nombre*
     */
    @Size(max = 50)
    @Column(name = "USR_NOMBRE")
    private String nombre;

    /**
     * Variable ultima modificacion*
     */
    @Column(name = "USR_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;

    /**
     * Variable correo electronico*
     */
    @Size(max = 50)
    @Column(name = "USR_CORREOELECTRONICO")
    private String correoElectronico;

    /**
     * Variable contraseña*
     */
    @Size(max = 300)
    @Column(name = "USR_CONTRASENA")
    private String contrasena;

    @Size(max = 300)
    @Column(name = "USR_RECUPERARCONTRASENA")
    private String recuperarContrasena;

    /**
     * Variable facha ingreso*
     */
    @Column(name = "USR_FECHAINGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    /**
     * Variable para la relacion con la entidad tipo documento*
     */
    @JoinColumn(name = "FK_USR_IDTIPODOCUMENTO", referencedColumnName = "PK_TIP_IDTIPODOCUMENTO")
    @ManyToOne
    private TipoDocumento tipoDocumento;

    /**
     * Variable para la relacion con la entidad usuario actividad*
     */
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioActividad> listaUsuarioActividad;

    /**
     * Constructor vacio de la clase*
     */
    public Usuario() {

    }

    /**
     * Constructor con variables
     *
     * @param token
     * @param numeroDocumento
     * @param apellido
     * @param estado
     * @param fechaNacimiento
     * @param numeroIntentos
     * @param nombre
     * @param ultimaModificacion
     * @param correoElectronico
     * @param contrasena
     * @param tipoDocumento
     * @param fechaIngreso
     *
     */
    public Usuario(String token, Integer numeroDocumento, String apellido, String estado, Date fechaNacimiento, Integer numeroIntentos,
            String nombre, Date ultimaModificacion, String correoElectronico, String contrasena, TipoDocumento tipoDocumento, Date fechaIngreso) {
        this.token = token;
        this.numeroDocumento = numeroDocumento;
        this.apellido = apellido;
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroIntentos = numeroIntentos;
        this.nombre = nombre;
        this.ultimaModificacion = ultimaModificacion;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.tipoDocumento = tipoDocumento;
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * Metodos get y set de las variables*
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(Integer numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(Integer numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<UsuarioActividad> getListaUsuarioActividad() {
        return listaUsuarioActividad;
    }

    public void setListaUsuarioActividad(List<UsuarioActividad> listaUsuarioActividad) {
        this.listaUsuarioActividad = listaUsuarioActividad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getRecuperarContrasena() {
        return recuperarContrasena;
    }

    public void setRecuperarContrasena(String recuperarContrasena) {
        this.recuperarContrasena = recuperarContrasena;
    }

}
