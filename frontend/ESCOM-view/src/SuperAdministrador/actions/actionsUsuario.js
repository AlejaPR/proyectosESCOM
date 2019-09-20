import axios from 'axios';

export const MOSTRAR_USUARIOS = 'MOSTRAR_USUARIOS'
export const AGREGAR_USUARIO = 'AGREGAR_USUARIO'
export const INFORMACION_USUARIO = 'INFORMACION_USUARIO'
export const ANADIR_CEDULA_EDITAR = "ANADIR_CEDULA_EDITAR"
export const EDITAR_USUARIO = "EDITAR_USUARIO"
export const ACTUALIZAR_USUARIOS='ACTUALIZAR_USUARIOS';

export function actionConsultarUsuarios() {
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/")
            .then(response => {
                dispatch({
                    type: MOSTRAR_USUARIOS,
                    respuesta: response.data
                });
            });
    };
}

export function actionAgregarUsuario(usuario) {
    return (dispatch, getState) => {
        axios.post("http://localhost:9090/SuperadministradorESCOM-web/api/usu/", usuario)
            .then(response => {
                dispatch({
                    type: AGREGAR_USUARIO,
                    usuarioARegistrar: usuario
                });
            });
    }
}

export function actionCargarInformacionDeUsuario(cedula) {
    return (dispatch, getState) => {
        axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/" + cedula)
            .then(response => {
                dispatch({
                    type: INFORMACION_USUARIO,
                    informacionUsuario: response.data
                });
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

export function actionActualizarUsuarios(usuario) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_USUARIOS,
            usuario: usuario
        });
    }
}

export function actionEditarUsuario(usuario, cedula) {
    return (dispatch, getState) => {
        axios.put("http://localhost:9090/SuperadministradorESCOM-web/api/usu/" + cedula, usuario).then(response => {
            console.log("RESULTADO", response.status);
        });;

        dispatch({
            type: EDITAR_USUARIO,
            payload: usuario
        });
    }
}
