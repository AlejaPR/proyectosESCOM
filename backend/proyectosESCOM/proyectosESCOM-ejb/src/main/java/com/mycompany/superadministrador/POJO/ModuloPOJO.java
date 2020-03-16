package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase POJO de la entidad modulo
 * contiene las variables de la tabla sin anotaciones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class ModuloPOJO {
    
    /**Variable id modulo*/
    private int idModulo;
    /**Variable estado de modulo*/
    private String estadoModulo;
    /**Variable para almacerna el logo del modulo*/
    private byte[] imagenModulo;
    /**Variable nombre*/
    private String nombreModulo;
    /**Variable descripcion*/
    private String descripcionModulo;
    /**Variable acronimo*/
    private String acronimo;
    /**Variable url*/
    private String url;
    /**Variable datos solicitud para registro de bitacora*/
    private DatosSolicitudPOJO datosSolicitud;

    /**Constructor vacio de la clase*/
    public ModuloPOJO() {
    }

    /**Constructor con variables
     * @param idModulo
     * @param estadoModulo
     * @param imagenModulo
     * @param nombreModulo
     * @param descripcionModulo
     * @param acronimo
     */
    public ModuloPOJO(int idModulo, String estadoModulo, byte[] imagenModulo, String nombreModulo, String descripcionModulo, String acronimo) {
        this.idModulo = idModulo;
        this.estadoModulo = estadoModulo;
        this.imagenModulo = imagenModulo;
        this.nombreModulo = nombreModulo;
        this.descripcionModulo = descripcionModulo;
        this.acronimo = acronimo;
    }

     /**Metodos get y set de las variables*/
    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getEstadoModulo() {
        return estadoModulo;
    }

    public void setEstadoModulo(String estadoModulo) {
        this.estadoModulo = estadoModulo;
    }

    public byte[] getImagenModulo() {
        return imagenModulo;
    }

    public void setImagenModulo(byte[] imagenModulo) {
        this.imagenModulo = imagenModulo;
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

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DatosSolicitudPOJO getDatosSolicitud() {
        return datosSolicitud;
    }

    public void setDatosSolicitud(DatosSolicitudPOJO datosSolicitud) {
        this.datosSolicitud = datosSolicitud;
    }
    
    
}
