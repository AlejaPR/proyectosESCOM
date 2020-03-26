import { GET_ANNEX_VERSIONS, ADD_ANNEX_VERSION } from '../actions/annexVersionA.js';

const initialState = {
    listAnnexVersionR: [],
    messageR: []
}

export function reducersAnnexVersion(state = initialState, action) {
    switch (action.type) {
        case GET_ANNEX_VERSIONS:
            return Object.assign({}, state, { listAnnexVersionR: action.payload })
        case ADD_ANNEX_VERSION:
            return Object.assign({}, state, { messageR: action.payload })
        default:
            return state
    }

}