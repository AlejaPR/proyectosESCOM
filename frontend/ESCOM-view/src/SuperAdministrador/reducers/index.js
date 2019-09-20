import { combineReducers } from 'redux';
import { reducerUsuario } from './reducerUsuario.js'
import {reducer as formReducer} from 'redux-form';

const rootReducer = combineReducers({
    user: reducerUsuario,form:formReducer
})

export default rootReducer;