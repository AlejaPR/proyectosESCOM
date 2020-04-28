import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { getListPrograms, addProgram, addMessageAdd } from '../../../redux/actions/programA.js';
import { required, minimum, threeHundred, select } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';
import { getListGeneralPro } from '../../../redux/actions/generalProgramA.js';


class Add extends Component {

    componentDidMount() {
        this.props.getListGeneralPro(localStorage.getItem('Token'))
    }

    componentDidUpdate() {
        if (this.props.messageAddPr !== '') {
            switch (this.props.messageAddPr) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageAdd('')
                    this.props.getListPrograms(localStorage.getItem('Token'))
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para agregar un elemento.');
                    this.props.addMessageAdd('')
                    this.props.getListPrograms(localStorage.getItem('Token'))
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('')
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let programN = {
            id: 0,
            name: formValues.name,
            levelEducation: formValues.levelEducation,
            institution: formValues.institution,
            academicCredits: formValues.academicCredits,
            duration: formValues.duration,
            methodology: formValues.methodology,
            campus: formValues.campus,
            state: 1,
            idGeneral: formValues.general,
            requestData: null
        }
        this.props.addProgram(localStorage.getItem('Token'), programN);
        formValues.name = '';
        formValues.levelEducation = '';
        formValues.institution = '';
        formValues.academicCredits = '';
        formValues.duration = '';
        formValues.campus = '';
        formValues.methodology = '';
        formValues.general = '';

    }

    loadList() {
        return this.props.listGeneralPro.map((general) => {
            return (
                <option value={general.id}>{general.name}</option>
            )
        })
    }

    render() {
        return (
            <div >
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModal" >
                    <i class="fas fa-plus"></i> Agregar
                </button>
                <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Nuevo programa</h5>
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
                                    <label for="form_control_1">Metodología: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="methodology" validate={[required, minimum, threeHundred]} component={generarInput} label="Motodología" />
                                        </div>
                                    </div>
                                    <label for="form_control_1">Sede: </label>
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
                                    <label for="form_control_1">Facultad: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="general" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                                <option value="0">Seleccione...</option>
                                                {this.loadList()}
                                            </Field>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    <button type="submit" className="btn btn-default naranja">Agregar</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

const generarSelect = ({ input, label, type, meta: { touched, error }, children }) => (
    <div>
        <div>
            <select {...input} className="form-control letra" style={{ height: "35px", fontSize: "13px" }}>
                {children}
            </select>
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>))}
        </div>
    </div>
)

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
        listGeneralPro: state.generalProgram.listGeneralPro,
        messageAddPr: state.program.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addProgram',
    enableReinitialize: true
})(Add)

export default withRouter(connect(mapStateToProps, { addProgram, addMessageAdd, getListGeneralPro, getListPrograms })(formAdd));