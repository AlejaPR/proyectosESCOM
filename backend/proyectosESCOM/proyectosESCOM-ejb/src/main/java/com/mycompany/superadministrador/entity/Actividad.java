/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "TBL_ACTIVIDAD")
@NamedQueries({
    @NamedQuery(name = "consultaActividades", query = "SELECT a from Actividad a,Modulo m,Usuario u, UsuarioActividad ua WHERE a.pkActIdactividad = ua.fkUacIdactividad.pkActIdactividad AND u.idUsuario=ua.fkUacIdusuario.idUsuario AND u.idUsuario=:idUsuario AND a.estado='Activo' AND a.fkActIdmodulo.estado='Activo'"),
    @NamedQuery(name = "consultaActividadesModulo", query = "SELECT a from Actividad a WHERE a.fkActIdmodulo = :idModulo"),
    @NamedQuery(name = "consultaActividadesUsuario", query = "SELECT ac from Actividad ac,Usuario us, UsuarioActividad ua WHERE us.idUsuario = ua.fkUacIdusuario.idUsuario AND ac.pkActIdactividad =ua.fkUacIdactividad.pkActIdactividad AND us.numeroDocumento=:numeroDocumento"),
    @NamedQuery(name = "consultaActividadesNoAsociadasUsuario", query = "SELECT act FROM Actividad act WHERE act.estado='Activo' AND act.fkActIdmodulo.pkModIdmodulo=:idModulo  AND act NOT IN(SELECT ac from Actividad ac,Usuario us, UsuarioActividad ua WHERE us.idUsuario = ua.fkUacIdusuario.idUsuario AND ac.pkActIdactividad =ua.fkUacIdactividad.pkActIdactividad AND ac.estado='Activo' AND us.numeroDocumento=:numeroDocumento AND ac.fkActIdmodulo.pkModIdmodulo=:idModulo)"),
    @NamedQuery(name = "consultaActividadPorNombre", query = "SELECT a from Actividad a WHERE a.nombreActividad = :nombreActividad"),
    @NamedQuery(name = "consultaActividadesUsuarioActivas", query = "SELECT ac from Actividad ac,Usuario us, UsuarioActividad ua WHERE us.idUsuario = ua.fkUacIdusuario.idUsuario AND ac.pkActIdactividad =ua.fkUacIdactividad.pkActIdactividad AND ac.estado='Activo' AND us.numeroDocumento=:numeroDocumento"),
})

public class Actividad implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_ACT_IDACTIVIDAD")
    private Integer pkActIdactividad;

    @Column(name = "ACT_ESTADO")
    private String estado;

    @Column(name = "ACT_DESCRIPCIONACTIVIDAD")
    private String descripcionActividad;

    @Column(name = "ACT_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;

    @Size(max = 43)
    @Column(name = "ACT_NOMBREACTIVIDAD")
    private String nombreActividad;

    @JoinColumn(name = "FK_ACT_IDMODULO", referencedColumnName = "PK_MOD_IDMODULO")
    @ManyToOne
    private Modulo fkActIdmodulo;

    @OneToMany(mappedBy = "fkUacIdactividad")
    private List<UsuarioActividad> usuarioActividadList;

    public Actividad() {

    }

    public Actividad(String estado, String descripcionActividad, Date ultimaModificacion, String nombreActividad, Modulo fkActIdmodulo) {
        this.estado = estado;
        this.descripcionActividad = descripcionActividad;
        this.ultimaModificacion = ultimaModificacion;
        this.nombreActividad = nombreActividad;
        this.fkActIdmodulo = fkActIdmodulo;
    }

    public Integer getIdActividad() {
        return pkActIdactividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.pkActIdactividad = idActividad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Modulo getFkActIdmodulo() {
        return fkActIdmodulo;
    }

    public void setFkActIdmodulo(Modulo fkActIdmodulo) {
        this.fkActIdmodulo = fkActIdmodulo;
    }

    public List<UsuarioActividad> getUsuarioActividadList() {
        return usuarioActividadList;
    }

    public void setUsuarioActividadList(List<UsuarioActividad> usuarioActividadList) {
        this.usuarioActividadList = usuarioActividadList;
    }

}
