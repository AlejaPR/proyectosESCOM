/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;

/**
 *
 * @author jeiso
 */
public interface UtilitarioFacadeLocal {
    
    public UsuarioPOJO devolverInformacionDeUsuario(String token);
    
    public void registroLogger(String error);
    
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud);
}
