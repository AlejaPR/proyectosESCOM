import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListGeneralPro, getGeneralPro, addMessageAdd, addMessageDisable, addMessageEdit, disableGeneralPro } from '../../../redux/actions/generalProgramA.js';
import { ToastContainer, toast } from 'react-toastify';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { confirmAlert } from 'react-confirm-alert';

import Add from './add.js';
import Edit from './edit.js';

import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ListGeneral extends Component {

    componentDidUpdate() {
        if (this.props.messageEditG !== '') {
            switch (this.props.messageEditG) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListGeneralPro(localStorage.getItem('Token'))
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para editar este elemento.');
                    this.props.addMessageEdit('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageEdit('');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddG !== '') {
            switch (this.props.messageAddG) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListGeneralPro(localStorage.getItem('Token'))
                    this.props.addMessageAdd('')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para agregar este elemento.');
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageAdd('')
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableG !== '') {
            switch (this.props.messageDisableG) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.getListGeneralPro(localStorage.getItem('Token'))
                    this.props.addMessageDisable('')
                    break;
                case 'Sin persimo':
                    toast.error('No tiene permiso para inhabilitar este elemento.');
                    this.props.addMessageDisable('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDisable('')
                    break;
                default:
                    break;
            }
        }
    }


    componentDidMount() {
        this.props.getListGeneralPro(localStorage.getItem('Token'))
    }


    saveEdit(id) {
        this.props.getGeneralPro(localStorage.getItem('Token'), id)
    }

    disable(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        let generalN = {
                            id: id,
                            description: "",
                            name: "",
                            requestData: null
                        }
                        this.props.disableGeneralPro(localStorage.getItem('Token'), generalN)
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
                    <h4>Lista de facultades</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar las condiciones</Alert>
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna facultad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Nombre de la facultad', field: 'name' },
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
                                    data={this.props.listGeneralPro}
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
        listGeneralPro: state.generalProgram.listGeneralPro,
        messageEditG: state.generalProgram.messageEdit,
        messageAddG: state.generalProgram.messageAdd,
        messageDisableG: state.generalProgram.messageDisable,
        enabled: state.generalProgram.stateGeneralProgram
    }
}

export default withRouter(connect(mapStateToProps, { getListGeneralPro, getGeneralPro, addMessageEdit, addMessageAdd, addMessageDisable, disableGeneralPro })(ListGeneral));