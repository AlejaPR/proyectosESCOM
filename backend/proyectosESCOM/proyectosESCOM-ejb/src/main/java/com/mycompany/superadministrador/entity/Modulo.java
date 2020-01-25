/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "TBL_MODULO")
@NamedQueries({
    @NamedQuery(name = "consultaModulos", query = "SELECT m FROM Modulo m"),
    @NamedQuery(name = "consultaModuloEsp", query = "SELECT m from Modulo m WHERE m.pkModIdmodulo=:idModulo"),
    @NamedQuery(name = "consultarExistenciaModulo", query = "SELECT m from Modulo m WHERE m.nombreModulo=:nombreModulo")
   
})
public class Modulo implements Serializable{
    
    @Id
    @Basic(optional = false)
    @Column(name = "PK_MOD_IDMODULO")
    private Integer pkModIdmodulo;
    
    @Column(name = "MOD_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    @Size(max = 20)
    @Column(name = "MOD_ESTADO")
    private String estado;
    
    @Lob
    @Column(name = "MOD_IMAGEN")
    private byte[] imagen;
    
    @Size(max = 20)
    @Column(name = "MOD_NOMBREMODULO")
    private String nombreModulo;
    
    @Column(name = "MOD_ACRONIMO")
    private String acronimo;
    
    @Size(max = 120)
    @Column(name = "MOD_DESCRIPCIONMODULO")
    private String descripcionModulo;
    
    @OneToMany(mappedBy = "fkActIdmodulo")
    private List<Actividad> actividadList;

    public Modulo(){
        
    }

    public Modulo(Date ultimaModificacion, String estado, byte[] imagen, String nombreModulo, String acronimo, String descripcionModulo) {
        this.ultimaModificacion = ultimaModificacion;
        this.estado = estado;
        this.imagen = imagen;
        this.nombreModulo = nombreModulo;
        this.acronimo = acronimo;
        this.descripcionModulo = descripcionModulo;
    }

    public Integer getIdModulo() {
        return pkModIdmodulo;
    }

    public void setIdModulo(Integer idModulo) {
        this.pkModIdmodulo = idModulo;
    }

    public Date getUltimaModificacion() {
        return ultimaModificacion;
    }

    public void setUltimaModificacion(Date ultimaModificacion) {
        this.ultimaModificacion = ultimaModificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }


    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getDescripcionModulo() {
        return descripcionModulo;
    }

    public void setDescripcionModulo(String descripcionModulo) {
        this.descripcionModulo = descripcionModulo;
    }

    public List<Actividad> getActividadList() {
        return actividadList;
    }

    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    
    
}
