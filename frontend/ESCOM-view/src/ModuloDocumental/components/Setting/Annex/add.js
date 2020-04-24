import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { addAnnex } from '../../../redux/actions/annexA.js';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { required, thousand, threeHundred, minimum } from '../../utilitarian/validations.js';

class Add extends Component {

    handleAdd = formValue => {
        let annexN = {
            id: 0,
            name: formValue.nameA,
            description: formValue.descriptionA,
            keywords: formValue.keywordsA,
            program: sessionStorage.getItem('programId'),
            state: 1,
            requestData: null
        }
        this.props.addAnnex(localStorage.getItem('Token'), annexN);
        formValue.nameA = '';
        formValue.descriptionA = '';
        formValue.keywordsA = '';
    }


    render() {
        return (
            <div>
                <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addModal" >
                    <i class="fas fa-plus"></i> Agregar
                </button>

                {/** modal element add */}
                <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <form name="agregar" className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleAdd)} id="hola">

                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Agregar anexo</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">

                                    <label for="form_control_1">Nombre: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="nameA" validate={[required, threeHundred, minimum]} component={generarInput} label="Nombre" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Descripción: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="descriptionA" validate={[required, minimum, thousand]} component={generarText} label="Descripcion" />
                                        </div>
                                    </div>
                                    <br />
                                    <label for="form_control_1">Palabras claves: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="keywordsA" validate={[required, threeHundred, minimum]} component={generarInput} label="Palabras claves" />
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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
        messageR: state.annex.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addAnnex',
    enableReinitialize: true
})(Add)
export default withRouter(connect(mapStateToProps, { addAnnex })(formAdd));
