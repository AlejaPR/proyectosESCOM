package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ReportePOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase logica bitacora
 * Contiene todos los metodos requeridos para la conexion de la logica con la entidad bitacora
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface LogicaBitacoraFacadeLocal {
    
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud);
    
    public List<DatosSolicitudPOJO> consultar(ReportePOJO reporte,int cantidadDeDatos,int paginaActual) throws ExcepcionGenerica;
    
    public int cantidadDeDatos(ReportePOJO reporte)throws ExcepcionGenerica;
    
    public List<DatosSolicitudPOJO> consultarBitacoraCompleta(ReportePOJO reporte) throws ExcepcionGenerica;
}
