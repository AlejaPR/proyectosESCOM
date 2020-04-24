import { STATE_ACTIVITY_ANNEX, STATE_ACTIVITY_INFO, ADD_MESSAGE_ADD, ADD_MESSAGE_ADD_INFO, ADD_INFORMATION, ADD_MESSAGE_DELETE, DELETE_ACTIVITY, ADD_ACTIVITY, EDIT_ACTIVITY, ADD_MESSAGE_EDIT, GET_ACTIVITY_ID, GET_ALL_INFORMATION, ASSOCIATE_ANNEX, ADD_MESSAGE_ASSOCIATE, GET_ACTIVITY_ANNEX, GET_LIST_ACTIVITIES_ANNEX, GET_LIST_ACTIVITIES_INFO, CHANGE_STATUS, ADD_MESSAGE_CHANGE } from '../actions/activityA.js'


const initialState = {
    listActivityInfoR: [],
    listActivityAnnexR: [],
    activityR: [],
    activityAnnexR: [],
    messageEdit: '',
    messageAdd: '',
    messageAddInfo: '',
    messageDelete: '',
    allInformation: '',
    messageAssociate: '',
    messageChange: '',
    stateActivityList: false,
    stateActivityAnnex: false
}

export function reducersActivity(state = initialState, action) {

    switch (action.type) {
        case GET_LIST_ACTIVITIES_ANNEX:
            return Object.assign({}, state, { listActivityAnnexR: action.payload })
        case GET_LIST_ACTIVITIES_INFO:
            return Object.assign({}, state, { listActivityInfoR: action.payload })
        case GET_ACTIVITY_ID:
            return Object.assign({}, state, { activityR: action.payload })
        case GET_ACTIVITY_ANNEX:
            return Object.assign({}, state, { activityAnnexR: action.payload })
        case ADD_ACTIVITY:
            return Object.assign({}, state, { messageAdd: action.payload })
        case EDIT_ACTIVITY:
            return Object.assign({}, state, { messageEdit: action.payload })
        case DELETE_ACTIVITY:
            return Object.assign({}, state, { messageDelete: action.payload })
        case ADD_INFORMATION:
            return Object.assign({}, state, { messageAddInfo: action.payload })
        case ASSOCIATE_ANNEX:
            return Object.assign({}, state, { messageAssociate: action.payload })
        case CHANGE_STATUS:
            return Object.assign({}, state, { messageChange: action.payload })
        case GET_ALL_INFORMATION:
            return Object.assign({}, state, { allInformation: action.payload })
        case ADD_MESSAGE_EDIT:
            return Object.assign({}, state, { messageEdit: action.payload })
        case ADD_MESSAGE_ADD:
            return Object.assign({}, state, { messageAdd: action.payload })
        case ADD_MESSAGE_CHANGE:
            return Object.assign({}, state, { messageChange: action.payload })
        case ADD_MESSAGE_ADD_INFO:
            return Object.assign({}, state, { messageAddInfo: action.payload })
        case ADD_MESSAGE_DELETE:
            return Object.assign({}, state, { messageDelete: action.payload })
        case ADD_MESSAGE_ASSOCIATE:
            return Object.assign({}, state, { messageAssociate: action.payload })
        case STATE_ACTIVITY_INFO:
            return Object.assign({}, state, { stateActivityList: action.payload })
        case STATE_ACTIVITY_ANNEX:
            return Object.assign({}, state, { stateActivityAnnex: action.payload })
        default:
            return state
    }

}
