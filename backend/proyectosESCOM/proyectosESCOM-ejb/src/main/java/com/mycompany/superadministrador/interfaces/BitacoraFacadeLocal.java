package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Bitacora;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase bitacora
 * Contiene todos los metodos requeridos para la entidad bitacora
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
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
