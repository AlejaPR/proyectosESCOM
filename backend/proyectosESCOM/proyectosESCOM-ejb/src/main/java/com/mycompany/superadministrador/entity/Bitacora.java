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

/**
 *
 * @author aleja
 */
@Entity
@Table(name = "TBL_BITACORA")
public class Bitacora implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_BTC_IDBITACORA")
    private Integer idBitacora;
    
    @Column(name = "BTC_OPERACION")
    private String operacion;
    
    @Column(name = "FK_BTC_IDUSUARIO")
    private Integer idUsuario;
    
    @Column(name = "BTC_TABLAINVOLUCRADA")
    private String tablaInvolucrada;
    
    @Column(name = "BTC_FECHABITACORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBitacora;
    
    @Column(name = "FK_BTC_IDMODULO")
    private Integer idModulo;

    @Column(name = "BTC_IP")
    private String ip;
    
    public Bitacora(){
        
    }

    public Bitacora(String operacion, Integer idUsuario, String tablaInvolucrada, Date fechaBitacora, Integer idModulo, String ip) {
        this.operacion = operacion;
        this.idUsuario = idUsuario;
        this.tablaInvolucrada = tablaInvolucrada;
        this.fechaBitacora = fechaBitacora;
        this.idModulo = idModulo;
        this.ip = ip;
    }

    public Integer getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(Integer idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getTablaInvolucrada() {
        return tablaInvolucrada;
    }

    public void setTablaInvolucrada(String tablaInvolucrada) {
        this.tablaInvolucrada = tablaInvolucrada;
    }

    public Date getFechaBitacora() {
        return fechaBitacora;
    }

    public void setFechaBitacora(Date fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }

    public Integer getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.idModulo = idModulo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
}
