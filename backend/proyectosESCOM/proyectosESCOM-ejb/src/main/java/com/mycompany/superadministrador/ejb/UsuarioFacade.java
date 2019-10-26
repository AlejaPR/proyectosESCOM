/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.ejb;

import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.seguridad.Seguridad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author aleja
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "conexionSuperadministrador")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    

    @Override
    public List<Usuario> busquedaToken(String token) {
        TypedQuery<Usuario> consulta = em.createNamedQuery("SELECT u FROM Usuario u WHERE u.token = :token", Usuario.class);
        consulta.setParameter("token", token);
        return consulta.getResultList();
    }
    
    @Override
    public List<Usuario> consultaLogin(String usuario, String clave) {
        TypedQuery<Usuario> consultaLogin = em.createNamedQuery("consultaLogin", Usuario.class);
             consultaLogin.setParameter("usuario", usuario);
             consultaLogin.setParameter("clave", clave);
             return consultaLogin.getResultList();
    }

    @Override
    public String loginUsuario(String usuario, String clave) {
        
         try {
            List<Usuario> listaUsuario = new ArrayList();
            listaUsuario= consultaLogin(usuario,clave);
            for (Usuario usuarios : listaUsuario) {
              if(usuarios.getNombre().equals(usuario) && usuarios.getContrasena().equals(clave)){
                      Seguridad token = new Seguridad();
                      String usua= usuarios.getNombre();
                      String clav = usuarios.getContrasena();
                      String tokencin =  token.generarToken(usuarios, usua, clav);
                      return tokencin;
                  }else{
                      System.out.println("Error");
                      return "";
                  }
            } 
        } catch (Exception e) {
             System.out.println("ERRR"+e);
        }
        return "";
    }

    @Override
    public void editarToken(Usuario usuario, String token) {
        
        System.out.println(usuario.getNombre());
        TypedQuery<Usuario> editar = em.createNamedQuery("editarToken", Usuario.class);
             editar.setParameter("usuario", usuario.getNombre());
             editar.setParameter("token", token);
             System.out.println("Consulta"+editar.getSingleResult());   
    }

  
    
}
