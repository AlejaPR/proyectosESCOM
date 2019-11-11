/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import java.util.Calendar;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author jeiso
 */
@Local
public interface SesionesFacadeLocal {
    
    public Map<String, Calendar> getMapaSesiones();
    
    public boolean modificarVencimiento(String llave);
    
    public  int validacionDeFecha(Calendar fechaToken);
}
