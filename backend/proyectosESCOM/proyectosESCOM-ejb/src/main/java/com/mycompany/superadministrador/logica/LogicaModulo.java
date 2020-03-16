package com.mycompany.superadministrador.logica;
import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.entity.Actividad;
import com.mycompany.superadministrador.entity.Modulo;
import com.mycompany.superadministrador.interfaces.ActividadFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.ModuloFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
/**
 * Esta es la clase encargada de la logica de modulo
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Stateless
public class LogicaModulo implements LogicaModuloFacadeLocal {

    /**Inyeccion de la interfaz de modulo**/
    @EJB
    ModuloFacadeLocal moduloDB;

    /**Inyeccion de la interfaz de actividad**/
    @EJB
    ActividadFacadeLocal actividadDB;

    /**Inyeccion de la interfaz de bitacora**/
    @EJB
    UtilitarioFacadeLocal bitacora;

    /**Variable para el registro de bitacora**/
    private static final String TABLA = "TBL_MODULO";

    /**
     * Metodo que llama a la consulta para registrar el modulo
     *
     * @param modulo
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void registrarModulo(ModuloPOJO modulo) throws ExcepcionGenerica {
        try {
            List<Modulo> moduloResultado = moduloDB.consultaDatosExistentes(modulo.getNombreModulo());
            if (moduloResultado.isEmpty()) {
                String primeraLetra, segundaLetra, acronimo;
                String nombre = modulo.getNombreModulo();
                String[] partes = nombre.split(" ");
                if (partes.length == 1) {
                    String parte1 = partes[0];
                    primeraLetra = parte1.substring(0, 1);
                    segundaLetra = parte1.substring(1, 2);
                    acronimo = primeraLetra + segundaLetra;
                    List<Modulo> moduloAcronimo = moduloDB.consultaAcronimo(acronimo);
                     modulo.getDatosSolicitud().setTablaInvolucrada(TABLA);
                    if (moduloAcronimo.isEmpty()) {
                        moduloDB.registrarModulo(modulo, acronimo);
                        bitacora.registrarEnBitacora(modulo.getDatosSolicitud());
                    } else {
                        primeraLetra = parte1.substring(1, 2);
                        segundaLetra = parte1.substring(2, 3);
                        acronimo = primeraLetra + segundaLetra;                      
                        moduloDB.registrarModulo(modulo, acronimo);                    
                        bitacora.registrarEnBitacora(modulo.getDatosSolicitud());
                    }

                } else {
                    String parte1 = partes[0];
                    String parte2 = partes[1];

                    primeraLetra = parte1.substring(0, 1);
                    segundaLetra = parte2.substring(0, 1);
                    acronimo = primeraLetra + segundaLetra;
                    List<Modulo> moduloAcronimo = moduloDB.consultaAcronimo(acronimo);
                     modulo.getDatosSolicitud().setTablaInvolucrada(TABLA);
                    if (moduloAcronimo.isEmpty()) {
                        moduloDB.registrarModulo(modulo, acronimo);
                        bitacora.registrarEnBitacora(modulo.getDatosSolicitud());
                    } else {
                        primeraLetra = parte1.substring(0, 1);
                        segundaLetra = parte2.substring(1, 2);
                        acronimo = primeraLetra + segundaLetra;
                        moduloDB.registrarModulo(modulo, acronimo);                     
                        bitacora.registrarEnBitacora(modulo.getDatosSolicitud());
                    }
                }
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
            moduloEditar.getDatosSolicitud().setTablaInvolucrada(TABLA);
            bitacora.registrarEnBitacora(moduloEditar.getDatosSolicitud());
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la modificacion del modulo");
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("El modulo no existe");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }

    }

    /**
     * Metodo que llama a la consulta para cambiar el estado del modulo
     * recibiendo como parametro el id
     *
     * @param idModulo
     * @param datosSolicitud
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoModulo(int idModulo,DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica {
        try {
            ModuloPOJO moduloResultado = moduloDB.buscarModuloEspecifico(idModulo);
            datosSolicitud.setTablaInvolucrada(TABLA);
            if (moduloResultado != null) {
                if (moduloResultado.getEstadoModulo().equals("Activo")) {
                    moduloDB.cambiarEstadoModulo(idModulo, "Suspendido");
                    bitacora.registrarEnBitacora(datosSolicitud);
                } else if (moduloResultado.getEstadoModulo().equals("Suspendido")) {
                    moduloDB.cambiarEstadoModulo(idModulo, "Activo");
                    bitacora.registrarEnBitacora(datosSolicitud);
                }
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
            throw new ExcepcionGenerica("No se encontraron datos del modulo");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

    /**
     * Metodo que cambia el estado de una actividad de un modulo en especifico
     *
     * @param listaActividad
     * @throws com.mycompany.superadministrador.utilitarios.ExcepcionGenerica
     *
     */
    @Override
    public void cambiarEstadoActividadModulo(List<ActividadPOJO> listaActividad) throws ExcepcionGenerica {
        try {

            for (int i = 0; i < listaActividad.size(); i++) {
                Actividad actividadResultado = actividadDB.find(listaActividad.get(i).getIdActividad());
                if (actividadResultado != null) {
                    if (actividadResultado.getEstado().equals("Activo")) {
                        actividadDB.cambiarEstadoActividadModulo(listaActividad.get(i).getIdActividad(), "Suspendido");
                    } else if (actividadResultado.getEstado().equals("Suspendido")) {
                        actividadDB.cambiarEstadoActividadModulo(listaActividad.get(i).getIdActividad(), "Activo");
                    }
                } else {
                    throw new NoResultException("No se encontraron datos de la actividad");
                }
            }
            listaActividad.get(0).getDatosSolicitud().setTablaInvolucrada(TABLA);
            bitacora.registrarEnBitacora(listaActividad.get(0).getDatosSolicitud());
        } catch (NoResultException ex) {
            throw new ExcepcionGenerica("No se encontraron datos de la actividad");
        } catch (NullPointerException ex) {
            throw new ExcepcionGenerica("Ocurrio un error al momento de hacer la consulta");
        } catch (Exception ex) {
            throw new ExcepcionGenerica("Ocurrio una excepcion ");
        }
    }

}
