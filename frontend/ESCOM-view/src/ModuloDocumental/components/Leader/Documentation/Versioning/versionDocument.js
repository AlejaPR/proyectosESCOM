import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getDocumentId } from '../../../../redux/actions/documentA.js';
import { getDocumentVersions } from '../../../../redux/actions/documentVersionA.js';

class versionDocument extends Component {
    componentWillMount() {
        this.props.getDocumentId(this.props.documentID)
        this.props.getDocumentVersions(this.props.documentID)
    }

    loadTable() {
        return this.props.documentVersions.map((version) => {
            return (
                <tr key={version.id}>
                    <td>{version.version}</td>
                    <td>{version.description}</td>
                    <td>{version.state}</td>
                    <td>
                        <button className="btn btn-sm text-light naranja">
                            Descargar
                        </button>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <div className="container color" style={{ width: "80%" }}>
                <p>{this.props.documentS.description}</p>
                <h5 className="text-center py-3">Versiones documento</h5>
                <div >
                    <table class="table border table-striped">
                        <thead class="colorBlue text-light">
                            <tr>
                                <th scope="col">Version</th>
                                <th scope="col">Descripción</th>
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
        )
    }
}

function mapStateToProps(state) {
    return {
        documentID: state.document.documentIdG,
        documentS: state.document.documentR,
        documentVersions: state.documentVersion.listDocumentVer

    }
}

export default connect(mapStateToProps, { getDocumentId, getDocumentVersions })(versionDocument)