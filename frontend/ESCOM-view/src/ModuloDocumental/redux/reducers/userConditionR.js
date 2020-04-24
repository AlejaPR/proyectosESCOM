import { GET_LIST_CONDITIONS_USER, ADD_MESSAGE_DELETE, GET_LIST_USERS, GET_LIST_USERS_CONDITION, ASSOCIATE_USER_CONDITION, ADD_MESSAGE_ASSOCIATE, DELETE_USER_CONDITION, STATE_USERS, STATE_USERS_CONDITION, STATE_CONDITIONS_USER } from '../actions/userConditionA.js'


const initialState = {
    listConditionsUserR: [],
    listUsersConditionR: [],
    listUsersR: [],
    messageAssociate: '',
    messageDelete: '',
    messageR: '',
    stateUsers: false,
    stateUsersCondition: false,
    stateConditionsUser: false
}

export function reducersUserCondition(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_CONDITIONS_USER:
            return Object.assign({}, state, { listConditionsUserR: action.payload });
        case GET_LIST_USERS:
            return Object.assign({}, state, { listUsersR: action.payload });
        case GET_LIST_USERS_CONDITION:
            return Object.assign({}, state, { listUsersConditionR: action.payload });
        case ASSOCIATE_USER_CONDITION:
            return Object.assign({}, state, { messageAssociate: action.payload });
        case DELETE_USER_CONDITION:
            return Object.assign({}, state, { messageDelete: action.payload });
        case ADD_MESSAGE_ASSOCIATE:
            return Object.assign({}, state, { messageAssociate: action.payload });
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload });
        case STATE_USERS:
            return Object.assign({}, state, { stateUsers: action.payload });
        case STATE_USERS_CONDITION:
            return Object.assign({}, state, { stateUsersCondition: action.payload });
        case STATE_CONDITIONS_USER:
            return Object.assign({}, state, { stateConditionsUser: action.payload });
        default:
            return state;
    }

}