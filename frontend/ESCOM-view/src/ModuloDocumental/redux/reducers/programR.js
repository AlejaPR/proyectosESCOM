import { GET_LIST_PROGRAMS, GET_PROGRAM_ID, ADD_MESSAGE_ADD, ADD_MESSAGE_EDIT, ADD_PROGRAM, EDIT_PROGRAM, DISABLE_PROGRAM, ADD_MESSAGE_DISABLE, STATE_PROGRAM } from '../actions/programA.js'

const initialState = {
    listProgramR: [],
    programR: [],
    messageEdit: '',
    messageAdd: '',
    messageDisable: '',
    stateProgram: false
}

export function reducersProgram(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_PROGRAMS:
            return Object.assign({}, state, { listProgramR: action.payload });
        case GET_PROGRAM_ID:
            return Object.assign({}, state, { programR: action.payload });
        case ADD_PROGRAM:
            return Object.assign({}, state, { messageAdd: action.payload });
        case EDIT_PROGRAM:
            return Object.assign({}, state, { messageEdit: action.payload });
        case DISABLE_PROGRAM:
            return Object.assign({}, state, { messageDisable: action.payload });
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DISABLE:
            return Object.assign({}, state, { messageDisable: action.payload });
        case STATE_PROGRAM:
            return Object.assign({}, state, { stateProgram: action.payload });
        default:
            return state;
    }

}