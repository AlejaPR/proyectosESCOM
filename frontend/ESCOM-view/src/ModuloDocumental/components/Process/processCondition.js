import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import { ToastContainer, toast } from 'react-toastify';
import { getListActivitiesInfo, getActivityAnnex, addMessageChange } from '../../redux/actions/activityA.js';
import { getConditionId, approveCondition, addMessageApprove } from '../../redux/actions/conditionA.js';
import MaterialTable from 'material-table';
import VisibilityIcon from '@material-ui/icons/Visibility';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

import ProcessAnnex from './ProcessAnnex.js';
class ProcessCondition extends Component {
    componentWillMount() {
        this.props.getConditionId(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesInfo(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
    }

    componentDidUpdate() {
        if (this.props.messageApprove !== '') {
            switch (this.props.messageApprove) {
                case 'approve':
                    toast.success('Se ha aprobado con éxito.');
                    this.props.addMessageApprove('');
                    this.props.history.push('/ProcessProgram')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageApprove('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageChange !== '') {
            switch (this.props.messageChange) {
                case 'approved':
                    toast.success('Aprobada con éxito.');
                    this.props.addMessageChange('');
                    break;
                case 'denied':
                    toast.success('Denegada con éxito.');
                    this.props.addMessageChange('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageChange('')
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

    approveCondition(id) {
        confirmAlert({
            title: 'Aprobar condición',
            message: '¿Esta seguro que quiere aprobadar esta condición?.',
            buttons: [
                {
                    label: 'Yes',
                    onClick: () => {
                        this.props.approveCondition(localStorage.getItem('Token'), id)
                    }
                },
                {
                    label: 'No',
                    onClick: () => {

                    }
                }
            ]
        });
    }

    saveInfo(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/ProcessActivity')
    }

    saveAnnex(id) {
        this.props.getActivityAnnex(localStorage.getItem('Token'), id)
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Proceso condición</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabledA || this.props.enabledI ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar de actividades</Alert>
                        </div> :
                            <div>
                                <button type="button" onClick={() => this.approveCondition(sessionStorage.getItem('condition'))} className="btn text-light btn-sm float-right naranja " >
                                    Aprobar
                                </button>
                                <h5 className="card-title text-center" ><strong>{this.props.conditionPro.name}</strong></h5>
                                <h6><strong>Descripción:</strong></h6>
                                <p>{this.props.conditionPro.description}</p>
                                <hr />
                                <MaterialTable
                                    title="Lista actividades informativas"
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna actividad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[
                                        { title: '#', field: 'number' },
                                        { title: 'Nombre de la actividad', field: 'name' },
                                        {
                                            title: 'Estado', field: 'state',
                                            render: rowData => {
                                                if (rowData.state === 1) {
                                                    return 'Activo'
                                                } else if (rowData.state === 2) {
                                                    return 'Finalizado'
                                                } else {
                                                    return (<strong>Por aprobación</strong>)
                                                }
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.saveInfo(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }
                                    ]}
                                    data={this.props.listActivityInfo}
                                    options={{
                                        search: true
                                    }}

                                />
                                <hr />
                                <MaterialTable
                                    title="Lista de actividades de anexo"
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna actividad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[
                                        { title: 'Nombre de la actividad', field: 'name' },
                                        { title: 'Descripción', field: 'description' },
                                        {
                                            title: 'Estado', field: 'state',
                                            render: rowData => {
                                                if (rowData.state === 1) {
                                                    return 'Activo'
                                                } else if (rowData.state === 2) {
                                                    return 'Finalizado'
                                                } else {
                                                    return (<strong>Por aprobación</strong>)
                                                }
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.saveInfo(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }
                                    ]}
                                    data={this.props.listActivityAnnex}
                                    options={{
                                        search: true
                                    }}

                                />
                            </div>
                    }
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        conditionPro: state.condition.conditionR,
        listActivityInfo: state.activity.listActivityInfoR,
        listActivityAnnex: state.activity.listActivityAnnexR,
        messageApprove: state.condition.messageApprove,
        messageChange: state.activity.messageChange,
        enabledI: state.activity.stateActivityList,
        enabledA: state.activity.stateActivityAnnex
    }
}

export default withRouter(connect(mapStateToProps, { getConditionId, addMessageChange, getActivityAnnex, getListActivitiesInfo, approveCondition, addMessageApprove })(ProcessCondition));