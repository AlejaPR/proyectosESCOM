import {
    ESTADO_MODULOS, MOSTRAR_MODULOS, ANADIR_CODIGO_EDITAR, INFORMACION_MODULO,
    MENSAJE_EDITAR_MODULO, ACTIVIDADES_MODULO, MENSAJE_SUSPENDER_MODULO, ACTUALIZAR_MODULOS
} from '../actions/actionsModulo.js'


const initialState = {
    modulosRegistrados: [],
    actividadesModulos: [],
    estadoModulos: false,
    codigoModulo: [],
    moduloEditar: [],
    mensajeEditarModulo: '',
    mensajeSuspenderModulo: ''
}

export function reducerModulo(state = initialState, action) {
    switch (action.type) {
        case MOSTRAR_MODULOS:
            return Object.assign({}, state, { modulosRegistrados: action.respuesta })
        case ACTIVIDADES_MODULO:
            return Object.assign({}, state, { actividadesModulos: action.actividades })
        case ESTADO_MODULOS:
            return Object.assign({}, state, { estadoModulos: action.estado })
        case ANADIR_CODIGO_EDITAR:
            return Object.assign({}, state, { codigoModulo: action.codigo })
        case INFORMACION_MODULO:
            return Object.assign({}, state, { moduloEditar: action.informacionModulo })
        case MENSAJE_EDITAR_MODULO:
            return Object.assign({}, state, { mensajeEditarModulo: action.mensaje })
        case MENSAJE_SUSPENDER_MODULO:
            return Object.assign({}, state, { mensajeSuspenderModulo: action.mensaje })
        case ACTUALIZAR_MODULOS:
            return Object.assign({}, state, { modulosRegistrados: action.modulo })
        default:
            return state
    }
}