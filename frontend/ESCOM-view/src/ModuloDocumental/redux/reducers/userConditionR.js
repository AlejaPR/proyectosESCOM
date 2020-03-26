import { GET_USER_CONDITION } from '../actions/userConditionR.js'

const initialState = {
    listUserConditionR: [],
    messageR: []
}

export function reducersUserCondition(state = initialState, action) {

    switch (action.type) {
        case GET_USER_CONDITION:
            return Object.assign({}, state, { listUserConditionR: action.payload });        
        default:
            return state;
    }

}