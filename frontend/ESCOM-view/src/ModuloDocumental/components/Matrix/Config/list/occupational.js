import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { reduxForm, Field } from 'redux-form';
import { confirmAlert } from 'react-confirm-alert';
import { required, minimum, fiveHundred } from '../../../utilitarian/validations.js';
import { getListGeneralC, addGeneralC, addMessageAdd, addMessageDelete, deleteGeneralC } from '../../../../redux/actions/generalClassA.js';

import { toast } from 'react-toastify';

import MaterialTable from 'material-table';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class Occupational extends Component {

    componentDidMount() {
    }

    componentDidUpdate() {
        if (this.props.messageAddO !== '') {
            switch (this.props.messageAddO) {
                case 'addO':
                    toast.success('Se agrego con exito.');
                    this.props.getListGeneralC(localStorage.getItem('Token'), sessionStorage.getItem('programId'), 'OccupationalProfile');
                    this.props.addMessageAdd('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDeleteO !== '') {
            switch (this.props.messageDeleteO) {
                case 'deleteO':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDelete('');
                    this.props.getListGeneralC(localStorage.getItem('Token'), sessionStorage.getItem('programId'), 'OccupationalProfile');
                    break;
                default:
                    break;
            }
        }
    }

    handleSubmit = formValues => {
        let generalA = {
            id: 0,
            name: formValues.nameO,
            idGeneral: sessionStorage.getItem('programId'),
            table: 'OccupationalProfile',
            requestData: null
        }
        this.props.addGeneralC(localStorage.getItem('Token'), generalA);
        formValues.nameO = '';
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
                            name: "",
                            idGeneral: "",
                            table: 'OccupationalProfile',
                            requestData: null
                        }
                        this.props.deleteGeneralC(localStorage.getItem('Token'), generalA)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    render() {
        return (
            <div >
                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                    <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addOccupational" >
                        <i class="fas fa-plus"></i> Agregar
                    </button>
                    <div class="modal fade" id="addOccupational" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Nuevo perfil ocupacional</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label for="form_control_1">Nombre: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="nameO" validate={[required, minimum, fiveHundred]} component={generarInput} label="Nombre" />
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
                        title="Perfiles ocupacionales"
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
                                emptyDataSourceMessage: 'Aun no hay ningun perfil registrado'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre del perfil', field: 'name' },
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
                        data={this.props.listGeneralC}
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

function mapStateToProps(state) {
    return {
        listGeneralC: state.generalClass.listGeneralClassR,
        messageAddO: state.generalClass.messageAddC,
        messageDeleteO: state.generalClass.messageDeleteC
    }
}

let formAddO = reduxForm({
    form: 'addOccupational',
    enableReinitialize: true
})(Occupational)

export default withRouter(connect(mapStateToProps, { getListGeneralC, deleteGeneralC, addGeneralC, addMessageAdd, addMessageDelete })(formAddO));