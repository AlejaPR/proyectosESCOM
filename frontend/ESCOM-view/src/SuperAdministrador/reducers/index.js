import { combineReducers } from 'redux';
import { reducerUsuario } from './reducerUsuario.js';
import { reducerConfiguracion} from './reducerConfiguracion.js';
import {reducer as formReducer} from 'redux-form';

const rootReducer = combineReducers({
    user: reducerUsuario,conf:reducerConfiguracion,form:formReducer
})

export default rootReducer;