/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Bitacora;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aleja
 */
@Local
public interface BitacoraFacadeLocal {

    void create(Bitacora bitacora);

    void edit(Bitacora bitacora);

    void remove(Bitacora bitacora);

    Bitacora find(Object id);

    List<Bitacora> findAll();

    List<Bitacora> findRange(int[] range);

    int count();
    
    public void registrarUsuario(DatosSolicitudPOJO solicitud);
    
    public List<DatosSolicitudPOJO> buscarUsuarioSinFechaFin(UsuarioPOJO usuario, ReportePOJO reporte);
    
    public List<DatosSolicitudPOJO> buscarUsuarioConFechaFin(UsuarioPOJO usuario, ReportePOJO reporte);
    
    public List<DatosSolicitudPOJO> buscarModuloSinFechaFin(ModuloPOJO usuario, ReportePOJO reporte);
    
    public List<DatosSolicitudPOJO> buscarModuloConFechaFin(ModuloPOJO usuario, ReportePOJO reporte);
    
    public List<DatosSolicitudPOJO> buscarActividadSinFechaFin(ReportePOJO reporte);
    
    public List<DatosSolicitudPOJO> buscarActividadConFechaFin(ReportePOJO reporte);
    
    
}
