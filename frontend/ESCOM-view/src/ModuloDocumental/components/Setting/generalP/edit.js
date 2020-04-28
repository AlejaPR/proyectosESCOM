import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { editGeneralPro  } from '../../../redux/actions/generalProgramA.js';
import { required, minimum, thousand, threeHundred } from '../../utilitarian/validations.js';

class Edit extends Component {

    handleSubmit = formValues => {
        let generalP = {
            id: this.props.generalP.id,
            description: formValues.description,
            name: formValues.name,
            requestData: null
        }
        this.props.editGeneralPro(localStorage.getItem('Token'), generalP);
    }

    render() {
        return (
            <div>
                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">

                            <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Editar facultad</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="name" validate={[required, minimum, threeHundred]} component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />

                                    <label for="form_control_1">Descripción: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="description" validate={[required, minimum, thousand]} component={generarText} label="Denominación del programa" />
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
        generalP: state.generalProgram.generalPro,
        initialValues: {
            description: state.generalProgram.generalPro.description,
            name: state.generalProgram.generalPro.name
        }
    }
}

let formEdit = reduxForm({
    form: 'editGeneral',
    enableReinitialize: true
})(Edit)

export default withRouter(connect(mapStateToProps, { editGeneralPro })(formEdit));