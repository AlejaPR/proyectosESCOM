import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import { mensajesDeError } from '../utilitario/MensajesError.js';

export const MOSTRAR_USUARIOS = 'MOSTRAR_USUARIOS';
export const MODULOS_ACCESO = 'MODULOS_ACCESO';
export const ESTADO_USUARIOS = 'ESTADO_USUARIOS';
export const MOSTRAR_DOCUMENTOS = 'MOSTRAR_DOCUMENTOS';
export const ACTIVIDADES_SIN_ASIGNAR = 'ACTIVIDADES_SIN_ASIGNAR';
export const MOSTRAR_ACTIVIDADES_USUARIO = 'MOSTRAR_ACTIVIDADES_USUARIO';
export const AGREGAR_USUARIO = 'AGREGAR_USUARIO';
export const AGREGAR_ACTIVIDAD = 'AGREGAR_ACTIVIDAD';
export const INFORMACION_USUARIO = 'INFORMACION_USUARIO';
export const ANADIR_CEDULA_EDITAR = "ANADIR_CEDULA_EDITAR";
export const EDITAR_USUARIO = "EDITAR_USUARIO";
export const ACTUALIZAR_USUARIOS = 'ACTUALIZAR_USUARIOS';
export const MENSAJE_LOGIN = 'MENSAJE_LOGIN';
export const MENSAJE_REGISTRAR = 'MENSAJE_REGISTRAR';
export const MENSAJE_ASIGNAR = 'MENSAJE_ASIGNAR';
export const MENSAJE_EDITAR = 'MENSAJE_EDITAR';
export const MENSAJE_CERRAR_SESION = 'MENSAJE_CERRAR_SESION';
export const MENSAJE_SUSPENDER = 'MENSAJE_SUSPENDER';
export const REDIRECCIONAR_LOGIN = 'REDIRECCIONAR_LOGIN';
export const ESTADO_ASIGNAR = 'ESTADO_ASIGNAR';
export const MODULOS_REGISTRADOS = 'MODULOS_REGISTRADOS';
export const NOMBRE_USUARIO = 'NOMBRE_USUARIO';

const URL_BASE = 'http://localhost:9090';
const PERMISO_REGISTRAR = 'SA_Registrar usuarios';
const PERMISO_CONSULTAR_USUARIOS = 'SA_Consultar usuarios registrados';
const PERMISO_EDITAR_USUARIOS = 'SA_Editar informacion de los usuarios';
const PERMISO_ASIGNACION_ACTIVIDADES = 'SA_Asignacion de actividades a los usuarios';
const PERMISO_SUSPENDER_ACTIVAR = 'SA_Suspender/activar usuarios';

export function actionLoginUsuario(correo, contrasena, cambiar) {
    var crypto = require('crypto');
    var contrasenaEncryp = crypto.createHmac('sha256', correo).update(contrasena).digest('hex');
    cambiar(true);
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/login/${correo}/${contrasenaEncryp}`)
            .then(response => {
                if (response.status === 200) {
                    var token = encriptar(response.data.token);
                    localStorage.setItem('Token', token);
                    dispatch({
                        type: MENSAJE_LOGIN,
                        mensaje: 'Login correcto'
                    });
                    var nombre = response.data.nombre + ' ' + response.data.apellido;
                    localStorage.setItem('Nombre', nombre);
                    dispatch({
                        type: NOMBRE_USUARIO,
                        nombre: nombre
                    });
                }
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_LOGIN,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                    cambiar(false);
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        cambiar(false);
                        dispatch({
                            type: MENSAJE_LOGIN,
                            mensaje: o.respuesta
                        });
                    }
                }
            })
    };
}


export function actionCerrarSesion(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token)
    }
    return (dispatch, getState) => {
        axios.delete(`${URL_BASE}/proyectosESCOM-web/api/login/cerrarSesion/${desencriptar(token)}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_CERRAR_SESION,
                    mensaje: 'cerrada'
                });
            }).catch(function (error) {
                console.log('error', error);
                dispatch({
                    type: MENSAJE_CERRAR_SESION,
                    mensaje: 'cerrada'
                });
            });
    }
}




