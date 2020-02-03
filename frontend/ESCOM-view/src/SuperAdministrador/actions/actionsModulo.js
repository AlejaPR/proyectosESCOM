import axios from 'axios';
import { desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';

export const MOSTRAR_MODULOS = 'MOSTRAR_USUARIOS';
export const ESTADO_MODULOS = 'ESTADO_MODULOS';
export const ANADIR_CODIGO_EDITAR = 'ANADIR_CODIGO_EDITAR';

export const INFORMACION_MODULO='INFORMACION_MODULO';
export const MENSAJE_EDITAR_MODULO='MENSAJE_EDITAR_MODULO';
export const MENSAJE_SUSPENDER_MODULO='MENSAJE_SUSPENDER_MODULO';
export const MENSAJE_ACTIVIDADES='MENSAJE_ACTIVIDADES';
export const ACTIVIDADES_MODULO='ACTIVIDADES_MODULO';
export const ACTUALIZAR_MODULOS='ACTUALIZAR_MODULOS';
export const AGREGAR_MODULO='AGREGAR_MODULO';
export const MENSAJE_REGISTRAR_MODULO='MENSAJE_REGISTRAR_MODULO';

export function actionAgregarModulo(modulo, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/modulo/registrarModulo", modulo, { headers: headers })
            .then(response => {
                dispatch({
                    type: AGREGAR_MODULO,
                    moduloARegistrar: modulo
                });
                dispatch({
                    type: MENSAJE_REGISTRAR_MODULO,
                    mensaje: 'modulo registrado'
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_REGISTRAR_MODULO,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_REGISTRAR_MODULO,
                                mensaje: respuesta
                            });
                        }
                    }
                } 
                
            });

    }
}

export function actualizarMensajeSuspenderModulo(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_SUSPENDER_MODULO,
            mensaje: mensaje
        });
    };
}

export function actualizarMensajeActividades(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_ACTIVIDADES,
            mensaje: mensaje
        });
    };
}

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
                dispatch({
                    type: ACTIVIDADES_MODULO,
                    actividades: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_ACTIVIDADES,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_ACTIVIDADES,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_ACTIVIDADES,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
            });
    };
}

export function actionSuspenderActivarModulo(codigoModulo, token,actualizados) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/modulo/cambiarEstadoModulo/"+codigoModulo, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_SUSPENDER_MODULO,
                    mensaje: 'Operacion hecha con exito'
                });
                dispatch({
                    type: ACTUALIZAR_MODULOS,
                    modulo: actualizados
                });
            }).catch((error) => {
                console.log(error);

                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_SUSPENDER_MODULO,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        console.log('respuesta',respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_SUSPENDER_MODULO,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_SUSPENDER_MODULO,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
                
            });

    }
}

export function actionCambiarEstadoActividades(actividades, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/proyectosESCOM-web/api/modulo/cambiarEstadoActividadModulo/",actividades, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_ACTIVIDADES,
                    mensaje: 'Operacion hecha con exito'
                });
            }).catch((error) => {
                console.log(error);

                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_ACTIVIDADES,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        console.log('respuesta',respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_ACTIVIDADES,
                                mensaje: respuesta
                            });
                        }else{
                            dispatch({
                                type: MENSAJE_ACTIVIDADES,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                } 
                
            });

    }
}

export function actionEditarModulo(modulo, codigoModulo, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/proyectosESCOM-web/api/modulo/editarModulo/" + codigoModulo, modulo, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_EDITAR_MODULO,
                    mensaje: 'modulo editado'
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_EDITAR_MODULO,
                        mensaje: 'Sin acceso al servicio'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_EDITAR_MODULO,
                                mensaje: 'Sin acceso al servicio'
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
    }
}