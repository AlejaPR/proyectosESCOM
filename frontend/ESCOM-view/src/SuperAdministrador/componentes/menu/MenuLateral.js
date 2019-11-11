import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

//iconos
import Home from '@icons/material/HomeIcon.js';
import Persona from '@icons/material/AccountIcon.js';
import Folder from '@icons/material/LibraryIcon';
import Bombillo from '@icons/material/LightbulbOnIcon';
import Libro from  '@icons/material/FileIcon';
import Configuracion from '@icons/material/PaletteIcon';
//asignar una actividad import Libro from  '@icons/material/ClipboardAccountIcon';
//suspender import Libro from  '@icons/material/ClockAlertIcon';
// import Libro from  '@icons/material/FileIcon';
//lapiz @icons/material/PencilIcon';

//imagenes
import home from '../../imagenes/icon-home.png';
import persona from '../../imagenes/icono-persona.png'
import modulo from '../../imagenes/icono-modulo.png'
import bombillo from '../../imagenes/icono-bombillo.png'
import documento from '../../imagenes/icono-doc.png'
import logo from '../../imagenes/logo.png'

import { connect } from 'react-redux';
import { consultarConfiguracion } from '../../actions/actionConfiguracion.js'

class MenuLateral extends React.Component {

	state={
		hoverInicio:false,
		hoverUsuario:false,
		hoverActividad:false,
		hoverModulo:false,
		hoverReportes:false
	}

	componentWillMount() {
		this.props.consultarConfiguracion();
	}

	hoverOn=(evento)=>{
		this.setState({ [evento.target.name]: true });
	}

	hoverOff=(evento)=>{ 
		this.setState({ [evento.target.name]: false });    
	}

	fondoBarr=()=>{
		return( {
			background:this.props.configuracion.fondoBarra,
			fontSize: "14px",
			fontFamily: "Open sans, sans-serif"
		})
	}

	render() {
		return (
			<div  id="sidebar-wrapper" className="toggled" style={this.fondoBarr()}>
				<div className="col-sm" style={this.fondoBarr()}>
					<div className="container text-center" style={this.fondoBarr()}>
						<img src={logo} alt="" width="130" height="50" />
					</div>
				</div>
				<li className="nav-item" style={{height:"65px"}}>
					<a href="/inicio" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={this.fondoBarr()} >
						<Home color="#CBC7C7" width="22px"/>
						<br />
						<span className="title letra">Inicio</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminUsuario" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={this.fondoBarr()}>
						<Persona color="#CBC7C7"/>
						<br />
						<span className="title letra">Administrar usuario</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminModulo" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Folder color="#CBC7C7"/>
						<br />
						<span className="title letra">Administrar modulo</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/AdminActividad" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					style={this.fondoBarr()} >
						<Bombillo color="#CBC7C7"/>
						<br />
						<span className="title letra">Administrar actividad</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/reportes" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff}   className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Libro color="#CBC7C7"/>
						<br />
						<span className="title letra">Reportes</span>
					</a>
				</li>
				<li className="nav-item"  style={{height:"65px"}}>
					<a href="/configuracion" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff}   className="list-group-item list-group-item-action text-light text-center" 
					style={this.fondoBarr()}>
						<Configuracion color="#CBC7C7"/>
						<br />
						<span className="title letra">Configuracion</span>
					</a>
				</li>
			</div>

		)
	}
}
const fondoMenuLateral = {
	background: "red",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}


const fondoHover = {
	background: "#2b3b55",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}

function mapStateToProps(state) {
    return {
        configuracion:state.conf.estilos
    }
}


export default connect(mapStateToProps, { consultarConfiguracion })(MenuLateral);