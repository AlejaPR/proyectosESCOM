package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase logica actividad
 * Contiene todos los metodos requeridos para la conexion de la logica con la entidad actividad
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface LogicaActividadFacadeLocal {
    
    public List<ActividadPOJO> devolverActividades()throws ExcepcionGenerica;
    
    public ActividadPOJO registrarActividad (ActividadPOJO actividad)throws ExcepcionGenerica;
     
    public void editarActividad(ActividadPOJO actividadEditar) throws ExcepcionGenerica;
    
    public void cambiarEstadoActividad(int idActividad,DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica;
    
    public ActividadPOJO traerActividadEspecifica(int idActividad) throws ExcepcionGenerica;
    
}
