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
    @NamedQuery(name = "consultaLogin", query = "SELECT u FROM Usuario u WHERE u.correoElectronico =:correo AND u.contrasena=:contrasena"),
    @NamedQuery(name = "busquedaToken", query = "SELECT u FROM Usuario u WHERE u.token = :token"),
    @NamedQuery(name = "editarToken", query = "UPDATE Usuario set token = :token WHERE idUsuario=:idUsuario"),
    @NamedQuery(name = "editarTokenCerrar", query = "UPDATE Usuario set token = :token WHERE correoElectronico=:correo")
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
    private TipoDocumento fkUsrIdtipodocumento;

    @OneToMany(mappedBy = "fkUacIdusuario")
    private List<UsuarioActividad> usuarioActividadList;

    public Usuario() {

    }

    public Usuario(String token, Integer numeroDocumento, String apellido, String estado, Date fechaNacimiento, Integer numeroIntentos, String nombre, Date ultimaModificacion, String correoElectronico, String contrasena, TipoDocumento fkUsrIdtipodocumento) {
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
        this.fkUsrIdtipodocumento = fkUsrIdtipodocumento;
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

    public TipoDocumento getFkUsrIdtipodocumento() {
        return fkUsrIdtipodocumento;
    }

    public void setFkUsrIdtipodocumento(TipoDocumento fkUsrIdtipodocumento) {
        this.fkUsrIdtipodocumento = fkUsrIdtipodocumento;
    }

    public List<UsuarioActividad> getUsuarioActividadList() {
        return usuarioActividadList;
    }

    public void setUsuarioActividadList(List<UsuarioActividad> usuarioActividadList) {
        this.usuarioActividadList = usuarioActividadList;
    }

}
