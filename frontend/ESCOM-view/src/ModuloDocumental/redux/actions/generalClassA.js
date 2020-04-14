import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_GENERAL_CLASS = 'GET_LIST_GENERAL_CLASS';
export const ADD_GENERAL_CLASS = 'ADD_GENERAL_CLASS';
export const DELETE_GENERAL_CLASS = 'DELETE_GENERAL_CLASS';

export const ADD_MESSAGE = 'ADD_MESSAGE';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';

const PERMIT_LIST_GENERAL_CLASS = 'MD_Prueba';
const PERMIT_ADD_GENERAL_CLASS = 'MD_Prueba';
const PERMIT_DELETE_GENERAL_CLASS = 'MD_Prueba';

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

//MD_Lista clase general
export function getListGeneralC(token, id, table) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_GENERAL_CLASS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/generalClass/list/${id}/${table}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_GENERAL_CLASS,
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
                        dispatch({
                            type: ADD_MESSAGE,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Agregar clase general
export function addGeneralC(token, generalN) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_GENERAL_CLASS
    }
    generalN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_GENERAL_CLASS
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/generalClass/add`,generalN, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_GENERAL_CLASS,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_GENERAL_CLASS,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_GENERAL_CLASS,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Eliminar clase general
export function deleteGeneralC(token, generalN) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_GENERAL_CLASS
    }
    generalN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_GENERAL_CLASS
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/generalClass/delete`,generalN, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_GENERAL_CLASS,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: DELETE_GENERAL_CLASS,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: DELETE_GENERAL_CLASS,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}