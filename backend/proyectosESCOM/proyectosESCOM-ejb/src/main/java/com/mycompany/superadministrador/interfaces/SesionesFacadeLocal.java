package com.mycompany.superadministrador.interfaces;
import java.util.Calendar;
import java.util.Map;
import javax.ejb.Local;
/**
 * Esta es la interfaz para la clase sesiones
 * Contiene todos los metodos requeridos para el manejo de sesiones
 * @author Alejandra Pabon, Jeison Gaona
 * Universidad de Cundinamarca
 */
@Local
public interface SesionesFacadeLocal {
    
    public Map<String, Calendar> getMapaSesiones();
    
    public boolean modificarVencimiento(String llave);
    
    public  int validacionDeFecha(Calendar fechaToken);
    
    public boolean validarPermiso(String llave,String permisoRequerido);
}
