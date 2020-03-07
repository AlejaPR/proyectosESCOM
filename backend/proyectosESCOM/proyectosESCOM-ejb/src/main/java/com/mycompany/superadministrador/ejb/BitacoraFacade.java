/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.entity.Bitacora;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author aleja
 */
@Stateless
public class BitacoraFacade extends AbstractFacade<Bitacora> implements BitacoraFacadeLocal {

    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraFacade() {
        super(Bitacora.class);
    }

    @Override
    public void create(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Bitacora documento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarUsuario(DatosSolicitudPOJO solicitud) {
        em.createNativeQuery("INSERT INTO TBL_BITACORA (BTC_OPERACION,FK_BTC_IDUSUARIO,BTC_FECHABITACORA,FK_BTC_IDMODULO,BTC_IP,BTC_TABLAINVOLUCRADA"
                + ") VALUES (?,?,?,?,?,?)")
                .setParameter(1, solicitud.getOperacion())
                .setParameter(2, solicitud.getIdUsuario())
                .setParameter(3, new Date())
                .setParameter(4, solicitud.getIdModulo())
                .setParameter(5, solicitud.getIp())
                .setParameter(6, solicitud.getTablaInvolucrada())
                .executeUpdate();

    }

    /**
     * Metodo que realiza la consulta a la tabla usuario Devuelve los datos de
     * un usuario registrado con la cedula enviada
     *
     * @param reporte
     * @param usuario
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarUsuarioSinFechaFin(UsuarioPOJO usuario, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where b.idUsuario=:idUsuario AND b.fechaBitacora >= :fechaInicio", Bitacora.class);
        bitacora.setParameter("idUsuario", usuario.getId());
        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(),TemporalType.DATE);
        List<Bitacora> lista = bitacora.getResultList();
        for (Bitacora b : lista) {
            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setIdUsuario(b.getIdUsuario());
            bitacoraU.setOperacion(b.getOperacion());
            bitacoraU.setTablaInvolucrada(b.getTablaInvolucrada());
            bitacoraU.setIdModulo(b.getIdModulo());
            bitacoraU.setIp(b.getIp());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    @Override
    public List<DatosSolicitudPOJO> buscarUsuarioConFechaFin(UsuarioPOJO usuario, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

         List<Bitacora> lista =em.createNativeQuery("select * from tbl_bitacora where tbl_bitacora.fk_btc_idusuario = ? AND tbl_bitacora.btc_fechabitacora>= ? AND tbl_bitacora.btc_fechabitacora<= ?")
                .setParameter(1, usuario.getId())
                .setParameter(2, reporte.getFechaInicio(),TemporalType.DATE)
                .setParameter(3, reporte.getFechaFin())
                 .getResultList();
 
//        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where b.idUsuario=:idUsuario AND b.fechaBitacora>= :fechaInicio AND b.fechaBitacora<= :fechaFin", Bitacora.class);
//        bitacora.setParameter("idUsuario", usuario.getId());
//        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(),TemporalType.DATE);
//        bitacora.setParameter("fechaFin", reporte.getFechaFin(),TemporalType.DATE);
//        List<Bitacora> lista = bitacora.getResultList();
        
        for (Bitacora b : lista) {
            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setIdUsuario(b.getIdUsuario());
            bitacoraU.setOperacion(b.getOperacion());
            bitacoraU.setTablaInvolucrada(b.getTablaInvolucrada());
            bitacoraU.setIdModulo(b.getIdModulo());
            bitacoraU.setIp(b.getIp());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

}
