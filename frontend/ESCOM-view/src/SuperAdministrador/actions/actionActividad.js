import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';

export const MOSTRAR_ACTIVIDADES = 'MOSTRAR_ACTIVIDADES';
export const ESTADO_ACTIVIDADES  = 'ESTADO_ACTIVIDADES';
export const AGREGAR_ACTIVIDAD = 'AGREGAR_ACTIVIDAD';
export const EDITAR_USUARIO = "EDITAR_USUARIO";
export const MENSAJE_REGISTRAR = 'MENSAJE_REGISTRAR';
export const MENSAJE_SUSPENDER = 'MENSAJE_SUSPENDER';
export const MODULOS_REGISTRADOS='MODULOS_REGISTRADOS';

export function actualizarMensajeRegistrar(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_REGISTRAR,
            mensaje: mensaje
        });
    };
}

export function actionConsultarActividades(token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_VER ICIONES'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/actividad/listarActividades", { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_ACTIVIDADES,
                    respuesta: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta==='Sin permiso'){
                            dispatch({
                                type: ESTADO_ACTIVIDADES,
                                estado: true
                            });
                        }else{
                            //
                        }
                    }
                } 
            });
    };
}

export function actionAgregarActividad(actividad, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/actividad/registrarActividad", actividad, { headers: headers })
            .then(response => {
                debugger;
                dispatch({
                    type: AGREGAR_ACTIVIDAD,
                    actividadARegistrar: actividad
                });
                dispatch({
                    type: MENSAJE_REGISTRAR,
                    mensaje: 'Usuario registrado'
                });
            }).catch((error) => {
                debugger;
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_REGISTRAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_REGISTRAR,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_REGISTRAR,
                                mensaje: 'Ya existen los datos registrados previamente'
                            });
                        }
                    }
                } 
                
            });

    }
}

export function actionConsultarModulos(token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_VER ICIONES'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/modulo/listarModulos", { headers: headers })
            .then(response => {
                dispatch({
                    type: MODULOS_REGISTRADOS,
                    respuesta: response.data
                });
                
            }).catch((error) => {
                if (error.request.response === '') {
                    

                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta==='Sin permiso'){
                            dispatch({
                                type: ESTADO_ACTIVIDADES,
                                estado: true
                            });
                        }else{
                            //
                        }
                    }
                } 
            });
    };
}