import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getConditionId } from '../../../../redux/actions/conditionA.js';
import { getListActivitiesAnnex, getListActivitiesInfo, getActivityId, addMessageDelete, deleteActivity } from '../../../../redux/actions/activityA.js';
import { getListUsersCondition, deleteUserCondition, addMessageDeleteUser } from '../../../../redux/actions/userConditionA.js';
import { ToastContainer, toast } from 'react-toastify';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';

import AddUser from '../Condition/addUser.js'
import AddInfo from '../Activity/addInfo.js';
import AddAnnex from '../Activity/addAnnex.js';
import Edit from '../Activity/edit.js';
import View from '../Activity/view.js';

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
            title: 'Eliminar',
            message: '¿Desea eliminar este elemento de forma permanente?',
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
                },
                {
                    label: 'No',
                    onClick: () => { },
                }
            ],
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

    loadTableInfo() {
        return this.props.activitiesInfo.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.number}</td>
                    <td>{activity.name}</td>
                    <td>{activity.description}</td>
                    <td>
                        <button onClick={() => this.save(activity.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <View />
                    </td>
                    <td>
                        <button onClick={() => this.save(activity.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#editModal">
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

    loadTableAnnex() {
        return this.props.activitiesAnnex.map((activity) => {
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
            <div className="container color" style={{ width: "90%" }}>
                <ToastContainer />
                <br />
                <button type="button" onClick={this.onClickCancelar} className="btn btn-danger btn-sm" >
                    <i class="fas fa-angle-double-left"></i>
                </button>
                <br />
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title text-center" style={{ textTransform: 'uppercase' }}><strong>{this.props.conditions.name}</strong></h2>
                        <hr />
                        <div className="row">
                            <div className="col-6">
                                <h5><strong>Descripcion</strong></h5>
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
                    </div>
                </div>

                <br />

                <div class="card">
                    <div class="card-body">
                        <AddInfo />
                        <h4 class="card-title text-center">LISTA ACTIVIDADES INFORMATIVAS</h4>
                        <table class="table border table-striped">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Actividad</th>
                                    <th scope="col">Descrpcion</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.loadTableInfo()}
                            </tbody>
                        </table>
                    </div>
                </div>
                <br />
                <div class="card">
                    <div class="card-body">
                        <AddAnnex />
                        <h4 class="card-title text-center">LISTA ACTIVIDADES DE ANEXOS</h4>
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
                                {this.loadTableAnnex()}
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
        activitiesInfo: state.activity.listActivityInfoR,
        activitiesAnnex: state.activity.listActivityAnnexR,
        messageDeleteA: state.activity.messageDelete,
        listUsersConditions: state.userCondition.listUsersConditionR,
        messageDeleteU: state.userCondition.messageDelete,

    }
}

export default withRouter(connect(mapStateToProps, { getListUsersCondition, deleteUserCondition, addMessageDeleteUser, getConditionId, deleteActivity, addMessageDelete, getListActivitiesAnnex, getListActivitiesInfo, getActivityId })(ViewCondition));