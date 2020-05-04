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
    
    public List<DatosSolicitudPOJO> buscarUsuarioSinFechaFin(Integer idUsuario, ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
    public List<DatosSolicitudPOJO> buscarUsuarioConFechaFin(Integer idUsuario, ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
    public List<DatosSolicitudPOJO> buscarModuloSinFechaFin(Integer idModulo, ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
    public List<DatosSolicitudPOJO> buscarModuloConFechaFin(Integer idModulo, ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
    public List<DatosSolicitudPOJO> buscarActividadSinFechaFin(ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
    public List<DatosSolicitudPOJO> buscarActividadConFechaFin(ReportePOJO reporte,int cantidadDatos,int paginaActual);
    
     public int buscarUsuarioSinFechaFinCantidad(Integer idUsuario, ReportePOJO reporte);
    
    public int buscarUsuarioConFechaFinCantidad(Integer idUsuario, ReportePOJO reporte);
    
    public int buscarModuloSinFechaFinCantidad(Integer idModulo, ReportePOJO reporte);
    
    public int buscarModuloConFechaFinCantidad(Integer idModulo, ReportePOJO reporte);
    
    public int buscarActividadSinFechaFinCantidad(ReportePOJO reporte);
    
    public int buscarActividadConFechaFinCantidad(ReportePOJO reporte);
    
    
}
