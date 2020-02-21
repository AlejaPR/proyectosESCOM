import React from 'react';



import MaterialTable from 'material-table';

//componentes
import Barra from '../general/BarraDirecciones.js'
import PopUpActividad from '../popup/PopUpActividad.js'
import MTableToolbar from '../../utilitario/MTableToolbar.js';
import { NotificationManager } from 'react-notifications';
import { confirmAlert } from 'react-confirm-alert';
import Alert from '@material-ui/lab/Alert';
import AlertTitle from '@material-ui/lab/AlertTitle';

//redux conexion
import { connect } from 'react-redux';
import { actionConsultarActividades,actionSuspenderActivarActividad,actionAsignarCodigoActividad, actualizarMensajeSuspender } from '../../actions/actionActividad.js'
import { withRouter } from 'react-router-dom';

class ContenidoAdminActividad extends React.Component {


	componentDidUpdate() {
		if (this.props.mensajeSuspender !== '') {
			switch (this.props.mensajeSuspender) {
				case 'Sin permiso':
					NotificationManager.error('No tiene permisos para suspender/activar los usuarios');
					break;
				case 'Operacion hecha con exito':
					NotificationManager.success('Operacion realizada con exito');
					break;
				default:
					break;
			}
		}
	}


	activarDesactivarActividad(codigo) {
		confirmAlert({
			title: '',
			message: '¿Esta seguro?',
			buttons: [
				{
					label: 'Si',
					onClick: () => {
						this.props.actualizarMensajeSuspender('');
						this.props.actionSuspenderActivarActividad(codigo, localStorage.getItem('Token'), this.actualizarActividades(codigo));
					}
				},
				{
					label: 'No',
					onClick: () => NotificationManager.info('Se cancelo la operacion')
				}
			]
		});

	}

	actualizarActividades(codigoActividad) {
		let nuevo = [];
		this.props.actividades.forEach(function (task, index, array) {
			if (task.idActividad === codigoActividad) {
				if (task.estado === "Suspendido") {
					let actividad = {
						idActividad: task.idActividad,
						nombre: task.nombre,
						moduloActividad: task.moduloActividad,
						estado: "Activo"
					}
					nuevo.push(actividad);
				} else {
					let actividad = {
						idActividad: task.idActividad,

						nombre: task.nombre,
						moduloActividad: task.moduloActividad,
						estado: "Suspendido"
					}
					nuevo.push(actividad);
				}
			} else {
				nuevo.push(task);
			}
		});
		return nuevo;
	}


	componentDidMount() {
		this.props.actionConsultarActividades(localStorage.getItem('Token'));
	}

	render() {
		return (
			<div>
				<div class="text-left titulo" style={estiloLetrero}>
					<h4>Administrar actividades</h4>
				</div>
				<Barra texto="Inicio > Administración de actividades" />
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "40px",
					paddingBottom: "7px"
				}}>
				</div>
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "12px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
						<br />
						<div className="jumbotron p-1 jumbotron-fluid" style={{background:"white"}}>
							{
								this.props.habilitado ? <div className="col-sm-12">
									<Alert severity="error" variant="outlined">
										<AlertTitle>Sin permiso</AlertTitle>
										No tiene permisos suficientes para administrar las actividades</Alert>
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
												emptyDataSourceMessage: 'Aun no hay ningun usuario registrado'
											},
											toolbar: {
												searchTooltip: 'Buscar',
												searchPlaceholder: 'Buscar'
											}
										}}
										columns={[
											{ title: 'Nombre', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila },
											{ title: 'Modulo', field: 'moduloActividad', headerStyle: estiloCabecera, cellStyle: estiloFila },

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
												},
												headerStyle: estiloCabecera, cellStyle: estiloFila
											},
										]}
										data={this.props.actividades}
										options={{
											search: true,
											rowStyle: estiloFila

										}}
										actions={[
											{
												icon: 'edit',
												tooltip: 'Editar informacion',
												onClick: (event, rowData) => {
													this.props.actionAsignarCodigoActividad(rowData.idActividad);
													this.props.history.push('/editarActividad');
												}
											},
											{
												icon: 'restore',
												tooltip: 'Suspender / Activar',
												onClick: (event, rowData) => this.activarDesactivarActividad(rowData.idActividad)
											}
										]}

										components={{
											Toolbar: props => (
												<div className="row">
													<div className="col-sm-4">
														<div style={{ padding: '16px' }}>
															<PopUpActividad />
														</div>
													</div>
													<div className="col-sm-8">
														<MTableToolbar {...props} />
													</div>
												</div>
											),
										}}

									/>
							}
						</div>
					</div>
				</div>
			</div>
		);
	}


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

const estiloLetrero = {
	paddingTop: "20px",
	paddingRight: "12px",
	paddingLeft: "40px",
	paddingBottom: "1px"
}


function mapStateToProps(state) {
	return {
		actividades: state.act.actividadesRegistradas,
		habilitado: state.act.estadoActividades,
		configuracion: state.conf.configuracion,
		codigoActividad:state.act.codigoActividad,
		mensajeSuspender:state.act.mensajeSuspender
	}
}


export default withRouter(connect(mapStateToProps, { actionConsultarActividades, actionSuspenderActivarActividad,actionAsignarCodigoActividad, actualizarMensajeSuspender })(ContenidoAdminActividad));
