import {
    ESTADO_MODULOS, MOSTRAR_MODULOS, ANADIR_CODIGO_EDITAR, INFORMACION_MODULO,
    MENSAJE_EDITAR_MODULO
} from '../actions/actionsModulo.js'


const initialState = {
    modulosRegistrados: [],
    estadoModulos: false,
    codigoModulo: [],
    moduloEditar: [],
    mensajeEditarModulo:''
}

export function reducerModulo(state = initialState, action) {
    switch (action.type) {
        case MOSTRAR_MODULOS:
            return Object.assign({}, state, { modulosRegistrados: action.respuesta })
        case ESTADO_MODULOS:
            return Object.assign({}, state, { estadoModulos: action.estado })
        case ANADIR_CODIGO_EDITAR:
            return Object.assign({}, state, { codigoModulo: action.codigo })
        case INFORMACION_MODULO:
            return Object.assign({}, state, { moduloEditar: action.informacionModulo })
        case MENSAJE_EDITAR_MODULO:
            return Object.assign({}, state, { mensajeEditarModulo: action.mensaje })
        default:
            return state
    }
}