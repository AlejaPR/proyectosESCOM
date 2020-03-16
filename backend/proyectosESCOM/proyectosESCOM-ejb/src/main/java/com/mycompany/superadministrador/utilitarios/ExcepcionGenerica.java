package com.mycompany.superadministrador.utilitarios;
import javax.ejb.ApplicationException;
/**
 * Clase encargada de capturar excepciones lanzadas en la aplicacion
 * @author Alejandra Pabon-Jeison Gaona
 */
@ApplicationException(rollback = true)
public class ExcepcionGenerica extends Exception{

    public ExcepcionGenerica(String mensaje) {
        super(mensaje);
    }
    
}
