/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
    private Actividad fkUacIdactividad;
    
    @Id
    @JoinColumn(name = "FK_UAC_IDUSUARIO")
    @ManyToOne
    private Usuario fkUacIdusuario;

    public UsuarioActividad(){
        
    }
    
    public UsuarioActividad(Date ultimaModificacion, Actividad fkUacIdactividad, Usuario fkUacIdusuario) {
        this.ultimaModificacion = ultimaModificacion;
        this.fkUacIdactividad = fkUacIdactividad;
        this.fkUacIdusuario = fkUacIdusuario;
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

    public Actividad getFkUacIdactividad() {
        return fkUacIdactividad;
    }

    public void setFkUacIdactividad(Actividad fkUacIdactividad) {
        this.fkUacIdactividad = fkUacIdactividad;
    }

    public Usuario getFkUacIdusuario() {
        return fkUacIdusuario;
    }

    public void setFkUacIdusuario(Usuario fkUacIdusuario) {
        this.fkUacIdusuario = fkUacIdusuario;
    }
    
    
}
