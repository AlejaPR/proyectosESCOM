import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import { getListGeneralC, addMessageAdd, addMessageDelete } from '../../../redux/actions/generalClassA.js';

import Occupational from './list/occupational.js';
import TrainingArea from './list/trainingArea.js';
import Thematic from './list/thematic.js';
import Professional from './list/professional.js';
import Competition from './list/competition.js';
import Distinctive from './list/distinctiveFeature.js';
import CompetitionG from './list/competitionGeneral.js';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

import ThematicCore from './list/thematicCore.js';


class ItemList extends Component {

    componentWillMount() {
    }

    componentDidUpdate() {
        if (this.props.messageAdd !== '') {
            switch (this.props.messageAdd) {
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('')
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDelete !== '') {
            switch (this.props.messageDelete) {
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDelete('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para inhabilitar este elemento.');
                    this.props.addMessageDelete('')
                    break;
                default:
                    break;
            }
        }
    }

    save(table) {
        this.props.getListGeneralC(localStorage.getItem('Token'), sessionStorage.getItem('programId'), table);
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Lista de configuraciones</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar estos elementos</Alert>
                        </div> :
                            <div>
                                <nav>
                                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                                        <a onClick={() => this.save('TrainingArea')} class="nav-item nav-link active" id="nav-area-tab" data-toggle="tab" href="#nav-area" role="tab" aria-controls="nav-area" aria-selected="true">Área </a>
                                        <a onClick={() => this.save('TrainingArea')} class="nav-item nav-link" id="nav-core-tab" data-toggle="tab" href="#nav-core" role="tab" aria-controls="nav-core" aria-selected="false">Núcleo </a>
                                        <a onClick={() => this.save('Thematic')} class="nav-item nav-link" id="nav-thematic-tab" data-toggle="tab" href="#nav-thematic" role="tab" aria-controls="nav-thematic" aria-selected="false">Temática</a>
                                        <a onClick={() => this.save('OccupationalProfile')} class="nav-item nav-link" id="nav-occupational-tab" data-toggle="tab" href="#nav-occupational" role="tab" aria-controls="nav-occupational" aria-selected="false">ocupacional</a>
                                        <a onClick={() => this.save('ProfessionalProfile')} class="nav-item nav-link" id="nav-professional-tab" data-toggle="tab" href="#nav-professional" role="tab" aria-controls="nav-professional" aria-selected="false">profesional</a>
                                        <a onClick={() => this.save('Competition')} class="nav-item nav-link" id="nav-competition-tab" data-toggle="tab" href="#nav-competition" role="tab" aria-controls="nav-competition" aria-selected="false">Competencia</a>
                                        <a onClick={() => this.save('Competition')} class="nav-item nav-link" id="nav-competitionG-tab" data-toggle="tab" href="#nav-competitionG" role="tab" aria-controls="nav-competitionG" aria-selected="false">Competencia específica</a>
                                        <a onClick={() => this.save('DistinctiveFeature')} class="nav-item nav-link" id="nav-distinctive-tab" data-toggle="tab" href="#nav-distinctive" role="tab" aria-controls="nav-distinctive" aria-selected="false">Distintivo</a>
                                    </div>
                                </nav>
                                <div class="tab-content" id="nav-tabContent">
                                    <div class="tab-pane fade show active" id="nav-area" role="tabpanel" aria-labelledby="nav-area-tab"><TrainingArea /></div>
                                    <div class="tab-pane fade" id="nav-core" role="tabpanel" aria-labelledby="nav-core-tab"><ThematicCore /></div>
                                    <div class="tab-pane fade" id="nav-thematic" role="tabpanel" aria-labelledby="nav-thematic-tab"><Thematic /></div>
                                    <div class="tab-pane fade" id="nav-occupational" role="tabpanel" aria-labelledby="nav-occupational-tab"><Occupational /></div>
                                    <div class="tab-pane fade" id="nav-professional" role="tabpanel" aria-labelledby="nav-professional-tab"><Professional /></div>
                                    <div class="tab-pane fade" id="nav-competition" role="tabpanel" aria-labelledby="nav-competition-tab"><Competition /></div>
                                    <div class="tab-pane fade" id="nav-competitionG" role="tabpanel" aria-labelledby="nav-competitionG-tab"><CompetitionG /></div>
                                    <div class="tab-pane fade" id="nav-distinctive" role="tabpanel" aria-labelledby="nav-distinctive-tab"><Distinctive /></div>
                                </div>
                            </div>
                    }
                </div>
            </div>
        );
    }

}

function mapStateToProps(state) {
    return {
        messageAdd: state.generalClass.messageAddC,
        messageDelete: state.generalClass.messageDeleteC,
        enabled: state.generalClass.stateGeneralClass
    }
}

export default withRouter(connect(mapStateToProps, { getListGeneralC, addMessageAdd, addMessageDelete })(ItemList));