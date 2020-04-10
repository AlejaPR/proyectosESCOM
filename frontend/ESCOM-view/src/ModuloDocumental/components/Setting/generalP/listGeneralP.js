import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListGeneralPro, getGeneralPro, addMessageAdd, addMessageDisable, addMessageEdit, disableGeneralPro } from '../../../redux/actions/generalProgramA.js';
import { ToastContainer, toast } from 'react-toastify';

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
                case 'error server':
                    toast.error('Se presento un error, intentelo mas tarde.');
                    this.props.addMessageDisable('')
                    break;
                default:
                    break;
            }
        }
    }


    componentDidMountMount() {
        this.props.getListGeneralPro(localStorage.getItem('Token'))
    }


    saveEdit(id) {
        this.props.getGeneralPro(localStorage.getItem('Token'), id)
    }

    disable(id) {
        let generalN = {
            id: id,
            description: "",
            name: "",
            requestData: null
        }
        this.props.disableGeneralPro(localStorage.getItem('Token'), generalN)
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
                <ToastContainer />
                <div className="text-left titulo">
                    <h4>Lista de programas generales</h4>
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
                                emptyDataSourceMessage: 'Aun no hay ningun rpgrama registrado'
                            },
                            toolbar: {
                                searchTooltip: 'Buscar',
                                searchPlaceholder: 'Buscar'
                            }
                        }}
                        columns={[

                            { title: 'Nombre del programa', field: 'name' },
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
            </div>
        )
    }

}

function mapStateToProps(state) {
    return {
        listGeneralPro: state.generalProgram.listGeneralPro,
        messageEditG: state.generalProgram.messageEdit,
        messageAddG: state.generalProgram.messageAdd,
        messageDisableG: state.generalProgram.messageDisable
    }
}

export default withRouter(connect(mapStateToProps, { getListGeneralPro,getGeneralPro, addMessageEdit, addMessageAdd, addMessageDisable, disableGeneralPro })(ListGeneral));