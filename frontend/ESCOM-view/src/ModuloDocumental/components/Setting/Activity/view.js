import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getActivityId } from '../../../redux/actions/activityA.js'

class View extends Component {

    componentWillMount() {
        this.props.getActivityId(localStorage.getItem('Token'), sessionStorage.getItem('activity'))
    }

    render() {
        return (
            <div >
                <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel" ><strong>{this.props.activity.name}</strong></h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h6><strong>Descripción:</strong></h6>
                                <p>{this.props.activity.description}</p>                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

function mapStateToProps(state) {
    return {
        activity: state.activity.activityR,
        act: state.activity.activityId
    }
}

export default connect(mapStateToProps, { getActivityId })(View);