/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental;

import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import javax.ejb.Local;

/**
 *
 * @author jeiso
 */
@Local
public interface PruebaEJB {
    public UsuarioPOJO recuperarDatosUsuario(String token);
    
}
