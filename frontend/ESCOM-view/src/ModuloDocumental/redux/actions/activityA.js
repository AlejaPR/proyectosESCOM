import axios from 'axios'

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_LIST_ACTIVITIES_INFO = 'GET_LIST_ACTIVITIES_INFO';
export const GET_LIST_ACTIVITIES_ANNEX = 'GET_LIST_ACTIVITIES_ANNEX';
export const GET_ACTIVITY_ID = 'SHOW_ACTIVITY_ID';
export const GET_ACTIVITY_ANNEX = 'SHOW_ACTIVITY_ANNEX';
export const ADD_ACTIVITY = 'ADD_ACTIVITY';
export const EDIT_ACTIVITY = 'EDIT_ACTIVITY';
export const DELETE_ACTIVITY = 'DELETE_ACTIVITY';
export const CHANGE_STATUS = 'CHANGE_STATUS';
export const ASSOCIATE_ANNEX = 'ASSOCIATE_ANNEX';

export const ADD_INFORMATION = 'ADD_INFORMATION';
export const GET_ALL_INFORMATION = 'GET_ALL_INFORMATION';

export const ADD_MESSAGE_EDIT = 'ADD_MESSAGE_EDIT';
export const ADD_MESSAGE_ADD = 'ADD_MESSAGE_ADD';
export const ADD_MESSAGE_DELETE = 'ADD_MESSAGE_DELETE';
export const ADD_MESSAGE_ASSOCIATE = 'ADD_MESSAGE_ASSOCIATE';
export const ADD_MESSAGE_CHANGE = 'ADD_MESSAGE_CHANGE';
export const ADD_MESSAGE_ADD_INFO = 'ADD_MESSAGE_ADD_INFO';
export const ADD_MESSAGE = 'ADD_MESSAGE';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';
const PERMIT_LIST_ACTIVITIES_INFO = 'MD_Prueba';
const PERMIT_LIST_ACTIVITIES_ANNEX = 'MD_Prueba';
const PERMIT_GET_ACTIVITY = 'MD_Obtiene actividad';
const PERMIT_GET_ACTIVITY_ANNEX = 'MD_Prueba';
const PERMIT_ADD_ACTIVITY = 'MD_Agregar actividad';
const PERMIT_EDIT_ACTIVITY = 'MD_Editar actividad';
const PERMIT_DELETE_ACTIVITY = 'MD_Eliminar actividad';
const PERMIT_ADD_INFORMATION = 'MD_Agregar informacion';
const PERMIT_ASSOCIATE_ANNEX = 'MD_Prueba';
const PERMIT_CHANGE_STATUS = 'MD_Prueba';
const PERMIT_ALL_INFORMATION = 'MD_toda informacion';

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

export function addMessageAddInfo(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ADD_INFO,
            mensaje: mensaje
        });
    };
}

export function addMessageChange(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_CHANGE,
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

export function addMessageAssociate(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: ADD_MESSAGE_ASSOCIATE,
            mensaje: mensaje
        });
    };
}

//MD_Lista actividades informacion
export function getListActivitiesInfo(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_ACTIVITIES_INFO
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/activity/listInfo/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_ACTIVITIES_INFO,
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

//MD_Lista actividades anexo
export function getListActivitiesAnnex(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_LIST_ACTIVITIES_ANNEX
    }

    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/activity/listAnnex/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_LIST_ACTIVITIES_ANNEX,
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


//MD_Obtiene actividad
export function getActivityId(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_GET_ACTIVITY
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/activity/getActivity/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ACTIVITY_ID,
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

//MD_Obtiene actividad anexo
export function getActivityAnnex(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_GET_ACTIVITY_ANNEX
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/activity/getActivityAnnex/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ACTIVITY_ANNEX,
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

//MD_Agregar actividad
export function addActivity(token, activityN) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_ACTIVITY
    }
    activityN.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_ACTIVITY
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/activity/add`, activityN, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_ACTIVITY,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_ADD,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_ADD,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Editar actividad
export function editActivity(token, activityE) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_EDIT_ACTIVITY
    }
    activityE.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_EDIT_ACTIVITY
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/activity/edit`, activityE, { headers: headers })
            .then(response => {
                console.log(response.data)
                dispatch({
                    type: EDIT_ACTIVITY,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_EDIT,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_EDIT,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Cambiar estado
export function changeStatus(token, activityE) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_CHANGE_STATUS
    }
    activityE.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_CHANGE_STATUS
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/activity/changeStatus`, activityE, { headers: headers })
            .then(response => {
                console.log(response.data)
                dispatch({
                    type: CHANGE_STATUS,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_CHANGE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_CHANGE,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Eliminar actividad
export function deleteActivity(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_DELETE_ACTIVITY
    }
    const requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_DELETE_ACTIVITY
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/api/activity/delete/${id}`, requestData, { headers: headers })
            .then(response => {
                dispatch({
                    type: DELETE_ACTIVITY,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_MESSAGE_DELETE,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_MESSAGE_DELETE,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Agregar informacion
export function addInformation(token, info) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ADD_INFORMATION
    }
    info.requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ADD_INFORMATION
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/activity/addInformation`, info, { headers: headers })
            .then(response => {
                dispatch({
                    type: ADD_INFORMATION,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ADD_INFORMATION,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ADD_INFORMATION,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_Asociar anexo
export function associateAnnex(token, activity, annexo) {
    const headers = {
        'Content-Type': 'application/json; charset= UTF-8',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ASSOCIATE_ANNEX
    }
    const requestData = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMIT_ASSOCIATE_ANNEX
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/api/activity/associate/${activity}/${annexo}`, requestData, { headers: headers })
            .then(response => {
                dispatch({
                    type: ASSOCIATE_ANNEX,
                    payload: response.data.data
                });
            }).catch(error => {
                if (error.request.response === '') {
                    dispatch({
                        type: ASSOCIATE_ANNEX,
                        payload: 'error server'
                    });
                } else {
                    if (error.request) {
                        dispatch({
                            type: ASSOCIATE_ANNEX,
                            payload: 'error server'
                        });
                    }
                }

            });
    }
}

//MD_toda informacion
export function getAllInformation(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_ALL_INFORMATION
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/activity/allInformation/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_ALL_INFORMATION,
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


