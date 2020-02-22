import React from 'react';


//reactstrap
import { Button, UncontrolledPopover, PopoverBody } from 'reactstrap';

//imagenes
import persona from '../../imagenes/icono-persona.png'
import { withRouter } from 'react-router-dom';

import { connect } from 'react-redux';
import { consultarConfiguracion } from '../../actions/actionConfiguracion.js'
import { actionCerrarSesion } from '../../actions/actionsUsuario.js'

class BarraSuperior extends React.Component {
	mensaje = () => {
		var token = localStorage.getItem('Token');
		this.props.actionCerrarSesion(token);
	}

	componentDidUpdate() {
		switch (this.props.mensaje) {
			case 'cerrada':
				localStorage.setItem('TokenAuto',' ');
				this.props.history.push('/');
				break;
			default:
				// localStorage.setItem('TokenAuto',' ');
				// this.props.history.push('/');
				break;
		}
	}

	fondobotoon = () => {
		return ({
			background: this.props.configuracion.barraSuperior,
			height: "48px",
			padding: ".1rem"
		})
	}
	fondoPerfil = () => {
		return ({
			background: this.props.configuracion.barraSuperior,
			height: "48px",
			padding: ".5rem"
		})
	}

	render() {
		return (
			<div>
				<div>
					<div className="jumbotron p-1 jumbotron-fluid shadow" style={{ background: this.props.configuracion.barraSuperior }} >
						<nav className="navbar navbar-expand" style={this.fondoPerfil()}>
							<div className="collapse navbar-collapse" id="navbarSupportedContent">
								<ul className="navbar-nav ml-auto mt-2 mt-lg-1 ">
									<div className="col-sm-12 text-right">
										<li className="" width="150px" style={{ display: "inline-block", marginLeft: ".255em", verticalAlign: ".255em" }}>
											<UncontrolledPopover trigger="focus" placement="bottom" target="PopoverFocus">
												<PopoverBody>
													<Button id="cambiarContra" type="button" style={botones} >Cambiar contraseña</Button>
													<br />
													<Button id="cerrarSesion" type="button" onClick={this.mensaje} style={botones}>Cerrar sesion</Button>
												</PopoverBody>
											</UncontrolledPopover>
											{/* <img src={persona} alt="" width="30" height="30" /> */}

											<Button id="PopoverFocus" className="dropdown-toggle text-dark" type="button" style={{ background: "none", border: "none", boxShadow: "0px 0px 0px 0px" }}>
												<img src={persona} alt="" width="30" height="30" />
												<span className="username username-hide-on-mobile text-dark letra"> Pepito perez </span>
											</Button>

										</li>
										<ul className="dropdown-menu dropdown-menu-default">
											<li>
												<a href="login.html" className="small"> Cambiar contraseña </a>
											</li>
											<li>
												<a href="cerrar" className="small"> Cerrar sesión </a>
											</li>
										</ul>
									</div>
								</ul>
							</div>
						</nav>
					</div>
				</div>
			</div>
		)
	}


}

const botones = {
	padding: "3px",
	color: "black",
	width: "150px",
	fontSize: "13px",
	background: "white",
	border: "none"
}


function mapStateToProps(state) {
	return {
		configuracion: state.conf.configuracion,
		mensaje: state.user.mensajeCerrarSesion
	}
}



export default withRouter(connect(mapStateToProps, { consultarConfiguracion, actionCerrarSesion })(BarraSuperior));

