import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_ANNEX_VERSIONS = 'GET_ANNEX_VERSIONS';
export const ADD_ANNEX_VERSION = 'ADD_ANNEX_VERSION';
export const STATE_ANNEX_VERSION = 'STATE_ANNEX_VERSION';

const PERMIT_GET_ANNEX_VERSION = 'MD_Lista version anexo';
const PERMIT_ADD_ANNEX_VERSION = 'MD_Agregar version anexo';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE = 'ADD_MESSAGE';

export function addMessageAdd(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD,
            mensaje: mensaje
        });
    };
}

//MD_Lista version anexo
export function getAnnexVersions(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_GET_ANNEX_VERSION
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/annexVersion/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ANNEX_VERSIONS,
                    payload: response.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        if (respuesta === 'Sin permiso') {
                            dispatch({
                                type: STATE_ANNEX_VERSION,
                                payload: true
                            });
                        } else {
                            dispatch({
                                type: ADD_MESSAGE,
                                payload: respuesta
                            });
                        }
                    }
                }

            });
    }
}

//MD_Agregar version anexo
export function addAnnexVersion(token, data, annexN) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_ANNEX_VERSION
    }
    annexN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_ANNEX_VERSION
    };
    return (dispatch, getState) => {
        axios.post("http://localhost:8000/upload", data)
            .then(res => {
                annexN.location = res.data
                axios.post(`${URL_BASE}/proyectosESCOM-web/api/annexVersion/add/`, annexN, { headers: headers })
                    .then(response => {
                        dispatch({
                            type: ADD_ANNEX_VERSION,
                            payload: response.data.respuesta
                        });
                    }).catch(error => {
                        if (error.request.response === '') {
                            dispatch({
                                type: ADD_ANNEX_VERSION,
                                payload: 'error server'
                            });
                        } else {
                            if (error.request) {
                                var data = JSON.parse(error.request.response);
                                let respuesta = data.respuesta;
                                dispatch({
                                    type: ADD_ANNEX_VERSION,
                                    payload: respuesta
                                });
                            }
                        }

                    })
            }).catch(err => {
                dispatch({
                    type: ADD_ANNEX_VERSION,
                    payload: err.data
                });
            })
    }
}
