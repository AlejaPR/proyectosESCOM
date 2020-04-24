import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_LIST_GENERAL_PRO = 'GET_LIST_GENERAL_PRO';
export const GET_GENERAL_PRO = 'GET_GENERAL_PRO';
export const ADD_GENERAL_PRO = 'ADD_GENERAL_PRO';
export const EDIT_GENERAL_PRO = 'EDIT_GENERAL_PRO';
export const DISABLE_GENERAL_PRO = 'DISABLE_GENERAL_PRO';
export const STATE_GENERAL_PROGRAM = 'STATE_GENERAL_PROGRAM';

export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_LIST_GENERAL_PRO = 'MD_Prueba';
const PERMIT_GET_GENERAL_PRO = 'MD_Prueba';
const PERMIT_ADD_GENERAL_PRO = 'MD_Prueba';
const PERMIT_EDIT_GENERAL_PRO = 'MD_Prueba';
const PERMIT_DISABLE_GENERAL_PRO = 'MD_Prueba';

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


export function getListGeneralPro(token) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_GENERAL_PRO
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/generalProgram/list`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_LIST_GENERAL_PRO,
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
                type: STATE_GENERAL_PROGRAM,
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


export function getGeneralPro(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_GET_GENERAL_PRO
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/generalProgram/get/${id}`, { headers: headers })
      .then(response => {
        console.log(response.data)
        dispatch({
          type: GET_GENERAL_PRO,
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


export function addGeneralPro(token, programN) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_ADD_GENERAL_PRO
  }
  programN.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_ADD_GENERAL_PRO
  };
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/proyectosESCOM-web/api/generalProgram/add`, programN, { headers: headers })
      .then(response => {
        dispatch({
          type: ADD_GENERAL_PRO,
          payload: response.data.respuesta
        });
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: ADD_GENERAL_PRO,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            var data = JSON.parse(error.request.response);
            let respuesta = data.respuesta;
            dispatch({
              type: ADD_GENERAL_PRO,
              payload: respuesta
            });
          }
        }

      });
  }
}


export function editGeneralPro(token, programE) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_EDIT_GENERAL_PRO
  }
  programE.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_EDIT_GENERAL_PRO
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/generalProgram/edit`, programE, { headers: headers })
      .then(response => {
        dispatch({
          type: EDIT_GENERAL_PRO,
          payload: response.data.respuesta
        });
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: EDIT_GENERAL_PRO,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            var data = JSON.parse(error.request.response);
            let respuesta = data.respuesta;
            dispatch({
              type: EDIT_GENERAL_PRO,
              payload: respuesta
            });
          }
        }

      });
  }
}


export function disableGeneralPro(token, generalN) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_DISABLE_GENERAL_PRO
  }
  generalN.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_DISABLE_GENERAL_PRO
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/generalProgram/disable`, generalN, { headers: headers })
      .then(response => {
        dispatch({
          type: DISABLE_GENERAL_PRO,
          payload: response.data.respuesta
        })
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: DISABLE_GENERAL_PRO,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            var data = JSON.parse(error.request.response);
            let respuesta = data.respuesta;
            dispatch({
              type: DISABLE_GENERAL_PRO,
              payload: respuesta
            });
          }
        }

      });
  }
}