export function actionConsultarUsuarios(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_CONSULTAR_USUARIOS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/listar`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_USUARIOS,
                    respuesta: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {

                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta === 'Sin permiso') {
                            dispatch({
                                type: ESTADO_USUARIOS,
                                estado: true
                            });
                        } else {
                            //
                        }
                    }
                }
            });
    };
}

export function actionConsultarModulosAcceso(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token)
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/redireccion/${desencriptar(token)}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MODULOS_ACCESO,
                    respuesta: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {

                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta === 'Sin permiso') {
                            dispatch({
                                type: MODULOS_ACCESO,
                                respuesta: []
                            });
                        } else {
                            dispatch({
                                type: MODULOS_ACCESO,
                                respuesta: []
                            });
                        }
                    }
                }
            });
    };
}

export function actionConsultarActividadesUsuario(numeroDocumento, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_ASIGNACION_ACTIVIDADES
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/listarActividades/${numeroDocumento}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_ACTIVIDADES_USUARIO,
                    respuesta: response.data
                });
            }).catch((error) => {
                console.log('errors', error);
            });
    };
}

export function actionConsultarDocumentos(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_CONSULTAR_USUARIOS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/tipoDocumento`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_DOCUMENTOS,
                    respuesta: response.data
                });
            });
    };
}

export function actionAsignarIp() {
    return (dispatch, getState) => {
        axios.get("https://api.ipify.org/?format=json")
            .then(response => {
                localStorage.setItem('Ip', response.data.ip)
            }).then(

            );
    };
}

export function actionAgregarUsuario(usuario, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_REGISTRAR
    }
    usuario.datosSolicitud = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMISO_REGISTRAR
    };
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/usuarios/registrar`, usuario, { headers: headers })
            .then(response => {
                dispatch({
                    type: AGREGAR_USUARIO,
                    usuarioARegistrar: usuario
                });
                dispatch({
                    type: MENSAJE_REGISTRAR,
                    mensaje: 'Usuario registrado'
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_REGISTRAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_REGISTRAR,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_REGISTRAR,
                                mensaje: 'Ya existen los datos registrados previamente'
                            });
                        }
                    }
                }

            });

    }
}

export function actionAsignarActividad(token, numeroDocumento, actividad) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_ASIGNACION_ACTIVIDADES
    }
    actividad.datosSolicitud = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMISO_ASIGNACION_ACTIVIDADES
    }
    return (dispatch, getState) => {
        axios.post(`${URL_BASE}/proyectosESCOM-web/api/usuarios/asignarActividad/${numeroDocumento}`, actividad, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_ASIGNAR,
                    mensaje: 'Actividad asignada'
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_ASIGNAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_ASIGNAR,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_ASIGNAR,
                                mensaje: 'Ya existen los datos registrados previamente'
                            });
                        }
                    }
                }

            });

    }
}

export function actionSuspenderActivarUsuario(cedula, token, actualizados, registrados) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_SUSPENDER_ACTIVAR
    }
    let datosSolicitud = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMISO_SUSPENDER_ACTIVAR
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/usuarios/cambiarEstado/${cedula}`, datosSolicitud, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_SUSPENDER,
                    mensaje: 'Operacion hecha con exito'
                });
                dispatch({
                    type: ACTUALIZAR_USUARIOS,
                    usuario: actualizados
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_SUSPENDER,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_SUSPENDER,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_SUSPENDER,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                }

            });

    }
}

export function actionCargarInformacionDeUsuario(cedula, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_EDITAR_USUARIOS
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/datos/${cedula}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: INFORMACION_USUARIO,
                    informacionUsuario: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_EDITAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_EDITAR,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_EDITAR,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                }
            });
    };
}

