import { GET_DOCUMENT_VERSIONS } from '../actions/documentVersionA.js'

const initialState = {
    listDocumentVer: []
}

export function reducersDocumentVersion(state = initialState, action) {

    switch (action.type) {
        case GET_DOCUMENT_VERSIONS:
            return Object.assign({}, state, { listDocumentVer: action.payload })
        default:
            return state
    }

}
