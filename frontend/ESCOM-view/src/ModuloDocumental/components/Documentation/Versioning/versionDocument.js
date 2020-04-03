import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { getProgramId } from '../../../redux/actions/programA';
import { getCurrentVersions, getOldVersions } from '../../../redux/actions/documentVersionA.js';

class versionDocument extends Component {
    componentDidMount() {
        this.props.getProgramId(localStorage.getItem('Token'),sessionStorage.getItem('programId'))
        this.props.getOldVersions(localStorage.getItem('Token'),sessionStorage.getItem('programId'))
        this.props.getCurrentVersions(localStorage.getItem('Token'),sessionStorage.getItem('documentId'))
    }

    loadTableCurrent() {
        return this.props.listCurrent.map((version) => {
            return (
                <tr key={version.id}>
                    <td>{version.description}</td>
                    <td>{'V-' + version.version}</td>
                    <td>{version.state === 1 ? 'activo' : 'no activo'}</td>
                    <td><Link to={version.location !== '' ? '/' + version.location : ''} target="_blank" download><i class="fas fa-download"></i></Link></td>
                </tr>
            )
        })
    }

    loadTableOld() {
        return this.props.listOld.map((version) => {
            return (
                <tr key={version.id}>
                    <td>{version.description}</td>
                    <td>{'V-' + version.version}</td>
                    <td><Link to={version.location !== '' ? '/' + version.location : ''} target="_blank" download><i class="fas fa-download"></i></Link></td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <div class="card">
                    <h2 className="text-center"><strong>{this.props.program.name}</strong></h2>
                    <h6 className="text-center">Sede:{this.props.program.campus}</h6>

                    <div class="card-body">
                        <div class="card">
                            <div class="card-body">
                                <h5 className="text-center py-3"><strong>Versiones documento actual</strong></h5>
                                <div >
                                    <table class="table border table-striped">
                                        <thead class="colorBlue text-light">
                                            <tr>
                                                <th scope="col">Descripción</th>
                                                <th scope="col">Version</th>
                                                <th scope="col">Estado</th>
                                                <th scope="col">Descargar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {this.loadTableCurrent()}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="card">
                            <div class="card-body">
                                <h4 className="text-center py-3"><strong>Documentos anteriores</strong></h4>
                                <div >
                                    <table class="table border table-striped">
                                        <thead class="colorBlue text-light">
                                            <tr>
                                                <th scope="col">Descripción</th>
                                                <th scope="col">Version</th>
                                                <th scope="col">Descargar</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {this.loadTableOld()}
                                        </tbody>
                                    </table>
                                </div>
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

        listCurrent: state.documentVersion.listCurrentVersions,
        listOld: state.documentVersion.listOldVersions,
        program: state.program.programR

    }
}

export default connect(mapStateToProps, { getCurrentVersions, getOldVersions, getProgramId })(versionDocument)