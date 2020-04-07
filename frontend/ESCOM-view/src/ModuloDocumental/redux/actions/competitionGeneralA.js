import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_COMPETITION_GENERAL = 'GET_LIST_COMPETITION_GENERAL';
export const ADD_COMPETITION_GENERAL = 'ADD_COMPETITION_GENERAL';
export const DELETE_COMPETITION_GENERAL = 'DELETE_COMPETITION_GENERAL';

export const PERMIT_LIST_COMPETITION_GENERAL = 'MD_Prueba';
export const PERMIT_ADD_COMPETITION_GENERAL = 'MD_Prueba';
export const PERMIT_DELETE_COMPETITION_GENERAL = 'MD_Prueba';

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

//MD_Lista competencia general
export function getListCompetitionG(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_COMPETITION_GENERAL
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/competitionGeneral/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_COMPETITION_GENERAL,
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
export function addCompetitionG(token, competitionG) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_COMPETITION_GENERAL
    }
    competitionG.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_COMPETITION_GENERAL
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/competitionGeneral/add`, competitionG, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_COMPETITION_GENERAL,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_COMPETITION_GENERAL,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_COMPETITION_GENERAL,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}

//MD_Eliminar nucleo tematico
export function deleteCompetitionG(token, competitionG) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_COMPETITION_GENERAL
    }
    competitionG.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_COMPETITION_GENERAL
    };
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/competitionGeneral/delete`, competitionG, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_COMPETITION_GENERAL,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: DELETE_COMPETITION_GENERAL,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: DELETE_COMPETITION_GENERAL,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}