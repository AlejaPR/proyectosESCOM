import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter, Link } from 'react-router-dom';
import { changeStatus } from '../../redux/actions/activityA.js';
import { confirmAlert } from 'react-confirm-alert';

class ProcessAnnex extends Component {

    approveActivity() {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let activityN = {
                            id: sessionStorage.getItem('activity'),
                            state: 2,
                            requestData: null
                        }
                        this.props.changeStatus(localStorage.getItem('Token'), activityN)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    deniedActivity() {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let activityN = {
                            id: sessionStorage.getItem('activity'),
                            state: 1,
                            requestData: null
                        }
                        this.props.changeStatus(localStorage.getItem('Token'), activityN)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    render() {
        return (
            <div>
                <div class="modal fade" id="viewModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5>Actividad Anexo</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <button type="button" onClick={() => this.approveActivity()} className="btn text-light btn-sm float-right naranja " >
                                    Aprobar
                                </button>
                                <button type="button" onClick={() => this.deniedActivity()} className="btn text-light btn-sm float-right naranja " >
                                    Denegar
                                </button>
                                <h4 className="card-title text-center"><strong>{this.props.activityAnnex.nameActivity}</strong></h4>
                                <h6><strong>Descripción:</strong></h6>
                                <p>-- {this.props.activityAnnex.descriptionActivity}</p>
                                <h6><strong>Anexo asociado:</strong></h6>
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

export default withRouter(connect(mapStateToProps, {changeStatus})(ProcessAnnex));