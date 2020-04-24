import {STATE_PROCESS, GET_LIST_PROCESSES, DISABLE_PROCESS, ADD_PROCESS, EDIT_PROCESS, ADD_MESSAGE_EDIT, ADD_MESSAGE_ADD, GET_PROCESS_ID, ADD_MESSAGE_DISABLE } from '../actions/processA.js'

const initialState = {
    listProcessR: [],
    processR: [],
    messageEdit: '',
    messageAdd: '',
    messageDisable: '',
    stateProcess: false
}

export function reducersProcess(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_PROCESSES:
            return Object.assign({}, state, { listProcessR: action.payload });
        case GET_PROCESS_ID:
            return Object.assign({}, state, { processR: action.payload });
        case ADD_PROCESS:
            return Object.assign({}, state, { messageAdd: action.payload });
        case EDIT_PROCESS:
            return Object.assign({}, state, { messageEdit: action.payload });
        case DISABLE_PROCESS:
            return Object.assign({}, state, { messageDisable: action.payload });
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DISABLE:
            return Object.assign({}, state, { messageDisable: action.payload });
        case STATE_PROCESS:
            return Object.assign({}, state, { stateProcess: action.payload });
        default:
            return state;
    }

}