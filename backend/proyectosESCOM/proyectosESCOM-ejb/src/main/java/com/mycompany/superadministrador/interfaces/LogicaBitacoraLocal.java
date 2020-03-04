/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import javax.ejb.Local;

/**
 *
 * @author jeiso
 */
@Local
public interface LogicaBitacoraLocal {
    
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud);
    
}
