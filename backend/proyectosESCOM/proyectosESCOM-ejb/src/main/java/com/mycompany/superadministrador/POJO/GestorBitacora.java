/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.POJO;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author aleja
 */
public class GestorBitacora {
    
    
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
