/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.entity.Usuario;
import java.util.List;
import javax.ejb.Local;
import org.springframework.stereotype.Service;

/**
 *
 * @author aleja
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    List<Usuario> busquedaToken(String token);
    
    List<Usuario> consultaLogin(String usuario, String Clave);
    
    String loginUsuario(String usuario, String clave);
    
    void editarToken(Usuario usuario, String token);
    
}
