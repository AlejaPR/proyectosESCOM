/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.utilitarios.ExcepcionGenerica;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jeiso
 */
@Local
public interface LogicaUsuarioFacadeLocal {

    public UsuarioPOJO loginUsuario(String correo, String contrasena) throws ExcepcionGenerica;

    public void cerrarSesion(String token) throws ExcepcionGenerica;

    public UsuarioPOJO devolverDatosUsuario(String token);

    public void registrarUsuario(UsuarioPOJO usuario)throws ExcepcionGenerica;
    
    List<UsuarioPOJO> devolverUsuarios()throws ExcepcionGenerica;
            
    List<TipoDocumentoPOJO> devolverDocumentos()throws ExcepcionGenerica;
    
   
}
