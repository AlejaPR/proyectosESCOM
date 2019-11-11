
import { MOSTRAR_USUARIOS, REDIRECCIONAR_LOGIN, AGREGAR_USUARIO, INFORMACION_USUARIO, ANADIR_CEDULA_EDITAR, EDITAR_USUARIO, ACTUALIZAR_USUARIOS, LOGIN_USUARIO } from '../actions/actionsUsuario.js'


const initialState = {
    usuariosRegistrados: [],
    usuarioEditar: [],
    cedula: [],
    token: [],
    redireccionLogin: []
}

export function reducerUsuario(state = initialState, action) {
    switch (action.type) {
        case MOSTRAR_USUARIOS:
            return Object.assign({}, state, { usuariosRegistrados: action.respuesta })
        case AGREGAR_USUARIO:
            return {
                ...state,
                usuariosRegistrados: state.usuariosRegistrados.concat(action.usuarioARegistrar)
            }
        case INFORMACION_USUARIO:
            return Object.assign({}, state, { usuarioEditar: action.informacionUsuario })
        case ANADIR_CEDULA_EDITAR:
            return Object.assign({}, state, { cedula: action.cedula })
        case EDITAR_USUARIO:
            return Object.assign({}, state, { usuarioEditar: action.payload })
        case ACTUALIZAR_USUARIOS:
            return Object.assign({}, state, { usuariosRegistrados: action.usuario })
        case LOGIN_USUARIO:
            return Object.assign({}, state, { token: action.token })
        default:
            return state
    }
}

