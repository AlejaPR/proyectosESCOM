import React from 'react';


//reactstrap
import Button from '@material-ui/core/Button';
import { UncontrolledPopover, PopoverBody } from 'reactstrap';
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import { Field, reduxForm } from 'redux-form'
import RenderPasword from '../../utilitario/GenerarInputs';
//imagenes
import { requerido } from '../../utilitario/validacionCampos.js';
import persona from '../../imagenes/icono-persona.png'
import { withRouter } from 'react-router-dom';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import VpnKeyIcon from '@material-ui/icons/VpnKey';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';




import { connect } from 'react-redux';
import { consultarConfiguracion } from '../../actions/actionConfiguracion.js'
import { actionCerrarSesion, actualizarMensajeCerrar, asignarNombreUsuario } from '../../actions/actionsUsuario.js'

class BarraSuperior extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			modal: false
		};
		this.toggle = this.toggle.bind(this);
	}

	state = {
		amount: '',
		password: '',
		weight: '',
		weightRange: '',
		showPassword: false,
	}

	toggle() {
		this.setState(prevState => ({
			modal: !prevState.modal
		}));
	}

	mensaje = () => {
		var token = localStorage.getItem('Token');
		this.props.actionCerrarSesion(token);
	}

	componentDidMount() {
		this.props.asignarNombreUsuario(localStorage.getItem('Nombre'));
	}

	handleChange = prop => event => {
		this.setState({ ...this.state, [prop]: event.target.value });
	};

	handleClickShowPassword = () => {
		this.setState({ ...this.state, showPassword: !this.state.showPassword });
	};

	handleMouseDownPassword = event => {
		event.preventDefault();
	};

	componentDidUpdate() {
		if (this.props.mensaje !== '') {
			switch (this.props.mensaje) {
				case 'cerrada':
					localStorage.setItem('Token', ' ');
					this.props.history.go('/');
					break;
				default:
					break;
			}
		}
		this.props.actualizarMensajeCerrar('');
	}

	fondobotoon = () => {
		return (this.props.configuracion.barraSuperior === undefined ?
			{
				background: 'white',
				height: "48px",
				padding: ".1rem"
			} : {
				background: this.props.configuracion.barraSuperior,
				height: "48px",
				padding: ".1rem"
			})
	}
	fondoPerfil = () => {
		return (this.props.configuracion.barraSuperior === undefined ?
			{
				background: 'white',
				height: "48px",
				padding: ".5rem"
			} : {
				background: this.props.configuracion.barraSuperior,
				height: "48px",
				padding: ".5rem"
			})
	}

	handleSubmitForm = values => {
		console.log('values', values);
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
										<li className="" width="250px" style={{ display: "inline-block", verticalAlign: ".255em" }}>
											<UncontrolledPopover trigger="focus" style={{ textTransform: "none", width: "250px" }} placement="bottom" target="PopoverFocus">
												<PopoverBody className="shadow" style={{ width: "250px" }}>
													<Button id="cambiarContra" style={botones} startIcon={<VpnKeyIcon />} onClick={this.toggle} >Cambiar contraseña</Button>
													<br />
													<Button id="cerrarSesion" onClick={this.mensaje} startIcon={<ExitToAppIcon />} style={botones}>Cerrar sesion</Button>
												</PopoverBody>
											</UncontrolledPopover>

											{/* <img src={persona} alt="" width="30" height="30" /> */}

											<Button id="PopoverFocus" startIcon={<AccountCircleIcon style={{ fontSize: 40 }} />} className="dropdown-toggle text-dark" type="button" style={{ background: "none", border: "none", boxShadow: "0px 0px 0px 0px", textTransform: "none" }}>
												<span className="username username-hide-on-mobile text-dark letra"> {this.props.nombreUsuario} </span>
											</Button>
											<Modal isOpen={this.state.modal}
												toggle={this.toggle}
												className={this.props.className}
												style={{ maxWidth: '400px', width: '50%', margin: '10px auto' }}
											>
												<ModalHeader toggle={this.toggle} className="center">Cambiar contraseña</ModalHeader>
												<ModalBody>
													<form onSubmit={this.props.handleSubmit(this.handleSubmitForm)}>
														<Field name="contrasenaActual" component={RenderPasword} validate={[requerido]} label="Contraseña actual" />
														<Field name="nuevaContrasena" component={RenderPasword} validate={[requerido]} label="Nueva contraseña" />
														<Field name="verificacionNueva" component={RenderPasword} validate={[requerido]} label="Confirmar nueva contraseña" />
														<ModalFooter>
															<div style={{paddingRight:"120px"}}>
																<Button
																	style={{ background: this.props.configuracion.botones, fontSize: "14px", fontFamily: "sans-serif", textTransform: "none" }}
																	className="btn btn-dark"
																	variant="contained"
																	type="submit">
																	Cambiar contraseña
															</Button>
															</div>
														</ModalFooter>
													</form>
												</ModalBody>
											</Modal>

										</li>
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
	width: "100%",
	fontSize: "14px",
	fontFamily: 'sans-serif',
	background: "white",
	border: "none",
	textTransform: "none"
}


function mapStateToProps(state) {
	return {
		configuracion: state.conf.configuracion,
		mensaje: state.user.mensajeCerrarSesion,
		nombreUsuario: state.user.nombreUsuario
	}
}

let formularioContrasena = reduxForm({
	form: "formularioContrasena"
})(BarraSuperior);


export default withRouter(connect(mapStateToProps, { consultarConfiguracion, actionCerrarSesion, actualizarMensajeCerrar, asignarNombreUsuario })(formularioContrasena));

