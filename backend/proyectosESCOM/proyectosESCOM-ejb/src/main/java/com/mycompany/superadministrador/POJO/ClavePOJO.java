package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase clave POJO para gestionar los cambios de contraseña
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class ClavePOJO {
    
    /**Variable token*/
    private String token;
    /**Variable para antigua clave*/
    private String antiguaClave;
    /**Variable para nueva clave*/
    private String nuevaClave;
    /**Variable datos solicitud para registrar en bitacora*/
    private DatosSolicitudPOJO datosSolicitud;

    /**Constructor vacio de la clase*/
    public ClavePOJO() {
    }

    /**Constructor con variables
     * @param token
     * @param antiguaClave
     * @param nuevaClave
     */
    public ClavePOJO(String token, String antiguaClave, String nuevaClave) {
        this.token = token;
        this.antiguaClave = antiguaClave;
        this.nuevaClave = nuevaClave;
    }
    
    /**Metodo get y set de las variables*/
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAntiguaClave() {
        return antiguaClave;
    }

    public void setAntiguaClave(String antiguaClave) {
        this.antiguaClave = antiguaClave;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    }

    
    
}
