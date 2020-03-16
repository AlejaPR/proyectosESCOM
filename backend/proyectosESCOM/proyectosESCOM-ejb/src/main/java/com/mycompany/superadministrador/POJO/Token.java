package com.mycompany.superadministrador.POJO;
/**
 * Esta es la clase para la construccion del token
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class Token {
    
    /**Variable header del token*/
    private String header;
    /**Variable body del token*/
    private String body;
    /**Variable firma del token*/
    private String firma;
    /**Variable issuer del token*/
    private String issuer;

    /**Constructor vacio de la clase*/
    public Token() {
    }

    /**Constructor con variables
     * @param header
     * @param body
     * @param firma
     * @param issuer
     */
    public Token(String header, String body, String firma, String issuer) {
        this.header = header;
        this.body = body;
        this.firma = firma;
        this.issuer = issuer;
    }

    /**Metodos get y set de las variables */
    public String getHeader() {
        return header;
    }

    
    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
    
}
