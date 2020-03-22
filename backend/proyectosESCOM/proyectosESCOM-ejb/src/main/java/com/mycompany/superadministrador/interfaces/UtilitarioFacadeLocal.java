package com.mycompany.superadministrador.interfaces;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import java.util.List;
import java.util.logging.Level;
/**
 * Esta es la interfaz para la clase utilitarios 
 * Contiene todos los metodos requeridos por los modulos
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
public interface UtilitarioFacadeLocal {
    
    public UsuarioPOJO devolverInformacionDeUsuario(String token);
    
    public void registroLogger(String clase, String metodo, Level nivel, String error);
    
    public void registrarEnBitacora(DatosSolicitudPOJO solicitud);
    
    public List<UsuarioPOJO> devolverUsuariosModulo();
}
