package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase respuesta para el manejo de mensajes
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class Respuesta {
    
    /**Variable para los mensajes*/
    private String respuesta;

     /**Constructor con variables
     * @param respuesta
      */
    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**Constructor vacio de la clase*/
    public Respuesta() {
    }

     /**Metodos get y set de la  variable*/
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
