import {STATE_PROGRAM_T, GET_LIST_PROGRAM_T, DELETE_PROGRAM_T, EDIT_PROGRAM_T, GET_PROGRAM_T, ADD_PROGRAM_T, ADD_MESSAGE_ADD, ADD_MESSAGE_DELETE, ADD_MESSAGE_EDIT } from '../actions/programThematicA.js'

const initialState = {
    listProgramTR: [],
    programTR: [],
    messageEdit: '',
    messageAdd: '',
    messageDelete: '',
    stateProgramT: false
}

export function reducersProgramThematic(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_PROGRAM_T:
            return Object.assign({}, state, { listProgramTR: action.payload });
        case GET_PROGRAM_T:
            return Object.assign({}, state, { programTR: action.payload });
        case ADD_PROGRAM_T:
            return Object.assign({}, state, { messageAdd: action.payload });
        case EDIT_PROGRAM_T:
            return Object.assign({}, state, { messageEdit: action.payload });
        case DELETE_PROGRAM_T:
            return Object.assign({}, state, { messageDelete: action.payload });
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload });
        case STATE_PROGRAM_T:
            return Object.assign({}, state, { stateProgramT: action.payload });
        default:
            return state;
    }

}