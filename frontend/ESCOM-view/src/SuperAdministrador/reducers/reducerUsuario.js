
import {
    MOSTRAR_USUARIOS,
    ESTADO_USUARIOS,
    MENSAJE_EDITAR, MOSTRAR_ACTIVIDADES_USUARIO,
    MOSTRAR_DOCUMENTOS,
    MENSAJE_REGISTRAR,
    AGREGAR_USUARIO,
    INFORMACION_USUARIO,
    ANADIR_CEDULA_EDITAR,
    EDITAR_USUARIO,
    ACTUALIZAR_USUARIOS,
    MENSAJE_LOGIN
} from '../actions/actionsUsuario.js'


const initialState = {
    usuariosRegistrados: [],
    estadoUsuarios: false,
    actividadesUsuario: [],
    tiposDocumento: [],
    usuarioEditar: [],
    cedula: [],
    redireccionLogin: [],
    mensajeLogin: [],
    mensajeRegistrar: '',
    mensajeEditar: ''
}

export function reducerUsuario(state = initialState, action) {
    switch (action.type) {
        case MOSTRAR_USUARIOS:
            return Object.assign({}, state, { usuariosRegistrados: action.respuesta })
        case MOSTRAR_ACTIVIDADES_USUARIO:
            return Object.assign({}, state, { actividadesUsuario: action.respuesta })
        case AGREGAR_USUARIO:
            return {
                ...state,
                usuariosRegistrados: state.usuariosRegistrados.concat(action.usuarioARegistrar)
            }
        case MENSAJE_REGISTRAR:
            return Object.assign({}, state, { mensajeRegistrar: action.mensaje })
        case MENSAJE_EDITAR:
            return Object.assign({}, state, { mensajeEditar: action.mensaje })
        case INFORMACION_USUARIO:
            return Object.assign({}, state, { usuarioEditar: action.informacionUsuario })
        case ANADIR_CEDULA_EDITAR:
            return Object.assign({}, state, { cedula: action.cedula })
        case EDITAR_USUARIO:
            return Object.assign({}, state, { usuarioEditar: action.payload })
        case ACTUALIZAR_USUARIOS:
            return Object.assign({}, state, { usuariosRegistrados: action.usuario })
        case MENSAJE_LOGIN:
            return Object.assign({}, state, { mensajeLogin: action.mensaje })
        case MOSTRAR_DOCUMENTOS:
            return Object.assign({}, state, { tiposDocumento: action.respuesta })
        case ESTADO_USUARIOS:
            return Object.assign({}, state, { estadoUsuarios: action.estado })
        default:
            return state
    }
}

