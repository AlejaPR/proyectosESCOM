import { GET_LIST_GENERAL_CLASS, ADD_GENERAL_CLASS, DELETE_GENERAL_CLASS, ADD_MESSAGE_ADD, ADD_MESSAGE_DELETE, STATE_GENERAL_CLASS } from '../actions/generalClassA.js'

const initialState = {
    listGeneralClassR: [],
    messageAddC: '',
    messageDeleteC: '',
    stateGeneralClass: ''
}

export function reducersGeneralClass(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_GENERAL_CLASS:
            return Object.assign({}, state, { listGeneralClassR: action.payload })
        case ADD_GENERAL_CLASS:
            return Object.assign({}, state, { messageAddC: action.payload })
        case DELETE_GENERAL_CLASS:
            return Object.assign({}, state, { messageDeleteC: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAddC: action.payload })
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDeleteC: action.payload })
        case STATE_GENERAL_CLASS:
            return Object.assign({}, state, { stateGeneralClass: action.payload })
        default:
            return state
    }

}
