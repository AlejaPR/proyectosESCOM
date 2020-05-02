import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { getListAnnexes, getAnnexId, addMessageEdit, addMessageAdd, addMessageDisable, disableAnnex } from '../../../redux/actions/annexA.js';
import { withRouter } from 'react-router-dom';
import Add from './add.js';
import Edit from './edit.js';
import View from './view.js';
import { ToastContainer, toast } from 'react-toastify';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import { confirmAlert } from 'react-confirm-alert';


import MaterialTable from 'material-table';
import EditIcon from '@material-ui/icons/Edit';
import VisibilityIcon from '@material-ui/icons/Visibility';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';

class ListAnnex extends Component {


    componentDidMount() {
        this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    componentDidUpdate() {
        if (this.props.messageEditX !== '') {
            switch (this.props.messageEditX) {
                case 'edit':
                    toast.success('Se agrego con exito.');
                    this.props.addMessageEdit('');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'));
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para editar un elemento.');
                    this.props.addMessageEdit('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageAddX !== '') {
            switch (this.props.messageAddX) {
                case 'add':
                    toast.success('Se agrego con exito.');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageAdd('')
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para agregar un nuevo elemento.');
                    this.props.addMessageAdd('')
                    break;
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    break;
                default:
                    break;
            }
        }
        if (this.props.messageDisableX !== '') {
            switch (this.props.messageDisableX) {
                case 'disable':
                    toast.success('Se inhabilito con exito.');
                    this.props.getListAnnexes(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    this.props.addMessageDisable('')
                    break;
                case 'Sin permiso':
                    toast.error('No tiene permisos suficientes para inhabilitar un elemento.');
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

    save(id) {
        this.props.getAnnexId(localStorage.getItem('Token'), id);
    }

    disable(id) {
        confirmAlert({
			title: '',
			message: '¿Esta seguro?',
			buttons: [
				{
					label: 'Si',
					onClick: () => {
						this.props.disableAnnex(localStorage.getItem('Token'), id)						
					}
				},
				{
					label: 'No',
					onClick: () => {}
				}
			]
		});        
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Anexos del programa</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                        No tiene permisos suficientes para listar anexos</Alert>
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

                                        { title: 'Nombre del anexo', field: 'name' },
                                        { title: 'Descripción', field: 'description' },
                                        { title: 'Palabras clave', field: 'keywords' },
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
                                    data={this.props.annexes}
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
        annexes: state.annex.listAnnexR,
        messageEditX: state.annex.messageEdit,
        messageAddX: state.annex.messageAdd,
        messageDisableX: state.annex.messageDisable,
        enabled: state.annex.stateAnnex
    }
}

export default withRouter(connect(mapStateToProps, { disableAnnex, addMessageDisable, getListAnnexes, getAnnexId, addMessageEdit, addMessageAdd })(ListAnnex));