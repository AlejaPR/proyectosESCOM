/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "TBL_TIPODOCUMENTO")
public class TipoDocumento implements Serializable {
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIP_IDTIPODOCUMENTO")
    private Integer idTipodocumento;
    
    @Size(max = 20)
    @Column(name = "TIP_TIPODOCUMENTO")
    private String tipoDocumento;
    
    @Column(name = "TIP_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    public TipoDocumento(){
        
    }

    public TipoDocumento(String tipoDocumento, Date ultimaModificacion) {
        this.tipoDocumento = tipoDocumento;
        this.ultimaModificacion = ultimaModificacion;
    }

    public Integer getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(Integer idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }
    
    
}
