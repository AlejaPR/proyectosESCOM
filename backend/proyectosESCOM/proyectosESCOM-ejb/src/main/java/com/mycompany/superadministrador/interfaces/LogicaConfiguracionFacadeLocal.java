package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase logica configuracion
 * Contiene todos los metodos requeridos para la conexion de la logica con la entidad configuracion
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface LogicaConfiguracionFacadeLocal {
    
    public void registrarConfiguracion (ConfiguracionPOJO configuracion)throws ExcepcionGenerica;
    
    List<ConfiguracionPOJO> listarEntorno() throws ExcepcionGenerica;
    
    List<ConfiguracionPOJO> listarInicio() throws ExcepcionGenerica;
    
    public List<ConfiguracionPOJO> listarConfiguracionCompleta() throws ExcepcionGenerica;
    
}
