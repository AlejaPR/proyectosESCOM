import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';


//componentes
import Barra from '../general/BarraDirecciones.js'
import PopUpModulo from '../popup/PopUpModulo.js'
import Fila from '../general/FilaTablaUsuario.js'
import MaterialTable from 'material-table';
import MTableToolbar from '../../utilitario/MTableToolbar.js';
import { confirmAlert } from 'react-confirm-alert';
import Alerta from '@icons/material/AlertIcon.js';
import { NotificationManager } from 'react-notifications';

//redux conexion
import { connect } from 'react-redux';
import { actionConsultarModulos ,actionAsignarModulo} from '../../actions/actionsModulo.js';
import { withRouter } from 'react-router-dom';

class ContenidoAdminModulo extends React.Component {

	eventoBorrado = (evento) => {
		evento.preventDefault();
		console.log(this.state.post)
	}

	componentWillMount(){
		this.props.actionConsultarModulos(localStorage.getItem('Token'));
	}

	onChange = (evento) => {
		this.setState({
			[evento.target.name]: evento.target.value
		});
	}

	 componentDidMount() {
		
	}


	renderTableData() {
		return this.state.post.map((post, index) => {
			const { id } = post //destructuring
			return (
				<Fila modulo={post} key={id} />
			)
		})

	}

	anadirModulo = (logo, nombremodulo, descripcion) => {
		const nuevoModulo = {
			logo: logo,
			nombremodulo: nombremodulo,
			descripcion: descripcion
		}
		this.setState({
			post: [...this.state.post, nuevoModulo]
		})
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
					<div className="container shadow" style={{background: "#FFFFFF",padding:"30px"}}>
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
								{
									title: 'Codigo de modulo', field: 'idModulo', type: 'numeric',
									headerStyle: estiloCabecera,
									cellStyle: estiloFila
								},
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
									onClick: (event, rowData) => this.activarDesactivarUsuario(rowData.numeroDocumento)
								},
								{
									icon: 'assignmentInd',
									tooltip: 'Asignar actividad',
									onClick: (event, rowData) => {
										this.props.history.push('/asignarActividadUsuario')
									}
								}
							]}

							components={{
								Toolbar: props => (
									<div className="row">
										<div className="col-sm-4">
											<div style={{ padding: '16px' }}>
												<PopUpModulo/>
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
		modulosRegistrados:state.mod.modulosRegistrados,
		habilitado: state.mod.estadoModulos,
		codigoModulo:state.mod.codigoModulo
	}
}

export default withRouter(connect(mapStateToProps, {actionConsultarModulos,actionAsignarModulo})(ContenidoAdminModulo));

