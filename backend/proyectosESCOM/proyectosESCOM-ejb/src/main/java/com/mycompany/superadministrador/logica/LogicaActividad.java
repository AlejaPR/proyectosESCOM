package com.mycompany.superadministrador.logica;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * Clase encargada de la logica de las actividades 
 * @author jeison gaona - alejandra pabon
 */
@Stateless
public class LogicaActividad implements LogicaActividadFacadeLocal {

    @EJB
    ActividadFacadeLocal actividadDB;
    
    @EJB
    ModuloFacadeLocal moduloDB;
    
    /**
     * Metodo que llama a la consulta para obtener la lista de actividades
     *
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ActividadPOJO> devolverActividades() throws ExcepcionGenerica {
        try {
            List<Actividad> actividadesResultado = actividadDB.findAll();
            if (!actividadesResultado.isEmpty()) {
                List<ActividadPOJO> listaActividadesM = new ArrayList<>();
                for (Actividad a : actividadesResultado) {
                    ActividadPOJO actividad = new ActividadPOJO();
                    actividad.setIdActividad(a.getIdActividad());
                    actividad.setNombre(a.getNombreActividad());
                    actividad.setDescripcionActividad(a.getDescripcionActividad());
                    actividad.setModuloActividad(a.getFkActIdmodulo().getNombreModulo());
                    actividad.setEstado(a.getEstado());
                    listaActividadesM.add(actividad);
                }
                return listaActividadesM;
            } else {
                throw new NoResultException("No se encontraron datos");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }
    
    /**
     * Metodo que llama a la consulta para registrar la actividad
     *
     * 
     * @param actividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public ActividadPOJO registrarActividad (ActividadPOJO actividad) throws ExcepcionGenerica {
        try {
            Modulo moduloResultado = moduloDB.find(actividad.getIdModulo());
            if (moduloResultado != null) {
                String acronimo = moduloResultado.getAcronimo();
                String nombreActividadN= acronimo + "_" + actividad.getNombre();
                
                List<Actividad> actividadResultado = actividadDB.buscarActividadPorNombre(nombreActividadN);
                if(actividadResultado.isEmpty()){
                     ActividadPOJO actividadR = new ActividadPOJO();
                     actividadR =actividadDB.registrarActividad(actividad, nombreActividadN, moduloResultado);
                     return actividadR;
                }else{
                    throw new NoResultException("La actividad ya se encuentra registrada");
                }             
            } else {
                throw new NoResultException("El modulo no se encuentra registrado");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el login del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }
    
    /**
     * Metodo que llama a la consulta para editar la actividad
     *
     * @param actividadEditar
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void editarActividad(ActividadPOJO actividadEditar) throws ExcepcionGenerica {
        try {
            actividadDB.editarActividad(actividadEditar);

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion de la actividad");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("La actividad no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion");
        }

    }
    
    /**
     * Metodo que llama a la consulta para editar el estado de la actividad
     *
     * @param idActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoActividad(int idActividad) throws ExcepcionGenerica {
        try {
            Actividad actividadResultado = actividadDB.find(idActividad);
            if (actividadResultado != null) {
                if (actividadResultado.getEstado().equals("Activo")) {
                    actividadDB.cambiarEstadoActividad(idActividad, "Suspendido");
                } else if (actividadResultado.getEstado().equals("Suspendido")) {
                   actividadDB.cambiarEstadoActividad(idActividad, "Activo");
                }
            } else {
                throw new NoResultException("No se encontraron datos de la actividad");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos de la actividad");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }

    }
    
     /**
     * Metodo que llama a la consulta que devuelve los datos del usuario
     * recibiendo la cedula
     *
     * @param idActividad
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public ActividadPOJO traerActividadEspecifica(int idActividad) throws ExcepcionGenerica {
        try {
            ActividadPOJO actividadResultado = actividadDB.buscarActividadEspecifica(idActividad);
            if (actividadResultado != null) {
                return actividadResultado;
            } else {
                throw new NoResultException("No se encontraron datos del usuario");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }
}
