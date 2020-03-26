import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListActivities } from '../../../redux/actions/activityA.js';
import { getConditionId } from '../../../redux/actions/conditionA.js';


class ProcessCondition extends Component {
    componentWillMount() {
        this.props.getConditionId(localStorage.getItem('Token'),sessionStorage.getItem('condition'))
        this.props.getListActivities(localStorage.getItem('Token'),sessionStorage.getItem('condition'))
    }

    save(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/ProcessActivity')
    }


    loadActivities() {
        return this.props.listActivities.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.name}</td>
                    <td>{activity.description}</td>
                    <td>{activity.state === 1 ? 'activo' : 'no activo'}</td>
                    <td>
                        <button onClick={() => this.save(activity.id)} className="btn btn-sm text-light naranja">
                            <i class="far fa-eye"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color">
                <br />
                <div className="card">
                    <div className="card-body">
                        <h3 className="card-title text-center" style={{ textTransform: 'uppercase' }}><strong>{this.props.conditionPro.name}</strong></h3>
                        <h5><strong>Descripcion</strong></h5>
                        <p>{this.props.conditionPro.description}</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center"><strong>Lista actividades</strong></h4>
                        <table class="table border table-striped">
                            <thead class="colorBlue text-light">
                                <tr>
                                    <th scope="col">Actividad</th>
                                    <th scope="col">Descrpcion</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.loadActivities()}
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
        conditionPro: state.condition.conditionR,
        listActivities: state.activity.listActivityR
    }
}

export default withRouter(connect(mapStateToProps, { getConditionId, getListActivities })(ProcessCondition));