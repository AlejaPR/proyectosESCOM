import {STATE_COMMENTARY, GET_LIST_COMMENTS, ADD_COMMENTARY, DELETE_COMMENTARY, ADD_MESSAGE_ADD, ADD_MESSAGE_DELETE } from '../actions/commentaryA.js';

const initialState = {
    listCommentsR: [],
    messageAdd: '',
    messageDelete: '',
    stateCommentary: false
}

export function reducersCommentary(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_COMMENTS:
            return Object.assign({}, state, { listCommentsR: action.payload })
        case ADD_COMMENTARY:
            return Object.assign({}, state, { messageAdd: action.payload })
        case DELETE_COMMENTARY:
            return Object.assign({}, state, { messageDelete: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload })
        case STATE_COMMENTARY:
            return Object.assign({}, state, { stateCommentary: action.payload })
        default:
            return state
    }

}