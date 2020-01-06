import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'


//componentes
import Barra from '../general/BarraDirecciones.js'
import PopUpUsuario from '../popup/PopUpUsuario.js'
import Fila from '../general/FilaTablaUsuario.js'
import { Button } from 'reactstrap';
import MaterialTable from 'material-table';
import MTableToolbar from '../../utilitario/MTableToolbar.js';

//redux conexion
import { connect } from 'react-redux';
import { actionConsultarUsuarios } from '../../actions/actionsUsuario.js'

class ContenidoAdminUsuario extends React.Component {


	componentDidMount() {
		this.props.actionConsultarUsuarios(localStorage.getItem('Token'));
	}
	componentWillMount() {
	}

	renderTableData() {
		return this.props.usuarios.map((usuario, index) => {
			const { cedula } = usuario //destructuring
			return (
				<Fila usuario={usuario} cambiar={this.props.cambiar} key={cedula} />
			)
		})

	}

	render() {
		return (
			<div>
				<div className="text-left titulo" style={estiloLetrero}>
					<h4>Administrar usuarios</h4>
				</div>
				<Barra texto="Inicio > Administración de usuarios" />
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "40px",
					paddingBottom: "7px",
				}}>
				</div>
				<div className="container" style={{
					paddingTop: "7px",
					paddingRight: "12px",
					paddingLeft: "40px",
					paddingBottom: "20px",
					margin: "0px 0px 32px"
				}}>
					<div className="container shadow" style={fondoBarraSuperior}>
						<div>
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
										title: 'Numero de identificacion', field: 'numeroDocumento', type: 'numeric',
										headerStyle: estiloCabecera,
										cellStyle: estiloFila
									},
									{ title: 'Nombre', field: 'nombre', headerStyle: estiloCabecera, cellStyle: estiloFila },
									{ title: 'Correo electronico', field: 'correoElectronico', headerStyle: estiloCabecera, cellStyle: estiloFila },
									{ title: 'Estado', field: 'estado', headerStyle: estiloCabecera, cellStyle: estiloFila },
								]}
								data={this.props.usuarios}
								options={{
									search: true,
									rowStyle: estiloFila

								}}
								actions={[
									{
										icon: 'edit',
										tooltip: 'Editar informacion',
										onClick: (event, rowData) => alert("You saved " + rowData.numeroDocumento)
									},
									{
										icon: 'restore',
										tooltip: 'Suspender / Activar',
										onClick: (event, rowData) => alert("You saved " + rowData.numeroDocumento)
									},
									{
										icon: 'assignmentInd',
										tooltip: 'Asignar actividad',
										onClick: (event, rowData) => alert("You saved " + rowData.numeroDocumento)
									}
								]}

								components={{
									Toolbar: props => (
										<div className="row">
											<div className="col-sm-4">
												<div style={{padding:'16px'}}>
												<PopUpUsuario funcion={this.anadirTarea} />
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

const fondoBoton = {
	background: "#ec671d",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

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

const fondoBarraSuperior = {
	background: "#FFFFFF",
	padding: '30px'
}

const fondoTabla = {
	background: "#EAF2F2"
}

function mapStateToProps(state) {
	return {
		usuarios: state.user.usuariosRegistrados
	}
}


export default connect(mapStateToProps, { actionConsultarUsuarios })(ContenidoAdminUsuario);