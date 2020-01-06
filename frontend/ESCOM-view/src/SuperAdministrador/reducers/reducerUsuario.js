
import { MOSTRAR_USUARIOS, MOSTRAR_DOCUMENTOS, MENSAJE_REGISTRAR, AGREGAR_USUARIO, INFORMACION_USUARIO, ANADIR_CEDULA_EDITAR, EDITAR_USUARIO, ACTUALIZAR_USUARIOS, LOGIN_USUARIO } from '../actions/actionsUsuario.js'


const initialState = {
    usuariosRegistrados: [],
    tiposDocumento: [],
    usuarioEditar: [],
    cedula: [],
    token: [],
    redireccionLogin: [],
    mensajeRegistrar:''
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
        case MENSAJE_REGISTRAR:
            return  Object.assign({}, state, { mensajeRegistrar: action.mensaje })
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
        case MOSTRAR_DOCUMENTOS:
            return Object.assign({}, state, { tiposDocumento: action.respuesta })
        default:
            return state
    }
}

