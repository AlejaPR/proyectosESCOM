/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author jeiso
 */
@Stateless
public class LogicaBitacora implements LogicaBitacoraFacadeLocal {

    @EJB
    ActividadFacadeLocal actividadDB;
    
    @EJB
    UsuarioFacadeLocal usuarioDB;
    
    @EJB
    ModuloFacadeLocal moduloDB;

    @EJB
    BitacoraFacadeLocal bitacoraDB;

    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    /**
     *
     * @param solicitud
     */
    @Override
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud) {
        try {
            solicitud.setIdUsuario(usuarioLogica.devolverDatosUsuario(solicitud.getToken()).getId());
            String actividadConAcronimo = solicitud.getOperacion();
            String actividadSinAcronimo = solicitud.getOperacion().substring(3);
            solicitud.setOperacion(actividadSinAcronimo);
            solicitud.setIdModulo(actividadDB.buscarActividadPorNombre(actividadConAcronimo).get(0).getModulo().getIdModulo());
            bitacoraDB.registrarUsuario(solicitud);
        } catch (Exception e) {

        }
    }
    
    /**
     * Metodo que llama a la consulta de bitacora
     *
     * @param reporte
     * @return 
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<DatosSolicitudPOJO> consultar(ReportePOJO reporte) throws ExcepcionGenerica {
        try {
            
            if (reporte.getIdBusqueda() == 1) {
                UsuarioPOJO usuario = usuarioDB.buscarUsuarioBitacora(reporte.getPalabraBusqueda());
                if(usuario!=null){
                    if (reporte.getFechaFin() == null) {
                        List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioSinFechaFin(usuario, reporte);
                        return listaSolicitud;
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarUsuarioConFechaFin(usuario, reporte);
                        return listaSolicitud;
                    }
                }else{
                    throw new NoResultException("No se encontro ningun usuario con el parametro consultado");
                }
                
            } else if(reporte.getIdBusqueda() == 2){
                ModuloPOJO modulo = moduloDB.buscarModuloBitacora(reporte.getPalabraBusqueda());
                if(modulo!=null){
                    if (reporte.getFechaFin() == null) {
                        List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloSinFechaFin(modulo, reporte);
                        return listaSolicitud;
                    } else {
                        List<DatosSolicitudPOJO> listaSolicitud = bitacoraDB.buscarModuloConFechaFin(modulo, reporte);
                        return listaSolicitud;
                    }
                }else{
                    throw new NoResultException("No se encontro ningun modulo con el parametro consultado");
                }
                
            } else if(reporte.getIdBusqueda() == 3){
                
            }
            else {
                throw new NoResultException("No se encontro ningun resultado con los parametros consultados");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta de bitacora");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
        return null;
    }


}
