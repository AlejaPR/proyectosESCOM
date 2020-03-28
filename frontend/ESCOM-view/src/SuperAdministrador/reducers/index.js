import { combineReducers } from 'redux';
import { reducerUsuario } from './reducerUsuario.js';
import { reducerConfiguracion} from './reducerConfiguracion.js';
import { reducerModulo} from './reducerModulo.js';
import {reducerActividad} from './reducerActividad.js'
import { reducerReporte} from './reducerReporte.js';
import {reducer as formReducer} from 'redux-form';
import { reducersCondition } from '../../ModuloDocumental/redux/reducers/conditionR.js';
import { reducersProcess } from '../../ModuloDocumental/redux/reducers/processR.js';
import { reducersActivity } from '../../ModuloDocumental/redux/reducers/activityR.js';
import { reducersAnnex } from '../../ModuloDocumental/redux/reducers/annexR.js';
import { reducersProgram } from '../../ModuloDocumental/redux/reducers/programR.js';
import { reducersDocument } from '../../ModuloDocumental/redux/reducers/documentR.js';
import { reducersAnnexVersion } from '../../ModuloDocumental/redux/reducers/annexVersionR'
import { reducersDocumentVersion } from '../../ModuloDocumental/redux/reducers/documentVersionR.js';
import { reducersUserCondition } from "../../ModuloDocumental/redux/reducers/userConditionR.js";
import { reducersCommentary } from "../../ModuloDocumental/redux/reducers/commentaryR.js";

const rootReducer = combineReducers({
    rep:reducerReporte,
    user: reducerUsuario,
    conf:reducerConfiguracion,
    mod:reducerModulo,
    act:reducerActividad,
    condition: reducersCondition,
    process: reducersProcess,
    activity: reducersActivity,
    annex: reducersAnnex,
    program: reducersProgram,
    document: reducersDocument,
    annexVersion: reducersAnnexVersion,
    documentVersion: reducersDocumentVersion,
    userCondition: reducersUserCondition,
    commentary: reducersCommentary,
    form:formReducer
})

export default rootReducer;