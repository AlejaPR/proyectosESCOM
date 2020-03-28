import axios from 'axios';
import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_CURRENT_VERSIONS = 'GET_CURRENT_VERSIONS';
export const GET_OLD_VERSIONS = 'GET_OLD_VERSIONS';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';

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
        axios.get(`${URL_BASE}/api/documentVersion/listCurrent/${id}`, { headers: headers })
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
                        dispatch({
                            type: ADD_MESSAGE,
                            payload: 'error server'
                        });
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
        axios.get(`${URL_BASE}/api/documentVersion/listOld/${id}`, { headers: headers })
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
                        dispatch({
                            type: ADD_MESSAGE,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}