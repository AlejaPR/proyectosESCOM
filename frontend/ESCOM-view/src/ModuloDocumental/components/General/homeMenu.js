import React from 'react';
import { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

//iconos
import Folder from '@material-ui/icons/LocalLibrary';
import logoDefecto from '../../../SuperAdministrador/imagenes/defectoLogo.png';
import HomeIcon from '@material-ui/icons/Home';
import AccountBalanceIcon from '@material-ui/icons/AccountBalance';
import SchoolIcon from '@material-ui/icons/School';

import { consultarConfiguracion } from '../../../SuperAdministrador/actions/actionConfiguracion.js';

class MenuLateral extends Component {

	state = {
		hoverInicio: false,
		hoverUsuario: false,
		hoverActividad: false,
		hoverModulo: false,
		hoverReportes: false
	}

	componentDidMount() {
		this.props.consultarConfiguracion(localStorage.getItem('Token'));
	}


	hoverOn = (evento) => {
		this.setState({ [evento.target.name]: true });
	}

	hoverOff = (evento) => {
		this.setState({ [evento.target.name]: false });
	}

	fondoBarr = () => {
		return (
			this.props.configuracion.barraLateral === undefined ?
				{
					background: '#0E3D38',
					fontSize: "14px",
					fontFamily: "Open sans, sans-serif"
				} : {
					background: this.props.configuracion.barraLateral,
					fontSize: "14px",
					fontFamily: "Open sans, sans-serif"
				}
		)
	}

	render() {
		return (
			<div id="sidebar-wrapper" className="toggled" style={this.fondoBarr()}>
				<div className="col-sm" style={this.fondoBarr()}>
					<div className="container text-center" style={this.fondoBarr()}>
						{this.props.configuracion.logo === undefined ? <img src={logoDefecto} alt="" width="130" height="50" /> : <img src={this.props.configuracion.logo} alt="" width="130" height="50" />}

					</div>
				</div>
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/inicio" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<HomeIcon />
						<br />
						<span className="title letra">Inicio</span>
					</Link>
				</li>
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/ListGeneral" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<AccountBalanceIcon />
						<br />
						<span className="title letra">Facultades</span>
					</Link>
				</li>
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/ListProgram" name="hoverUsuario" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<SchoolIcon />
						<br />
						<span className="title letra">Programas</span>
					</Link>
				</li>
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/ListDocument" name="hoverModulo" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()}>
						<Folder />
						<br />
						<span className="title letra">Documentos maestros</span>
					</Link>
				</li>
				{/**
				<li className="nav-item" style={{ height: "65px" }}>
					<Link to="/Prueba" name="hoverActividad" onMouseEnter={this.hoverOn} onMouseLeave={this.hoverOff} className="list-group-item list-group-item-action text-light text-center"
						style={this.fondoBarr()} >
						
						<br />
						<span className="title letra">Pruebas</span>
					</Link>
				</li>
				*/}
			</div>

		)
	}
}

function mapStateToProps(state) {
	return {
		configuracion: state.conf.configuracion
	}
}

export default connect(mapStateToProps, { consultarConfiguracion })(MenuLateral)