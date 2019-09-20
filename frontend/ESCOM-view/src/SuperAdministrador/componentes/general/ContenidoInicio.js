import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

import { Button } from 'reactstrap';


//componentes
import Barra from '../general/BarraDirecciones.js'
import Fila from './FilaTablaUsuario.js'

class ContenidoInicio extends React.Component {

	state = {
		post: []
	}

	eventoBorrado = (evento) => {
		evento.preventDefault();
		console.log(this.state.post)
	}



	onChange = (evento) => {
		this.setState({
			[evento.target.name]: evento.target.value
		});
	}

	async componentDidMount() {
		const respuesta = await fetch('http://localhost:8080/SuperadminustradorESCOM-web/api/usu/');
		const transformado = await respuesta.json();
		this.setState({ post: transformado });
		console.log(transformado);
	}


	renderTableData() {
		return this.state.post.map((post, index) => {
			const { cedula} = post //destructuring
			return (
				<Fila usuario={post} key={cedula} />
			)
		})

	}

	anadirTarea = (nombre, correo, cedula) => {
		const nuevaTarea = {
			nombre: nombre,
			correo: correo,
			cedula: cedula
		}
		this.setState({
			post: [...this.state.post, nuevaTarea]
		})
	}

	render() {
		return (
			<div>
				<div class="text-left titulo" style={estiloLetrero}>
					<h4>Lista de modulos</h4>
				</div>
				<Barra texto="Inicio  "/>
				<div className="col-sm-12" style={{
					paddingTop: "20px",
					paddingRight: "46px",
					paddingLeft: "40px",
					paddingBottom: "7px",
				}}>
					<div className="input-group">
						<input type="text" style={{fontSize:"14px"}}  className="form-control" placeholder="" />
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
						<div className="jumbotron p-1 jumbotron-fluid" style={fondoTabla}>
							<table className="table table-hover table-bordered table-checkable" style={fondoBarraSuperior}>
								<thead className="table table-hover table-striped col-md-12">
									<tr>
										<th> Logo </th>
										<th> Nombre de modulo </th>
										<th> Descripción de modulo  </th>
										<th> Acción </th>
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


export default ContenidoInicio;