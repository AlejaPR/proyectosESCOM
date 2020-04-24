import axios from 'axios';
import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_CURRENT_VERSIONS = 'GET_CURRENT_VERSIONS';
export const GET_OLD_VERSIONS = 'GET_OLD_VERSIONS';
export const STATE_OLD_VERSION = 'STATE_OLD_VERSION';
export const STATE_CURRENT_VERSION = 'STATE_CURRENT_VERSION';

const PERMIT_CURRENT_VERSIONS = 'MD_Prueba';
const PERMIT_OLD_VERSIONS = 'MD_Prueba';

const ADD_MESSAGE = 'ADD_MESSAGE';

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