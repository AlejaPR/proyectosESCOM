import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { reduxForm, Field } from 'redux-form';
import { confirmAlert } from 'react-confirm-alert';
import { required, minimum, fiveHundred, select } from '../../../utilitarian/validations.js';
import { getListThematicCore, addThematicCore, addMessageAdd, addMessageDelete, deleteThemacticCore } from '../../../../redux/actions/thematicCoreA.js';
import { getListGeneralC } from '../../../../redux/actions/generalClassA.js';


import { toast } from 'react-toastify';

import MaterialTable from 'material-table';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ThematicCore extends Component {

    componentDidMount() {
        this.props.getListThematicCore(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListThematicCore(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
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
        if (this.props.messageDelete !== '') {
            switch (this.props.messageDelete) {
                case 'delete':
                    toast.success('Se elimino con exito.');
                    this.props.addMessageDelete('');
                    this.props.getListThematicCore(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDelete('');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let thematicC = {
            id: 0,
            name: formValues.nameTC,
            credits: formValues.credits,
            idTrainingArea: formValues.trainingA,
            requestData: null
        }
        this.props.addThematicCore(localStorage.getItem('Token'), thematicC);
        formValues.nameTC = '';
        formValues.credits = '';
        formValues.trainingA = '';
    }


    disable(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let generalA = {
                            id: id,
                            name: '',
                            objective: '',
                            idTrainingArea: '',
                            requestData: null
                        }
                        this.props.deleteThemacticCore(localStorage.getItem('Token'), generalA)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    loadList() {
        return this.props.listTraining.map((training) => {
            return (
                <option value={training.id}>{training.name}</option>
            )
        })
    }

    render() {
        return (
            <div >
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addThematicCore" >
                        <i class="fas fa-plus"></i> Agregar
                    </button>
                    <div class="modal fade" id="addThematicCore" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Nuevo núcleo temático</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label for="form_control_1">Nombre: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="nameTC" validate={[required, minimum, fiveHundred]} component={generarInput} label="Nombre" />
                                            </div>
                                        </div>
                                        <br />
                                        <label for="form_control_1">Créditos académicos: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="credits" validate={[required]} type="number" component={generarInput} label="Créditos académicos" />
                                            </div>
                                        </div>
                                        <br />
                                        <label for="form_control_1">Área de formación: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="trainingA" validate={[select]} className="bs-select form-control" component={generarSelect}>
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
                    {/* home table of contents */}
                    <br />
                    <br />
                    <MaterialTable
                        title="Núcleos temáticos"
                        localization={{
                            header: {
                                actions: ' '
                            },
                            pagination: {
                                nextTooltip: 'Siguiente ',
                                previousTooltip: 'Anterior',
                                labelDisplayedRows: '{from}-{to} de {count}',
                                lastTooltip: 'Ultima pagina',
                                firstTooltip: 'Primera pagina',
                                labelRowsSelect: 'Registros',
                                firstAriaLabel: 'oooo'
                            },
                            body: {
                                emptyDataSourceMessage: 'Aun no hay ninguna temática registrada'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre de la temática', field: 'name' },
                            { title: 'Numero de creditos', field: 'credits' },
                            {
                                title: '', field: 'id',
                                render: rowData => {
                                    return (
                                        <div>
                                            <a onClick={() => this.disable(rowData.id)}>
                                                <DeleteForeverIcon />
                                            </a>
                                        </div>
                                    )
                                }
                            }

                        ]}
                        data={this.props.listThematicC}
                        options={{
                            search: true
                        }}

                    />

                </div>
            </div >
        );
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
        listTraining: state.generalClass.listGeneralClassR,
        messageAdd: state.thematicCore.messageAdd,
        messageDelete: state.thematicCore.messageDelete
    }
}

let formAddTC = reduxForm({
    form: 'addThematicCore',
    enableReinitialize: true
})(ThematicCore)

export default withRouter(connect(mapStateToProps, { getListGeneralC, deleteThemacticCore, getListThematicCore, addThematicCore, addMessageAdd, addMessageDelete })(formAddTC));