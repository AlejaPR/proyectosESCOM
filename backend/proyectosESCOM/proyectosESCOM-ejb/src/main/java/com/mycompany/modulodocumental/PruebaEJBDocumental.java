/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modulodocumental;

import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aleja
 */
@Stateless
public class PruebaEJBDocumental implements PruebaEJB {

    public PruebaEJBDocumental() {
    }
    
    @EJB
    UtilitarioFacadeLocal utilitarioDB;

    @Override
    public UsuarioPOJO recuperarDatosUsuario(String token) {
        return utilitarioDB.devolverInformacionDeUsuario(token);
    }
   
}
