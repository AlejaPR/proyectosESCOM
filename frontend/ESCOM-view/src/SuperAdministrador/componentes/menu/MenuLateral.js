import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'

//imagenes
import home from '../../imagenes/icon-home.png';
import persona from '../../imagenes/icono-persona.png'
import modulo from '../../imagenes/icono-modulo.png'
import bombillo from '../../imagenes/icono-bombillo.png'
import documento from '../../imagenes/icono-doc.png'
import logo from '../../imagenes/logo.png'


class MenuLateral extends React.Component {

	state={
		hoverInicio:false,
		hoverUsuario:false,
		hoverActividad:false,
		hoverModulo:false,
		hoverReportes:false
	}

	hoverOn=(evento)=>{
		this.setState({ [evento.target.name]: true });
	}

	hoverOff=(evento)=>{ 
		this.setState({ [evento.target.name]: false });    
	}

	render() {
		return (
			<div  id="sidebar-wrapper" className="toggled" style={fondoMenuLateral}>
				<div className="col-sm" style={fondoMenuLateral}>
					<div className="container text-center" style={fondoMenuLateral}>
						<img src={logo} alt="" width="140" height="60" />
					</div>
				</div>
				<li className="nav-item">
					<a href="/inicio" name="hoverInicio" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={ this.state.hoverInicio ? fondoHover : fondoMenuLateral } >
						<img src={home} alt="" width="25" height="25" />
						<br />
						<span className="title">Inicio</span>
					</a>
				</li>
				<li className="nav-item">
					<a href="/AdminUsuario" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					 style={ this.state.hoverUsuario ? fondoHover : fondoMenuLateral }>
						<img src={persona} alt="" width="25" height="25" />
						<br />
						<span className="title">Administrar usuario</span>
					</a>
				</li>
				<li className="nav-item">
					<a href="/AdminModulo" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center" 
					style={ this.state.hoverModulo ? fondoHover : fondoMenuLateral }>
						<img src={modulo} alt="" width="25" height="25" />
						<br />
						<span className="title">Administrar modulo</span>
					</a>
				</li>
				<li className="nav-item">
					<a href="/AdminActividad" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
					style={ this.state.hoverActividad ? fondoHover : fondoMenuLateral } >
						<img src={bombillo} alt="" width="25" height="25" />
						<br />
						<span className="title">Administrar actividad</span>
					</a>
				</li>
				<li className="nav-item">
					<a href="/reportes" name="hoverReportes" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff}   className="list-group-item list-group-item-action text-light text-center" 
					style={ this.state.hoverReportes ? fondoHover : fondoMenuLateral }>
						<img src={documento} alt="" width="25" height="25" />
						<br />
						<span className="title letra">Reportes</span>
					</a>
				</li>
			</div>

		)
	}
}

const fondoMenuLateral = {
	background: "#26344B",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}

const fondoHover = {
	background: "#2b3b55",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif"

}


export default MenuLateral;