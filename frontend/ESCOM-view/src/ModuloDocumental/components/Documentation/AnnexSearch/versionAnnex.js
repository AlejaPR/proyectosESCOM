import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getAnnexId } from '../../../redux/actions/annexA.js';
import { getAnnexVersions } from '../../../redux/actions/annexVersionA.js';
import AddVersion from './addVersion.js';
import { Link } from 'react-router-dom';
import MaterialTable from 'material-table';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

class versionAnnex extends Component {

    componentWillMount() {
        this.props.getAnnexId(localStorage.getItem('Token'), sessionStorage.getItem('annex'))
        this.props.getAnnexVersions(localStorage.getItem('Token'), sessionStorage.getItem('annex'))
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Información del anexo</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar las versiones de anexo</Alert>
                        </div> :
                            <div>
                                <h5 class="text-center" ><strong>{this.props.annexSelect.name}</strong></h5>
                                <h6 ><strong>Descripción</strong></h6>
                                <p>{this.props.annexSelect.description}</p>
                                <hr />
                                <AddVersion />
                                <br />
                                <br />
                                <MaterialTable
                                    title=""
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna version registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Descripción', field: 'description' },
                                        {
                                            title: 'Version', field: 'version',
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
                                    data={this.props.annexVersions}
                                    options={{
                                        search: true
                                    }}

                                />
                            </div>
                    }
                </div>
            </div>
        )
    }

}


function mapStateToProps(state) {
    return {
        annexID: state.annex.annexId,
        annexSelect: state.annex.annexR,
        annexVersions: state.annexVersion.listAnnexVersionR,
        enabled: state.annexVersion.stateAnnexVersion
    }
}

export default connect(mapStateToProps, { getAnnexVersions, getAnnexId })(versionAnnex)