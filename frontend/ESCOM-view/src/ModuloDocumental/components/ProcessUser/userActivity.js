import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { getListActivitiesInfo, getActivityId, getListActivitiesAnnex } from '../../redux/actions/activityA.js';
import { getConditionId } from '../../redux/actions/conditionA.js';

import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';
import MaterialTable from 'material-table';
import VisibilityIcon from '@material-ui/icons/Visibility';

class ProcessCondition extends Component {
    componentWillMount() {
        this.props.getConditionId(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesInfo(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
        this.props.getListActivitiesAnnex(localStorage.getItem('Token'), sessionStorage.getItem('condition'))
    }

    save(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/AddInformation')
    }
    saveAnnex(id) {
        sessionStorage.setItem('activity', id)
        this.props.history.push('/AddAnnex')
    }

    loadCondition() {
        return (
            <div className="pg">
                <h5 className="text-center"><strong>{this.props.conditionPro.name}</strong></h5>
                <h6><strong>Descripción:</strong></h6>
                <p>
                    {this.props.conditionPro.description}
                </p>
            </div>
        )
    }

    render() {
        return (
            <div className="container color" style={{ width: "90%" }}>
                <div className="text-left titulo">
                    <h4>Información de la condición</h4>
                </div>
                <br />
                <div className="shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
                    {
                        this.props.enabledI || this.props.enabledA ? <div className="col-sm-12">
                            <Alert severity="error" variant="outlined">
                                <AlertTitle>Sin permiso</AlertTitle>
                            No tiene permisos suficientes para listar las condiciones</Alert>
                        </div> :
                            <div>
                                {this.loadCondition()}
                                <hr />
                                <MaterialTable
                                    title="Lista actividades informativas"
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna actividad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[
                                        { title: '#', field: 'number' },
                                        { title: 'Nombre de la actividad', field: 'name' },
                                        {
                                            title: 'Estado', field: 'state',
                                            render: rowData => {
                                                if (rowData.state === 1) {
                                                    return 'Activo'
                                                } else if (rowData.state === 2) {
                                                    return 'Finalizado'
                                                } else {
                                                    return (<strong>Por aprobación</strong>)
                                                }
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
                                    data={this.props.listActivityInfo}
                                    options={{
                                        search: true
                                    }}

                                />
                                <hr />
                                <MaterialTable
                                    title="Lista de actividades de anexo"
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
                                            emptyDataSourceMessage: 'Aun no hay ninguna actividad registrada'
                                        },
                                        toolbar: {
                                            searchTooltip: 'Buscar',
                                            searchPlaceholder: 'Buscar'
                                        }
                                    }}
                                    columns={[
                                        { title: 'Nombre de la actividad', field: 'name' },
                                        { title: 'Descripción', field: 'description' },
                                        {
                                            title: 'Estado', field: 'state',
                                            render: rowData => {
                                                if (rowData.state === 1) {
                                                    return 'Activo'
                                                } else if (rowData.state === 2) {
                                                    return 'Finalizado'
                                                } else {
                                                    return (<strong>Por aprobación</strong>)
                                                }
                                            }
                                        },
                                        {
                                            title: '', field: 'id',
                                            render: rowData => {
                                                return (
                                                    <div>
                                                        <a onClick={() => this.saveAnnex(rowData.id)} data-toggle="modal" data-target="#viewModal">
                                                            <VisibilityIcon />
                                                        </a>
                                                    </div>
                                                )
                                            }
                                        }
                                    ]}
                                    data={this.props.listActivityAnnex}
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
        conditionPro: state.condition.conditionR,
        listActivityInfo: state.activity.listActivityInfoR,
        listActivityAnnex: state.activity.listActivityAnnexR,
        enabledI: state.activity.stateActivityList,
        enabledA: state.activity.stateActivityAnnex
    }
}

export default withRouter(connect(mapStateToProps, { getConditionId, getListActivitiesInfo, getListActivitiesAnnex, getActivityId })(ProcessCondition));