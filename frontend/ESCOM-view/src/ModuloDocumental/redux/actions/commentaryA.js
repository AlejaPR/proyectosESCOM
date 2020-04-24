import axios from 'axios';
import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const ADD_COMMENTARY = 'ADD_COMMENTARY';
export const GET_LIST_COMMENTS = 'GET_LIST_COMMENTS';
export const DELETE_COMMENTARY = 'DELETE_COMMENTARY';
export const STATE_COMMENTARY = 'STATE_COMMENTARY';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_ADD_COMMENTARY = 'MD_Prueba';
const PERMIT_LIST_COMMENTS = 'MD_Prueba';
const PERMIT_DELETE_COMMENTARY = 'MD_Prueba';



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

//MD_Agregar comentario
export function addCommentary(token, commentaryN) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_COMMENTARY
    }
    commentaryN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_COMMENTARY
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/commentary/add`, commentaryN, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_COMMENTARY,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_ADD,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_MESSAGE_ADD,
                            payload: respuesta
                        });
                    }
                }
            });
    }
}


//MD_Lista comentarios
export function getListComments(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_COMMENTS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/commentary/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_COMMENTS,
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
                                type: STATE_COMMENTARY,
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

//MD_Lista comentarios
export function deleteCommentary(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_COMMENTARY
    }
    const requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_COMMENTARY
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/commentary/delete/${id}`, requestData, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_COMMENTARY,
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
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_MESSAGE_DELETE,
                            payload: respuesta
                        });
                    }
                }
            });
    }
}