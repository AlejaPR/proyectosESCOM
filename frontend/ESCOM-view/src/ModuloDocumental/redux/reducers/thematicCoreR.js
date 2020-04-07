import { ADD_MESSAGE_ADD, ADD_THEMATIC_CORE, ADD_MESSAGE_DELETE, GET_LIST_THEMATIC_CORE, DELETE_THEMATIC_CORE } from '../actions/thematicCoreA.js'


const initialState = {
    listThematicCoreR: [],
    messageAdd: '',
    messageDelete: '',
    messageR: ''
}

export function reducersThematicCore(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_THEMATIC_CORE:
            return Object.assign({}, state, { listThematicCoreR: action.payload });
        case ADD_THEMATIC_CORE:
            return Object.assign({}, state, { messageAdd: action.payload });
        case DELETE_THEMATIC_CORE:
            return Object.assign({}, state, { messageDelete: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload });
        default:
            return state;
    }

}