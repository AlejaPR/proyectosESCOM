import axios from 'axios';
import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_CURRENT_VERSIONS = 'GET_CURRENT_VERSIONS';
export const GET_OLD_VERSIONS = 'GET_OLD_VERSIONS';
export const STATE_OLD_VERSION = 'STATE_OLD_VERSION';
export const STATE_CURRENT_VERSION = 'STATE_CURRENT_VERSION';
export const ADD_DOCUMENT_VERSION = 'ADD_DOCUMENT_VERSION';

const PERMIT_CURRENT_VERSIONS = 'MD_Prueba';
const PERMIT_OLD_VERSIONS = 'MD_Prueba';
const PERMIT_ADD_DOCUMENT_VERSION = 'MD_Prueba';

export const ADD_MESSAGE = 'ADD_MESSAGE';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';

export function addMessageAdd(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD,
            mensaje: mensaje
        });
    };
}

//MD_Lista versiones actuales
export function getCurrentVersions(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_CURRENT_VERSIONS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/documentVersion/listCurrent/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_CURRENT_VERSIONS,
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
                                type: STATE_CURRENT_VERSION,
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


//MD_Lista versiones anteriores
export function getOldVersions(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_OLD_VERSIONS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/documentVersion/listOld/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_OLD_VERSIONS,
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
                                type: STATE_OLD_VERSION,
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
export function addDocumentVersion(token, data, document) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_DOCUMENT_VERSION
    }
    document.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_DOCUMENT_VERSION
    };
    return (dispatch, getState) => {
        axios.post("http://localhost:8000/upload", data)
            .then(res => {
                document.location = res.data
                axios.post(`${URL_BASE}/proyectosESCOM-web/api/documentVersion/add/`, document, { headers: headers })
                    .then(response => {
                        dispatch({
                            type: ADD_DOCUMENT_VERSION,
                            payload: response.data.respuesta
                        });
                    }).catch(error => {
                        if (error.request.response === '') {
                            dispatch({
                                type: ADD_DOCUMENT_VERSION,
                                payload: 'error server'
                            });
                        } else {
                            if (error.request) {
                                var data = JSON.parse(error.request.response);
                                let respuesta = data.respuesta;
                                dispatch({
                                    type: ADD_DOCUMENT_VERSION,
                                    payload: respuesta
                                });
                            }
                        }

                    })
            }).catch(err => {
                dispatch({
                    type: ADD_DOCUMENT_VERSION,
                    payload: err.data
                });
            })
    }
}
