/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;

/**
 *
 * @author aleja
 */
public class TipoDocumentoPOJO {
    /**Variable para id tipo de documento**/
    private int idTipoDocumento;
    /**Variable para tipo de documento**/
    private String tipoDocumento;

    /**Constructor vacio de la clase**/
    public TipoDocumentoPOJO(){
        
    }

    /**Constructor con variables
     * @param idTipoDocumento
     * @param tipoDocumento
     **/
    public TipoDocumentoPOJO(int idTipoDocumento, String tipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.tipoDocumento = tipoDocumento;
    }
    
    /**Metodo get de id tipo documento
     * @return 
     **/
    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**Metodo set de id tipo documento
     * @param idTipoDocumento
     **/
    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    /**Metodo get de tipo documento
     * @return 
     **/
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**Metodo set de tipo documento
     * @param tipoDocumento
     **/
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    
    
    
}
