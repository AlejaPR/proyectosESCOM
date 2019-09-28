/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.entity.UsuarioActividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author aleja
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
    
}
