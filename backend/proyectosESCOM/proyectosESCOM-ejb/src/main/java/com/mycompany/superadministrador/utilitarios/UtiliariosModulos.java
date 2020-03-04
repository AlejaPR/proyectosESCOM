/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.utilitarios;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.GestorBitacora;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jeiso
 */
@Stateless
public class UtiliariosModulos implements UtilitarioFacadeLocal {
    
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;
    
    @EJB
    LogicaBitacoraLocal bitacoraLogica;
    
    static Logger bitacora = GestorBitacora.getBitacora("com.mycompany.superadministrador.services.Login", "./bitacoraADMIN.txt", Level.SEVERE);
    
     @Override
    public UsuarioPOJO devolverInformacionDeUsuario(String token){
        return usuarioLogica.devolverDatosUsuario(token);
    }
    
    @Override
    public void registroLogger(String error){
        bitacora.severe(error);
    }
    
    @Override
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud){
        bitacoraLogica.registrarEnBitacora(solicitud);
    }
    
}
