package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.entity.UsuarioActividad;
import java.util.List;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase usuario-actividad
 * Contiene todos los metodos requeridos para la entidad usuario-actividad
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface UsuarioActividadFacadeLocal {

    void create(UsuarioActividad usuarioActividad);

    void edit(UsuarioActividad usuarioActividad);

    void remove(UsuarioActividad usuarioActividad);

    UsuarioActividad find(Object id);

    List<UsuarioActividad> findAll();

    List<UsuarioActividad> findRange(int[] range);

    int count();
    
     public void eliminarActividadUsuario(Usuario usuario, Actividad actividad);
}
