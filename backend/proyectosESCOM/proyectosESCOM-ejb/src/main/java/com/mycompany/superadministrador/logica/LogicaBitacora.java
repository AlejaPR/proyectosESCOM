/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jeiso
 */
@Stateless
public class LogicaBitacora implements LogicaBitacoraFacadeLocal {

    @EJB
    ActividadFacadeLocal actividadDB;

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

}
