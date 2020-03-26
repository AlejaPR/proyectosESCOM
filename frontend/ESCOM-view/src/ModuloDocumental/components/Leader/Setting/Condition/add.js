import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { getListProcesses } from '../../../../redux/actions/processA.js';
import { addCondition } from '../../../../redux/actions/conditionA.js';
import { withRouter } from 'react-router-dom';
import { required, thousand, twoHundred, minimum, select } from '../../../utilitarian/validations.js';

class Add extends Component {

    componentWillMount() {
        if (sessionStorage.getItem('documentId') <= 0) {
            this.props.history.push('/')
        }
        this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }

    handleSubmit = formValues => {
        let dateS = new Date(formValues.startDate);
        let dateF = new Date(formValues.finalDate);
        let conditionN = {
            id: 0,
            name: formValues.name,
            description: formValues.description,
            state: 1,
            startDate: dateS,
            finalDate: dateF,
            process: formValues.process,
            requestData: null
        }
        this.props.addCondition(localStorage.getItem('Token'), conditionN);
        formValues.number = '';
        formValues.name = '';
        formValues.description = '';
        formValues.startDate = '';
        formValues.finalDate = '';
        formValues.process = '';
    }

    loadList() {
        return this.props.processes.map((process) => {
            return (
                <option value={process.id}>{process.name}</option>
            )
        })
    }

    render() {
        return (
            <div>
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModal" >
                    <i class="fas fa-plus"></i> Agregar
                </button>
                <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Nueva condicion</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm-8">
                                            <Field name="name" validate={[required, twoHundred, minimum]} component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Descripcion: </label>
                                    <div className="row">
                                        <div className="col-sm-8">
                                            <Field name="description" validate={[required, thousand, minimum]} component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Fecha inicio: </label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="startDate" validate={[required]} type="date" component={generarInput} />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Fecha final: </label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="finalDate" validate={[required]} type="date" component={generarInput} />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Proceso: </label>
                                    <div className="row">
                                        <div className="col-sm-8">
                                            <Field name="process" validate={[select]} className="bs-select form-control" component="select">
                                                <option selected value="0">Seleccione...</option>
                                                {this.loadList()}
                                            </Field>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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


const generarInput = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <input {...input} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
        </div>
    </div>
)

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
        processes: state.process.listProcessR
    }
}

let formAdd = reduxForm({
    form: 'addCondition',
    enableReinitialize: true
})(Add)

export default withRouter(connect(mapStateToProps, { getListProcesses, addCondition })(formAdd));
