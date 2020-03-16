package com.mycompany.superadministrador.entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Esta es la clase de la entidad tipo de documento
 * Contiene todos los campos para la persistencia y consultas named query
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Entity
@Table(name = "TBL_TIPODOCUMENTO")
@NamedQueries({
    @NamedQuery(name = "consultaDocumentos", query = "SELECT td from TipoDocumento td")
})
public class TipoDocumento implements Serializable {
    
    /**Variable id**/
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TIP_IDTIPODOCUMENTO")
    private Integer idTipodocumento;
    
    /**Variable tipo documento**/
    @Size(max = 20)
    @Column(name = "TIP_TIPODOCUMENTO")
    private String tipoDocumento;
    
    /**Variable ultima modificacion**/
    @Column(name = "TIP_ULTIMAMODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacion;
    
    /**Constructor vacio de la clase**/
    public TipoDocumento(){
        
    }

    /**Constructor con variables
     * @param tipoDocumento
     * @param ultimaModificacion
     **/
    public TipoDocumento(String tipoDocumento, Date ultimaModificacion) {
        this.tipoDocumento = tipoDocumento;
        this.ultimaModificacion = ultimaModificacion;
    }

    /**Metodos get y set de las variables**/
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
