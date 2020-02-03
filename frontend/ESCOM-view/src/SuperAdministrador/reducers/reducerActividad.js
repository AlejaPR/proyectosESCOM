import {
    AGREGAR_ACTIVIDAD,
    ESTADO_ACTIVIDADES,
    MOSTRAR_ACTIVIDADES,
    MENSAJE_REGISTRAR,
    MODULOS_REGISTRADOS
} from '../actions/actionActividad.js'


const initialState = {
    actividadesRegistradas: [],
    estadoActividades: false,
    mensajeRegistrar: '',
    modulosActividades:[]
}


export function reducerActividad(state = initialState, action) {
    switch (action.type) {
        case MODULOS_REGISTRADOS:
            return Object.assign({}, state, { modulosActividades: action.respuesta })

        case MOSTRAR_ACTIVIDADES:
            return Object.assign({}, state, { actividadesRegistradas: action.respuesta })
        case AGREGAR_ACTIVIDAD:
            return {
                ...state,
                actividadesRegistradas: state.actividadesRegistradas.concat(action.actividadARegistrar)
            }
        case MENSAJE_REGISTRAR:
            return Object.assign({}, state, { mensajeRegistrar: action.mensaje })
        case ESTADO_ACTIVIDADES:
            return Object.assign({}, state, { estadoUsuarios: action.estado })
        default:
            return state
    }
}

