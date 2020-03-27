import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getConditionId } from '../../../../redux/actions/conditionA.js';
import { getListActivities, getActivityId, addMessageEdit, addMessageAdd, addMessageDelete, deleteActivity } from '../../../../redux/actions/activityA.js';
import { getListUsersCondition, addMessageAssociate, deleteUserCondition, addMessageDeleteUser } from '../../../../redux/actions/userConditionA.js';
import { ToastContainer, toast } from 'react-toastify';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';

import AddUser from '../Condition/addUser.js'
import Add from '../Activity/add.js';
import Edit from '../Activity/edit.js';
import View from '../Activity/view.js';

class ViewCondition extends Component {

    componentDidUpdate() {
        if (this.props.messageEditA !== '') {
            switch (this.props.messageEditA) {
                case 'edit':
                    toast.success('Se agrego con exito.');
                    this.props.getListActivities(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageEdit('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddA !== '') {
            switch (this.props.messageAddA) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListActivities(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDeleteA !== '') {
            switch (this.props.messageDeleteA) {
                case 'delete':
                    toast.success('Se elimino correctamente.');
                    this.props.getListActivities(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageDelete('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
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
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAssociate !== '') {
            switch (this.props.messageAssociate) {
                case 'associate':
                    toast.success('Usuario asociado.');
                    this.props.getListUsersCondition(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
                    this.props.addMessageAssociate('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }


    }

    componentDidMount() {
        this.props.getConditionId(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivities(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListUsersCondition(localStorage.getItem('Token'), sessionStorage.getItem('condition'));
    }

    saveView(id) {
        this.props.getActivityId(localStorage.getItem('Token'), id)
    }
    saveEdit(id) {
        this.props.getActivityId(localStorage.getItem('Token'), id)
    }

    deleteUser(id) {
        confirmAlert({
            title: '¿Desea Eliminar?',
            message: 'Desea eliminar este elemento de forma permanente.',
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
                    },
                    className: "btn btn-sm text-light naranja"
                },
                {
                    label: 'No',
                    onClick: () => { },
                }
            ],
            className: "btn btn-sm text-light naranja"
        })
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

    loadTable() {
        return this.props.activities.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.name}</td>
                    <td>{activity.description}</td>
                    <td>
                        <button onClick={() => this.saveView(activity.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <View />
                    </td>
                    <td>
                        <button onClick={() => this.saveEdit(activity.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal">
                            <i class="fas fa-pen"></i>
                        </button>
                        <Edit />
                    </td>
                    <td>
                        <button onClick={() => this.submit(activity.id)} className="btn btn-sm text-light naranja">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }



    submit(id) {
        confirmAlert({
            title: '¿Desea Eliminar?',
            message: 'Desea eliminar este elemento de forma permanente.',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => this.props.deleteActivity(id),
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
            <div className="container color" >
                <ToastContainer />
                <br />
                <button type="button" onClick={this.onClickCancelar} className="btn btn-danger btn-sm" >
                    <i class="fas fa-angle-double-left"></i>
                </button>
                <br />
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title text-center" style={{ textTransform: 'uppercase' }}><strong>{this.props.conditions.name}</strong></h2>
                        <hr/>
                        <div className="row">
                            <div className="col-6">
                                <h5><strong>Descripcion</strong></h5>
                                <p>
                                    {this.props.conditions.description}
                                </p>
                                <h6><strong>Fecha</strong></h6>
                                <p>Fecha inicio: {this.props.conditions.startDateS} <br/>Fecha final: {this.props.conditions.finalDateS}</p>
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
                    </div>
                </div>

                <br />

                <div class="card">
                    <div class="card-body">
                        <Add />
                        <h4 class="card-title text-center">LISTA ACTIVIDADES</h4>
                        <table class="table border table-striped">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">Actividad</th>
                                    <th scope="col">Descrpcion</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.loadTable()}
                            </tbody>
                        </table>
                    </div>
                </div>
                <br />
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        conditions: state.condition.conditionR,
        activities: state.activity.listActivityR,
        messageEditA: state.activity.messageEdit,
        messageAddA: state.activity.messageAdd,
        messageDeleteA: state.activity.messageDelete,
        messageAssociate: state.userCondition.messageAssociate,
        listUsersConditions: state.userCondition.listUsersConditionR,
        messageDeleteU: state.userCondition.messageDelete,

    }
}

export default withRouter(connect(mapStateToProps, { getListUsersCondition, addMessageAssociate, deleteUserCondition, addMessageDeleteUser, getConditionId, deleteActivity, addMessageDelete, getListActivities, getActivityId, addMessageEdit, addMessageAdd })(ViewCondition));