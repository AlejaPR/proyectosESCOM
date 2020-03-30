import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter, Link } from 'react-router-dom';

class ProcessAnnex extends Component {

    render() {
        return (
            <div>
                <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4>Actividad Anexo</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h3 className="card-title text-center"><strong>{this.props.activityAnnex.nameActivity}</strong></h3>
                                <h5>Descripcion:</h5>
                                <p>-- {this.props.activityAnnex.descriptionActivity}</p>
                                <h5>Anexo asociado: </h5>
                                <p>-- {this.props.activityAnnex.nameAnnex === "" ? 'Ningún anexo asociado ' : this.props.activityAnnex.nameAnnex}</p>
                                {() => {
                                    if (this.props.activityAnnex.url !== "") {
                                        return (
                                            <Link to={'/' + this.props.activityAnnex.url} target="_blank" download><i class="fas fa-download"></i></Link>
                                        )
                                    }
                                }
                                }
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
        activityAnnex: state.activity.activityAnnexR
    }
}

export default withRouter(connect(mapStateToProps, {})(ProcessAnnex));