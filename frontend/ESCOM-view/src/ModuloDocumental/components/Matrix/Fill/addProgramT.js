import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { reduxForm, Field } from 'redux-form';
import { withRouter } from 'react-router-dom';
import { select } from '../../utilitarian/validations.js';
import { toast } from 'react-toastify';
import { getListProgramT, addMessageAdd, addProgramT } from '../../../redux/actions/programThematicA.js';
import { getListThematicCore } from '../../../redux/actions/thematicCoreA.js';

class AddProgramT extends Component {

    componentDidMount() {
        this.props.getListThematicCore(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    loadList() {
        return this.props.listThematicC.map((com) => {
            return (
                <option value={com.id}>{com.name}</option>
            )
        })
    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'add':
                    toast.success('Se agrego con éxito.');
                    this.props.getListProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageAdd('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let programT = {
            id: 0,
            contributeObjetive: '',
            contributeProfessional: '',
            contributeOccupational: '',
            objectiveOutput: '',
            teamContribution: '',
            observationFinal: '',
            idThematicCore: formValues.thematic,
            idProgram: sessionStorage.getItem('programId'),
            requestData: null
        }
        this.props.addProgramT(localStorage.getItem('Token'), programT);
        formValues.thematic = '';
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
                                    <h5 class="modal-title" id="exampleModalLabel">Nueva temática</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <label for="form_control_1">Núcleo temático: </label>
                                    <div className="row">
                                        <div className="col-sm">
                                            <Field name="thematic" validate={[select]} className="bs-select form-control" component={generarSelect}>
                                                <option value="0">Seleccione...</option>
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

function mapStateToProps(state) {
    return {
        listThematicC: state.thematicCore.listThematicCoreR,
        messageAdd: state.programThematic.messageAdd
    }
}

let formAdd = reduxForm({
    form: 'addProgramT',
    enableReinitialize: true
})(AddProgramT)

export default withRouter(connect(mapStateToProps, { getListProgramT, addMessageAdd, addProgramT, getListThematicCore })(formAdd));