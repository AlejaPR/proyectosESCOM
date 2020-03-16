package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase POJO de la tipo documento
 * contiene las variables de la tabla sin anotaciones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
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
