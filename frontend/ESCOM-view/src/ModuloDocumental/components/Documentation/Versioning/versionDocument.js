import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { getProgramId } from '../../../redux/actions/programA';
import { getCurrentVersions, getOldVersions } from '../../../redux/actions/documentVersionA.js';
import MaterialTable from 'material-table';
import AddVersionD from './addVersionD.js';

class versionDocument extends Component {

    componentDidMount() {
        this.props.getProgramId(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
        this.props.getOldVersions(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
        this.props.getCurrentVersions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Lista versiones documento maestro</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    <h5 className="text-center"><strong>{this.props.program.name}</strong></h5>
                    <h6 className="text-center">Sede:{this.props.program.campus}</h6>
                    {/*<hr />
                    <MaterialTable
                        title="Versiones documento actual"
                        localization={{
                            header: {
                                actions: ' '
                            },
                            pagination: {
                                nextTooltip: 'Siguiente ',
                                previousTooltip: 'Anterior',
                                labelDisplayedRows: '{from}-{to} de {count}',
                                lastTooltip: 'Ultima pagina',
                                firstTooltip: 'Primera pagina',
                                labelRowsSelect: 'Registros',
                                firstAriaLabel: 'oooo'
                            },
                            body: {
                                emptyDataSourceMessage: 'Aun no hay ningun documento registrado'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[
                            { title: 'Descripción', field: 'description' },
                            {
                                title: 'Versión', field: 'version',
                                render: rowData => {
                                    return 'V-' + rowData.version
                                }

                            },
                            {
                                title: 'Estado', field: 'state',
                                render: rowData => {
                                    if (rowData.state === 1) {
                                        return 'Activo'
                                    } else {
                                        return 'No activo'
                                    }
                                }
                            },
                            {
                                title: '', field: 'location',
                                render: rowData => {
                                    if (rowData.location !== null) {
                                        return <Link to={'/' + rowData.location} target="_blank" download><i class="fas fa-download"></i></Link>
                                    } else {
                                        return ''
                                    }
                                }
                            }
                        ]}
                        data={this.props.listCurrent}
                        options={{
                            search: true
                        }}

                    />
                    <hr />
                    */}
                    <AddVersionD />
                    <br />
                    <br />
                    <MaterialTable
                        title="Documentos anteriores"
                        localization={{
                            header: {
                                actions: ' '
                            },
                            pagination: {
                                nextTooltip: 'Siguiente ',
                                previousTooltip: 'Anterior',
                                labelDisplayedRows: '{from}-{to} de {count}',
                                lastTooltip: 'Ultima pagina',
                                firstTooltip: 'Primera pagina',
                                labelRowsSelect: 'Registros',
                                firstAriaLabel: 'oooo'
                            },
                            body: {
                                emptyDataSourceMessage: 'Aun no hay ningun documento registrado'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[
                            { title: 'Descripción', field: 'description' },
                            {
                                title: 'Versión', field: 'version',
                                render: rowData => {
                                    return 'V-' + rowData.version
                                }

                            },
                            {
                                title: '', field: 'location',
                                render: rowData => {
                                    if (rowData.location !== null) {
                                        return <Link to={'/' + rowData.location} target="_blank" download><i class="fas fa-download"></i></Link>
                                    } else {
                                        return ''
                                    }
                                }
                            }
                        ]}
                        data={this.props.listOld}
                        options={{
                            search: true
                        }}

                    />
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