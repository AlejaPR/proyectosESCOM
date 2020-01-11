
// import axios from 'axios';

export const MOSTRAR_CONFIGURACION = 'MOSTRAR_CONFIGURACION'
export const ACTUALIZAR_BARRALATERAL= 'ACTUALIZAR_BARRALATERAL'
export const ACTUALIZAR_BARRASUPERIOR= 'ACTUALIZAR_BARRASUPERIOR'
export const ACTUALIZAR_BOTONES='ACTUALIZAR_BOTONES';

export function consultarConfiguracion() {
    return (dispatch, getState) => {
        // axios.get("http://localhost:9090/SuperadministradorESCOM-web/api/usu/")
        // .then(response => {
        //     dispatch({
        //         type: MOSTRAR_USUARIOS,
        //         respuesta: response.data
        //     });
        dispatch({
            type: MOSTRAR_CONFIGURACION,
            configuracion:{
                fondoBarra: "#0E3D38",
                fondoSuperior:"white",
                botones:"blue"
            }
        });
    }
}

export function actionActualizarBarraLateral(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BARRALATERAL,
            color:color
        });
    }
}

export function actionActualizarBarraSuperior(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BARRASUPERIOR,
            color:color
        });
    }
}

export function actionActualizarBotones(color) {
    return (dispatch, getState) => {
        dispatch({
            type: ACTUALIZAR_BOTONES,
            color:color
        });
    }
}