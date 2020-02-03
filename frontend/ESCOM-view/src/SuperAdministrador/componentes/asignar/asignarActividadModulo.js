import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import { withRouter } from 'react-router-dom';
import MaterialTable from 'material-table';
import Divider from '@material-ui/core/Divider';
import Alerta from '@icons/material/AlertIcon.js';

//componentes
import Barra from '../general/BarraDirecciones.js';

//redux
import { actionConsultarActividadesModulo ,actionCambiarEstadoActividades,actualizarMensajeActividades} from '../../actions/actionsModulo'
import { connect } from 'react-redux';

class AsignarActividadModulo extends React.Component {

    state = {
        actividadesSeleccionadas: []
    }
    componentDidMount() {
        if (this.props.codigoModulo === undefined || this.props.codigoModulo.length === 0) {
            this.props.history.push('/adminModulo');
        } else {
            this.props.actionConsultarActividadesModulo(this.props.codigoModulo, localStorage.getItem('Token'));
        }
    }

    componentDidUpdate() {
        switch (this.props.mensaje) {
            case 'Operacion hecha con exito':
                NotificationManager.success('Hecho');
                this.props.actionConsultarActividadesModulo(this.props.codigoModulo, localStorage.getItem('Token'));
                this.props.actualizarMensajeActividades('');
                break;
            case 'Sin permiso':

                break;
            default:
                break;
        }
    }

    render() {
        return (
            <div>
                <div class="text-left titulo" style={estiloLetrero}>
                    <h4>Asignar actividad a modulo</h4>
                </div>
                <Barra texto="Inicio > Asignación de actividad a modulo" />



                <div className="col-sm-12" style={{
                    paddingTop: "7px",
                    paddingRight: "40px",
                    paddingLeft: "40px",
                    paddingBottom: "20px",
                    margin: "0px 0px 32px"

                }}>
                    <div className="container shadow" style={fondoBarraSuperior}>
                        <br />
                        <div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
                            <MaterialTable
                                title="Actividades actualmente asociadas al modulo"
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
                                        emptyDataSourceMessage: 'Aun no hay ningun modulo registrado'
                                    },
                                    toolbar: {
                                        searchTooltip: 'Buscar',
                                        searchPlaceholder: 'Buscar',
                                        nRowsSelected: '{0} actividades seleccionadas'
                                    }
                                }}
                                columns={[
                                    { title: 'Nombre de modulo', field: 'idActividad', headerStyle: estiloCabecera, cellStyle: estiloFila },
                                    { title: 'Descripcion del modulo', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila },
                                    {
                                        title: 'Estado', field: 'estado',
                                        render: rowData => {
                                            if (rowData.estado === 'Suspendido') {
                                                return <span className="label label-sm letra"
                                                    style={{
                                                        textShadow: "none!important",
                                                        fontSize: "12px",
                                                        fontFamily: "Open Sans,sans-serif",
                                                        fontWeight: "300",
                                                        padding: "3px 6px",
                                                        color: "#fff",
                                                        background: "#ED6B75"
                                                    }}>{rowData.estado}</span>
                                            } else {
                                                return <span className="label label-sm letra"
                                                    style={{
                                                        textShadow: "none!important",
                                                        fontSize: "12px",
                                                        fontFamily: "Open Sans,sans-serif",
                                                        fontWeight: "300",
                                                        padding: "3px 6px",
                                                        color: "#fff",
                                                        background: "#408725"
                                                    }}>{rowData.estado}</span>
                                            }
                                        }, headerStyle: estiloCabecera, cellStyle: estiloFila
                                    }

                                ]}
                                data={this.props.actividades}
                                options={{
                                    search: true,
                                    rowStyle: estiloFila,
                                    selection: true
                                }}
                                onSelectionChange={(rows) => {
                                    this.setState({ actividadesSeleccionadas: rows });
                                }}
                                actions={[
                                    {
                                        tooltip: 'Remove All Selected Users',
                                        icon: 'delete',
                                        onClick: (evt, data) => this.props.actionCambiarEstadoActividades(this.state.actividadesSeleccionadas,localStorage.getItem('Token'))
                                    }
                                ]}
                            />
                        </div>
                    </div>
                    <br />
                </div>
                <NotificationContainer/>
            </div>
        )
    }
}

const estiloTitulo = {

    paddingTop: "7px",
    paddingRight: "12px",
    paddingLeft: "5px",
    paddingBottom: "1px"
}

const estiloLetrero = {
    paddingTop: "20px",
    paddingRight: "12px",
    paddingLeft: "40px",
    paddingBottom: "1px"
}

const estiloCabecera = {
    fontSize: '13px',
    fontFamily: 'sans-serif',
    padding: '8px',
    background: '#e7ecf1'

}

const estiloFila = {
    fontSize: '12px',
    fontFamily: 'sans-serif',
    padding: '8px',
}

const fondoBarraSuperior = {
    background: "#FFFFFF"

}

const fondoTabla = {
    background: "#EAF2F2"
}


const fondoBoton = {
    background: "#ec671d",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"

}

const fondoBotonS = {
    background: "secondary",
    fontSize: "14px",
    fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
    return {
        codigoModulo: state.mod.codigoModulo,
        actividades: state.mod.actividadesModulos,
        mensaje: state.mod.mensajeActividadesModulo
    }
}

export default withRouter(connect(mapStateToProps, { actionConsultarActividadesModulo,actionCambiarEstadoActividades,actualizarMensajeActividades })(AsignarActividadModulo));

