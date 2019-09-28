/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "TBL_BITACORA")
public class Bitacora implements Serializable{
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_BTC_IDBITACORA")
    private Integer idBitacora;
    
    @Size(max = 50)
    @Column(name = "BTC_OPERACION")
    private String operacion;
    
    @Column(name = "FK_BTC_IDUSUARIO")
    private Integer fkIdUsuario;
    
    @Size(max = 20)
    @Column(name = "BTC_TABLAINVOLUCRADA")
    private String tablaInvolucrada;
    
    @Column(name = "BTC_FECHABITACORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBitacora;
    
    @Size(max = 20)
    @Column(name = "BTC_MAC")
    private String mac;
    
    @Column(name = "FK_BTC_IDMODULO")
    private Integer fkBtcIdmodulo;
    
    @Size(max = 20)
    @Column(name = "BTC_IP")
    private String ip;
    
    public Bitacora(){
        
    }

    public Bitacora(String operacion, Integer fkIdUsuario, String tablaInvolucrada, Date fechaBitacora, String mac, Integer fkBtcIdmodulo, String ip) {
        this.operacion = operacion;
        this.fkIdUsuario = fkIdUsuario;
        this.tablaInvolucrada = tablaInvolucrada;
        this.fechaBitacora = fechaBitacora;
        this.mac = mac;
        this.fkBtcIdmodulo = fkBtcIdmodulo;
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

    public Integer getFkIdUsuario() {
        return fkIdUsuario;
    }

    public void setFkIdUsuario(Integer fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getFkBtcIdmodulo() {
        return fkBtcIdmodulo;
    }

    public void setFkBtcIdmodulo(Integer fkBtcIdmodulo) {
        this.fkBtcIdmodulo = fkBtcIdmodulo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    
    
}
