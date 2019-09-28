/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.services;

import com.mycompany.superadministrador.entity.Usuario;
import com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jeiso
 */
@Controller
public class UsuarioServicios {
    
    UsuarioFacadeLocal interfaceUsuario = inicializarInterfaceUsuario();

    private UsuarioFacadeLocal inicializarInterfaceUsuario() {
        try {
            Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/proyectosESCOM-web/UsuarioFacade!com.mycompany.superadministrador.interfaces.UsuarioFacadeLocal");
        } catch (NamingException e) {
            System.out.println("ERROR AL TRAER: " + e);
            return null;
        }
    }
    
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Usuario> usuariosRegistrados() {
        return interfaceUsuario.findAll();
    }
    
    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public @ResponseBody
    String registrarUsuario(@RequestBody Usuario usuario) {
        //interfaceUsuario.create(usuario);
        return "creado";
    }
    
    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    String modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
//        interfaceUsuario.edit(usuario);
        return "editado";
    }
    
    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String borrarEmpleado(@PathVariable("id") Long id) {

//        interfaceUsuario.remove(null);
        return "borrado";
    }
}
