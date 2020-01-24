import axios from 'axios';
import { encriptar, desencriptar } from '../componentes/general/Encriptar.js';
import {mensajesDeError} from '../utilitario/MensajesError.js';

export const MOSTRAR_USUARIOS = 'MOSTRAR_USUARIOS';
export const ESTADO_USUARIOS = 'ESTADO_USUARIOS';
export const MOSTRAR_DOCUMENTOS = 'MOSTRAR_DOCUMENTOS';
export const ACTIVIDADES_SIN_ASIGNAR='ACTIVIDADES_SIN_ASIGNAR';
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
export const MENSAJE_SUSPENDER = 'MENSAJE_SUSPENDER';
export const REDIRECCIONAR_LOGIN = 'REDIRECCIONAR_LOGIN';
export const ESTADO_ASIGNAR='ESTADO_ASIGNAR';
export const MODULOS_REGISTRADOS='MODULOS_REGISTRADOS';

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
                        switch (o.respuesta) {
                            case 'Credenciales incorrectas':
                                dispatch({
                                    type: MENSAJE_LOGIN,
                                    mensaje: 'Credenciales incorrectas'
                                });
                                break;
                            case 'Ocurrio un error en el servidor':
                                dispatch({
                                    type: MENSAJE_LOGIN,
                                    mensaje: 'Ocurrio un error en el servidor'
                                });
                                break;
                            default:
                                dispatch({
                                    type: MENSAJE_LOGIN,
                                    mensaje: 'Ocurrio un error en el servidor'
                                });
                                break;
                        }
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

    axios.delete("http://localhost:9090/SuperadministradorESCOM-web/api/usuario/" + tokenlim, { headers: headers })
        .then(response => {
            if (response.status === 200) {
                console.log('cerrada')
            }
        }).catch(function (error) {
            // handle error
            console.log('error es: ', error)
        });
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
        'Permiso': 'SA_VER ICIONES'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/listarUsuarios", { headers: headers })
            .then(response => {
                console.log('respuesta usuarios reg',response.data);
                dispatch({
                    type: MOSTRAR_USUARIOS,
                    respuesta: response.data
                });
            }).catch((error) => {
                if (error.request.response === '') {
                    
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta==='Sin permiso'){
                            dispatch({
                                type: ESTADO_USUARIOS,
                                estado: true
                            });
                        }else{
                            //
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
        'Permiso': 'SA_VER ICIONES'
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
        'Permiso': 'SA_VER ICIONES'
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
        'Permiso': 'SA_CREAR USUARIO'
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
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_REGISTRAR,
                                mensaje: respuesta
                            });
                        }else{
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

export function actionAsignarActividad(token,numeroDocumento,actividad) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/proyectosESCOM-web/api/usuario/asignarActividad/"+numeroDocumento,actividad, { headers: headers })
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
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_ASIGNAR,
                                mensaje: respuesta
                            });
                        }else{
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

export function actionSuspenderActivarUsuario(cedula, token,actualizados) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_CREAR USUARIO'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/cambiarEstadoUsuario/"+cedula, { headers: headers })
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
                console.log(error);

                if (error.request.response === '') {
                    dispatch({
                        type: MENSAJE_SUSPENDER,
                        mensaje: 'Servidor fuera de servicio temporalmente'
                    });
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_SUSPENDER,
                                mensaje: respuesta
                            });
                        }else{
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
        'Permiso': 'SA_CREAR USUARIO'
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
                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta!==''){
                            dispatch({
                                type: MENSAJE_EDITAR,
                                mensaje: respuesta
                            });
                        }else{
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
        'Permiso': 'SA_VER ICIONES'
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
                    

                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta==='Sin permiso'){
                            dispatch({
                                type: ESTADO_ASIGNAR,
                                estado: true
                            });
                        }else{
                            //
                        }
                    }
                } 
            });
    };
}

export function actionConsultarActividadesSinAsignar(token,numeroDocumento,codigoModulo) {
    var tokenRequest = desencriptar(token);
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': tokenRequest,
        'Permiso': 'SA_VER ICIONES'
    }
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/proyectosESCOM-web/api/usuario/listarActividadesNoAsociadasUsuario/"+numeroDocumento+"/"+codigoModulo, { headers: headers })
            .then(response => {
                dispatch({
                    type: ACTIVIDADES_SIN_ASIGNAR,
                    respuesta: response.data
                });
                
            }).catch((error) => {
                if (error.request.response === '') {
                    

                }else{
                    if (error.request) {
                        var o = JSON.parse(error.request.response);
                        let respuesta=mensajesDeError(o.respuesta);
                        if(respuesta==='Sin permiso'){
                            dispatch({
                                type: ESTADO_ASIGNAR,
                                estado: true
                            });
                        }else{
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
        'Permiso': 'SA_CREAR USUARIO'
    }
    console.log('usuario k llego',usuario);
    console.log('cedula es',cedula);

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
            });
    }
}
