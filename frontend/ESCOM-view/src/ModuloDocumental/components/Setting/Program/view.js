import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';

class ViewProgram extends Component {


    render() {
        return (
            <div >
                <br />
                <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 style={{ textTransform: 'uppercase' }}>{this.props.programS.name}</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h5>NIVEL EDUCACION</h5>
                                <p>{this.props.programS.levelEducation}</p>
                                <h5>CREDITOS ACADEMICOS</h5>
                                <p>{this.props.programS.academicCredits}</p>
                                <h5>DURACION</h5>
                                <p>{this.props.programS.duration} Semestres</p>
                                <h5>METODOLOGIA</h5>
                                <p>{this.props.programS.methodology}</p>
                                <h5>INSTITUCION EDUCATIVA</h5>
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

export default withRouter(connect(mapStateToProps, { })(ViewProgram));