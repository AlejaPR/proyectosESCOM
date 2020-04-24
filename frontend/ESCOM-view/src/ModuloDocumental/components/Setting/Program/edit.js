import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { editProgram, addMessageEdit } from '../../../redux/actions/programA.js';
import { required, minimum, threeHundred } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';

class Edit extends Component {

    componentDidUpdate() {
        if (this.props.messageEditPr !== '') {
            switch (this.props.messageEditPr) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListPrograms(localStorage.getItem('Token'))
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para editar.');
                    this.props.addMessageEdit('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let programE = {
            id: this.props.program.id,
            name: formValues.name,
            levelEducation: formValues.levelEducation,
            institution: formValues.institution,
            academicCredits: formValues.academicCredits,
            duration: formValues.duration,
            methodology: formValues.methodology,
            campus: formValues.campus,
            requestData: null
        }
        this.props.editProgram(localStorage.getItem('Token'), programE);
    }

    render() {
        return (
            <div >
                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar programa</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Institución: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="institution" validate={[required, minimum, threeHundred]} component={generarInput} label="Institución" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Denominación del programa: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="name" validate={[required, minimum, threeHundred]} component={generarInput} label="Denominación del programa" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Nivel de formación: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="levelEducation" validate={[required, minimum, threeHundred]} component={generarInput} label="Nivel de formación" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Motodología: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="methodology" validate={[required, minimum, threeHundred]} component={generarInput} label="Motodología" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Campus: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="campus" validate={[required, minimum, threeHundred]} component={generarInput} label="Campus" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Créditos académicos: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="academicCredits" validate={[required]} type="number" component={generarInput} label="Créditos académicos" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Duración semestres: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="duration" validate={[required]} type="number" component={generarInput} label="Duración semestres" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    <button type="submit" className="btn btn-default naranja">Guardar</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}


const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <input {...input} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

function mapStateToProps(state) {
    return {
        program: state.program.programR,
        messageEditPr: state.program.messageEdit,
        initialValues: {
            name: state.program.programR.name,
            levelEducation: state.program.programR.levelEducation,
            institution: state.program.programR.institution,
            academicCredits: state.program.programR.academicCredits,
            duration: state.program.programR.duration,
            methodology: state.program.programR.methodology,
            campus: state.program.programR.campus
        }
    }
}

let formEdit = reduxForm({
    form: 'editProgram',
    enableReinitialize: true
})(Edit)

export default withRouter(connect(mapStateToProps, { editProgram, addMessageEdit })(formEdit));