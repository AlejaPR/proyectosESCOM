import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import { getProgramT } from '../../../../redux/actions/programThematicA.js';
import EditProgramT from '../editProgramT.js';
import { getListGeneralC } from '../../../../redux/actions/generalClassA.js';
import { getListRelational, addMessageAdd, addMessageDelete } from '../../../../redux/actions/relationalClassA.js';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { toast } from 'react-toastify';

import PtCompetitionG from './ptCompetitionG.js';
import PtDistinctive from './ptDistinctive.js';
import PtOccupational from './ptOccupational.js';
import PtProfessional from './ptProfessional.js';
import PtThematic from './ptThematic.js';

class ThematicList extends Component {

    componentDidMount() {
        this.props.getProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programT'))
    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDelete !== '') {
            switch (this.props.messageDelete) {
                case 'Sin persimo':
                    toast.error('No tiene permiso para eliminar este elemento.');
                    this.props.addMessageDelete('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDelete('');
                    break;
                default:
                    break;
            }
        }
    }

    save(general, relation) {
        this.props.getListGeneralC(localStorage.getItem('Token'), sessionStorage.getItem('programId'), general);
        this.props.getListRelational(localStorage.getItem('Token'), sessionStorage.getItem('programT'), relation);
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Núcleo temático seleccionado</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para visualizar el nucleo tematico del programa</Alert>
                        </div> :
                            <div>
                                <div>
                                    <div className="row">
                                        <div className="col-sm">
                                            <h6><strong>Núcleo temático</strong></h6>
                                            <p>{this.props.programTh.nameThematicCore}</p>
                                        </div>
                                        <div className="col-sm">
                                            <h6><strong>Objetivo</strong></h6>
                                            <p>{this.props.programTh.objetive === '' || this.props.programTh.objetive === undefined ? 'No definido' : this.props.programTh.objetive}</p>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-sm">
                                            <h6><strong>Contribución objetivo</strong></h6>
                                            <p>{this.props.programTh.contributeObjetive === '' || this.props.programTh.contributeObjetive === undefined ? 'No definido' : this.props.programTh.contributeObjetive}</p>
                                        </div>
                                        <div className="col-sm">
                                            <h6><strong>Objetico de salida</strong></h6>
                                            <p>{this.props.programTh.objectiveOutput === '' || this.props.programTh.objectiveOutput === undefined ? 'No definido' : this.props.programTh.objectiveOutput}</p>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-sm">
                                            <h6><strong>Contribución perfil ocupacional</strong></h6>
                                            <p>{this.props.programTh.contributeOccupational === '' || this.props.programTh.contributeOccupational === undefined ? 'No definido' : this.props.programTh.contributeOccupational}</p>
                                        </div>
                                        <div className="col-sm">
                                            <h6><strong>Contribución perfil profesional</strong></h6>
                                            <p>{this.props.programTh.contributeProfessional === '' || this.props.programTh.contributeProfessional === undefined ? 'No definido' : this.props.programTh.contributeProfessional}</p>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <div className="col-sm">
                                            <h6><strong>Contribución del equipo</strong></h6>
                                            <p>{this.props.programTh.teamContribution === '' || this.props.programTh.teamContribution === undefined ? 'No definido' : this.props.programTh.teamContribution}</p>
                                        </div>
                                        <div className="col-sm">
                                            <h6><strong>Observación final</strong></h6>
                                            <p>{this.props.programTh.observationFinal === '' || this.props.programTh.observationFinal === undefined ? 'No definido' : this.props.programTh.observationFinal}</p>
                                        </div>
                                    </div>
                                </div>
                                <EditProgramT />
                                <br />
                                <hr />
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <a onClick={() => this.save('Thematic', 'PtThematic')} class="nav-item nav-link" id="nav-thematic-tab" data-toggle="tab" href="#nav-thematic" role="tab" aria-controls="nav-thematic" aria-selected="true">Temática</a>
                                        <a onClick={() => this.save('OccupationalProfile', 'PtOccupational')} class="nav-item nav-link" id="nav-occupational-tab" data-toggle="tab" href="#nav-occupational" role="tab" aria-controls="nav-occupational" aria-selected="false">ocupacional</a>
                                        <a onClick={() => this.save('ProfessionalProfile', 'PtProfessional')} class="nav-item nav-link" id="nav-professional-tab" data-toggle="tab" href="#nav-professional" role="tab" aria-controls="nav-professional" aria-selected="false">profesional</a>
                                        <a onClick={() => this.save('Competition', 'PtCompetitionG')} class="nav-item nav-link" id="nav-competitionG-tab" data-toggle="tab" href="#nav-competitionG" role="tab" aria-controls="nav-competitionG" aria-selected="false">Competencia específica</a>
                                        <a onClick={() => this.save('DistinctiveFeature', 'PtDistinctive')} class="nav-item nav-link" id="nav-distinctive-tab" data-toggle="tab" href="#nav-distinctive" role="tab" aria-controls="nav-distinctive" aria-selected="false">Distintivo</a>
                                    </div>
                                </nav>
                                <div class="tab-content" id="nav-tabContent">
                                    <div class="tab-pane fade show active" id="nav-thematic" role="tabpanel" aria-labelledby="nav-thematic-tab"><PtThematic /></div>
                                    <div class="tab-pane fade" id="nav-occupational" role="tabpanel" aria-labelledby="nav-occupational-tab"><PtOccupational /></div>
                                    <div class="tab-pane fade" id="nav-professional" role="tabpanel" aria-labelledby="nav-professional-tab"><PtProfessional /></div>
                                    <div class="tab-pane fade" id="nav-competitionG" role="tabpanel" aria-labelledby="nav-competitionG-tab"><PtCompetitionG /></div>
                                    <div class="tab-pane fade" id="nav-distinctive" role="tabpanel" aria-labelledby="nav-distinctive-tab"><PtDistinctive /></div>
                                </div>
                            </div>
                    }
                </div>
                <br />
            </div>
        );
    }

}

function mapStateToProps(state) {
    return {
        programTh: state.programThematic.programTR,
        messageAdd: state.relationalClass.messageAddR,
        messageDelete: state.relationalClass.messageDeleteR,
        enabled: state.relationalClass.stateRelational
    }
}

export default withRouter(connect(mapStateToProps, { addMessageAdd, addMessageDelete, getProgramT, getListRelational, getListGeneralC })(ThematicList));