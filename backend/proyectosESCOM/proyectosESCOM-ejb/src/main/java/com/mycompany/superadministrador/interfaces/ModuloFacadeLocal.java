package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.entity.Modulo;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase modulo
 * Contiene todos los metodos requeridos para la entidad modulo
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface ModuloFacadeLocal {

    void create(Modulo modulo);

    void edit(Modulo modulo);

    void remove(Modulo modulo);

    Modulo find(Object id);

    List<Modulo> findAll();

    List<Modulo> findRange(int[] range);

    int count();
    
    public List<Modulo> consultaDatosExistentes(String nombreModulo);
    
     public List<Modulo> consultaAcronimo(String acronimo);
    
    public void registrarModulo(ModuloPOJO modulo, String acronimo);
    
    public List<ModuloPOJO> listarModulos();
    
    public ModuloPOJO buscarModuloEspecifico(int idModulo);
    
    public void editarModulo(int idModulo, ModuloPOJO moduloEditar);
    
    public void cambiarEstadoModulo(int idModulo, String estado);
    
    public ModuloPOJO buscarModuloBitacora(String palabraBusqueda);
    
    public ModuloPOJO buscarModuloBitacoraId(int idModulo);
    
    public List<Modulo> consultaDatosExistentesUrlModulo(String urlModulo);
}
