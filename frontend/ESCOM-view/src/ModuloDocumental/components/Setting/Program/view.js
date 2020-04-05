import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';

class ViewProgram extends Component {


    render() {
        return (
            <div >
                <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 >{this.props.programS.name}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h6><strong>Nivel educación</strong></h6>
                                <p>{this.props.programS.levelEducation}</p>
                                <h6><strong>Créditos académicos</strong></h6>
                                <p>{this.props.programS.academicCredits}</p>
                                <h6><strong>Duración</strong></h6>
                                <p>{this.props.programS.duration} Semestres</p>
                                <h6><strong>Metodología</strong></h6>
                                <p>{this.props.programS.methodology}</p>
                                <h6><strong>Institución educativa</strong></h6>
                                <p>{this.props.programS.institution}</p>
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
        programS: state.program.programR
    }
}

export default withRouter(connect(mapStateToProps, {})(ViewProgram));