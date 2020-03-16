package com.mycompany.superadministrador.POJO;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Esta es la clase para gestionar el logger y la bitacora
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public class GestorBitacora {
    
    /**Metodo para generar el logger
     * @param paquete
     * @param nombreArchivoBitacora
     * @param nivel
     * @return 
     **/
    public static Logger getBitacora(String paquete, String nombreArchivoBitacora, Level nivel){
         Logger bitacora = null;
         bitacora = Logger.getLogger(paquete);
         
        try{
             FileHandler handler = new FileHandler(nombreArchivoBitacora);
             SimpleFormatter formateador = new SimpleFormatter();
             handler.setFormatter(formateador);
             bitacora.addHandler(handler);            
         }catch(IOException e){
             bitacora = Logger.getGlobal();
             bitacora.log(Level.SEVERE, "Error en la creacion de bitacora{0}", e.getMessage());
             return bitacora;
         }
         bitacora.setLevel(nivel);
         return bitacora;
     }
}
