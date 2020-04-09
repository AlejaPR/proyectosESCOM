import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_THEMATIC_CORE = 'GET_LIST_THEMATIC_CORE';
export const ADD_THEMATIC_CORE = 'ADD_THEMATIC_CORE';
export const DELETE_THEMATIC_CORE = 'DELETE_THEMATIC_CORE';

export const PERMIT_LIST_THEMATIC_CORE = 'MD_Prueba';
export const PERMIT_ADD_THEMATIC_CORE = 'MD_Prueba';
export const PERMIT_DELETE_THEMATIC_CORE = 'MD_Prueba';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';

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

//MD_Lista nucleo tematico
export function getListThematicCore(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_THEMATIC_CORE
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/thematicCore/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_THEMATIC_CORE,
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

//MD_Agregar nucleo tematica
export function addThematicCore(token, thematicC) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_THEMATIC_CORE
    }
    thematicC.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_THEMATIC_CORE
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/thematicCore/add`, thematicC, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_THEMATIC_CORE,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_THEMATIC_CORE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_THEMATIC_CORE,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}

//MD_Eliminar nucleo tematico
export function deleteThemacticCore(token, thematicC) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_THEMATIC_CORE
    }
    thematicC.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_THEMATIC_CORE
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/thematicCore/delete`,thematicC, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_THEMATIC_CORE,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: DELETE_THEMATIC_CORE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: DELETE_THEMATIC_CORE,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}