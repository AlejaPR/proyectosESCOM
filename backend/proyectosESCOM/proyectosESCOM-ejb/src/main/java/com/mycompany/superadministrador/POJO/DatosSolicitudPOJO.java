package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase POJO para generar encargada de guardar los datos para el registro de bitacora
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class DatosSolicitudPOJO {
    
    /**Variable token*/
    private String token;
    /**Variable ip*/
    private String ip;
    /**Variable para la operacion realizada*/
    private String operacion;
    /**Variable id usuario*/
    private int idUsuario;
    /**Variable para tabla involucrada*/
    private String tablaInvolucrada;
    /**Variable id modulo*/
    private int idModulo;
    /**Variable para fecha de operacion*/
    private String fechaBitacora;
    /**Variable para correo de usuario*/
    private String correo;
    /**Variable nombre modulo*/
    private String nombreModulo;
    
    /**Constructor vacio de la clase*/
    public DatosSolicitudPOJO() {
    }
    
    /**Metodos get y set de las variables*/ 
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }
    
    
    
}
