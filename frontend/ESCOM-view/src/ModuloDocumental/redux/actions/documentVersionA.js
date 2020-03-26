import axios from 'axios';

import { desencriptar } from '../../../SuperAdministrador/componentes/general/Encriptar.js';

export const GET_DOCUMENT_VERSIONS = 'GET_DOCUMENT_VERSIONS';

const URL_BASE = 'http://localhost:9090/proyectosESCOM-web';
const PERMIT_VERSIONS_DOCUMENT = 'MD_versiones documento';

//MD_versiones documento
export function getDocumentVersions(token, id) {
    const headers = {
        'Content-Type': 'application/json',
        'TokenAuto': desencriptar(token),
        'Permiso': PERMIT_VERSIONS_DOCUMENT
    }
    return (dispatch, getState) => {
        axios.get(`${URL_BASE}/api/documentVersion/list/${id}`, { headers: headers })
            .then(response => {
                dispatch({
                    type: GET_DOCUMENT_VERSIONS,
                    payload: response.data
                });
            });
    }
}