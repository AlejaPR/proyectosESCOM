import axios from 'axios';
import { desencriptar } from '../componentes/general/Encriptar.js';
import { mensajeDeConsulta } from '../mensajesDeError/MensajesDeErrorReporte.js';
import { URL_BASE } from '../utilitario/Configuracion.js';

export const REPORTES = 'REPORTES';
export const MENSAJE_REPORTE = 'MENSAJE_REPORTE';

const PERMISO_REALIZAR = 'SA_Realizar reportes';



export function actionConsultarReporte(token, reporte) {
    const headers = {
        'Content-Type': 'application/json; charset=UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_REALIZAR
    }
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/bitacora/consultar`, reporte, { headers: headers })
            .then(response => {
                dispatch({
                    type: REPORTES,
                    reporte: response.data
                });
            }).catch((error) => {
                try {
                    if (error.request.response === '') {
                        dispatch({
                            type: MENSAJE_REPORTE,
                            mensaje: 'Servidor fuera de servicio temporalmente'
                        });
                    } else {
                        if (error.request) {
                            var o = JSON.parse(error.request.response);
                            let respuesta = mensajeDeConsulta(o.respuesta);
                            dispatch({
                                type: MENSAJE_REPORTE,
                                mensaje: respuesta
                            });
                        }
                    }
                } catch (error) {
                    dispatch({
                        type: MENSAJE_REPORTE,
                        mensaje: 'Ocurrio un error en el servidor'
                    });
                }
            });
    }
}


export function actualizarReporte() {
    return (dispatch, getState) => {
        dispatch({
            type: REPORTES,
            reporte: []
        });
    };
}
export function actualizarMensaje(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_REPORTE,
            mensaje: mensaje
        });
    };
}

