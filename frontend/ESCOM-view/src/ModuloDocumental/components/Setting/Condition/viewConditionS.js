import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getConditionId } from '../../../redux/actions/conditionA.js';
import { getListActivitiesAnnex, getListActivitiesInfo, getActivityId, addMessageDelete, deleteActivity } from '../../../redux/actions/activityA.js';
import { getListUsersCondition, deleteUserCondition, addMessageDeleteUser } from '../../../redux/actions/userConditionA.js';
import { ToastContainer, toast } from 'react-toastify';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

import AddUser from '../Condition/addUser.js'
import AddInfo from '../Activity/addInfo.js';
import AddAnnex from '../Activity/addAnnex.js';
import Edit from '../Activity/edit.js';
import View from '../Activity/view.js';

import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ViewCondition extends Component {

    componentDidUpdate() {
        if (this.props.messageDeleteA !== '') {
            switch (this.props.messageDeleteA) {
                case 'delete':
                    toast.success('Se elimino correctamente.');
                    this.props.getListActivitiesInfo(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.getListActivitiesAnnex(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageDelete('')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para eliminar este elemento.');
                    this.props.addMessageDelete('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDelete('')
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDeleteU !== '') {
            switch (this.props.messageDeleteU) {
                case 'delete':
                    toast.success('Se elimino correctamente.');
                    this.props.getListUsersCondition(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageDeleteUser('')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para eliminar este elemento.');
                    this.props.addMessageDeleteUser('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDeleteUser('')
                    break;
                default:
                    break;
            }
        }
    }

    componentDidMount() {
        this.props.getConditionId(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesAnnex(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesInfo(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListUsersCondition(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
    }

    save(id) {
        this.props.getActivityId(localStorage.getItem('Token'), id)
    }

    deleteUser(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let userCondition = {
                            id: 0,
                            idUser: id,
                            idCondition: sessionStorage.getItem('condition'),
                            requestData: null
                        }
                        this.props.deleteUserCondition(localStorage.getItem('Token'), userCondition)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    tableUser() {
        return this.props.listUsersConditions.map((user) => {
            return (
                <tr key={user.id}>
                    <td>{user.nombre}</td>
                    <td>
                        <button onClick={() => this.deleteUser(user.id)} className="btn btn-sm text-light float-right naranja">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }

    submit(id) {
        confirmAlert({
            title: '¿Eliminar?',
            message: 'Desea eliminar este elemento de forma permanente.',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.deleteActivity(localStorage.getItem('Token'), id)
                    },
                    className: "btn btn-sm text-light naranja"
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        })
    }

    onClickCancelar = (event) => {
        event.preventDefault();
        this.props.history.push('/ListCondition');
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Condición especifica</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {

                        this.props.enabledI || this.props.enabledA ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                        No tiene permisos suficientes para ver esta condición</Alert>
                        </div> :
                            <div>
                                <button type="button" onClick={this.onClickCancelar} className="btn btn-danger btn-sm" >
                                    <i class="fas fa-angle-double-left"></i>
                                </button>
                                <h5 class="card-title text-center"><strong>{this.props.conditions.name}</strong></h5>
                                <hr />
                                <div className="row">
                                    <div className="col-6">
                                        <h6><strong>Descripcion</strong></h6>
                                        <p>
                                            {this.props.conditions.description}
                                        </p>
                                        <h6><strong>Fecha</strong></h6>
                                        <p>Fecha inicio: {this.props.conditions.startDateS} <br />Fecha final: {this.props.conditions.finalDateS}</p>
                                        <br />
                                    </div>
                                    <div className="col-6">
                                        <h5><strong>Personas encargadas</strong></h5>
                                        <table class="table border table-striped">
                                            <tbody>
                                                {this.tableUser()}
                                            </tbody>
                                        </table>
                                        <AddUser />
                                    </div>
                                </div>
                                <hr />
                                <AddInfo />
                                <br />
                                <br />
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna activadad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[
                                        { title: '#', field: 'number' },
                                        { title: 'Nombre de la actividad', field: 'name' },
                                        { title: 'Descripción', field: 'description' },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                        <View />
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#editModal">
                                                            <EditIcon />
                                                        </a>
                                                        <Edit />
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.submit(rowData.id)}>
                                                            <DeleteForeverIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }

                                    ]}
                                    data={this.props.activitiesInfo}
                                    options={{
                                        search: true
                                    }}

                                />
                                <hr />
                                <AddAnnex />
                                <br />
                                <br />
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
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                        <View />
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#editModal">
                                                            <EditIcon />
                                                        </a>
                                                        <Edit />
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.submit(rowData.id)}>
                                                            <DeleteForeverIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }

                                    ]}
                                    data={this.props.activitiesAnnex}
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
        conditions: state.condition.conditionR,
        activitiesInfo: state.activity.listActivityInfoR,
        activitiesAnnex: state.activity.listActivityAnnexR,
        messageDeleteA: state.activity.messageDelete,
        listUsersConditions: state.userCondition.listUsersConditionR,
        messageDeleteU: state.userCondition.messageDelete,
        enabledI: state.activity.stateActivityList,
        enabledA: state.activity.stateActivityAnnex

    }
}

export default withRouter(connect(mapStateToProps, { getListUsersCondition, deleteUserCondition, addMessageDeleteUser, getConditionId, deleteActivity, addMessageDelete, getListActivitiesAnnex, getListActivitiesInfo, getActivityId })(ViewCondition));