import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListDocuments, getDocumentIdEdit, addMessageEdit, addMessageAdd, addMessageDisable, disableDocument } from '../../../redux/actions/documentA.js';
import { ToastContainer, toast } from 'react-toastify';
import { confirmAlert } from 'react-confirm-alert';

import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';

import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

class ListDocument extends Component {

    componentDidUpdate() {
        if (this.props.messageEditD !== '') {
            switch (this.props.messageEditD) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    this.props.addMessageEdit('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para editar este elemento.');
                    this.props.addMessageEdit('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddD !== '') {
            switch (this.props.messageAddD) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    this.props.addMessageAdd('');
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableD !== '') {
            switch (this.props.messageDisableD) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDisable('');
                    this.props.getListDocuments(localStorage.getItem('Token'));
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para inhabilitar este elemento.');
                    this.props.addMessageDisable('')
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
        this.props.getListDocuments(localStorage.getItem('Token'))
    }

    save(id) {
        this.props.getDocumentIdEdit(localStorage.getItem('Token'), id)
    }

    disable(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.disableDocument(localStorage.getItem('Token'), id)
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
                <div className="text-left titulo">
                    <h4>Lista documentos maestros</h4>
                </div>
                <ToastContainer />
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar los documentos</Alert>
                        </div> :
                            <div>
                                <Add />
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
                                            emptyDataSourceMessage: 'Aun no hay ningun documento registrado'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Nombre del documento', field: 'program' },
                                        { title: 'Descripción', field: 'description' },
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
                                    data={this.props.listDocument}
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
        listDocument: state.document.listDocumentR,
        messageEditD: state.document.messageEdit,
        messageAddD: state.document.messageAdd,
        messageDisableD: state.document.messageDisable,
        enabled: state.document.stateDocument
    }
}

export default withRouter(connect(mapStateToProps, { getListDocuments, getDocumentIdEdit, disableDocument, addMessageEdit, addMessageAdd, addMessageDisable })(ListDocument));