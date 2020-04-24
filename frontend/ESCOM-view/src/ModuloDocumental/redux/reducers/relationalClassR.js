import { ADD_MESSAGE_ADD, ADD_MESSAGE_DELETE, GET_LIST_RELATIONAL_CLASS, ADD_RELATIONAL_CLASS, DELETE_RELATIONAL_CLASS, STATE_RELATIONAL_CLASS } from '../actions/relationalClassA.js'

const initialState = {
    listRelationalClassR: [],
    messageAddR: '',
    messageDeleteR: '',
    stateRelational: false
}

export function reducersRelationalClass(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_RELATIONAL_CLASS:
            return Object.assign({}, state, { listRelationalClassR: action.payload })
        case ADD_RELATIONAL_CLASS:
            return Object.assign({}, state, { messageAddR: action.payload })
        case DELETE_RELATIONAL_CLASS:
            return Object.assign({}, state, { messageDeleteR: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAddR: action.payload })
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDeleteR: action.payload })
        case STATE_RELATIONAL_CLASS:
            return Object.assign({}, state, { stateRelational: action.payload })
        default:
            return state
    }

}
