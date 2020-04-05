import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListPrograms, getProgramId } from '../../../redux/actions/programA.js';
import { ToastContainer } from 'react-toastify';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';

import MaterialTable from 'material-table';

import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';


class ListPrograms extends Component {

    componentWillMount() {
        this.props.getListPrograms(localStorage.getItem('Token'))
    }

    save(id) {
        this.props.getProgramId(localStorage.getItem('Token'), id)
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Lista de programas</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    <div>
                        <Add />
                    </div>
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
                                emptyDataSourceMessage: 'Aun no hay ningun programa registrado'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre del programa', field: 'name' },
                            { title: 'Nivel educativo', field: 'levelEducation' },
                            {
                                title: '', field: 'id',
                                render: rowData => {
                                    return (
                                        <div>
                                            <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                <VisibilityIcon />
                                            </a>
                                            <View />
                                        </div>
                                    )
                                }
                            },
                            {
                                title: '', field: 'id',
                                render: rowData => {
                                    return (
                                        <div>
                                            <a onClick={() => this.save(rowData.id)} data-toggle="modal" data-target="#editModal">
                                                <EditIcon />
                                            </a>
                                            <Edit />
                                        </div>
                                    )
                                }
                            }                        

                        ]}
                        data={this.props.listProgram}
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
        listProgram: state.program.listProgramR,
        messageDisablePr: state.program.messageDisable

    }
}

export default withRouter(connect(mapStateToProps, { getListPrograms, getProgramId })(ListPrograms));
