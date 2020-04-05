import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListPrograms } from '../../../redux/actions/programA.js';
import { getListAnnexes, searchAnnexS } from '../../../redux/actions/annexA.js';
import { reduxForm, Field } from 'redux-form';
import { Link, withRouter } from 'react-router-dom';

import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class searchAnnexs extends Component {

    componentWillMount() {
        this.props.getListPrograms(localStorage.getItem('Token'))
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
                </div>
                <br />
            </div >
        )
    }

}


function mapStateToProps(state) {
    return {
        programs: state.program.listProgramR,
        annexes: state.annex.listAnnexR
    }
}

let formSearch = reduxForm({
    form: 'searchAnnex',
    enableReinitialize: true
})(searchAnnexs)
export default withRouter(connect(mapStateToProps, { getListPrograms, getListAnnexes, searchAnnexS })(formSearch));