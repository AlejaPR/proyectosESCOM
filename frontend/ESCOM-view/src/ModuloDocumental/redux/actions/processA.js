import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_LIST_PROCESSES = 'GET_LIST_PROCESSES';
export const ADD_PROCESS = 'ADD_PROCESS';
export const EDIT_PROCESS = 'EDIT_PROCESS';
export const DISABLE_PROCESS = 'DISABLE_PROCESS';
export const GET_PROCESS_ID = 'GET_PROCESS_ID';
export const STATE_PROCESS = 'STATE_PROCESS';

export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_LIST_PROCESSES = 'MD_Lista procesos';
const PERMIT_GET_PROCESS = 'MD_Obtiene proceso';
const PERMIT_ADD_PROCESS = 'MD_Agregar proceso';
const PERMIT_EDIT_PROCESS = 'MD_Editar proceso';
const PERMIT_DISABLE_PROCESS = 'MD_Inhabilitar proceso';

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

//MD_Lista procesos
export function getListProcesses(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_PROCESSES
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/process/list/${id}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_LIST_PROCESSES,
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
                type: STATE_PROCESS,
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

//MD_Obtiene proceso
export function getProcessId(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_GET_PROCESS
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/process/get/${id}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_PROCESS_ID,
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

//MD_Agregar proceso
export function addProcess(token, processN) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_ADD_PROCESS
  }
  processN.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_ADD_PROCESS
  };
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/proyectosESCOM-web/api/process/add`, processN, { headers: headers })
      .then(response => {
        dispatch({
          type: ADD_PROCESS,
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

//MD_Editar proceso
export function editProcess(token, processE) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_EDIT_PROCESS
  }
  processE.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_EDIT_PROCESS
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/process/edit`, processE, { headers: headers })
      .then(response => {
        dispatch({
          type: EDIT_PROCESS,
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

//MD_Inhabilitar proceso
//disable the selected 
export function disableProcess(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_DISABLE_PROCESS
  }
  const requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_DISABLE_PROCESS
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/process/disable/${id}`, requestData, { headers: headers })
      .then(response => {
        dispatch({
          type: DISABLE_PROCESS,
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
