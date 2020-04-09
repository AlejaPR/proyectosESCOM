import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import { getListProgramT, deleteProgramT, addMessageDelete } from '../../../redux/actions/programThematicA.js';
import AddProgramT from './addProgramT.js';

import MaterialTable from 'material-table';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import VisibilityIcon from '@material-ui/icons/Visibility';

class ThematicList extends Component {

    componentWillMount() {
        this.props.getListProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {

    }

    save(id) {
        sessionStorage.setItem('programT', id);
        this.props.history.push('/ThematicSelect');
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Lista de temáticas del programa</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    <AddProgramT />
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
                                emptyDataSourceMessage: 'Aun no hay ninguna temática registrada'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre del nucleo thematico', field: 'nameThematicCore' },
                            { title: 'Objetico', field: 'objetive' },
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
                                title: '', field: 'id',
                                render: rowData => {
                                    return (
                                        <div>
                                            <a onClick={() => this.disable(rowData.id)}>
                                                <DeleteForeverIcon />
                                            </a>
                                        </div>
                                    )
                                }
                            }

                        ]}
                        data={this.props.listProgramT}
                        options={{
                            search: true
                        }}

                    />
                </div>
                <br />
            </div>
        );
    }

}

function mapStateToProps(state) {
    return {
        listProgramT: state.programThematic.listProgramTR,
        messageDelete: state.programThematic.messageDelete
    }
}

export default withRouter(connect(mapStateToProps, { getListProgramT, deleteProgramT, addMessageDelete })(ThematicList));