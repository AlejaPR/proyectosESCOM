
package com.mycompany.superadministrador.POJO;

/**
 * Clase encargada de guardar los datos para el registro de bitacora
 * @author jeiso
 */
public class DatosSolicitudPOJO {
    private String token;
    private String ip;
    private String MAC;

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

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }
    
    
}
