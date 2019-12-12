
package com.mycompany.superadministrador.utilitarios;

import javax.ejb.ApplicationException;

/**
 * Clase encargada de capturar excepciones lanzadas en la aplicacion
 * @author Jeison Gaona - Alejandra Pabon
 */
@ApplicationException(rollback = true)
public class ExcepcionGenerica extends Exception{

    public ExcepcionGenerica(String mensaje) {
        super(mensaje);
    }
    
}
