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

export function actionLoginUsuario(correo, contrasena, cambiar) {
    var crypto = require('crypto');
    var contrasenaEncryp = crypto.createHmac('sha256', correo).update(contrasena).digest('hex');
    cambiar(true);
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/login/" + correo + '/' + contrasenaEncryp)
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
    var tokenlim = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenlim
    }
    return (dispatch, getState) => {
        axios.delete("http://localhost:9090/proyectosESCOM-web/api/login/cerrarSesion/" + tokenlim, { headers: headers })
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


export function actionConsultarUsuarios(token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Consultar usuarios registrados'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/listarUsuarios", { headers: headers })
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
    debugger;
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso':'sj'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/redireccionUsuario/" + tokenRequest, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Asignacion de actividades a los usuarios'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/listarActividadesUsuario/" + numeroDocumento, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Consultar usuarios registrados'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/tipoDocumento", { headers: headers })
            .then(response => {
                dispatch({
                    type: MOSTRAR_DOCUMENTOS,
                    respuesta: response.data
                });
            });
    };
}

export function actionAgregarUsuario(usuario, token) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Registrar usuarios'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/usuario/registrarUsuario", usuario, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Asignacion de actividades a los usuarios'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/usuario/asignarActividad/" + numeroDocumento, actividad, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Suspender/activar usuarios'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/cambiarEstadoUsuario/" + cedula, { headers: headers })
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
        'Permiso': 'sa_Editar informacion de los usuarios'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/datosUsuario/" + cedula, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Asignacion de actividades a los usuarios'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/modulo/listarModulos", { headers: headers })
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

export function actionLimpiar() {
    return (dispatch, getState) => {
        dispatch({
            type: ACTIVIDADES_SIN_ASIGNAR,
            respuesta: undefined
        });
    };
}

export function actionEliminarActividades(actividades, token, numeroDocumento) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Asignacion de actividades a los usuarios'
    }
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/proyectosESCOM-web/api/usuario/eliminarActividadUsuario/" + numeroDocumento, actividades, { headers: headers })
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
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'sa_Asignacion de actividades a los usuarios'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/listarActividadesNoAsociadasUsuario/" + numeroDocumento + "/" + codigoModulo, { headers: headers })
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

export function actionEditarUsuario(usuario, cedula, token) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': 'sa_Editar informacion de los usuarios'
    }
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/proyectosESCOM-web/api/usuario/editarUsuario/" + cedula, usuario, { headers: headers })
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
