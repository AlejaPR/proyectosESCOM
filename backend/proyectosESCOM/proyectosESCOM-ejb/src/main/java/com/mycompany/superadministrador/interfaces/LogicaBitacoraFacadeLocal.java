/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jeiso
 */
@Local
public interface LogicaBitacoraFacadeLocal {
    
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud);
    
    public List<DatosSolicitudPOJO> consultar(ReportePOJO reporte) throws ExcepcionGenerica;
    
    
}
