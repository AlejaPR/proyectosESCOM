import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';

export const GET_LIST_PROGRAMS = 'GET_LIST_PROGRAMS';
export const GET_PROGRAM_ID = 'GET_PROGRAM_ID';
export const ADD_PROGRAM = 'ADD_PROGRAM';
export const EDIT_PROGRAM = 'EDIT_PROGRAM';
export const DISABLE_PROGRAM = 'DISABLE_PROGRAM';
export const STATE_PROGRAM = 'STATE_PROGRAM';

export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_LIST_PROGRAMS = 'MD_Lista programas';
const PERMIT_GET_PROGRAM = 'MD_Obtiene programa';
const PERMIT_ADD_PROGRAM = 'MD_Agregar programa';
const PERMIT_EDIT_PROGRAM = 'MD_Editar programa';
const PERMIT_DISABLE_PROGRAM = 'MD_Inhabilitar programa';

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

//MD_Lista programas
export function getListPrograms(token) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_PROGRAMS
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/program/list`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_LIST_PROGRAMS,
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
                type: STATE_PROGRAM,
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

//MD_Obtiene programa
export function getProgramId(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_GET_PROGRAM
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/program/get/${id}`, { headers: headers })
      .then(response => {
        console.log(response.data)
        dispatch({
          type: GET_PROGRAM_ID,
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

//MD_Agregar programa
export function addProgram(token, programN) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_ADD_PROGRAM
  }
  programN.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_ADD_PROGRAM
  };
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/proyectosESCOM-web/api/program/add`, programN, { headers: headers })
      .then(response => {
        dispatch({
          type: ADD_PROGRAM,
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

//MD_Editar programa
export function editProgram(token, programE) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_EDIT_PROGRAM
  }
  programE.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_EDIT_PROGRAM
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/program/edit`, programE, { headers: headers })
      .then(response => {
        dispatch({
          type: EDIT_PROGRAM,
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

//MD_Inhabilitar programa 
//disable the selected 
export function disableProgram(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_DISABLE_PROGRAM
  }
  const requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_DISABLE_PROGRAM
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/program/disable/${id}`, requestData, { headers: headers })
      .then(response => {
        dispatch({
          type: DISABLE_PROGRAM,
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
