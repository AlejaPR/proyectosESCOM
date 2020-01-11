import React from 'react';
import { confirmAlert } from 'react-confirm-alert'; // Import
import { NotificationContainer, NotificationManager } from 'react-notifications';

import CreateTwoToneIcon from '@icons/material/AlertIcon.js';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import 'react-confirm-alert/src/react-confirm-alert.css';

//redux
import { actionAsignarCedula, actionActualizarUsuarios } from '../../actions/actionsUsuario.js';
import { connect } from "react-redux";
import { Button } from 'reactstrap';

class FilaTablaUsuario extends React.Component {
	eventoBorrado = (evento) => {
		if (evento.target.value === '1') {
			this.props.cambiar('e');
			this.props.actionAsignarCedula(this.props.usuario.cedula);
		}

		if (evento.target.value === '2') {
			console.log('llego aqui seleccion dos');
			confirmAlert({
				title: '',
				message: '¿Esta seguro?',
				buttons: [
					{
						label: 'Si',
						onClick: () => {
							// this.props.usuario
							// const respuesta = fetch('http://localhost:9090/SuperadministradorESCOM-web/api/usu/' + this.props.usuario.cedula + "/Suspendido",
							// 	{ method: 'put' }).then(response => {
							// 		console.log(response.status);
							// 	});

							// this.actualiza(this.props.usuario.cedula);
							// NotificationManager.success('Operacion realizada con exito')
						}
					},
					{
						label: 'No',
						onClick: () => NotificationManager.info('Se cancelo la operacion')
					}
				]
			});
		}


	}

	actualiza = (cedula) => {
		// let nuevo = [];
		// this.props.actuales.map(function (task, index, array) {
		// 	if (task.cedula === cedula) {

		// 		if (task.estado === "Suspendido") {
		// 			let usuario = {
		// 				nombre: task.nombre,
		// 				cedula: task.cedula,
		// 				correo: task.correo,
		// 				estado: "Activo"
		// 			}
		// 			nuevo.push(usuario);
		// 		} else {
		// 			let usuario = {
		// 				nombre: task.nombre,
		// 				cedula: task.cedula,
		// 				correo: task.correo,
		// 				estado: "Suspendido"
		// 			}
		// 			nuevo.push(usuario);
		// 		}
		// 	} else {
		// 		nuevo.push(task);
		// 	}
		// });
		// this.props.actionActualizarUsuarios(nuevo);
	}


	estilocompletado() {
		if (this.props.usuario.estado === 'Suspendido') {
			return {
				textShadow: "none!important",
				fontSize: "12px",
				fontFamily: "Open Sans,sans-serif",
				fontWeight: "300",
				padding: "3px 6px",
				color: "#fff",
				background: "#ED6B75"

			}
		} else {
			return {
				textShadow: "none!important",
				fontSize: "12px",
				fontFamily: "Open Sans,sans-serif",
				fontWeight: "300",
				padding: "3px 6px",
				color: "#fff",
				background: "#408725"
			}
		}

	}



	render() {
		const { nombre, correoElectronico, numeroDocumento, estado } = this.props.usuario;
		return (

			<tr key={numeroDocumento}>
				<td className="letra">{nombre}</td>
				<td className="letra">{numeroDocumento}</td>
				<td className="letra">{correoElectronico}</td>
				<td><span className="label label-sm letra" style={this.estilocompletado()}>{estado}</span></td>
				<td>
					<Button type="submit"><CreateTwoToneIcon /></Button>
					<Button type="submit"><CreateTwoToneIcon /></Button>
					<Button type="submit"><CreateTwoToneIcon /></Button>
					<Button type="submit"><CreateTwoToneIcon /></Button>

				</td>
				<NotificationContainer />
			</tr>



		)
	}
}


let mapStateToProps = state => {
	return {
		cedula: state.user.cedula,
		actuales: state.user.usuariosRegistrados
	}
};




export default connect(mapStateToProps, { actionAsignarCedula, actionActualizarUsuarios })(FilaTablaUsuario);