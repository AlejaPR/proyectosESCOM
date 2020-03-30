import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_ANNEX_VERSIONS = 'GET_ANNEX_VERSIONS';
export const ADD_ANNEX_VERSION = 'ADD_ANNEX_VERSION';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';
const PERMIT_GET_ANNEX_VERSION = 'MD_Lista version anexo';
const PERMIT_ADD_ANNEX_VERSION = 'MD_Agregar version anexo';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';

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
        axios.get(`${URL_BASE}/api/annexVersion/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ANNEX_VERSIONS,
                    payload: response.data
                });
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
                axios.post(`${URL_BASE}/api/annexVersion/add/`, annexN, { headers: headers })
                    .then(response => {
                        dispatch({
                            type: ADD_ANNEX_VERSION,
                            payload: response.data.data
                        });
                    });
            }).catch(err => {
                dispatch({
                    type: ADD_ANNEX_VERSION,
                    payload: err.data
                });
            })
    }
}
