/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.entity.Usuario;
import java.util.List;
import javax.ejb.Local;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;

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

    public Usuario consultaLogin(String correo, String contrasena);

    public List<ActividadPOJO> consultarActividadesUsuario(int idUsuario);

    public int editarToken(String token, int idUsuario);

    public UsuarioPOJO busquedaToken(String firma);

    public int editarTokenCerrarSesion(String firma, String correo);

    public List<Usuario> consultaDatosExistentes(String correo, int idDocumento);

    public void registrarUsuario(UsuarioPOJO usuario);
    
    public List<UsuarioPOJO> listarUsuarios();
    
    public UsuarioPOJO buscarUsuarioEspecifico(int cedula);
    
    public void editarUsuario(int cedula, UsuarioPOJO usuarioEditar);
    
    public void cambiarEstadoUsuario(int cedula, String estado);
    
   
}
