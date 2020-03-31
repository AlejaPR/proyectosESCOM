import { GET_ANNEX_VERSIONS, ADD_ANNEX_VERSION, ADD_MESSAGE_ADD } from '../actions/annexVersionA.js';

const initialState = {
    listAnnexVersionR: [],
    messageAdd: '',
    messageR: ''
}

export function reducersAnnexVersion(state = initialState, action) {
    switch (action.type) {
        case GET_ANNEX_VERSIONS:
            return Object.assign({}, state, { listAnnexVersionR: action.payload })
        case ADD_ANNEX_VERSION:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        default:
            return state
    }

}