import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListActivitiesInfo, getActivityId, getListActivitiesAnnex } from '../../../redux/actions/activityA.js';
import { getConditionId } from '../../../redux/actions/conditionA.js';


class ProcessCondition extends Component {
    componentWillMount() {
        this.props.getConditionId(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesInfo(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesAnnex(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
    }

    save(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/AddInformation')
    }
    saveAnnex(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/AddAnnex')
    }

    loadCondition() {
        return (
            <div className="pg">
                <h2 className="text-center" style={{ textTransform: 'uppercase' }}>
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

    loadActivitiesInfo() {
        return this.props.listActivityInfo.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.number}</td>
                    <td>{activity.name}</td>
                    <td>{activity.state === 1 ? 'Activo' : 'Finalizado'}</td>
                    <td>{activity.type === 1 ? 'Informativo' : 'anexo'}</td>
                    <td>
                        <button onClick={() => this.save(activity.id)} className="btn btn-sm text-light naranja">
                            Ver
                        </button>
                    </td>
                </tr>
            )
        })
    }

    loadActivitiesAnnex() {
        return this.props.listActivityAnnex.map((activity) => {
            return (
                <tr key={activity.id}>
                    <td>{activity.name}</td>
                    <td>{activity.state === 1 ? 'Activo' : 'Finalizado'}</td>
                    <td>{activity.type === 1 ? 'Informativo' : 'anexo'}</td>
                    <td>{
                        <button onClick={() => this.saveAnnex(activity.id)} className="btn btn-sm text-light naranja">
                            Ver
                        </button>
                    }
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                {this.loadCondition()}
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
                                <th scope="col">Estado</th>
                                <th scope="col">Tipo</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.loadActivitiesAnnex()}
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
        listActivityInfo: state.activity.listActivityInfoR,
        listActivityAnnex: state.activity.listActivityAnnexR
    }
}

export default withRouter(connect(mapStateToProps, { getConditionId, getListActivitiesInfo, getListActivitiesAnnex, getActivityId })(ProcessCondition));