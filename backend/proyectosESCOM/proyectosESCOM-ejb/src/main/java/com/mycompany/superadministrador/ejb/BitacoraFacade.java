package com.mycompany.superadministrador.ejb;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.BitacoraFacadeLocal;
import com.mycompany.superadministrador.entity.Bitacora;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
/**
 * Este es el bean de la entidad bitacora
 * Contiene todos los metodos para la persistencia y consultas a la base de datos
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
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

    /**Inyeccion a la interfaz de usuario facade**/
    @EJB 
    UsuarioFacadeLocal usuarioDB;
    /**Inyeccion a la interfaz de modulo facade*/
    @EJB 
    ModuloFacadeLocal moduloDB;
    
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

    /**
     * Metodo que realiza el registro en bitacora de las operaciones del sistema
     *
     * @param solicitud
     *
     */
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
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte del usuario solicitado con fecha de inicio
     *
     * @param reporte
     * @param idUsuario
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarUsuarioSinFechaFin(Integer idUsuario, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where b.idUsuario=:idUsuario AND b.fechaBitacora >= :fechaInicio", Bitacora.class);
        bitacora.setParameter("idUsuario", idUsuario);
        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        List<Bitacora> lista = bitacora.getResultList();

        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    /**
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte del usuario solicitado con fecha de inicio y fecha fin
     *
     * @param reporte
     * @param idUsuario
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarUsuarioConFechaFin(Integer idUsuario, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

        TypedQuery<Bitacora> bitacoraDos = em.createQuery("select b from Bitacora b where (b.idUsuario=:idUsuario AND b.fechaBitacora >= :fechaInicio) "
                + "AND (b.idUsuario=:idUsuario AND b.fechaBitacora <= :fechaFin)", Bitacora.class);
        bitacoraDos.setParameter("idUsuario", idUsuario);
        bitacoraDos.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        bitacoraDos.setParameter("fechaFin", reporte.getFechaFin(), TemporalType.DATE);
        List<Bitacora> lista = bitacoraDos.getResultList();

        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    /**
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte del modulo solicitado con fecha de inicio
     *
     * @param reporte
     * @param idModulo
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarModuloSinFechaFin(Integer idModulo, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();
        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where b.idModulo =:idModulo AND b.fechaBitacora >= :fechaInicio", Bitacora.class);
        bitacora.setParameter("idModulo", idModulo);
        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        List<Bitacora> lista = bitacora.getResultList();
        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

           DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());
            
            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    /**
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte del modulo solicitado con fecha de inicio y fecha fin
     *
     * @param reporte
     * @param idModulo
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarModuloConFechaFin(Integer idModulo, ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

        TypedQuery<Bitacora> bitacoraDos = em.createQuery("select b from Bitacora b where (b.idModulo=:idModulo AND b.fechaBitacora >=:fechaInicio)"
                + " AND (b.idModulo=:idModulo AND b.fechaBitacora <=:fechaFin)", Bitacora.class);
        bitacoraDos.setParameter("idModulo", idModulo);
        bitacoraDos.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        bitacoraDos.setParameter("fechaFin", reporte.getFechaFin(), TemporalType.DATE);
        List<Bitacora> lista = bitacoraDos.getResultList();

        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    /**
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte de la actividad solicitada con fecha de inicio
     *
     * @param reporte
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarActividadSinFechaFin(ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();

        String busqueda = reporte.getPalabraBusqueda().toLowerCase();
        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where "
                + "(Lower(b.operacion)  like :palabraBusqueda AND b.fechaBitacora >= :fechaInicio)", Bitacora.class);
        bitacora.setParameter("palabraBusqueda", "%" + busqueda + "%");
        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        List<Bitacora> lista = bitacora.getResultList();
        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }

    /**
     * Metodo que realiza la consulta a la tabla bitacora 
     * Devuelve los datos de reporte de la actividad solicitada con fecha de inicio y fecha fin
     *
     * @param reporte
     * @return
     *
     */
    @Override
    public List<DatosSolicitudPOJO> buscarActividadConFechaFin(ReportePOJO reporte) {

        List<DatosSolicitudPOJO> listaBitacora = new ArrayList();
        String busqueda = reporte.getPalabraBusqueda().toLowerCase();
        TypedQuery<Bitacora> bitacora = em.createQuery("select b from Bitacora b where "
                + "(Lower(b.operacion)  like :palabraBusqueda AND b.fechaBitacora >= :fechaInicio) AND (Lower(b.operacion)  like :palabraBusqueda AND b.fechaBitacora <= :fechaFin)", Bitacora.class);
        bitacora.setParameter("palabraBusqueda", "%" + busqueda + "%");
        bitacora.setParameter("fechaInicio", reporte.getFechaInicio(), TemporalType.DATE);
        bitacora.setParameter("fechaFin", reporte.getFechaFin(), TemporalType.DATE);
        List<Bitacora> lista = bitacora.getResultList();
        for (int i = 0; i < lista.size(); i++) {
            /**
             * Conversion de fecha Oracle
             */
            SimpleDateFormat formatoUsuario = new SimpleDateFormat("dd-MM-yyyy");
            Date fecha = null;
            String fechaN = null;

            if (lista.get(i).getFechaBitacora() == null) {
                fecha = null;
            } else {
                fecha = lista.get(i).getFechaBitacora();
                fechaN = formatoUsuario.format(fecha);
            }

            DatosSolicitudPOJO bitacoraU = new DatosSolicitudPOJO();
            bitacoraU.setOperacion(lista.get(i).getOperacion());
            bitacoraU.setTablaInvolucrada(lista.get(i).getTablaInvolucrada());
            bitacoraU.setIp(lista.get(i).getIp());
            bitacoraU.setFechaBitacora(fechaN);
            
            UsuarioPOJO usuarioBitacora = devolverUsuarioBitacora(lista.get(i).getIdUsuario());
            bitacoraU.setIdUsuario(lista.get(i).getIdUsuario());
            bitacoraU.setCorreo(usuarioBitacora.getCorreoElectronico());
            
            ModuloPOJO moduloBitacora = devolverModuloBitacora(lista.get(i).getIdModulo());
            bitacoraU.setIdModulo(lista.get(i).getIdModulo());
            bitacoraU.setNombreModulo(moduloBitacora.getNombreModulo());

            listaBitacora.add(bitacoraU);
        }
        return listaBitacora;
    }
    
    /**Metodo que conecta con la interfaz de usuario para consultar el usuario con el id
     * @param id
     * @return 
     **/
    public UsuarioPOJO devolverUsuarioBitacora(int id){
        
        UsuarioPOJO usuario = usuarioDB.buscarUsuarioBitacoraId(id);
        return usuario;
    }
    
    /**Metodo que conecta con la interfaz de modulo para consultar el modulo con el id
     * @param id
     * @return 
     **/
    public ModuloPOJO devolverModuloBitacora(int id){
        
        ModuloPOJO modulo = moduloDB.buscarModuloBitacoraId(id);
        return modulo;
    }

}
