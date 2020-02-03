import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import 'react-notifications/lib/notifications.css';

//componentes
import Barra from '../general/BarraDirecciones.js'
import PopUpModulo from '../popup/PopUpModulo.js'
import MaterialTable from 'material-table';
import MTableToolbar from '../../utilitario/MTableToolbar.js';
import { confirmAlert } from 'react-confirm-alert';
import Alerta from '@icons/material/AlertIcon.js';
import { NotificationManager } from 'react-notifications';

import { campo } from '../../utilitario/GenerarInputs.js'
//redux conexion
import { connect } from 'react-redux';
import { actionConsultarModulos, actionAsignarModulo, actionSuspenderActivarModulo, actualizarMensajeSuspenderModulo } from '../../actions/actionsModulo.js';
import { withRouter } from 'react-router-dom';

class ContenidoAdminModulo extends React.Component {

	state = {
		codigoModulo: 0
	}

	actualizarModulos(idModulo) {
		let nuevo = [];
		this.props.modulosRegistrados.map(function (task, index, array) {
			if (task.idModulo === idModulo) {
				if (task.estadoModulo === "Suspendido") {
					let modulo = {
						idModulo: task.idModulo,
						estadoModulo: "Activo",
						nombreModulo: task.nombreModulo,
						descripcionModulo: task.descripcionModulo,
						acronimo: task.acronimo
					}
					nuevo.push(modulo);
				} else {
					let modulo = {
						nombreModulo: task.nombreModulo,
						idModulo: task.idModulo,
						descripcionModulo: task.descripcionModulo,
						estadoModulo: "Suspendido",
						acronimo: task.acronimo
					}
					nuevo.push(modulo);
				}
			} else {
				nuevo.push(task);
			}
			return nuevo;
		});
	}

	componentWillMount() {
		this.props.actionConsultarModulos(localStorage.getItem('Token'));
		console.log('modulos registrados son', this.props.modulosRegistrados)
	}

	componentDidUpdate() {
		console.log('msg suspender', this.props.modulosRegistrados);
		if (this.props.mensajeSuspender !== '') {
			switch (this.props.mensajeSuspender) {
				case 'Sin permiso':
					NotificationManager.warning('No tiene permisos para suspender/activar los usuarios')
					break;
				case 'Operacion hecha con exito':
					NotificationManager.info('Operacion realizada con exito')
					break;
				default:
					break;
			}
		}
	}



	activarDesactivarModulo(codigoModulo) {
		confirmAlert({
			title: '',
			message: '¿Esta seguro?',
			buttons: [
				{
					label: 'Si',
					onClick: () => {
						this.props.actionSuspenderActivarModulo(codigoModulo, localStorage.getItem('Token'), this.actualizarModulos(codigoModulo));
					}
				},
				{
					label: 'No',
					onClick: () => NotificationManager.info('Se cancelo la operacion')
				}
			]
		});

	}

	render() {
		return (
			<div>
				<div class="text-left titulo" style={estiloLetrero}>
					<h4>Administrar modulos</h4>
				</div>
				<Barra texto="Inicio > Administración de modulos" />
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "40px",
					paddingBottom: "7px"
				}}>
				</div>
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "44px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={{ background: "#FFFFFF", padding: "30px" }}>
						{
							this.props.habilitado ? <div className="col-sm-12"> <span className="col-sm-2 center" style={{
								textShadow: "none!important",
								fontSize: "16px",
								fontFamily: "Open Sans,sans-serif",
								fontWeight: "300",
								padding: "13px 248px",
								color: "#fff",
								background: "rgb(158, 35, 45)"
							}}><Alerta />No tiene los permisos suficientes para administrar los usuarios</span></div> :
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
											emptyDataSourceMessage: 'Aun no hay ningun modulo registrado'
										},
										toolbar: {
											searchTooltip: 'Buscar',
											searchPlaceholder: 'Buscar'
										}
									}}
									columns={[
										{ title: '', field: 'imagenModulo', render: rowData => { return <img src={campo(rowData.imagenModulo)} alt='' style={{ width: 40, borderRadius: '50%' }} /> } },
										{ title: 'Nombre de modulo', field: 'nombreModulo', headerStyle: estiloCabecera, cellStyle: estiloFila },
										{ title: 'Descripcion del modulo', field: 'descripcionModulo', headerStyle: estiloCabecera, cellStyle: estiloFila },
										{
											title: 'Estado', field: 'estadoModulo',
											render: rowData => {
												if (rowData.estadoModulo === 'Suspendido') {
													return <span className="label label-sm letra"
														style={{
															textShadow: "none!important",
															fontSize: "12px",
															fontFamily: "Open Sans,sans-serif",
															fontWeight: "300",
															padding: "3px 6px",
															color: "#fff",
															background: "#ED6B75"
														}}>{rowData.estadoModulo}</span>
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
														}}>{rowData.estadoModulo}</span>
												}
											},
											headerStyle: estiloCabecera, cellStyle: estiloFila
										},
									]}
									data={this.props.modulosRegistrados}
									options={{
										search: true,
										rowStyle: estiloFila

									}}
									actions={[
										{
											icon: 'edit',
											tooltip: 'Editar informacion',
											onClick: (event, rowData) => {
												this.props.actionAsignarModulo(rowData.idModulo);
												this.props.history.push('/editarModulo');
											}
										},
										{
											icon: 'restore',
											tooltip: 'Suspender / Activar',
											onClick: (event, rowData) => this.activarDesactivarModulo(rowData.idModulo)
										},
										{
											icon: 'assignmentInd',
											tooltip: 'Asignar actividad',
											onClick: (event, rowData) => {
												this.props.actionAsignarModulo(rowData.idModulo);
												this.props.history.push('/asignarActividadModulo')
											}
										}
									]}

									components={{
										Toolbar: props => (
											<div className="row">
												<div className="col-sm-4">
													<div style={{ padding: '16px' }}>
														<PopUpModulo />
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
		modulosRegistrados: state.mod.modulosRegistrados,
		habilitado: state.mod.estadoModulos,
		codigoModulo: state.mod.codigoModulo,
		mensajeSuspender: state.mod.mensajeSuspenderModulo
	}
}

export default withRouter(connect(mapStateToProps, { actionConsultarModulos, actionAsignarModulo, actionSuspenderActivarModulo, actualizarMensajeSuspenderModulo })(ContenidoAdminModulo));

