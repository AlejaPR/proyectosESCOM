
package com.mycompany.superadministrador.POJO;

/**
 * Clase encargada de guardar los datos para el registro de bitacora
 * @author jeiso
 */
public class DatosSolicitudPOJO {
    private String token;
    private String ip;
    private String operacion;
    private int idUsuario;
    private String tablaInvolucrada;
    private int idModulo;
    private String fechaBitacora;
    
    public DatosSolicitudPOJO() {
    }
    

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTablaInvolucrada() {
        return tablaInvolucrada;
    }

    public void setTablaInvolucrada(String tablaInvolucrada) {
        this.tablaInvolucrada = tablaInvolucrada;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getFechaBitacora() {
        return fechaBitacora;
    }

    public void setFechaBitacora(String fechaBitacora) {
        this.fechaBitacora = fechaBitacora;
    }

    
    
    
}
