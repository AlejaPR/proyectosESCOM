import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { fiveHundred, required, minimum } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';
import { getProgramT, addMessageEdit, editProgramT } from '../../../redux/actions/programThematicA.js';
class EditProgramT extends Component {

    componentDidMount() {
        this.props.getProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programT'))
    }

    componentDidUpdate() {
        if (this.props.messageEdit !== '') {
            switch (this.props.messageEdit) {
                case 'edit':
                    toast.success('Se modifico con éxito.');
                    this.props.getProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programT'))
                    this.props.addMessageEdit('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para editar este elemento.');
                    this.props.addMessageEdit('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageEdit('');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let programT = {
            id: sessionStorage.getItem('programT'),
            contributeObjetive: formValues.contributeOb,
            contributeProfessional: formValues.contributePr,
            contributeOccupational: formValues.contributeOc,
            objectiveOutput: formValues.objectiveOut,
            teamContribution: formValues.team,
            observationFinal: formValues.observation,
            objetive: formValues.objective,
            requestData: null
        }
        this.props.editProgramT(localStorage.getItem('Token'), programT);
    }

    render() {
        return (
            <div>
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#editProgramT" >
                    <i class="fas fa-plus"></i> Editar
                </button>
                <div class="modal fade" id="editProgramT" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">

                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar núcleo temático</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Objetivo: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="objective" validate={[required, minimum, fiveHundred]} component={generarText} label="Objetivo" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Contribución objetivo: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="contributeOb" validate={[fiveHundred]} component={generarText} label="Contribución objetivo" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Objetico de salida: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="objectiveOut" validate={[fiveHundred]} component={generarText} label="Objetico de salida" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Contribución perfil ocupacional: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="contributeOc" validate={[fiveHundred]} component={generarText} label="Contribución perfil ocupacional" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Contribución perfil profesional: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="contributePr" validate={[fiveHundred]} component={generarText} label="Contribución perfil profesional" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Contribución del equipo: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="team" validate={[fiveHundred]} component={generarText} label="Contribución del equipo" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Observación final: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="observation" validate={[fiveHundred]} component={generarText} label="Observación final" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" className="btn btn-default naranja">Editar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

const generarText = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <textarea {...input} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

function mapStateToProps(state) {
    return {
        programThematic: state.programThematic.programTR,
        messageEdit: state.programThematic.messageEdit,
        initialValues: {
            contributeOb: state.programThematic.programTR.contributeObjetive,
            contributeOc: state.programThematic.programTR.contributeOccupational,
            contributePr: state.programThematic.programTR.contributeProfessional,
            objectiveOut: state.programThematic.programTR.objectiveOutput,
            objective: state.programThematic.programTR.objetive,
            observation: state.programThematic.programTR.observationFinal,
            team: state.programThematic.programTR.teamContribution
        }
    }
}

let formAdd = reduxForm({
    form: 'editProgramT',
    enableReinitialize: true
})(EditProgramT)

export default withRouter(connect(mapStateToProps, { getProgramT, addMessageEdit, editProgramT })(formAdd));