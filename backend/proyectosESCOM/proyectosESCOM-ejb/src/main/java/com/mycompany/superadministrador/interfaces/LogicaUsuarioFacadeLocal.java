/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.superadministrador.interfaces;

import com.mycompany.superadministrador.POJO.ActividadPOJO;
import com.mycompany.superadministrador.POJO.DatosSolicitudPOJO;
import com.mycompany.superadministrador.POJO.ModuloPOJO;
import com.mycompany.superadministrador.POJO.TipoDocumentoPOJO;
import com.mycompany.superadministrador.POJO.UsuarioPOJO;
import com.mycompany.superadministrador.entity.Actividad;
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

    public void registrarUsuario(UsuarioPOJO usuario) throws ExcepcionGenerica;

    public List<UsuarioPOJO> devolverUsuarios() throws ExcepcionGenerica;

    public List<TipoDocumentoPOJO> devolverDocumentos() throws ExcepcionGenerica;

    public UsuarioPOJO traerUsuarioCedula(int cedula) throws ExcepcionGenerica;

    public void editarUsuario(int cedula, UsuarioPOJO usuarioEditar) throws ExcepcionGenerica;

    public void cambiarEstadoUsuario(int cedula, DatosSolicitudPOJO datosSolicitud) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesUsuario(int cedula) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesNoAsociadasUsuario(int numeroDocumento, int idModulo) throws ExcepcionGenerica;

    public void eliminarActividadUsuario(int cedula, List<ActividadPOJO> listaActividad) throws ExcepcionGenerica;

    public void asignarActividadAUsuario(int numeroDocumento, int idActividad) throws ExcepcionGenerica;

    public List<ActividadPOJO> listarActividadesUsuarioActivas(int cedula) throws ExcepcionGenerica;

    public List<ModuloPOJO> redireccionUsuario(String token) throws ExcepcionGenerica;

}
