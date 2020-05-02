import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { reduxForm, Field } from 'redux-form';
import { confirmAlert } from 'react-confirm-alert';
import { required, minimum, fiveHundred, select } from '../../../utilitarian/validations.js';
import { getListGeneralC } from '../../../../redux/actions/generalClassA.js';
import { getListCompetitionG, addCompetitionG, deleteCompetitionG, addMessageDelete, addMessageAdd } from '../../../../redux/actions/competitionGeneralA.js';

import { toast } from 'react-toastify';

import MaterialTable from 'material-table';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class CompetitionG extends Component {

    componentDidMount() {
        this.props.getListCompetitionG(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {
        if (this.props.messageAddG !== '') {
            switch (this.props.messageAddG) {
                case 'addG':
                    toast.success('Se agrego con exito.');
                    this.props.getListCompetitionG(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.getListGeneralC(localStorage.getItem('Token'), sessionStorage.getItem('programId'), 'Competition');
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
        if (this.props.messageDeleteG !== '') {
            switch (this.props.messageDeleteG) {
                case 'deleteG':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDelete('');
                    this.props.getListCompetitionG(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
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
        let generalA = {
            id: 0,
            name: formValues.nameG,
            idCompetition: formValues.competition,
            requestData: null
        }
        this.props.addCompetitionG(localStorage.getItem('Token'), generalA);
        formValues.nameG = '';
        formValues.Competition = '';
    }

    loadList() {
        return this.props.listCompetition.map((com) => {
            return (
                <option value={com.id}>{com.name}</option>
            )
        })
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
                            idCompetition: '',
                            requestData: null
                        }
                        this.props.deleteCompetitionG(localStorage.getItem('Token'), generalA)
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
                    <button type="button" className="btn text-light btn-sm float-right naranja " data-toggle="modal" data-target="#addCompetitionG" >
                        <i class="fas fa-plus"></i> Agregar
                    </button>
                    <div class="modal fade" id="addCompetitionG" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">

                                <form className="form-horizontal" onSubmit={this.props.handleSubmit(this.handleSubmit)}>
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Nueva competencia</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label for="form_control_1">Nombre: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="nameG" validate={[required, minimum, fiveHundred]} component={generarInput} label="Nombre" />
                                            </div>
                                        </div>
                                        <br />
                                        <label for="form_control_1">Competencia: </label>
                                        <div className="row">
                                            <div className="col-sm">
                                                <Field name="competition" validate={[select]} className="bs-select form-control" component={generarSelect}>
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
                        title="Competencias especificas"
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
                                emptyDataSourceMessage: 'Aun no hay ninguna competencia registrada'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre', field: 'name' },
                            { title: 'Nombre de la Competencia', field: 'nameCompetition' },
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
                        data={this.props.listCompetitionG}
                        options={{
                            search: true
                        }}

                    />

                </div>
            </div >
        );
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
        listCompetitionG: state.competitionGeneral.listCompetitionGR,
        listCompetition: state.generalClass.listGeneralClassR,
        messageAddG: state.competitionGeneral.messageAdd,
        messageDeleteG: state.competitionGeneral.messageDelete
    }
}

let formAddCG = reduxForm({
    form: 'addCompetitionG',
    enableReinitialize: true
})(CompetitionG)

export default withRouter(connect(mapStateToProps, { getListGeneralC, getListCompetitionG, addCompetitionG, deleteCompetitionG, addMessageAdd, addMessageDelete })(formAddCG));