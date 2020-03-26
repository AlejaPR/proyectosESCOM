import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_USER_CONDITION = 'GET_USER_CONDITION';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';

export function getUserCondition(user, id) {
    
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/userCondition/list/` + user + `/` + id)
            .then(response => {
                dispatch({
                    type: GET_USER_CONDITION,
                    payload: response.data
                });
            });
    }
}