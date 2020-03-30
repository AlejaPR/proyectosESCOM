import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getConditionsUser } from '../../../redux/actions/userConditionA.js';

class UserCondition extends Component {

    componentWillMount() {
        this.props.getConditionsUser(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
    }

    save(id) {
        sessionStorage.setItem('condition', id)
        this.props.history.push('/UserActivity')
    }

    load() {
        return this.props.conditions.map((ConditionView) => {
            return (
                <tr key={ConditionView.id}>
                    <td>{ConditionView.number}</td>
                    <td>{ConditionView.name}</td>
                    <td>
                        <div className="progress">
                            <div className="progress-bar" style={bar(ConditionView.percentage)} role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">{ConditionView.percentage}%</div>
                        </div>
                    </td>
                    <td>
                        <button onClick={() => this.save(ConditionView.id)} className="btn btn-sm text-light naranja">
                            Ver
                        </button>
                    </td>
                </tr >
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <h2 className="text-center"><strong>Lista de condiciones</strong></h2>
                <div className="pg">
                    <table class="table border table-striped">
                        <thead class="colorBlue text-light">
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Condición</th>
                                <th scope="col">Proceso</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.load()}
                        </tbody>
                    </table>
                </div>

            </div>
        )
    }

}

function bar(value) {
    return {
        width: value + "%"
    }

}

function mapStateToProps(state) {
    return {
        conditions: state.userCondition.listConditionsUserR
    }
}


export default withRouter(connect(mapStateToProps, { getConditionsUser })(UserCondition));