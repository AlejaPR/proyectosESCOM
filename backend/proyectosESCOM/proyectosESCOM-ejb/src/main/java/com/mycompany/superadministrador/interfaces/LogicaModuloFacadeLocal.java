package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase logica modulo
 * Contiene todos los metodos requeridos para la conexion de la logica con la entidad modulo
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface LogicaModuloFacadeLocal {
    
    public void registrarModulo(ModuloPOJO modulo)throws ExcepcionGenerica;
    
    public List<ModuloPOJO> devolverModulos()throws ExcepcionGenerica;
    
    public ModuloPOJO traerModuloId(int idModulo) throws ExcepcionGenerica;
    
    public List<ActividadPOJO> listarActividadesModulo(int idModulo) throws ExcepcionGenerica;
    
    public void editarModulo(int idModulo, ModuloPOJO moduloEditar) throws ExcepcionGenerica;
    
    public void cambiarEstadoModulo(int idModulo,DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica;
    
    public void cambiarEstadoActividadModulo(List<ActividadPOJO> idActividad) throws ExcepcionGenerica;
    
}
