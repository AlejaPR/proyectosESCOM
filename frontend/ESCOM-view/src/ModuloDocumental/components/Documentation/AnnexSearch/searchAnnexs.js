import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListAnnexes, searchAnnexS } from '../../../redux/actions/annexA.js';
import { Link, withRouter } from 'react-router-dom';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import MaterialTable from 'material-table';
import VisibilityIcon from '@material-ui/icons/Visibility';

class searchAnnexs extends Component {

    componentDidMount() {
        this.props.getListAnnexes(localStorage.getItem('Token'), 0)
    }

    save(id) {
        sessionStorage.setItem('annex', id)
        this.props.history.push('/VersionAnnex')
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Búsqueda de anexos</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar los anexos</Alert>
                        </div> :
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
                                        emptyDataSourceMessage: 'Aun no hay ningun documento registrado'
                                    },
                                    toolbar: {
                                        searchTooltip: 'Buscar',
                                        searchPlaceholder: 'Buscar'
                                    }
                                }}
                                columns={[
                                    { title: 'Nombre del anexo', field: 'name' },
                                    { title: 'Programa', field: 'nameProgram' },
                                    { title: 'Palabras clave', field: 'keywords' },
                                    {
                                        title: '', field: 'id',
                                        render: rowData => {
                                            return (
                                                <div>
                                                    <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                        <VisibilityIcon />
                                                    </a>
                                                </div>
                                            )
                                        }
                                    },
                                    {
                                        title: '', field: 'link',
                                        render: rowData => {
                                            if (rowData.link !== null) {
                                                return <Link to={'/' + rowData.link} target="_blank" download><i class="fas fa-download"></i></Link>
                                            } else {
                                                return ''
                                            }
                                        }
                                    }
                                ]}
                                data={this.props.annexes}
                                options={{
                                    search: true
                                }}

                            />
                    }
                </div>
                <br />
            </div >
        )
    }

}


function mapStateToProps(state) {
    return {
        annexes: state.annex.listAnnexR,
        enabled: state.annex.stateAnnex
    }
}

export default withRouter(connect(mapStateToProps, { getListAnnexes, searchAnnexS })(searchAnnexs));