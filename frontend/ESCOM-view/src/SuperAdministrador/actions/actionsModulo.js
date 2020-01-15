import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';

export const MOSTRAR_MODULOS = 'MOSTRAR_USUARIOS';
export const ESTADO_MODULOS = 'ESTADO_MODULOS';
export const ANADIR_CODIGO_EDITAR = 'ANADIR_CODIGO_EDITAR';
export const INFORMACION_MODULO='INFORMACION_MODULO';
export const MENSAJE_EDITAR_MODULO='MENSAJE_EDITAR_MODULO';
export const ACTIVIDADES_MODULO='ACTIVIDADES_MODULO';

// export function actualizarMensajeSuspender(mensaje) {
//     return (dispatch, getState) => {
//         dispatch({
//             type: MENSAJE_SUSPENDER,
//             mensaje: mensaje
//         });
//     };
// }

export function actionAsignarModulo(codigoModulo) {
    return (dispatch, getState) => {
        dispatch({
            type: ANADIR_CODIGO_EDITAR,
            codigo: codigoModulo
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
                    type: MOSTRAR_MODULOS,
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
                                type: ESTADO_MODULOS,
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

export function actionCargarInformacionDeModulo(codigoModulo, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': 'SA_CREAR USUARIO'
    }

    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/modulo/datosModulo/" + codigoModulo, { headers: headers })
            .then(response => {
                dispatch({
                    type: INFORMACION_MODULO,
                    informacionModulo: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_EDITAR_MODULO,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_EDITAR_MODULO,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_EDITAR_MODULO,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
            });
    };
}

export function actionConsultarActividadesModulo(codigoModulo, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': 'SA_CREAR USUARIO'
    }

    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/modulo/listarActividadesModulo/" + codigoModulo, { headers: headers })
            .then(response => {
                console.log('respuesta',response.data);
                dispatch({
                    type: ACTIVIDADES_MODULO,
                    actividades: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_EDITAR_MODULO,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_EDITAR_MODULO,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_EDITAR_MODULO,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
            });
    };
}