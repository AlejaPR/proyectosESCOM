import React from 'react';


//reactstrap
import { Button, UncontrolledPopover, PopoverBody } from 'reactstrap';
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import { Field, reduxForm } from 'redux-form'
import TextField from '@material-ui/core/TextField'
import RenderPasword from '../../utilitario/GenerarInputs';
//imagenes
import {requerido} from '../../utilitario/validacionCampos.js';
import persona from '../../imagenes/icono-persona.png'
import { withRouter } from 'react-router-dom';

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
										<li className="" width="150px" style={{ display: "inline-block", marginLeft: ".255em", verticalAlign: ".255em" }}>
											<UncontrolledPopover trigger="focus" placement="bottom" target="PopoverFocus">
												<PopoverBody>
													<Button id="cambiarContra" type="button" style={botones} onClick={this.toggle} >Cambiar contraseña</Button>
													<br />
													<Button id="cerrarSesion" type="button" onClick={this.mensaje} style={botones}>Cerrar sesion</Button>
												</PopoverBody>
											</UncontrolledPopover>

											{/* <img src={persona} alt="" width="30" height="30" /> */}

											<Button id="PopoverFocus" className="dropdown-toggle text-dark" type="button" style={{ background: "none", border: "none", boxShadow: "0px 0px 0px 0px" }}>
												<img src={persona} alt="" width="30" height="30" />
												<span className="username username-hide-on-mobile text-dark letra"> {this.props.nombreUsuario} </span>
											</Button>
											<Modal isOpen={this.state.modal}
												toggle={this.toggle}
												className={this.props.className}
												size="col-md-6"
											>
												<ModalHeader toggle={this.toggle} className="center">Crear actividad</ModalHeader>
												<ModalBody>
													<form onSubmit={this.props.handleSubmit(this.handleSubmitForm)}>
														<Field name="pass" component={RenderPasword} validate={[requerido]} label="Last Name" />

														<Field name="lastName" component={renderTextField} label="Last Name" />

														<br />
														<ModalFooter>
															<Button
																type="submit">
																Registrar
															</Button>
															{/* <Button style={{ background: this.props.configuracion.botones, fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark" variant="contained" startIcon={<SaveAltIcon />} type="submit">Registrar</Button>{''}
															<Button style={fondoBotonCancelar} className="btn btn-dark" variant="contained" startIcon={<CancelIcon />} onClick={this.toggle}>Cancelar</Button> */}
														</ModalFooter>
													</form>
												</ModalBody>
											</Modal>

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

const renderTextField = ({
	input,
	label,
	meta: { touched, error },
	...custom
}) => (

		<TextField
			helperText={touched && error}
			label={label}
			error={(touched && error) ? true : false}
			{...input}
			{...custom}
			required={true}
			variant="outlined"
			size="small"
		/>

	)



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
		mensaje: state.user.mensajeCerrarSesion,
		nombreUsuario: state.user.nombreUsuario
	}
}

let formularioContrasena = reduxForm({
	form: "formularioContrasena"
})(BarraSuperior);


export default withRouter(connect(mapStateToProps, { consultarConfiguracion, actionCerrarSesion, actualizarMensajeCerrar, asignarNombreUsuario })(formularioContrasena));

