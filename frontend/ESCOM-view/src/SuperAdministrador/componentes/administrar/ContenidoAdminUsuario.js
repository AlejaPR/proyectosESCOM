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


//redux conexion
import { connect } from 'react-redux';
import { actionConsultarUsuarios } from '../../actions/actionsUsuario.js'

class ContenidoAdminUsuario extends React.Component {

	componentWillMount() {
		this.props.actionConsultarUsuarios();
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
					<div className="input-group">
						<input type="text" style={{ fontSize: "14px" }} className="form-control" placeholder="" />
						<span className="input-group-btn">
							<Button style={fondoBoton}>Buscar</Button>
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
						<PopUpUsuario funcion={this.anadirTarea} />
						<br />
						<div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
							<table className="table table-hover table-bordered table-checkable" style={fondoBarraSuperior}>
								<thead className="table table-hover table-striped col-md-12">
									<tr>
										<th className="letra"> Nombre </th>
										<th className="letra">Cedula </th>
										<th className="letra"> Correo </th>
										<th className="letra"> Estado </th>
										<th className="letra"> Acciones </th>
									</tr>
								</thead>
								<tbody>
									{
										this.renderTableData()
									}
								</tbody>
							</table>
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
        usuarios:state.user.usuariosRegistrados
    }
}


export default connect(mapStateToProps, { actionConsultarUsuarios })(ContenidoAdminUsuario);