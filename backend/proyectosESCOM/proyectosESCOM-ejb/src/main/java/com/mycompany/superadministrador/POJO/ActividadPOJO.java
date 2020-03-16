package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase POJO de la entidad actividad 
 * contiene las variables de la tabla sin anotaciones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class ActividadPOJO {

    /**Variable id de la actividad*/
    private int idActividad;
    /**Variable nombre de la actividad*/
    private String nombre;
    /**Variable descripcion de la actividad*/
    private String descripcionActividad;
    /**Variable para el nombre del modulo*/
    private String moduloActividad;
    /**Variable estado de la actividad*/
    private String estado;
    /**Variable datos solicitud para la bitacora*/
    private DatosSolicitudPOJO datosSolicitud;
    /**Variable id modulo*/
    private int idModulo;

    /**Constructor vacio de la clase */
    public ActividadPOJO() {
    }

    /**Constructor que maneja dos variables para metodos especificos
     * @param idActividad
     * @param nombre
     */
    public ActividadPOJO(int idActividad, String nombre) {
        this.idActividad = idActividad;
        this.nombre = nombre;
    }

    /**Constructor que maneja cuatro variables para metodos especificos
     * @param idActividad
     * @param nombre
     * @param descripcionActividad
     * @param moduloActividad
     */
    public ActividadPOJO(int idActividad, String nombre, String descripcionActividad, String moduloActividad) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.descripcionActividad = descripcionActividad;
        this.moduloActividad = moduloActividad;
    }

    /**Constructor que maneja tres variables para metodos especificos
     * @param idActividad
     * @param nombre
     * @param idModulo
     */
    public ActividadPOJO(int idActividad, String nombre, int idModulo) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.idModulo = idModulo;
    }

    /**Constructor que maneja cuatro variables para metodos especificos
     * @param idActividad
     * @param nombre
     * @param idModulo
     * @param estado
     */
    public ActividadPOJO(int idActividad, String nombre, int idModulo, String estado) {
        this.idActividad = idActividad;
        this.nombre = nombre;
        this.idModulo = idModulo;
        this.estado = estado;
    }

    /**Metodos get y set de las variables*/
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public String getModuloActividad() {
        return moduloActividad;
    }

    public void setModuloActividad(String moduloActividad) {
        this.moduloActividad = moduloActividad;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    } 
}
