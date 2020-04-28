import { ADD_DOCUMENT_VERSION, ADD_MESSAGE_ADD, GET_CURRENT_VERSIONS, GET_OLD_VERSIONS, STATE_OLD_VERSION, STATE_CURRENT_VERSION } from '../actions/documentVersionA.js'

const initialState = {
    listCurrentVersions: [],
    listOldVersions: [],
    messageAdd: '',
    stateCurrentVersion: false,
    stateOldVersion: false
}

export function reducersDocumentVersion(state = initialState, action) {

    switch (action.type) {
        case GET_CURRENT_VERSIONS:
            return Object.assign({}, state, { listCurrentVersions: action.payload })
        case GET_OLD_VERSIONS:
            return Object.assign({}, state, { listOldVersions: action.payload })
        case STATE_OLD_VERSION:
            return Object.assign({}, state, { stateOldVersion: action.payload })
        case STATE_CURRENT_VERSION:
            return Object.assign({}, state, { stateCurrentVersion: action.payload })
        case ADD_DOCUMENT_VERSION:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        default:
            return state
    }

}
