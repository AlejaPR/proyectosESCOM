import { ADD_MESSAGE_ADD, ADD_MESSAGE_EDIT, ADD_MESSAGE_DISABLE, GET_LIST_GENERAL_PRO, GET_GENERAL_PRO, ADD_GENERAL_PRO, EDIT_GENERAL_PRO, DISABLE_GENERAL_PRO, STATE_GENERAL_PROGRAM } from '../actions/generalProgramA.js'

const initialState = {
    listGeneralPro: [],
    generalPro: [],
    messageEdit: '',
    messageAdd: '',
    messageDisable: '',
    stateGeneralProgram: false
}

export function reducersGeneralProgram(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_GENERAL_PRO:
            return Object.assign({}, state, { listGeneralPro: action.payload });
        case GET_GENERAL_PRO:
            return Object.assign({}, state, { generalPro: action.payload });
        case ADD_GENERAL_PRO:
            return Object.assign({}, state, { messageAdd: action.payload });
        case EDIT_GENERAL_PRO:
            return Object.assign({}, state, { messageEdit: action.payload });
        case DISABLE_GENERAL_PRO:
            return Object.assign({}, state, { messageDisable: action.payload });
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DISABLE:
            return Object.assign({}, state, { messageDisable: action.payload });
        case STATE_GENERAL_PROGRAM:
            return Object.assign({}, state, { stateGeneralProgram: action.payload });
        default:
            return state;
    }

}