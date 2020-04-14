import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListPrograms, getProgramId, disableProgram, addMessageDisable } from '../../../redux/actions/programA.js';
import { ToastContainer, toast } from 'react-toastify';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';

import MaterialTable from 'material-table';

import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ListPrograms extends Component {

    componentDidMount() {
        this.props.getListPrograms(localStorage.getItem('Token'))
    }

    componentDidUpdate() {
        if (this.props.messageDisableP !== '') {
            switch (this.props.messageDisableP) {
                case 'disable':
                    toast.success('Se inhabilito con éxito.');
                    this.props.addMessageDisable('');
                    this.props.getListPrograms(localStorage.getItem('Token'));
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDisable('');
                    break;
                default:
                    break;
            }
        }
    }

    save(id) {
        this.props.getProgramId(localStorage.getItem('Token'), id)
    }

    disable(id) {
        this.props.disableProgram(localStorage.getItem('Token'), id)
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
        messageDisableP: state.program.messageDisable

    }
}

export default withRouter(connect(mapStateToProps, { getListPrograms, getProgramId, disableProgram, addMessageDisable })(ListPrograms));
