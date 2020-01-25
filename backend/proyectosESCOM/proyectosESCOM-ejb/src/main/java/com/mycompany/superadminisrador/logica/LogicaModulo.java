/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadminisrador.logica;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author aleja
 */
@Stateless
public class LogicaModulo implements LogicaModuloFacadeLocal {

    @EJB
    ModuloFacadeLocal moduloDB;

    @EJB
    ActividadFacadeLocal actividadDB;
    
    
    
     @Override
    public void registrarModulo(ModuloPOJO modulo) throws ExcepcionGenerica {
        try {
            List<Modulo> moduloResultado = moduloDB.consultaDatosExistentes(modulo.getNombreModulo());
            if (moduloResultado.isEmpty()) {
                moduloDB.registrarModulo(modulo);
            } else {
                throw new NoResultException("El nombre de modulo ya esta registrado");
            }
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer el registro de modulo");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontro ningun dato coincidente");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**
     * Metodo que llama a la consulta para obtener la lista de modulos
     *
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ModuloPOJO> devolverModulos() throws ExcepcionGenerica {
        try {
            List<ModuloPOJO> modulosResultado = moduloDB.listarModulos();
            if (!modulosResultado.isEmpty()) {
                return modulosResultado;
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
     * Metodo que llama a la consulta que devuelve los datos del modulo
     * recibiendo el id
     *
     * @param idModulo
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public ModuloPOJO traerModuloId(int idModulo) throws ExcepcionGenerica {
        try {
            ModuloPOJO moduloResultado = moduloDB.buscarModuloEspecifico(idModulo);
            if (moduloResultado != null) {
                return moduloResultado;
            } else {
                throw new NoResultException("No se encontraron datos del modulo");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del modulo");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**
     * Metodo que llama a la consulta para editar modulo recibiendo como
     * parametro el id
     *
     * @param idModulo
     * @param moduloEditar
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void editarModulo(int idModulo, ModuloPOJO moduloEditar) throws ExcepcionGenerica {
        try {
            moduloDB.editarModulo(idModulo, moduloEditar);

        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion del usuario ");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El usuario no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }

    }

    /**
     * Metodo que llama a la consulta para cambiar el estado del modulo
     * recibiendo como parametro el id
     *
     * @param idModulo
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoModulo(int idModulo) throws ExcepcionGenerica {
        try {
            ModuloPOJO moduloResultado = moduloDB.buscarModuloEspecifico(idModulo);
            if (moduloResultado != null) {
                if (moduloResultado.getEstadoModulo().equals("Activo")) {
                    moduloDB.cambiarEstadoModulo(idModulo, "Suspendido");
                } else if (moduloResultado.getEstadoModulo().equals("Suspendido")) {
                    moduloDB.cambiarEstadoModulo(idModulo, "Activo");
                }
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

    /**
     * Metodo que llama a la consulta para buscar la lista de actividades de un
     * modulo
     *
     * @param idModulo
     * @return
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public List<ActividadPOJO> listarActividadesModulo(int idModulo) throws ExcepcionGenerica {
        try {
            List<ActividadPOJO> listaActividadesM = new ArrayList();
            listaActividadesM = actividadDB.listarActividadesModulo(moduloDB.find(idModulo));
            if (listaActividadesM.size() >= 0) {

                return listaActividadesM;
            } else {
                throw new NoResultException("Error en la consulta");
            }
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos del usuario");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

     /**
     * Metodo que cambia el estado de una actividad de un modulo en especifico
     *
     * @param idActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoActividadModulo(int idActividad) throws ExcepcionGenerica {
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

   
}
