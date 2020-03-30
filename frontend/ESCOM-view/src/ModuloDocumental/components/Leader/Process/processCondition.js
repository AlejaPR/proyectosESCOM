import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import { ToastContainer, toast } from 'react-toastify';
import { getListActivitiesInfo, getActivityAnnex } from '../../../redux/actions/activityA.js';
import { getConditionId, approveCondition, addMessageApprove } from '../../../redux/actions/conditionA.js';

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
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
    }

    approveCondition(id) {
        confirmAlert({
            title: 'Aprobar condición',
            message: '¿Esta seguro que quiere dar como aprobada esta condición?.',
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

    loadActivitiesInfo() {
        return this.props.listActivityInfo.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.number}</td>
                    <td>{activity.name}</td>
                    <td>{activity.state === 1 ? 'Activo' : 'Finalizado'}</td>
                    <td>
                        <button onClick={() => this.saveInfo(activity.id)} className="btn btn-sm text-light naranja">
                            <i class="far fa-eye"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }

    saveInfo(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/ProcessActivity')
    }

    saveAnnex(id) {
        this.props.getActivityAnnex(localStorage.getItem('Token'), id)
    }

    loadActivitiesAnnex() {
        return this.props.listActivityAnnex.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.name}</td>
                    <td>{activity.description}</td>
                    <td>{activity.state === 1 ? 'Activo' : 'Finalizado'}</td>
                    <td>
                        <button onClick={() => this.saveAnnex(activity.id)} className="btn btn-sm text-light naranja" data-toggle="modal" data-target="#viewModal">
                            <i class="far fa-eye"></i>
                        </button>
                        <ProcessAnnex />
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <ToastContainer />
                <br />
                <div className="card">
                    <div className="card-body">
                        <button type="button" onClick={() => this.approveCondition(sessionStorage.getItem('condition'))} className="btn text-light btn-sm float-right naranja " >
                            Aprobar
                        </button>
                        <h3 className="card-title text-center" style={{ textTransform: 'uppercase' }}><strong>{this.props.conditionPro.name}</strong></h3>
                        <h5><strong>Descripcion</strong></h5>
                        <p>{this.props.conditionPro.description}</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h2 className="text-center">
                            Lista actividades informativas
                        </h2>
                        <div className="pg">
                            <table class="table border table-striped">
                                <thead class="colorBlue text-light">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Actividad</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col">Tipo</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.loadActivitiesInfo()}
                                </tbody>
                            </table>
                        </div>
                        <h2 className="text-center">
                            Lista actividades de anexos
                        </h2>
                        <div className="pg">
                            <table class="table border table-striped">
                                <thead class="colorBlue text-light">
                                    <tr>
                                        <th scope="col">Actividad</th>
                                        <th scope="col">Descripcion</th>
                                        <th scope="col">Estado</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {this.loadActivitiesAnnex()}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <br />
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        conditionPro: state.condition.conditionR,
        listActivityInfo: state.activity.listActivityInfoR,
        listActivityAnnex: state.activity.listActivityAnnexR,
        messageApprove: state.condition.messageApprove
    }
}

export default withRouter(connect(mapStateToProps, { getConditionId, getActivityAnnex, getListActivitiesInfo, approveCondition, addMessageApprove })(ProcessCondition));