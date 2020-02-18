import axios from 'axios';
import {desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';


export const MOSTRAR_CONFIGURACION = 'MOSTRAR_CONFIGURACION'
export const ACTUALIZAR_BARRALATERAL= 'ACTUALIZAR_BARRALATERAL'
export const ACTUALIZAR_BARRASUPERIOR= 'ACTUALIZAR_BARRASUPERIOR'
export const ACTUALIZAR_FOTO_LOGIN= 'ACTUALIZAR_FOTO_LOGIN'
export const ACTUALIZAR_FOTO_LOGO= 'ACTUALIZAR_FOTO_LOGO'
export const ACTUALIZAR_BOTONES='ACTUALIZAR_BOTONES';
export const MENSAJE_CONFIGURACION='MENSAJE_CONFIGURACION';
export const CARGAR_CONFIGURACION='CARGAR_CONFIGURACION';

export function actionAgregarConfiguracion(configuracion, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Administrar configuracion de aspecto'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/configuracion/registrarConfiguracion", configuracion, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_CONFIGURACION,
                    mensaje: 'Usuario registrado'
                });
                dispatch({
                    type: CARGAR_CONFIGURACION,
                    configuracion: configuracion
                });

            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_CONFIGURACION,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_CONFIGURACION,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_CONFIGURACION,
                                mensaje: 'Ya existen los datos registrados previamente'
                            });
                        }
                    }
                } 
                
            });
    }
}

export function consultarConfiguracion() {
    const headers = {
        'Content-Type': 'application/json'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/configuracion/listarEntorno", { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_CONFIGURACION,
                    configuracion: response.data[0]
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MOSTRAR_CONFIGURACION,
                        configuracion:{
                            barraLateral: "#164D14",
                            barraSuperior:"white",
                            botones:"#164D14"
                        }
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MOSTRAR_CONFIGURACION,
                                configuracion:{
                                    barraLateral: "#164D14",
                                    barraSuperior:"#FFFFFF",
                                    botones:"#164D14"
                                }
                            });
                        }else{
                            dispatch({
                                type: MOSTRAR_CONFIGURACION,
                                configuracion:{
                                    barraLateral: "#164D14",
                                    fondoSuperior:"#FFFFFF",
                                    botones:"#164D14"
                                }
                            });
                        }
                    }
                } 
                
            });
    }
}

export function actionConsultarConfiguracionCompleta(token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Administrar configuracion de aspecto'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/configuracion/listarConfiguracionCompleta", { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_CONFIGURACION,
                    mensaje: 'Operacion hecha con exito'
                });
                dispatch({
                    type: CARGAR_CONFIGURACION,
                    configuracion: response.data[0]
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_CONFIGURACION,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_CONFIGURACION,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_CONFIGURACION,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
                
            });
    }
}

export function actionActualizarBarraLateral(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BARRALATERAL,
            color:color
        });
    }
}

export function actionActualizarBarraSuperior(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BARRASUPERIOR,
            color:color
        });
    }
}

export function actualizarFotoLogin(foto) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_FOTO_LOGIN,
            fotoLogin:foto
        });
    }
}

export function actualizarFotoLogo(foto) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_FOTO_LOGO,
            fotoLogo:foto
        });
    }
}

export function actionActualizarBotones(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BOTONES,
            color:color
        });
    }
}