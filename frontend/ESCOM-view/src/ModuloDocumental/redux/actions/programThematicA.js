import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_LIST_PROGRAM_T = 'GET_LIST_PROGRAM_T';
export const GET_PROGRAM_T = 'GET_PROGRAM_T';
export const ADD_PROGRAM_T = 'ADD_PROGRAM_T';
export const EDIT_PROGRAM_T = 'EDIT_PROGRAM_T';
export const DELETE_PROGRAM_T = 'DELETE_PROGRAM_T';
export const STATE_PROGRAM_T = 'STATE_PROGRAM_T';

export const PERMIT_LIST_PROGRAM_T = 'MD_Prueba';
export const PERMIT_GET_PROGRAM_T = 'MD_Prueba';
export const PERMIT_ADD_PROGRAM_T = 'MD_Prueba';
export const PERMIT_EDIT_PROGRAM_T = 'MD_Prueba';
export const PERMIT_DELETE_PROGRAM_T = 'MD_Prueba';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';
export const ADD_MESSAGE = 'ADD_MESSAGE';


export function addMessageAdd(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD,
            mensaje: mensaje
        });
    };
}

export function addMessageEdit(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_EDIT,
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

//MD_Lista programa nucleo tematico
export function getListProgramT(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_PROGRAM_T
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/programThematicCore/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_PROGRAM_T,
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
                                type: STATE_PROGRAM_T,
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

//MD_Obtener programa nucleo tematico
export function getProgramT(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_GET_PROGRAM_T
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/programThematicCore/get/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_PROGRAM_T,
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
export function addProgramT(token, programT) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_PROGRAM_T
    }
    programT.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_PROGRAM_T
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/programThematicCore/add`, programT, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_PROGRAM_T,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_PROGRAM_T,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_PROGRAM_T,
                            payload: respuesta
                        });
                    }
                }
            });
    }
}

//MD_Editar nucleo tematica
export function editProgramT(token, programT) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_EDIT_PROGRAM_T
    }
    programT.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_EDIT_PROGRAM_T
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/programThematicCore/edit`, programT, { headers: headers })
            .then(response => {
                dispatch({
                    type: EDIT_PROGRAM_T,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: EDIT_PROGRAM_T,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: EDIT_PROGRAM_T,
                            payload: respuesta
                        });
                    }
                }
            });
    }
}

//MD_Eliminar nucleo tematico
export function deleteProgramT(token, programT) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_PROGRAM_T

    }
    let requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_PROGRAM_T
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/programThematicCore/delete/${programT}`, requestData, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_PROGRAM_T,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: DELETE_PROGRAM_T,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: DELETE_PROGRAM_T,
                            payload: respuesta
                        });
                    }
                }
            });
    }
}