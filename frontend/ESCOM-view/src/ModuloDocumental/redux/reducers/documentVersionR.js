import { GET_CURRENT_VERSIONS, GET_OLD_VERSIONS } from '../actions/documentVersionA.js'

const initialState = {
    listCurrentVersions: [],
    listOldVersions: []
}

export function reducersDocumentVersion(state = initialState, action) {

    switch (action.type) {
        case GET_CURRENT_VERSIONS:
            return Object.assign({}, state, { listCurrentVersions: action.payload })
        case GET_OLD_VERSIONS:
            return Object.assign({}, state, { listOldVersions: action.payload })
        default:
            return state
    }

}
