package com.mycompany.superadministrador.utilitarios;

import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.GestorBitacora;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.interfaces.LogicaBitacoraFacadeLocal;
import com.mycompany.superadministrador.interfaces.LogicaUsuarioFacadeLocal;
import com.mycompany.superadministrador.interfaces.UtilitarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Esta es la clase encargada del manejo de metodos requeridos por los modulos
 *
 * @author Alejandra Pabon, Jeison Gaona Universidad de Cundinamarca
 */
@Stateless
public class UtiliariosModulos implements UtilitarioFacadeLocal {

    /**
     * Inyeccion de la interfaz de logica de usuario
     */
    @EJB
    LogicaUsuarioFacadeLocal usuarioLogica;

    /**
     * Inyeccion de la interfaz de logica de bitacora
     */
    @EJB
    LogicaBitacoraFacadeLocal bitacoraLogica;

    /**
     * Variable para la gestion del logger
     */
    private Logger bitacora;

    /**
     * Metodo para devolver la informacion del usuario(id, numero documento,
     * correo) Recibe el token
     *
     * @param token
     * @return
     */
    @Override
    public UsuarioPOJO devolverInformacionDeUsuario(String token) {
        return usuarioLogica.devolverDatosUsuario(token);
    }

    /**
     * Metodo para regitrar los errores en logger
     *
     * @param error
     * @param clase
     * @param metodo
     * @param nivel
     */
    @Override
    public void registroLogger(String clase, String metodo, Level nivel, String error) {

        bitacora = GestorBitacora.getBitacora(clase, "./bitacora.txt", nivel, metodo, error);
    }

    /**
     * Metodo para el registro en bitacora de los metodos de los modulos
     *
     * @param solicitud
     */
    @Override
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud) {
        bitacoraLogica.registrarEnBitacora(solicitud);
    }

    /**
     * Metodo para devolver los usuarios registrados
     *
     * @return
     */
    @Override
    public List<UsuarioPOJO> devolverUsuariosModulo() {
        List<UsuarioPOJO> listaUsuariosModulo = new ArrayList();
        listaUsuariosModulo = usuarioLogica.devolverUsuariosModuloDocumental();
        return listaUsuariosModulo;
    }

}
