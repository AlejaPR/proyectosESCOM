import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_CONDITIONS_USER = 'GET_LIST_CONDITIONS_USER';
export const GET_LIST_USERS_CONDITION = 'GET_LIST_USERS_CONDITION';
export const GET_LIST_USERS = 'GET_LIST_USERS';
export const ASSOCIATE_USER_CONDITION = 'ASSOCIATE_USER_CONDITION';
export const DELETE_USER_CONDITION = 'DELETE_USER_CONDITION';

export const PERMIT_LIST_USERS = 'MD_Prueba';
export const PERMIT_LIST_CONDITIONS_USER = 'MD_Prueba';
export const PERMIT_LIST_USERS_CONDITION = 'MD_Prueba';
export const PERMIT_ASSOCIATE_USER_CONDITION = 'MD_Prueba';
export const PERMIT_DELETE_USER_CONDITION = 'MD_Prueba';

export const ADD_MESSAGE_ASSOCIATE = 'ADD_MESSAGE_ASSOCIATE';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';

export function addMessageAssociate(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ASSOCIATE,
            mensaje: mensaje
        });
    };
}

export function addMessageDeleteUser(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_DELETE,
            mensaje: mensaje
        });
    };
}

//MD_Lista condiciones usuario
export function getConditionsUser(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_CONDITIONS_USER
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/userCondition/list/` + id, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_CONDITIONS_USER,
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

//MD_Lista usuarios
export function getListUser(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_USERS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/userCondition/listUsers/`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_USERS,
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

//MD_Lista usuarios condicion
export function getListUsersCondition(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_USERS_CONDITION
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/userCondition/listUsersC/` + id, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_USERS_CONDITION,
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


//MD_Asociar usuario condicion
export function associateUserCondition(token, userCondition) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ASSOCIATE_USER_CONDITION
    }
    userCondition.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ASSOCIATE_USER_CONDITION
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/userCondition/associate`, userCondition, { headers: headers })
            .then(response => {
                dispatch({
                    type: ASSOCIATE_USER_CONDITION,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_ASSOCIATE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_ASSOCIATE,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}

//MD_Eliminar usuario condicion
export function deleteUserCondition(token, userCondition) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_USER_CONDITION
    }
    userCondition.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_USER_CONDITION
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/userCondition/delete`, userCondition, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_USER_CONDITION,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_DELETE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_DELETE,
                            payload: 'error server'
                        });
                    }
                }
            });
    }
}