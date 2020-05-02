import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListConditions, getConditionId, disableCondition, addMessageEdit, addMessageAdd, addMessageDisable } from '../../../redux/actions/conditionA.js';
import { ToastContainer, toast } from 'react-toastify';
import Add from './add.js';
import Edit from './edit.js';
import MaterialTable from 'material-table';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { confirmAlert } from 'react-confirm-alert';

import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class FormCondition extends Component {

    componentDidUpdate() {
        if (this.props.messageEditC !== '') {
            switch (this.props.messageEditC) {
                case 'edit':
                    toast.success('Se modifico con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
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
        if (this.props.messageAddC !== '') {
            switch (this.props.messageAddC) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageAdd('')
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
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
        if (this.props.messageDisableC !== '') {
            switch (this.props.messageDisableC) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.addMessageDisable('')
                    this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
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
        this.props.getListConditions(localStorage.getItem('Token'), sessionStorage.getItem('documentId'))
    }

    saveView(id) {
        sessionStorage.setItem('condition', id);
        this.props.history.push('/ViewCondition');
    }

    saveEdit(id) {
        this.props.getConditionId(localStorage.getItem('Token'), id)
    }

    disable(id) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.disableCondition(localStorage.getItem('Token'), id)
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
                    <h4>Lista de condiciones</h4>
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
                                            emptyDataSourceMessage: 'Aun no hay ningun documento registrado'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[

                                        { title: 'Nombre de la condición', field: 'name' },
                                        {
                                            title: 'Descripción', field: 'description',
                                            render: rowData => {
                                                if (rowData.description.length > 150) {
                                                    return rowData.description.substr(0, 150) + ' ...'
                                                } else {
                                                    return rowData.description
                                                }
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.saveView(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                if (rowData.name === 'General') {
                                                    return (<div></div>)
                                                } else {
                                                    return (
                                                        <div>
                                                            <a onClick={() => this.saveEdit(rowData.id)} data-toggle="modal" data-target="#editModal">
                                                                <EditIcon />
                                                            </a>
                                                            <Edit />
                                                        </div>
                                                    )
                                                }
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                if (rowData.name === 'General') {
                                                    return (<div></div>)
                                                } else {
                                                    return (
                                                        <div>
                                                            <a onClick={() => this.disable(rowData.id)}>
                                                                <DeleteForeverIcon />
                                                            </a>
                                                        </div>
                                                    )
                                                }
                                            }
                                        }

                                    ]}
                                    data={this.props.conditions}
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
        conditions: state.condition.listConditions,
        messageEditC: state.condition.messageEdit,
        messageAddC: state.condition.messageAdd,
        messageDisableC: state.condition.messageDisable,
        enabled: state.condition.stateCondition
    }
}

export default withRouter(connect(mapStateToProps, { getListConditions, addMessageDisable, getConditionId, disableCondition, addMessageEdit, addMessageAdd })(FormCondition))
