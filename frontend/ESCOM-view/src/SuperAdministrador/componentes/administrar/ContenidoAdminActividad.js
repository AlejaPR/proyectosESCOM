import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';

import MaterialTable from 'material-table';

//componentes
import Barra from '../general/BarraDirecciones.js'
import PopUpActividad from '../popup/PopUpActividad.js'
import Fila from '../general/FilaTablaUsuario.js'
import MTableToolbar from '../../utilitario/MTableToolbar.js';

//redux conexion
import { connect } from 'react-redux';
import { actionConsultarActividades } from '../../actions/actionActividad.js'
import { withRouter } from 'react-router-dom';

class ContenidoAdminActividad extends React.Component {

	state = {
		post: []
	}

	eventoBorrado = (evento) => {
		evento.preventDefault();
		console.log(this.state.post)
	}

	componentDidUpdate(){
		console.log('Actividades son',this.props.actividades);
	}

	onChange = (evento) => {
		this.setState({
			[evento.target.name]: evento.target.value
		});
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
					<div className="input-group">
						<input type="text" style={{ fontSize: "14px" }} className="form-control" placeholder="" />
						<span className="input-group-btn">
							<Button className="" style={fondoBoton}>Buscar</Button>
						</span>
					</div>
				</div>
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "12px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={fondoBarraSuperior}>
						<br />
						<div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
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
									{
										title: 'Codigo de actividad', field: 'idActividad', type: 'numeric',
										headerStyle: estiloCabecera,
										cellStyle: estiloFila
									},
									{ title: 'Nombre', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila },
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
											this.props.actionAsignarCedula(rowData.numeroDocumento);
											this.props.history.push('/editarUsuario');
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
											this.props.actionAsignarCedula(rowData.numeroDocumento);
											this.props.history.push('/asignarActividadUsuario')
										}
									}
								]}

								components={{
									Toolbar: props => (
										<div className="row">
											<div className="col-sm-4">
												<div style={{ padding: '16px' }}>
													<PopUpActividad/>
												</div>
											</div>
											<div className="col-sm-8">
												<MTableToolbar {...props} />
											</div>
										</div>
									),
								}}

							/>
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


const fondoBoton = {
	background: "#ec671d",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}

const estiloLetrero = {
	paddingTop: "20px",
	paddingRight: "12px",
	paddingLeft: "40px",
	paddingBottom: "1px"
}

const fondoBarraSuperior = {
	background: "#FFFFFF"

}

const fondoTabla = {
	background: "#EAF2F2"
}

function mapStateToProps(state) {
	return {
		actividades: state.act.actividadesRegistradas
	}
}


export default withRouter(connect(mapStateToProps, { actionConsultarActividades })(ContenidoAdminActividad));
