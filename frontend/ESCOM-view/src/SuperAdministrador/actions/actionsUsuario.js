import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import { browserHistory } from 'react-router';

export const MOSTRAR_USUARIOS = 'MOSTRAR_USUARIOS'
export const AGREGAR_USUARIO = 'AGREGAR_USUARIO'
export const INFORMACION_USUARIO = 'INFORMACION_USUARIO'
export const ANADIR_CEDULA_EDITAR = "ANADIR_CEDULA_EDITAR"
export const EDITAR_USUARIO = "EDITAR_USUARIO"
export const ACTUALIZAR_USUARIOS = 'ACTUALIZAR_USUARIOS';
export const LOGIN_USUARIO = 'LOGIN_USUARIO';
export const REDIRECCIONAR_LOGIN = 'REDIRECCIONAR_LOGIN';


export function actionLoginUsuario(correo, contrasena, cambiar) {
    var crypto = require('crypto');
    var contrasenaEncryp = crypto.createHmac('sha256', correo).update(contrasena).digest('hex');
    cambiar(true);
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/login/" + correo + '/' + contrasenaEncryp)
            .then(response => {
                if (response.status == 200) {
                    var token = encriptar(response.data.token);
                    localStorage.setItem('Token', token);
                    dispatch({
                        type: LOGIN_USUARIO,
                        token: 'Login correcto'
                    });
                    cambiar(false);
                }
            }).catch((error) => {
                dispatch({
                    type: LOGIN_USUARIO,
                    token: 'Credenciales incorrectas'
                });
                cambiar(false);
            })
    };
}

export function actionCerrarSesion(token) {
    var tokenlim = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenlim
    }

    axios.delete("http://localhost:9090/SuperadministradorESCOM-web/api/usuario/" + tokenlim, { headers: headers })
        .then(response => {
            if (response.status == 200) {
                console.log('cerrada')
            }
        }).catch(function (error) {
            // handle error
            console.log('error es: ', error)
        });
}

export function actionConsultarUsuarios() {
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/")
            .then(response => {
                dispatch({
                    type: MOSTRAR_USUARIOS,
                    respuesta: response.data
                });
            });
    };
}

export function actionAgregarUsuario(usuario, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        try{
        axios.post("http://localhost:9090/proyectosESCOM-web/api/usuario", usuario, { headers: headers })
            .then(response => {
                dispatch({
                    type: AGREGAR_USUARIO,
                    usuarioARegistrar: usuario
                });
            }).catch((error) => {
                // handle error
                console.log('error es: ', error)
            });
        }catch(errora){
            console.log('atrapado el error a');
        }
    }
}

export function actionCargarInformacionDeUsuario(cedula) {
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/" + cedula)
            .then(response => {
                dispatch({
                    type: INFORMACION_USUARIO,
                    informacionUsuario: response.data
                });
            });
    };
}

export function actionAsignarCedula(cedula) {
    return (dispatch, getState) => {
        dispatch({
            type: ANADIR_CEDULA_EDITAR,
            cedula: cedula
        });
    }
}

export function actionActualizarUsuarios(usuario) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_USUARIOS,
            usuario: usuario
        });
    }
}

export function actionEditarUsuario(usuario, cedula) {
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/SuperadministradorESCOM-web/api/usu/" + cedula, usuario).then(response => {
            console.log("RESULTADO", response.status);
        });;

        dispatch({
            type: EDITAR_USUARIO,
            payload: usuario
        });
    }
}
