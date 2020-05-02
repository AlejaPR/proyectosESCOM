import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { addActivity, addMessageAdd, getListActivitiesAnnex } from '../../../redux/actions/activityA.js';
import { withRouter } from 'react-router-dom';
import { required, thousand, threeHundred, minimum } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';

class AddAnnex extends Component {

    handleSubmit = formValues => {
        let activityN = {
            id: 0,
            name: formValues.name,
            description: formValues.description,
            information: '',
            state: 1,
            idCondition: sessionStorage.getItem('condition'),
            type: 2,
            parentActivity: 0,
            requestData: null
        }
        this.props.addActivity(localStorage.getItem('Token'), activityN);
        formValues.name = '';
        formValues.description = '';

    }

    render() {
        return (
            <div>
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModalAnnex" >
                    <i class="fas fa-plus"></i> Agregar
                </button>
                <div class="modal fade" id="addModalAnnex" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                        <div className="col-sm">
                                            <Field name="name" validate={[required, minimum, threeHundred]} type="text" component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Descripción: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="description" validate={[required, thousand, minimum]} type="text" component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
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
        messageAddA: state.activity.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addActivity',
    enableReinitialize: true
})(AddAnnex)

export default withRouter(connect(mapStateToProps, { addActivity, addMessageAdd, getListActivitiesAnnex })(formAdd));
