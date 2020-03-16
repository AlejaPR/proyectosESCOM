package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.ConfiguracionPOJO;
import com.mycompany.superadministrador.entity.Configuracion;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase configuracion
 * Contiene todos los metodos requeridos para la entidad configuracion
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface ConfiguracionFacadeLocal {

    void create(Configuracion configuracion);

    void edit(Configuracion configuracion);

    void remove(Configuracion configuracion);

    Configuracion find(Object id);

    List<Configuracion> findAll();

    List<Configuracion> findRange(int[] range);

    int count();
    
    public void registrarConfiguracion(int idConfiguracion,ConfiguracionPOJO configuracion);
    
}
