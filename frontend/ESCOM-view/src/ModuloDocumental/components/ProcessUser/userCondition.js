import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getConditionsUser } from '../../redux/actions/userConditionA.js';

import MaterialTable from 'material-table';
import VisibilityIcon from '@material-ui/icons/Visibility';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

class UserCondition extends Component {

    componentWillMount() {
        this.props.getConditionsUser(localStorage.getItem('Token'), sessionStorage.getItem('documentId'));
    }

    save(id) {
        sessionStorage.setItem('condition', id)
        this.props.history.push('/UserActivity')
    }

    render() {
        return (
            <div className="container" style={{ width: "90%" }}>
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
                                        title: 'Proceso', field: 'percentage',
                                        render: rowData => {
                                            return (
                                                <div className="progress">
                                                    <div className="progress-bar" style={bar(rowData.percentage)} role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100">{rowData.percentage}%</div>
                                                </div>
                                            )
                                        }
                                    },
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
                                    }

                                ]}
                                data={this.props.conditions}
                                options={{
                                    search: true
                                }}

                            />
                    }
                </div>
            </div>
        )
    }

}

function bar(value) {
    return {
        width: value + "%"
    }

}

function mapStateToProps(state) {
    return {
        conditions: state.userCondition.listConditionsUserR,
        enabled: state.userCondition.stateConditionsUser
    }
}


export default withRouter(connect(mapStateToProps, { getConditionsUser })(UserCondition));