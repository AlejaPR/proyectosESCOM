import { STATE_DOCUMENT, GET_ID_DOCUMENT, GET_DOCUMENT_ID, ADD_DOCUMENT, DISABLE_DOCUMENT, ADD_MESSAGE_ADD, ADD_MESSAGE_EDIT, GET_DOCUMENT_ID_EDIT, EDIT_DOCUMENT, GET_LIST_DOCUMENTS, ADD_MESSAGE_DISABLE, ADD_ID_DOCUMENT } from '../actions/documentA.js'

const initialState = {
    documentIdG: [],
    listDocumentR: [],
    documentR: [],
    messageEdit: '',
    messageAdd: '',
    messageDisable: '',
    stateDocument: false
}

export function reducersDocument(state = initialState, action) {

    switch (action.type) {
        case GET_ID_DOCUMENT:
            return Object.assign({}, state, { documentIdG: action.payload })
        case GET_DOCUMENT_ID:
            return Object.assign({}, state, { documentR: action.payload })
        case GET_DOCUMENT_ID_EDIT:
            return Object.assign({}, state, { documentR: action.payload })
        case EDIT_DOCUMENT:
            return Object.assign({}, state, { messageEdit: action.payload })
        case DISABLE_DOCUMENT:
            return Object.assign({}, state, { messageDisable: action.payload })
        case ADD_DOCUMENT:
            return Object.assign({}, state, { messageAdd: action.payload })
        case GET_LIST_DOCUMENTS:
            return Object.assign({}, state, { listDocumentR: action.payload })
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_DISABLE:
            return Object.assign({}, state, { messageDisable: action.payload })
        case ADD_ID_DOCUMENT:
            return Object.assign({}, state, { documentIdG: action.payload })
        case STATE_DOCUMENT:
            return Object.assign({}, state, { stateDocument: action.payload })
        default:
            return state
    }

}
