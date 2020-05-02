import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListProcesses, getProcessId, addMessageEdit, addMessageAdd, addMessageDisable, disableProcess } from '../../../redux/actions/processA.js';
import { ToastContainer, toast } from 'react-toastify';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { confirmAlert } from 'react-confirm-alert';

import Add from './add.js';
import Edit from './edit.js';

import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ListProcess extends Component {

    componentDidUpdate() {
        if (this.props.messageEditP !== '') {
            switch (this.props.messageEditP) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
                    break;
                case 'Sin permiso':
                    toast.success('No tiene permisos suficientes para editar.');
                    this.props.addMessageEdit('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddP !== '') {
            switch (this.props.messageAddP) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
                    this.props.addMessageAdd('')
                    break;
                case 'Sin permiso':
                    toast.success('No tiene permisos suficientes para agregar un nuevo elemento.');
                    this.props.addMessageAdd('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableP !== '') {
            switch (this.props.messageDisableP) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
                    this.props.addMessageDisable('')
                    break;
                case 'Sin permiso':
                    toast.success('No tiene permisos suficientes para inhabilitar.');
                    this.props.addMessageDisable('');
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
    }


    componentWillMount() {
        if (sessionStorage.getItem('documentId') <= 0) {
            this.props.history.push('/')
        }
        this.props.getListProcesses(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }


    saveEdit(id) {
        this.props.getProcessId(localStorage.getItem('Token'), id)
    }

    disable(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.disableProcess(localStorage.getItem('Token'), id)
                    }
                },
                {
                    label: 'No',
                    onClick: () => { }
                }
            ]
        });
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Lista de procesos</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                                No tiene permisos suficientes para listar los procesos.</Alert>
                        </div> :
                            <div>
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
                                            emptyDataSourceMessage: 'Aun no hay ningun proceso registrado'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Nombre del proceso', field: 'name' },
                                        { title: 'Descripción', field: 'description' },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.saveEdit(rowData.id)} data-toggle="modal" data-target="#editModal">
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
                                    data={this.props.listProcess}
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
        listProcess: state.process.listProcessR,
        messageEditP: state.process.messageEdit,
        messageAddP: state.process.messageAdd,
        messageDisableP: state.process.messageDisable,
        enabled: state.process.stateProcess
    }
}

export default withRouter(connect(mapStateToProps, { getListProcesses, getProcessId, addMessageEdit, addMessageAdd, addMessageDisable, disableProcess })(ListProcess));