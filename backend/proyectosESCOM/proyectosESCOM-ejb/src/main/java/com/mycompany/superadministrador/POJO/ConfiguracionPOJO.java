package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase POJO de la entidad configuracion
 * contiene las variables de la tabla sin anotaciones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class ConfiguracionPOJO {
    
    /**Variable id*/
    private int idConfiguracion;
    /**Variable para almacenar imagen logo*/
    private byte[] logo;
    /**Variable para almacenar imagen login*/
    private byte[] imagenLogin;
    /**Variable para color de barra superior*/
    private String barraSuperior;
    /**Variable para color de barra lateral*/
    private String barraLateral;
    /**Variable para color de botones*/
    private String botones;
    /**Variable datos solicitud para el registro de bitacora*/
    private DatosSolicitudPOJO datosSolicitud;
    
    /**Constructor vacio de la clase*/
    public ConfiguracionPOJO() {
    }

    /**Constructor con variables
     * @param idConfiguracion
     * @param logo
     * @param imagenLogin
     * @param barraSuperior
     * @param barraLateral
     * @param botones
     */
    public ConfiguracionPOJO(int idConfiguracion,byte[] logo, byte[] imagenLogin, String barraSuperior, String barraLateral, String botones) {
        this.idConfiguracion = idConfiguracion;
        this.logo = logo;
        this.imagenLogin = imagenLogin;
        this.barraSuperior = barraSuperior;
        this.barraLateral = barraLateral;
        this.botones = botones;
    }

    /**Metodos get y set de las variables*/
    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }
    

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public byte[] getImagenLogin() {
        return imagenLogin;
    }

    public void setImagenLogin(byte[] imagenLogin) {
        this.imagenLogin = imagenLogin;
    }

    public String getBarraSuperior() {
        return barraSuperior;
    }

    public void setBarraSuperior(String barraSuperior) {
        this.barraSuperior = barraSuperior;
    }

    public String getBarraLateral() {
        return barraLateral;
    }

    public void setBarraLateral(String barraLateral) {
        this.barraLateral = barraLateral;
    }

    public String getBotones() {
        return botones;
    }

    public void setBotones(String botones) {
        this.botones = botones;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    }

    
    
}
