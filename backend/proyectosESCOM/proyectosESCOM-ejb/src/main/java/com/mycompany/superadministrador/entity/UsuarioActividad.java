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
 * Esta es la clase de la entidad usuario-actividad
 * Contiene todos los campos para la persistencia y consultas named query
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Entity
@Table(name = "TBL_USUARIOACTIVIDAD")
public class UsuarioActividad implements Serializable {
    
    /**Variable id**/
    @Id
    @Column(name = "PK_UAC_IDRELACION")
    private Integer idRelacion;
    
    /**Variable ultima modificacion**/
    @Column(name = "UAC_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    /**Variable para la relacion con la entidad actividad**/
    @Id
    @JoinColumn(name = "FK_UAC_IDACTIVIDAD")
    @ManyToOne
    private Actividad actividad;
    
    /**Variable para la relacion con la entidad usuario**/
    @Id
    @JoinColumn(name = "FK_UAC_IDUSUARIO")
    @ManyToOne
    private Usuario usuario;

    /**Constructor vacio de la clase**/
    public UsuarioActividad(){
        
    }
    
    /**Constructor con variables
     * @param ultimaModificacion
     * @param actividad
     * @param usuario
     **/
    public UsuarioActividad(Date ultimaModificacion, Actividad actividad, Usuario usuario) {
        this.ultimaModificacion = ultimaModificacion;
        this.actividad = actividad;
        this.usuario = usuario;
    }

    /**Metodos get y set de las variables**/
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
