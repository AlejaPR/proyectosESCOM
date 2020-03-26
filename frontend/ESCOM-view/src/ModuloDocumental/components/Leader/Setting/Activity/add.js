import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { addActivity } from '../../../../redux/actions/activityA.js';
import { withRouter } from 'react-router-dom';
import { required, thousand, twoHundred, minimum, select } from '../../../utilitarian/validations.js';

class Add extends Component {

    handleSubmit = formValues => {
        let activityN = {
            id: 0,
            name: formValues.name,
            description: formValues.description,
            information: '',
            state: 1,
            idCondition: sessionStorage.getItem('condition'),
            type: formValues.type,
            requestData: null
        }
        this.props.addActivity(localStorage.getItem('Token'), activityN);
        formValues.name = '';
        formValues.description = '';
        formValues.type = '';

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
                            <form className="form-horizontal container" onSubmit={this.props.handleSubmit(this.handleSubmit)}>

                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Nueva Actividad</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="name" validate={[required, minimum, twoHundred]} type="text" component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Descripcion: </label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="description" validate={[required, thousand, minimum]} type="text" component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Tipo actividad: </label>
                                    <div className="row">
                                        <div className="col-sm-5">
                                            <Field name="type" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                                <option value="0">Seleccione...</option>
                                                <option value="1">Informativa</option>
                                                <option value="2">Anexo</option>
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

const generarText = ({ input, placeholder, label, type, meta: { touched, warning, error } }) => (
    <div>
        <div>
            <textarea {...input} className="form-control letra form-control-solid placeholder-no-fix" />
            {touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
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
        messageR: state.activity.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addActivity',
    enableReinitialize: true
})(Add)

export default withRouter(connect(mapStateToProps, { addActivity })(formAdd));
