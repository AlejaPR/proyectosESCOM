import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_ANNEXES = 'GET_LIST_ANNEXES';
export const SEARCH_ANNEX_S = 'SEARCH_ANNEX_S';
export const GET_ANNEX_ID = 'GET_ANNEX_ID';
export const ADD_ANNEX = 'ADD_ANNEX';
export const EDIT_ANNEX = 'EDIT_ANNEX';
export const DISABLE_ANNEX = 'DISABLE_ANNEX';

export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE = 'ADD_MESSAGE';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';
const PERMIT_LIST_ANNEXES = 'MD_Lista anexos';
const PERMIT_SEARCH_ANNEXES = 'MD_Buscar anexos';
const PERMIT_GET_ANNEX = 'MD_Obtiene anexo';
const PERMIT_ADD_ANNEX = 'MD_Agregar anexo';
const PERMIT_EDIT_ANNEX = 'MD_Editar anexo';
const PERMIT_DISABLE_ANNEX = 'MD_Inhabilitar anexo';

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

//MD_Lista anexos
export function getListAnnexes(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_ANNEXES
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/api/annex/list/${id}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_LIST_ANNEXES,
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

//MD_Buscar anexos
export function searchAnnexS(token, searchAnn) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_SEARCH_ANNEXES
  }
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/api/annex/searchAnnexS`, searchAnn, { headers: headers })
      .then(response => {
        dispatch({
          type: SEARCH_ANNEX_S,
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

//MD_Obtiene anexo
export function getAnnexId(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_GET_ANNEX
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/api/annex/get/${id}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_ANNEX_ID,
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

//MD_Agregar anexo
export function addAnnex(token, annex) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_ADD_ANNEX
  }
  annex.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_ADD_ANNEX
  };
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/api/annex/add`, annex, { headers: headers })
      .then(response => {
        dispatch({
          type: ADD_ANNEX,
          payload: response.data.respuesta
        });
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: ADD_ANNEX,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            dispatch({
              type: ADD_ANNEX,
              payload: 'error server'
            });
          }
        }

      });
  }
}

//MD_Editar anexo
export function editAnnex(token, annex) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_EDIT_ANNEX
  }
  annex.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_EDIT_ANNEX
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/api/annex/edit`, annex, { headers: headers })
      .then(response => {
        dispatch({
          type: EDIT_ANNEX,
          payload: response.data.respuesta
        });
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: EDIT_ANNEX,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            dispatch({
              type: EDIT_ANNEX,
              payload: 'error server'
            });
          }
        }

      });
  }

}

//MD_Inhabilitar anexo
export function disableAnnex(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_DISABLE_ANNEX
  }
  const requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_DISABLE_ANNEX
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/api/annex/disable/${id}`, requestData, { headers: headers })
      .then(response => {
        dispatch({
          type: DISABLE_ANNEX,
          payload: response.data.respuesta
        });
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: ADD_MESSAGE_DISABLE,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            dispatch({
              type: ADD_MESSAGE_DISABLE,
              payload: 'error server'
            });
          }
        }

      });
  }
}
