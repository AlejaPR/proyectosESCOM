/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.POJO.Respuesta;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import javax.ejb.EJB;
import javax.ws.rs.Path;

/**
 *
 * @author aleja
 */
@javax.enterprise.context.RequestScoped
@Path("bitacora")
public class BitacoraServicio {
    
    @EJB
    LogicaBitacoraFacadeLocal bitacoraLogica;

    private final Respuesta respuesta = new Respuesta();

    public BitacoraServicio() {
        
    }
    
    

    
}
