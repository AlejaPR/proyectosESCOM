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
 * Esta es la clase de la entidad bitacora
 * Contiene todos los campos para la persistencia
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Entity
@Table(name = "TBL_BITACORA")
public class Bitacora implements Serializable{
    
    /**Variable id**/
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_BTC_IDBITACORA")
    private Integer idBitacora;
    
    /**Variable operacion realizada**/
    @Column(name = "BTC_OPERACION")
    private String operacion;
    
    /**Variable id usuario**/
    @Column(name = "FK_BTC_IDUSUARIO")
    private Integer idUsuario;
    
    /**Variable tabla involucrada**/
    @Column(name = "BTC_TABLAINVOLUCRADA")
    private String tablaInvolucrada;
    
    /**Variable fecha de operacion**/
    @Column(name = "BTC_FECHABITACORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBitacora;
    
    /**Variable id modulo**/
    @Column(name = "FK_BTC_IDMODULO")
    private Integer idModulo;

    /**Variable ip**/
    @Column(name = "BTC_IP")
    private String ip;
    
    /**Constructor vacio de la clase**/
    public Bitacora(){
        
    }

    /**Constructor con variables
     * @param operacion
     * @param idUsuario
     * @param tablaInvolucrada
     * @param fechaBitacora
     * @param idModulo
     * @param ip
     **/
    public Bitacora(String operacion, Integer idUsuario, String tablaInvolucrada, Date fechaBitacora, Integer idModulo, String ip) {
        this.operacion = operacion;
        this.idUsuario = idUsuario;
        this.tablaInvolucrada = tablaInvolucrada;
        this.fechaBitacora = fechaBitacora;
        this.idModulo = idModulo;
        this.ip = ip;
    }

    /**Metodos get y set de las variables**/
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
