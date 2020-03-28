import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListActivities, getActivityId} from '../../../redux/actions/activityA.js';
import { getConditionId } from '../../../redux/actions/conditionA.js';


class ProcessCondition extends Component {
    componentWillMount() {
        this.props.getConditionId(localStorage.getItem('Token'),sessionStorage.getItem('condition'))
        this.props.getListActivities(localStorage.getItem('Token'),sessionStorage.getItem('condition'))
    }

    save(id) {
        sessionStorage.setItem('activity',id)        
        this.props.getActivityId(id)
        this.props.history.push('/AddInformation')
    }

    loadCondition() {
        return (
            <div className="pg">
                <h2 className="text-center" style={{ textTransform: 'uppercase'}}>
                    {this.props.conditionPro.name}
                </h2>
                <h4>
                    Descripcion
                </h4>
                <p>
                    {this.props.conditionPro.description}
                </p>
            </div>
        )
    }

    loadActivities() {        
        return this.props.listActivities.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.name}</td>
                    <td>{activity.description}</td>
                    <td>{activity.state}</td>
                    <td>
                        <button onClick={() => this.save(activity.id)} className="btn btn-sm text-light naranja">
                            Ver
                        </button>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "80%" }}>
                {this.loadCondition()}
                <h2 className="text-center">
                    Lista actividades
                </h2>
                <div className="pg">
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
        )
    }
}

function mapStateToProps(state) {
    return {
        conditionPro: state.condition.conditionR,
        listActivities: state.activity.listActivityR
    }
}

export default withRouter( connect(mapStateToProps, { getConditionId, getListActivities, getActivityId })(ProcessCondition));