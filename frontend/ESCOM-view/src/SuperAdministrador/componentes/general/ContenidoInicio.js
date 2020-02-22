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

	componentDidMount() {
		// const respuesta = await fetch('http://localhost:8080/SuperadminustradorESCOM-web/api/usu/');
		// const transformado = await respuesta.json();
		// this.setState({ post: transformado });
		// console.log(transformado);
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
			<div style={{background:"yellow"}}>
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