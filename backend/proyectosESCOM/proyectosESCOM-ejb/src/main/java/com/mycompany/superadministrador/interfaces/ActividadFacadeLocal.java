package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import java.util.List;
import javax.ejb.Local;

/**
 * Esta es la interfaz para la clase actividad Contiene todos los metodos
 * requeridos para la entidad actividad
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Local
public interface ActividadFacadeLocal {

    void create(Actividad actividad);

    void edit(Actividad actividad);

    void remove(Actividad actividad);

    Actividad find(Object id);

    List<Actividad> findAll();

    List<Actividad> findRange(int[] range);

    int count();

    List<ActividadPOJO> listarActividadesUsuario(Integer idUsuario);

    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int idUsuario, int idModulo);

    void eliminarActividadUsuario(Integer idUsuario, Integer idActividad);

    List<ActividadPOJO> listarActividadesModulo(Modulo modulo);

    void cambiarEstadoActividadModulo(int idActividad, String estado);

    List<Actividad> buscarActividadPorNombre(String nombreActividad);

    ActividadPOJO registrarActividad(ActividadPOJO actividad, String nombreActividad, Modulo modulo);

    public void editarActividad(ActividadPOJO actividadEditar);

    public void cambiarEstadoActividad(int idActividad, String estado);

    public ActividadPOJO buscarActividadEspecifica(int idActividad);

    List<ActividadPOJO> listarActividadesUsuarioActivas(Integer idUsuario);

    public List<ActividadPOJO> listarActividadesUsuarioSuper(Integer idUsuario);

    public int filtrarActividadesCantidad(String palabraBusqueda);

    public List<ActividadPOJO> filtrarActividades(String palabraBusqueda, int cantidadDatos, int paginaActual);

    public List<ActividadPOJO> listarActividades(int cantidadDatos, int paginaActual);

    }
