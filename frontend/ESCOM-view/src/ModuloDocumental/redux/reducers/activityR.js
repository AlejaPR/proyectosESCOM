import { ADD_MESSAGE_ADD, ADD_INFORMATION, ADD_MESSAGE_DELETE, DELETE_ACTIVITY, ADD_ACTIVITY, EDIT_ACTIVITY, ADD_MESSAGE_EDIT, GET_ACTIVITY_ID, GET_ALL_INFORMATION, GET_LIST_ACTIVITIES, ADD_MESSAGE_ASSOCIATE } from '../actions/activityA.js'


const initialState = {
    listActivityR: [],
    activityR: [],
    messageEdit: '',
    messageAdd: '',
    messageDelete: '',
    allInformation: '',
    messageAssociate: ''
}

export function reducersActivity(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_ACTIVITIES:
            return Object.assign({}, state, { listActivityR: action.payload })
        case GET_ACTIVITY_ID:
            return Object.assign({}, state, { activityR: action.payload })
        case ADD_ACTIVITY:
            return Object.assign({}, state, { messageAdd: action.payload })
        case EDIT_ACTIVITY:
            return Object.assign({}, state, { messageEdit: action.payload })
        case DELETE_ACTIVITY:
            return Object.assign({}, state, { messageDelete: action.payload })
        case ADD_INFORMATION:
            return Object.assign({}, state, { messageR: action.payload })
        case ASSOCIATE_ANNEX:
            return Object.assign({}, state, { messageAssociate: action.payload })
        case GET_ALL_INFORMATION:
            return Object.assign({}, state, { allInformation: action.payload })
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload })
        case ADD_MESSAGE_ASSOCIATE:
            return Object.assign({}, state, { messageAssociate: action.payload })
        default:
            return state
    }

}
