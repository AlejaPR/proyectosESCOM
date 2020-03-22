package com.mycompany.superadministrador.POJO;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Esta es la clase para gestionar el logger y la bitacora
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
public class GestorBitacora {

    /**
     * Metodo para generar el logger
     *
     * @param clase
     * @param nombreArchivoBitacora
     * @param nivel
     * @param error
     * @param metodo
     * @return 
     *
     */
    public static Logger getBitacora(String clase, String nombreArchivoBitacora, Level nivel, String metodo, String error) {
        Logger bitacora = null;
        bitacora = Logger.getLogger(clase);

        try {
            Handler handler = new FileHandler(nombreArchivoBitacora, true);
            SimpleFormatter formateador = new SimpleFormatter();
            handler.setFormatter(formateador);
            bitacora.addHandler(handler);
            bitacora.logp(nivel, clase, metodo, error);

        } catch (IOException e) {
            bitacora = Logger.getGlobal();
            bitacora.log(Level.SEVERE, "Error en la creacion de bitacora{0}", e.getMessage());
            return bitacora;
        }
        return bitacora;
    }
}