export function actionConsultarModulos(token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_ASIGNACION_ACTIVIDADES
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/modulos/listar`, { headers: headers })
            .then(response => {
                dispatch({
                    type: MODULOS_REGISTRADOS,
                    respuesta: response.data
                });

            }).catch((error) => {
                if (error.request.response === '') {


                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta === 'Sin permiso') {
                            dispatch({
                                type: ESTADO_ASIGNAR,
                                estado: true
                            });
                        } else {
                            //
                        }
                    }
                }
            });
    };
}



export function actionEliminarActividades(actividades, token, numeroDocumento) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_ASIGNACION_ACTIVIDADES
    }
    actividades[0].datosSolicitud = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMISO_ASIGNACION_ACTIVIDADES
    }
    debugger;
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/usuarios/eliminarActividad/${numeroDocumento}`, actividades, { headers: headers })
            .then(response => {
                dispatch({
                    type: MENSAJE_ASIGNAR,
                    mensaje: 'Actividades eliminadas'
                });
            }).catch((error) => {
                console.log(error);

                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_ASIGNAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        console.log('respuesta', respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_ASIGNAR,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_ASIGNAR,
                                mensaje: 'Sin acceso al servicio'
                            });
                        }
                    }
                }

            });

    }
}

export function actionConsultarActividadesSinAsignar(token, numeroDocumento, codigoModulo) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_ASIGNACION_ACTIVIDADES
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/proyectosESCOM-web/api/usuarios/listarActividadesNoAsociadas/${numeroDocumento}/${codigoModulo}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: ACTIVIDADES_SIN_ASIGNAR,
                    respuesta: response.data
                });

            }).catch((error) => {
                if (error.request.response === '') {


                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta === 'Sin permiso') {
                            dispatch({
                                type: ESTADO_ASIGNAR,
                                estado: true
                            });
                        } else {
                            //
                        }
                    }
                }
            });
    };
}


export function actionEditarUsuario(usuario, cedula, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMISO_EDITAR_USUARIOS
    }
    usuario.datosSolicitud = {
        'ip': localStorage.getItem('Ip'),
        'token': desencriptar(token),
        'operacion': PERMISO_EDITAR_USUARIOS
    };
    return (dispatch, getState) => {
        axios.put(`${URL_BASE}/proyectosESCOM-web/api/usuarios/editar/${cedula}`, usuario, { headers: headers })
            .then(response => {
                dispatch({
                    type: EDITAR_USUARIO,
                    payload: usuario
                });
                dispatch({
                    type: MENSAJE_EDITAR,
                    mensaje: 'Modificado'
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_EDITAR,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                } else {
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta = mensajesDeError(o.respuesta);
                        if (respuesta !== '') {
                            dispatch({
                                type: MENSAJE_EDITAR,
                                mensaje: respuesta
                            });
                        } else {
                            dispatch({
                                type: MENSAJE_EDITAR,
                                mensaje: 'Ya existen los datos registrados previamente'
                            });
                        }
                    }
                }

            });
    }
}

export function actualizarMensajeCerrar(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_CERRAR_SESION,
            mensaje: mensaje
        });
    };
}

export function actualizarMensajeEditar(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_EDITAR,
            mensaje: mensaje
        });
    };
}

export function actualizarMensajeLogin(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_LOGIN,
            mensaje: mensaje
        });
    };
}

export function actualizarMensajeRegistrar(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_REGISTRAR,
            mensaje: mensaje
        });
    };
}

export function actualizarMensajeSuspender(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_SUSPENDER,
            mensaje: mensaje
        });
    };
}


export function asignarNombreUsuario(nombre) {
    return (dispatch, getState) => {
        dispatch({
            type: NOMBRE_USUARIO,
            nombre: nombre
        });
    };
}

export function actualizarMensajeAsignar(mensaje) {
    return (dispatch, getState) => {
        dispatch({
            type: MENSAJE_ASIGNAR,
            mensaje: mensaje
        });
    };
}

export function actionAsignarCedula(cedula) {
    return (dispatch, getState) => {
        dispatch({
            type: ANADIR_CEDULA_EDITAR,
            cedula: cedula
        });
    }
}

export function actionAsignarActividades() {
    return (dispatch, getState) => {
        dispatch({
            type: ACTIVIDADES_SIN_ASIGNAR,
            respuesta: null
        });
    }
}



export function actionActualizarUsuarios(usuarios) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_USUARIOS,
            usuario: usuarios
        });
    }
}