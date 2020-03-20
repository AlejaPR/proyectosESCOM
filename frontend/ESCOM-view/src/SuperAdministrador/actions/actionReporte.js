import axios from 'axios';
import { desencriptar } from '../componentes/general/Encriptar.js';
import { mensajeDeConsulta } from '../mensajesDeError/MensajesDeErrorReporte.js';

export const REPORTES = 'REPORTES';
export const MENSAJE_REPORTE = 'MENSAJE_REPORTE';

const URL_BASE = 'http://localhost:9090';
const PERMISO_REALIZAR = 'SA_Realizar reportes';



export function actionConsultarReporte(token, reporte) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_REALIZAR
    }
    // actividad.datosSolicitud = {
    //     'ip': localStorage.getItem('Ip'),
    //     'token': desencriptar(token),
    //     'operacion': PERMISO_ASIGNACION_ACTIVIDADES
    // }
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/bitacora/consultar`, reporte, { headers: headers })
            .then(response => {
                dispatch({
                    type: REPORTES,
                    reporte: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_REPORTE,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        debugger;
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajeDeConsulta(o.respuesta);
                        dispatch({
                            type: MENSAJE_REPORTE,
                            mensaje: respuesta
                        });
                    }
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

