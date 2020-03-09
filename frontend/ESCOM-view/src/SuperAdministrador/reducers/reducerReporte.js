import {
    MENSAJE_REPORTE,
    REPORTES
} from '../actions/actionReporte.js'


const initialState = {
    reporte: [],
    mensajeReporte: ''
}

export function reducerReporte(state = initialState, action) {
    switch (action.type) {
        case REPORTES:
            return Object.assign({}, state, { reporte: action.reporte })
        case MENSAJE_REPORTE:
            return Object.assign({}, state, { mensajeReporte: action.mensaje })
        default:
            return state
    }
}
