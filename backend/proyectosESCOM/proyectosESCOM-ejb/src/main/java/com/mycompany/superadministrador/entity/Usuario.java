/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "TBL_USUARIO")
@NamedQueries({
    @NamedQuery(name = "consultaUsuarios", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "consultaLogin", query = "SELECT u FROM Usuario u WHERE u.correoElectronico =:correo AND u.contrasena=:contrasena"),
    @NamedQuery(name = "busquedaToken", query = "SELECT u FROM Usuario u WHERE u.token = :token"),
    @NamedQuery(name = "editarToken", query = "UPDATE Usuario set token = :token WHERE idUsuario=:idUsuario"),
    @NamedQuery(name = "editarTokenCerrar", query = "UPDATE Usuario set token = :token WHERE correoElectronico=:correo"),
    @NamedQuery(name = "consultarExistencia", query = "SELECT u from Usuario u WHERE u.correoElectronico=:correo OR u.numeroDocumento=:numeroDocumento"),
    @NamedQuery(name = "consultaUsuarioEsp", query = "SELECT u from Usuario u WHERE u.numeroDocumento=:cedula"),
    @NamedQuery(name = "editarUsuario", query = "UPDATE Usuario u set u.nombre=:nombre,u.apellido=:apellido,"
            + "u.numeroDocumento=:numeroDocumento,u.fechaNacimiento=:fechaNacimiento,u.correoElectronico=:correoElectronico,u.tipoDocumento=:tipoDocumento"
            + " WHERE u.numeroDocumento=:documento"),
    @NamedQuery(name = "eliminarActividad", query = "DELETE FROM UsuarioActividad UA WHERE UA.usuario.idUsuario=:numeroDocumento AND UA.actividad.idActividad=:codigoActividad ")

})
public class Usuario implements Serializable {

    @Id
    @Column(name = "PK_USR_IDUSUARIO")
    private Integer idUsuario;

    @Column(name = "USR_TOKEN")
    private String token;

    @Column(name = "USR_NUMERODOCUMENTO")
    private Integer numeroDocumento;

    @Column(name = "USR_APELLIDO")
    private String apellido;

    @Column(name = "USR_ESTADO")
    private String estado;

    @Column(name = "USR_FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;

    @Column(name = "USR_NUMEROINTENTOS")
    private Integer numeroIntentos;

    @Column(name = "USR_NOMBRE")
    private String nombre;

    @Column(name = "USR_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;

    @Column(name = "USR_CORREOELECTRONICO")
    private String correoElectronico;

    @Column(name = "USR_CONTRASENA")
    private String contrasena;

    @JoinColumn(name = "FK_USR_IDTIPODOCUMENTO", referencedColumnName = "PK_TIP_IDTIPODOCUMENTO")
    @ManyToOne
    private TipoDocumento tipoDocumento;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioActividad> listaUsuarioActividad;

    public Usuario() {

    }

    public Usuario(String token, Integer numeroDocumento, String apellido, String estado, Date fechaNacimiento, Integer numeroIntentos, String nombre, Date ultimaModificacion, String correoElectronico, String contrasena, TipoDocumento tipoDocumento) {
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
    }

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

    

}
