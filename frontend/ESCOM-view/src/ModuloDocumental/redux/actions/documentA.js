import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_ID_DOCUMENT = 'GET_ID_DOCUMENT';
export const GET_DOCUMENT_ID = 'GET_DOCUMENTS_ID';
export const ADD_DOCUMENT = 'ADD_DOCUMENT';
export const GET_DOCUMENT_ID_EDIT = 'SHOW_DOCUMENT_ID_EDIT';
export const EDIT_DOCUMENT = 'EDIT_DOCUMENT';
export const DISABLE_DOCUMENT = 'DISABLE_DOCUMENT';
export const GET_LIST_DOCUMENTS = 'GET_LIST_DOCUMENTS';
export const STATE_DOCUMENT = 'STATE_DOCUMENT';

export const ADD_ID_DOCUMENT = 'ADD_ID_DOCUMENT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_ID_DOCUMENT = 'MD_Obtiene id documento';
const PERMIT_GET_DOCUMENT = 'MD_Obtiene documento';
const PERMIT_DOCUMENT_EDIT = 'MD_Documento editar';
const PERMIT_ADD_DOCUMENT = 'MD_Agregar documento';
const PERMIT_EDIT_DOCUMENT = 'MD_Editar documento';
const PERMIT_LIST_DOCUMENTS = 'MD_Lista documentos ';
const PERMIT_DISABLE_DOCUMENT = 'MD_Inhabilitar documento ';

export function addMessageEdit(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_EDIT,
            mensaje: mensaje
        });
    };
}

export function addMessageAdd(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD,
            mensaje: mensaje
        });
    };
}

export function addMessageDisable(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_DISABLE,
            mensaje: mensaje
        });
    };
}

export function addIdDocument(id) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_ID_DOCUMENT,
            mensaje: id
        });
    };
}


//MD_Obtiene id documento
export function getIdDocument(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ID_DOCUMENT
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/document/getIdDocument/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ID_DOCUMENT,
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

//MD_Obtiene documento
export function getDocumentId(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_GET_DOCUMENT
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/document/get/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_DOCUMENT_ID,
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

//MD_Documento editar
export function getDocumentIdEdit(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DOCUMENT_EDIT
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/document/documentIdEdit/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_DOCUMENT_ID_EDIT,
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

//MD_Agregar documento
export function addDocument(token, documentN) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_DOCUMENT
    }
    documentN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_DOCUMENT
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/document/add`, documentN, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_DOCUMENT,
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

//MD_Editar documento
export function editDocument(token, documentE) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_EDIT_DOCUMENT
    }
    documentE.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_EDIT_DOCUMENT
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/document/edit`, documentE, { headers: headers })
            .then(response => {
                dispatch({
                    type: EDIT_DOCUMENT,
                    payload: response.data.respuesta
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_EDIT,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_MESSAGE_EDIT,
                            payload: respuesta
                        });
                    }
                }

            });
    }
}

//MD_Lista documentos 
export function getListDocuments(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_DOCUMENTS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/document/list`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_DOCUMENTS,
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
                                type: STATE_DOCUMENT,
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

//MD_Inhabilitar documento 
//disable the selected 
export function disableDocument(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DISABLE_DOCUMENT
    }
    const requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DISABLE_DOCUMENT
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/document/disable/${id}`, requestData, { headers: headers })
            .then(response => {
                dispatch({
                    type: DISABLE_DOCUMENT,
                    payload: response.data.respuesta
                })
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_DISABLE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        var data = JSON.parse(error.request.response);
                        let respuesta = data.respuesta;
                        dispatch({
                            type: ADD_MESSAGE_DISABLE,
                            payload: respuesta
                        });
                    }
                }

            });
    }
}
