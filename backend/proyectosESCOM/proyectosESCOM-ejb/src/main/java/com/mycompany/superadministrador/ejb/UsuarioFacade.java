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
        TypedQuery<Usuario> consulta = em.createNamedQuery("busquedaToken", Usuario.class);
        consulta.setParameter("token", token);
        return consulta.getResultList();
    }

    
    public Usuario consultaLogin(String correo, String clave) {
        TypedQuery<Usuario> consultaLogin = em.createNamedQuery("consultaLogin", Usuario.class);
        consultaLogin.setParameter("correo", correo);
        consultaLogin.setParameter("clave", clave);
        return consultaLogin.getSingleResult();
    }

    @Override
    public Usuario loginUsuario(String correo, String clave) {
        try {
            Usuario usuario = consultaLogin(correo, clave);
            Seguridad token = new Seguridad();
            String tokencin = token.generarToken(usuario);
            usuario.setToken(tokencin);
            return usuario;
        } catch (Exception e) {
            System.out.println("ERRR" + e);
            System.out.println("Error");
            return null;
        }
    }
}
