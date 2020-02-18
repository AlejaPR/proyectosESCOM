
import { MOSTRAR_CONFIGURACION, CARGAR_CONFIGURACION,MENSAJE_CONFIGURACION, ACTUALIZAR_BARRALATERAL, ACTUALIZAR_BARRASUPERIOR, ACTUALIZAR_BOTONES } from '../actions/actionConfiguracion.js'


const initialState = {
    configuracion: [],
    mensaje: ''
}

export function reducerConfiguracion(state = initialState, action) {
    switch (action.type) {
        case CARGAR_CONFIGURACION:
            return Object.assign({}, state, { configuracion: action.configuracion })
        case MENSAJE_CONFIGURACION:
            return Object.assign({}, state, { mensaje: action.mensaje })
        case MOSTRAR_CONFIGURACION:
            return Object.assign({}, state, { configuracion: action.configuracion })
        case ACTUALIZAR_BARRALATERAL:
            const actualizaBarraLateral = () => {
                return ({
                    logo:state.configuracion.logo,
                    imagenLogin:state.configuracion.imagenLogin,
                    barraLateral: action.color,
                    barraSuperior: state.configuracion.barraSuperior,
                    botones: state.configuracion.botones
                })
            }
            return Object.assign({}, state, { configuracion: actualizaBarraLateral() })
        case ACTUALIZAR_BARRASUPERIOR:
            const actualizaBarraSuperior = () => {
                return ({
                    logo:state.configuracion.logo,
                    imagenLogin:state.configuracion.imagenLogin,
                    barraLateral: state.configuracion.barraLateral,
                    barraSuperior: action.color,
                    botones: state.configuracion.botones
                })
            }
            return Object.assign({}, state, { configuracion: actualizaBarraSuperior() })
        case ACTUALIZAR_BOTONES:
            const actualizarBotones = () => {
                return ({
                    logo:state.configuracion.logo,
                    imagenLogin:state.configuracion.imagenLogin,
                    barraLateral: state.configuracion.barraLateral,
                    barraSuperior: state.configuracion.barraSuperior,
                    botones: action.color
                })
            }
            return Object.assign({}, state, { configuracion: actualizarBotones() })
        default:
            return state
    }
}

