package com.mycompany.superadministrador.POJO;
import java.util.Date;
/**
 * Esta es la clase POJO para generar los reportes 
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class ReportePOJO {
    
    /**Variable id para filtrar la busqueda**/
    private int idBusqueda;
    /**Variable para filtrar la busqueda con palabra*/
    private String palabraBusqueda;
    /**Variable fecha inicio*/
    private Date fechaInicio;
    /**Variable fecha fin*/
    private Date fechaFin;

    /**Constructor de la clase*/
    public ReportePOJO() {
    }
    
     /**Constructor con variables
     * @param idBusqueda
     * @param palabraBusqueda
     * @param fechaInicio
     * @param fechaFin
      */
    public ReportePOJO(int idBusqueda, String palabraBusqueda, Date fechaInicio, Date fechaFin) {
        this.idBusqueda = idBusqueda;
        this.palabraBusqueda = palabraBusqueda;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    } 

     /**Metodos get y set de las variables*/
    public int getIdBusqueda() {
        return idBusqueda;
    }

    public void setIdBusqueda(int idBusqueda) {
        this.idBusqueda = idBusqueda;
    }

    public String getPalabraBusqueda() {
        return palabraBusqueda;
    }

    public void setPalabraBusqueda(String palabraBusqueda) {
        this.palabraBusqueda = palabraBusqueda;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
    
}
