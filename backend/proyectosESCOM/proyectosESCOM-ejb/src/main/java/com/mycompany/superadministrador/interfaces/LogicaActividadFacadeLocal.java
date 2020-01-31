/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aleja
 */
@Local
public interface LogicaActividadFacadeLocal {
    
    public List<ActividadPOJO> devolverActividades()throws ExcepcionGenerica;
    
    public void registrarActividad (ActividadPOJO actividad)throws ExcepcionGenerica;
     
    public void editarActividad(ActividadPOJO actividadEditar) throws ExcepcionGenerica;
    
    public void cambiarEstadoActividad(int idActividad) throws ExcepcionGenerica;
    
}
