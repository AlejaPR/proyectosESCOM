import React from 'react';

import '../../css/menu.css'
import '../../css/registro.css'

import Button from '@material-ui/core/Button';
import { reduxForm, Field } from 'redux-form';
import Alert from '@material-ui/lab/Alert';
import { actionLoginUsuario, actualizarMensajeLogin, asignarNombreUsuario, actionAsignarIp, actualizarMensajeInicio } from '../../actions/actionsUsuario.js'
import { consultarConfiguracionLogin } from '../../actions/actionConfiguracion.js';
import { connect } from 'react-redux';
import { requerido, correo } from '../../utilitario/validacionCampos.js';
import imagenDefecto from '../../imagenes/defectoLogin.png';
import CircularProgress from '@material-ui/core/CircularProgress';
import { withRouter } from 'react-router-dom';

class Login extends React.Component {

	state = {
		habilitado: false
	}



	componentDidMount() {
		this.props.actualizarMensajeInicio('');
		this.props.consultarConfiguracionLogin();
	}

	componentDidUpdate() {
		switch (this.props.mensaje) {
			case 'Login correcto':
				this.props.actionAsignarIp();
				this.props.history.push('/inicio');
				break;
			default:
				break;
		}
	}

	habilitarBoton = (valor) => {
		this.setState({
			habilitado: valor
		});
	}

	handleSubmit = formValues => {
		this.props.actionLoginUsuario(formValues.correo, formValues.contrasena, this.habilitarBoton);
		this.props.reset();
	}


	render() {
		return (
			<div>
				<div className="container-fluid" style={{ background: "white" }}>
					<div className="row no-gutter">
						<div className="d-none d-md-flex col-md-4 col-lg-6" style={{ paddingLeft: "0px" }} >
							{
								this.props.configuracionLogin.imagenLogin === undefined ? <div style={{ background: "white" }}><img src={imagenDefecto} alt="" style={{ backgroundSize: 'cover', backgroundPosition: 'center' }} width="660px" height="695px" /></div>
									:
									<img src={this.props.configuracionLogin.imagenLogin} alt="" style={{ backgroundSize: 'cover', backgroundPosition: 'center' }} width="660px" height="695px" />
							}
						</div>
						<div className="col-md-8 col-lg-6">
							<div className="login d-flex align-items-center py-5">
								<div className="container">
									<div className="row">
										<div className="col-md-6 col-lg-10 mx-auto">
											<form onSubmit={this.props.handleSubmit(this.handleSubmit)} className="center">
												<h3 className="login-heading mb-4">Sistema para el apoyo administrativo</h3>
												<div className="row">
													<div className="col-md-12">
														<Field name="correo" component={generarInput} validate={[requerido, correo]} label="Correo electronico" />
													</div>
												</div>
												<br />
												<div className="row">
													<div className="col-md-12 center">
														<Field name="contrasena" type="password" component={generarInput} validate={[requerido]} label="Contraseña" />
													</div>
												</div>
												<div className="row">
													<div className="col-sm-12 center">

														<Field name="mensaje" component={generarMensaje} label={this.props.mensaje} />
													</div>
												</div>
												<div className="row">
													<div className="col-sm-12 center">
														<a href="/editar" id="forget-password" style={{ fontSize: "15px", fontFamily: "sans-serif" }} >¿Olvido su contraseña?</a>
													</div>
												</div>
												<br />
												<div className="row">
													<div className="col-sm-6 center">
														<Button style={{ background: this.props.configuracionLogin.botones, fontSize: "15px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark" variant="contained" disabled={this.state.habilitado} type="submit">Iniciar sesion</Button>

													</div>
													{
														this.state.habilitado ? <div className="col-sm-2 center"><CircularProgress color="secondary" /></div> : <div></div>
													}
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		)
	}
}

const generarInput = ({ input, label, type, meta: { touched, error, warning } }) => (
	<div>
		<div>
			<input {...input} placeholder={label} type={type} className="form-control letra form-control-solid placeholder-no-fix" />
			{touched && ((error && <span className="text-danger letra form-group">{error}</span>) || (warning && <span>{warning}</span>))}
		</div>
	</div>
)

const generarMensaje = ({ input, label, type, meta: { touched, error, warning } }) => (
	<div>
		<div>
			<br />
			{label === undefined | label === '' ? <div></div> : <Alert draggable={true} severity="error">{label}</Alert>}
			<br />
		</div>
	</div>
)


function mapStateToProps(state) {
	return {
		mensaje: state.user.mensajeLogin,
		nombreUsuario: state.user.nombreUsuario,
		configuracionLogin: state.conf.configuracionLogin,
		mensajeInicio: state.user.mensajeInicio
	}
}

let formulario = reduxForm({
	form: 'loginUsuario'
})(Login)



export default withRouter(connect(mapStateToProps, { actionAsignarIp, actionLoginUsuario, actualizarMensajeInicio, actualizarMensajeLogin, consultarConfiguracionLogin, asignarNombreUsuario })(formulario));
