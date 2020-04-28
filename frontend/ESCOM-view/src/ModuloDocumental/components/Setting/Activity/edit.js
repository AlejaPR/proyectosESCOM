import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { editActivity, addMessageEdit } from '../../../redux/actions/activityA.js';
import { withRouter } from 'react-router-dom';
import { required, thousand, threeHundred, minimum } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';

class Edit extends Component {

    componentDidUpdate() {
        if (this.props.messageEditA !== '') {
            switch (this.props.messageEditA) {
                case 'edit':
                    toast.success('Se agrego con exito.');
                    this.props.getListActivities(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageEdit('');
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para editar un elemento.');
                    this.props.addMessageEdit('')
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
        let activityE = {
            id: this.props.activity.id,
            name: formValues.name,
            description: formValues.description,
            information: '',
            state: this.props.activity.state,
            idCondition: sessionStorage.getItem('condition'),
            type: this.props.activity.type,
            requestData: null
        }
        this.props.editActivity(localStorage.getItem('Token'), activityE);
    }

    render() {
        return (
            <div>
                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form className="form-horizontal container" onSubmit={this.props.handleSubmit(this.handleSubmit)}>

                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar Actividad</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="name" type="text" validate={[required, minimum, threeHundred]} component={generarInput} label="Nombre" />
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
        activity: state.activity.activityR,
        initialValues: {
            name: state.activity.activityR.name,
            description: state.activity.activityR.description,
            type: state.activity.activityR.type
        },
        messageEditA: state.activity.messageEdit
    }
}

let formEdit = reduxForm({
    form: 'editActivity',
    enableReinitialize: true
})(Edit)

export default withRouter(connect(mapStateToProps, { editActivity, addMessageEdit })(formEdit));