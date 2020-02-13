
import { MOSTRAR_CONFIGURACION, CARGAR_CONFIGURACION,MENSAJE_CONFIGURACION, ACTUALIZAR_BARRALATERAL, ACTUALIZAR_BARRASUPERIOR, ACTUALIZAR_BOTONES } from '../actions/actionConfiguracion.js'


const initialState = {
    estilos: { fondoBarra: "", fondoSuperior: "", botones: "" },
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
            return Object.assign({}, state, { estilos: action.configuracion })
        case ACTUALIZAR_BARRALATERAL:
            const actualizaBarraLateral = () => {
                return ({
                    fondoBarra: action.color,
                    fondoSuperior: state.estilos.fondoSuperior,
                    botones: state.estilos.botones
                })
            }
            return Object.assign({}, state, { estilos: actualizaBarraLateral() })
        case ACTUALIZAR_BARRASUPERIOR:
            const actualizaBarraSuperior = () => {
                return ({
                    fondoBarra: state.estilos.fondoBarra,
                    fondoSuperior: action.color,
                    botones: state.estilos.botones
                })
            }
            return Object.assign({}, state, { estilos: actualizaBarraSuperior() })
        case ACTUALIZAR_BOTONES:
            const actualizarBotones = () => {
                return ({
                    fondoBarra: state.estilos.fondoBarra,
                    fondoSuperior: state.estilos.fondoSuperior,
                    botones: action.color
                })
            }
            return Object.assign({}, state, { estilos: actualizarBotones() })
        default:
            return state
    }
}

