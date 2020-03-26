import React from 'react';
import { Component } from 'react';

import { Button, UncontrolledPopover, PopoverBody } from 'reactstrap';
import persona from '../../img/icono-persona.png'


class BarraSuperior extends Component {
	mensaje = () => {
		console.log('hola');
	}

	render() {
		return (
			<div>
				<div>
					<div className="jumbotron p-2 jumbotron-fluid shadow" style={fondoBoton}>
						<nav className="navbar navbar-expand" style={fondoBoton}>
							<div className="collapse navbar-collapse" id="navbarSupportedContent">
								<ul className="navbar-nav ml-auto mt-2 mt-lg-1 ">
									<div className="col-sm-12 text-right">
										<li className="" width="100px" style={{ display: "inline-block", marginLeft: ".255em", verticalAlign: ".255em" }}>

											<UncontrolledPopover trigger="focus" placement="bottom" target="PopoverFocus">
												<PopoverBody>
													<Button id="cambiarContra" className="col-md-12 text-dark" type="button" style={botones} >Cambiar contraseña</Button>
													<Button id="cerrarSesion" className="col-md-12 text-dark" type="button" style={botones}>Cerrar sesion</Button>
												</PopoverBody>
											</UncontrolledPopover>
											<img src={persona} alt="" width="30" height="30" />
											<span className="username username-hide-on-mobile text-dark letra"> Pepito perez </span>
											<Button id="PopoverFocus" className="dropdown-toggle text-dark" type="button" style={{ background: "none", border: "0px" }}></Button>

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
	background: "white",
	fontSize: "14px",
	fontFamily: "Open sans, sans-serif",
	padding:"1px",
	border:"0px"

}

const fondoBoton = {
	background: "#FFFFFF"
}



export default BarraSuperior;
