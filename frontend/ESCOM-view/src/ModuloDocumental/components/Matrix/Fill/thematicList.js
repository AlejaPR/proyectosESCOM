import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import { confirmAlert } from 'react-confirm-alert';
import { getListProgramT, deleteProgramT, addMessageDelete } from '../../../redux/actions/programThematicA.js';
import AddProgramT from './addProgramT.js';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

import MaterialTable from 'material-table';
import DeleteForeverIcon from '@material-ui/icons/DeleteForever';
import VisibilityIcon from '@material-ui/icons/Visibility';

class ThematicList extends Component {

    componentWillMount() {
        this.props.getListProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
    }

    save(id) {
        sessionStorage.setItem('programT', id);
        this.props.history.push('/ThematicSelect');
    }

    componentDidUpdate() {
        if (this.props.messageDelete !== '') {
            switch (this.props.messageDelete) {
                case 'delete':
                    toast.success('Se elimino con exito.');
                    this.props.addMessageDelete('');
                    this.props.getListProgramT(localStorage.getItem('Token'), sessionStorage.getItem('programId'))
                    break;
                default:
                    break;
            }
        }
    }

    delete(idP) {
        confirmAlert({
            title: '',
            message: '¿Esta seguro?',
            buttons: [
                {
                    label: 'Si',
                    onClick: () => {
                        this.props.deleteProgramT(localStorage.getItem('Token'), idP);
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
                    <h4>Lista de temáticas del programa</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabled ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar los nucleos temáticos</Alert>
                        </div> :
                            <div>
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

                                        { title: 'Nombre del nucleo tematico', field: 'nameThematicCore' },
                                        { title: 'Objetivo', field: 'objetive' },
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
                                                        <a onClick={() => this.delete(rowData.id)}>
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
                    }
                </div>
                <br />
            </div >
        );
    }

}

function mapStateToProps(state) {
    return {
        listProgramT: state.programThematic.listProgramTR,
        messageDelete: state.programThematic.messageDelete,
        enabled: state.programThematic.stateProgramT
    }
}

export default withRouter(connect(mapStateToProps, { getListProgramT, deleteProgramT, addMessageDelete })(ThematicList));