import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';
import { URL_BASE } from '../../../SuperAdministrador/utilitario/Configuracion.js';


export const GET_LIST_CONDITIONS = 'GET_LIST_CONDITIONS';
export const GET_CONDITIONS_PER = 'GET_CONDITIONS_PER';
export const GET_CONDITION_ID = 'GET_CONDITION_ID';
export const ADD_CONDITION = 'ADD_CONDITION';
export const EDIT_CONDITION = 'EDIT_CONDITION';
export const DISABLE_CONDITION = 'DISABLE_CONDITION';
export const APPROVE_CONDITION = 'APPROVE_CONDITION';
export const STATE_CONDITION = 'STATE_CONDITION';
export const STATE_CONDITION_PER = 'STATE_CONDITION_PER';

export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_DISABLE = 'ADD_MESSAGE_DISABLE';
export const ADD_MESSAGE_APPROVE = 'ADD_MESSAGE_APPROVE';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const PERMIT_LIST_CONDITIONS = 'MD_Lista condiciones';
const PERMIT_LIST_CONDITIONS_PER = 'MD_Lista condiciones porcentaje';
const PERMIT_GET_CONDITION = 'MD_Obtiene condicion';
const PERMIT_ADD_CONDITION = 'MD_Agregar condicion';
const PERMIT_EDIT_CONDITION = 'MD_Editar condicion';
const PERMIT_DISABLE_CONDITION = 'MD_Inhabilitar condicion';
const PERMIT_APPROVE_CONDITION = 'MD_Prueba';

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

export function addMessageApprove(mensaje) {
  return (dispatch, getState) => {
    dispatch({
      type: ADD_MESSAGE_APPROVE,
      mensaje: mensaje
    });
  };
}

//MD_Lista condiciones
//returns the list of conditions of the selected document
export function getListConditions(token, idD) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_CONDITIONS
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/condition/list/${idD}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_LIST_CONDITIONS,
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
                type: STATE_CONDITION,
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

//MD_Lista condiciones porcentaje
//returns the list of conditions of the selected 
//document with the percentage of activities completed
export function getConditionsPer(token, idP) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_LIST_CONDITIONS_PER
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/condition/listPercentage/${idP}`, { headers: headers })
      .then(response => {
        dispatch({
          type: GET_CONDITIONS_PER,
          payload: response.data
        })
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
                type: STATE_CONDITION_PER,
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

//MD_Obtiene condicion
//returns the information of the selected condition
export function getConditionId(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_GET_CONDITION
  }
  return (dispatch, getState) => {
    axios.get(`${URL_BASE}/proyectosESCOM-web/api/condition/get/${id}`, { headers: headers })
      .then(response => {
        console.log(response)
        dispatch({
          type: GET_CONDITION_ID,
          payload: response.data
        })
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

//MD_Agregar condicion
//add a new condition
export function addCondition(token, conditionN) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_ADD_CONDITION
  }
  conditionN.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_ADD_CONDITION
  };
  return (dispatch, getState) => {
    axios.post(`${URL_BASE}/proyectosESCOM-web/api/condition/add`, conditionN, { headers: headers })
      .then(response => {
        dispatch({
          type: ADD_CONDITION,
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

//MD_Editar condicion
//edit condition
export function editCondition(token, conditionE) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_EDIT_CONDITION
  }
  conditionE.requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_EDIT_CONDITION
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/condition/edit`, conditionE, { headers: headers })
      .then(response => {
        dispatch({
          type: EDIT_CONDITION,
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

//MD_Inhabilitar condicion
//disable the selected condition
export function disableCondition(token, id) {
  const headers = {
    'Content-Type': 'application/json',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_DISABLE_CONDITION
  }
  const requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_DISABLE_CONDITION
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/condition/disable/${id}`, requestData, { headers: headers })
      .then(response => {
        dispatch({
          type: DISABLE_CONDITION,
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

//MD_Aprobar condicion
export function approveCondition(token, id) {
  const headers = {
    'Content-Type': 'application/json; charset= UTF-8',
    'TokenAuto': desencriptar(token),
    'Permiso': PERMIT_APPROVE_CONDITION
  }
  const requestData = {
    'ip': localStorage.getItem('Ip'),
    'token': desencriptar(token),
    'operacion': PERMIT_APPROVE_CONDITION
  };
  return (dispatch, getState) => {
    axios.put(`${URL_BASE}/proyectosESCOM-web/api/condition/approve/${id}`, requestData, { headers: headers })
      .then(response => {
        dispatch({
          type: APPROVE_CONDITION,
          payload: response.data.respuesta
        })
      }).catch(error => {
        if (error.request.response === '') {
          dispatch({
            type: ADD_MESSAGE_APPROVE,
            payload: 'error server'
          });
        } else {
          if (error.request) {
            var data = JSON.parse(error.request.response);
            let respuesta = data.respuesta;
            dispatch({
              type: ADD_MESSAGE_APPROVE,
              payload: respuesta
            });
          }
        }

      });
  }
}

