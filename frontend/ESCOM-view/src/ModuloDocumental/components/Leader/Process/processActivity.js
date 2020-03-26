import React from 'react';
import { Component } from 'react';


// Require Font Awesome.
import 'font-awesome/css/font-awesome.css';

import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom'
import { getActivityId, addInformation } from '../../../redux/actions/activityA.js';

class ProcessActivity extends Component {

    componentWillMount() {
        this.props.getActivityId(localStorage.getItem('Token'),sessionStorage.getItem('activity'))
    }

    saveView() {
        this.props.history.push('/ProcessCondition')
    }

    render() {
        return (
            <div className="container color">
                <br />
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><strong>INFORMACION ACTIVIDAD</strong></h5>
                        
                        <br />
                        <button onClick={() => this.saveView()} className="btn btn-sm text-light naranja">
                            Volver
                        </button>
                    </div>
                </div>
                <br />
            </div>
        );
    }
}

function mapStateToProps(state) {
    return {
        dataModel: state.activity.activityR
    }
}

export default withRouter(connect(mapStateToProps, { getActivityId, addInformation })(ProcessActivity));