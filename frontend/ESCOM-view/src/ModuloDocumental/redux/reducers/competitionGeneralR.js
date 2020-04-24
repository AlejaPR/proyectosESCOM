import { ADD_MESSAGE_ADD, ADD_MESSAGE_DELETE, GET_LIST_COMPETITION_GENERAL, ADD_COMPETITION_GENERAL, DELETE_COMPETITION_GENERAL, STATE_COMPETITION_GENERAL } from '../actions/competitionGeneralA.js'

const initialState = {
    listCompetitionGR: [],
    messageAdd: '',
    messageDelete: '',
    messageR: '',
    stateCompetitionGeneral: false
}

export function reducersCompetitionGeneral(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_COMPETITION_GENERAL:
            return Object.assign({}, state, { listCompetitionGR: action.payload });
        case ADD_COMPETITION_GENERAL:
            return Object.assign({}, state, { messageAdd: action.payload });
        case DELETE_COMPETITION_GENERAL:
            return Object.assign({}, state, { messageDelete: action.payload });
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload });
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload });
        case STATE_COMPETITION_GENERAL:
            return Object.assign({}, state, { stateCompetitionGeneral: action.payload });
        default:
            return state;
    }

}