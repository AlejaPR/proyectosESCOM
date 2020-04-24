import { STATE_ANNEX, SEARCH_ANNEX_S, ADD_MESSAGE_ADD, ADD_MESSAGE_EDIT, ADD_ANNEX, EDIT_ANNEX, GET_ANNEX_ID, GET_LIST_ANNEXES, ADD_MESSAGE_DISABLE, DISABLE_ANNEX } from '../actions/annexA.js';

const initialState = {
    listAnnexR: [],
    annexR: [],
    messageEdit: '',
    messageAdd: '',
    messageDisable: '',
    stateAnnex: false
}

export function reducersAnnex(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_ANNEXES:
            return Object.assign({}, state, { listAnnexR: action.payload })
        case SEARCH_ANNEX_S:
            return Object.assign({}, state, { listAnnexR: action.payload })
        case GET_ANNEX_ID:
            return Object.assign({}, state, { annexR: action.payload })
        case ADD_ANNEX:
            return Object.assign({}, state, { messageAdd: action.payload })
        case EDIT_ANNEX:
            return Object.assign({}, state, { messageEdit: action.payload })
        case DISABLE_ANNEX:
            return Object.assign({}, state, { messageDisable: action.payload })
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_DISABLE:
            return Object.assign({}, state, { messageDisable: action.payload })
        case STATE_ANNEX:
            return Object.assign({}, state, { stateAnnex: action.payload })
        default:
            return state
    }

}