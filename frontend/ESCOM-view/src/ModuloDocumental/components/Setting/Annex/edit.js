import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { editAnnex } from '../../../redux/actions/annexA.js';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { required, thousand, threeHundred, minimum } from '../../utilitarian/validations.js';

class Edit extends Component {

    handleEdit = formValues => {
        let annexE = {
            id: this.props.annex.id,
            name: formValues.name,
            description: formValues.description,
            keywords: formValues.keywords,
            program: sessionStorage.getItem('programId'),
            state: 1,
            requestData: null
        }
        this.props.editAnnex(localStorage.getItem('Token'), annexE);
    }

    render() {
        return (
            <div>
                <div className="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <form name="agregar" className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleEdit)} id="hola">

                                <div className="modal-header">
                                    <h5 classNames="modal-title" id="exampleModalLabel">Editar Anexo</h5>
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">

                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="name" validate={[required, threeHundred, minimum]} component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Descripción: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="description" validate={[required, thousand, minimum]} component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Palabras clave: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="keywords" validate={[required, minimum, threeHundred]} component={generarInput} label="Palabras claves" />
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
        annex: state.annex.annexR,
        initialValues: {
            name: state.annex.annexR.name,
            description: state.annex.annexR.description,
            keywords: state.annex.annexR.keywords
        }
    }
}

let formEdit = reduxForm({
    form: 'editAnnex',
    enableReinitialize: true
})(Edit)
export default withRouter(connect(mapStateToProps, { editAnnex })(formEdit));
