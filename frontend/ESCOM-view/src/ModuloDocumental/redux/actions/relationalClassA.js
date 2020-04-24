import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_LIST_RELATIONAL_CLASS = 'GET_LIST_RELATIONAL_CLASS';
export const ADD_RELATIONAL_CLASS = 'ADD_RELATIONAL_CLASS';
export const DELETE_RELATIONAL_CLASS = 'DELETE_RELATIONAL_CLASS';
export const STATE_RELATIONAL_CLASS = 'STATE_RELATIONAL_CLASS';

export const ADD_MESSAGE = 'ADD_MESSAGE';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';

const PERMIT_LIST_RELATIONAL_CLASS = 'MD_Prueba';
const PERMIT_ADD_RELATIONAL_CLASS = 'MD_Prueba';
const PERMIT_DELETE_RELATIONAL_CLASS = 'MD_Prueba';

export function addMessageAdd(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD,
            mensaje: mensaje
        });
    };
}

export function addMessageDelete(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_DELETE,
            mensaje: mensaje
        });
    };
}

//MD_Lista clase relacional
export function getListRelational(token, id, table) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_RELATIONAL_CLASS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/relationalClass/list/${id}/${table}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_RELATIONAL_CLASS,
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
                                type: STATE_RELATIONAL_CLASS,
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

//MD_Agregar clase relacional
export function addRelational(token, relationalN) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_RELATIONAL_CLASS
    }
    relationalN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_RELATIONAL_CLASS
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/relationalClass/add`, relationalN, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_RELATIONAL_CLASS,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_RELATIONAL_CLASS,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_RELATIONAL_CLASS,
                            payload: respuesta
                        });
                    }
                }

            });
    }
}

//MD_Eliminar clase relacional
export function deleteRelational(token, relationalN) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_RELATIONAL_CLASS
    }
    relationalN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_RELATIONAL_CLASS
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/relationalClass/delete`, relationalN, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_RELATIONAL_CLASS,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: DELETE_RELATIONAL_CLASS,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: DELETE_RELATIONAL_CLASS,
                            payload: respuesta
                        });
                    }
                }

            });
    }
}