/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aleja
 */
@Local
public interface LogicaConfiguracionFacadeLocal {
    
    public void registrarConfiguracion (ConfiguracionPOJO configuracion)throws ExcepcionGenerica;
    
    List<ConfiguracionPOJO> listarEntorno() throws ExcepcionGenerica;
    
    List<ConfiguracionPOJO> listarInicio() throws ExcepcionGenerica;
    
    public List<ConfiguracionPOJO> listarConfiguracionCompleta() throws ExcepcionGenerica;
    
}
