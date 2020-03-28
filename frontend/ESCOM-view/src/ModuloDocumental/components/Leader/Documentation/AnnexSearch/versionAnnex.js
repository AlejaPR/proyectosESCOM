import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getAnnexId } from '../../../../redux/actions/annexA.js';
import { getAnnexVersions } from '../../../../redux/actions/annexVersionA.js';
import AddVersion from './addVersion.js';
import { Link } from 'react-router-dom';

class versionAnnex extends Component {

    componentWillMount() {
        this.props.getAnnexId(localStorage.getItem('Token'),sessionStorage.getItem('annex'))
        this.props.getAnnexVersions(localStorage.getItem('Token'),sessionStorage.getItem('annex'))
    }

    loadTable() {
        return this.props.annexVersions.map((annex) => {
            return (
                <tr key={annex.id}>
                    <td>{annex.description}</td>
                    <td>{'V-'+annex.version}</td>
                    <td>{annex.state === 1 ? 'activo' : 'no activo'}</td>
                    <td><Link to={annex.location !== '' ? '/'+annex.location : ''}  target="_blank" download><i class="fas fa-download"></i></Link></td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color">
                <br />
                <div class="card">
                    <div class="card-body">
                        <h2 class="card-title text-center" style={{ textTransform: 'uppercase' }}><strong>{this.props.annexSelect.name}</strong></h2>
                        <h5 class="card-title"><strong>Descripción</strong></h5>
                        <p>{this.props.annexSelect.description}</p>
                    </div>
                </div>
                <br />
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center"><strong>Lista Versiones</strong></h4>
                        <AddVersion />
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
                                {this.loadTable()}
                            </tbody>
                        </table>
                    </div>
                </div>
                <br />
            </div>
        )
    }

}


function mapStateToProps(state) {
    return {
        annexID: state.annex.annexId,
        annexSelect: state.annex.annexR,
        annexVersions: state.annexVersion.listAnnexVersionR
    }
}

export default connect(mapStateToProps, { getAnnexVersions, getAnnexId })(versionAnnex)