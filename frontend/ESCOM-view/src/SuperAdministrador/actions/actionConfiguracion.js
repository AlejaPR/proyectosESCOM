import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';
// import axios from 'axios';

export const MOSTRAR_CONFIGURACION = 'MOSTRAR_CONFIGURACION'
export const ACTUALIZAR_BARRALATERAL= 'ACTUALIZAR_BARRALATERAL'
export const ACTUALIZAR_BARRASUPERIOR= 'ACTUALIZAR_BARRASUPERIOR'
export const ACTUALIZAR_BOTONES='ACTUALIZAR_BOTONES';
export const MENSAJE_CONFIGURACION='MENSAJE_CONFIGURACION';
export const CARGAR_CONFIGURACION='CARGAR_CONFIGURACION';


export function consultarConfiguracion() {
    return (dispatch, getState) => {
        // axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/")
        // .then(response => {
        //     dispatch({
        //         type: MOSTRAR_USUARIOS,
        //         respuesta: response.data
        //     });
        dispatch({
            type: MOSTRAR_CONFIGURACION,
            configuracion:{
                fondoBarra: "#0E3D38",
                fondoSuperior:"white",
                botones:"blue"
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
                    configuracion: response.data
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

export function actionActualizarBotones(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BOTONES,
            color:color
        });
    }
}