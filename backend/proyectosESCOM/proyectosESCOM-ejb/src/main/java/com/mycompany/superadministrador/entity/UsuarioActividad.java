/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "TBL_USUARIOACTIVIDAD")
public class UsuarioActividad implements Serializable {
    
    @Id
    @Column(name = "PK_UAC_IDRELACION")
    private Integer idRelacion;
    
    @Column(name = "UAC_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    @Id
    @JoinColumn(name = "FK_UAC_IDACTIVIDAD")
    @ManyToOne
    private Actividad actividad;
    
    @Id
    @JoinColumn(name = "FK_UAC_IDUSUARIO")
    @ManyToOne
    private Usuario usuario;

    public UsuarioActividad(){
        
    }
    
    public UsuarioActividad(Date ultimaModificacion, Actividad actividad, Usuario usuario) {
        this.ultimaModificacion = ultimaModificacion;
        this.actividad = actividad;
        this.usuario = usuario;
    }

    public Integer getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(Integer idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
    
    
}
