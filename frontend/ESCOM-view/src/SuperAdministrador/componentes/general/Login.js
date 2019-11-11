import React from 'react';

//estilos
import '../../css/business-casual.css'
import '../../css/estilos.css'
import '../../css/bootstrap.min.css'
import '../../css/menu.css'
import '../../css/registro.css'

import { Button } from 'reactstrap';
import { encriptar, desencriptar } from '../general/Encriptar.js';
import { encriptarContrasena, validarContrasena } from './EncriptarContrasena';
import { reduxForm, Field } from 'redux-form';

import { actionLoginUsuario } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';

class Login extends React.Component {

	pruebita = () => {

	}

	handleSubmit = formValues => {
		this.props.actionLoginUsuario(formValues.correo, formValues.contrasena,this.props.cambiar);
		// this.props.actionRedireccionarLogin('i');
	}


	render() {
		return (
			<div>
				<div className="container-fluid fondo-blanco">
					<div className="row no-gutter">
						<div className="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
						<div className="col-md-8 col-lg-6">
							<div className="login d-flex align-items-center py-5">
								<div className="container">
									<div className="row">
										<div className="col-md-6 col-lg-10 mx-auto">
											<form onSubmit={this.props.handleSubmit(this.handleSubmit)} className="center">
												<h3 className="login-heading mb-4">Sistema para el apoyo administrativo</h3>
												<div className="row">
													<div className="col-md-12">
														<Field name="correo" component={generarInput} label="Correo electronico" />
													</div>
												</div>
												<br />
												<div className="row">
													<div className="col-md-12 center">
														<Field name="contrasena" type="password" component={generarInput} label="Contraseña" />
													</div>
												</div>
												<div className="row">
													<div className="col-sm-12 center">
														<Field name="mensaje" component={generarMensaje} label={this.props.token} />
													</div>
												</div>
												<div className="row">
													<div className="col-sm-12 center">
														<a href="/editar" id="forget-password" className="small" >¿Olvido su contraseña?</a>
													</div>
												</div>
												<br />
												<div className="row">
													<div className="col-sm-8 center">
														<Button style={fondoBoton} type="submit">Iniciar sesion</Button>
													</div>
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
			<input {...input} value={label} placeholder={label} type={type} style={{ background: "none", border: "none", height: "10px", width: "400px", fontSize: "13px", fontFamily: "sans-serif", color: "#dc3545", margin: "0px 0px 16px" }} disabled="true" className="letra" />
		</div>
	</div>
)

const validate = values => {
	const errors = {}
	if (!values.correo) {
		errors.correo = 'Este campo es obligatorio *'
	} else if (!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(values.correo)) {
		errors.correo = 'El correo no tiene el formato correcto'
	}
	if (!values.contrasena) {
		errors.contrasena = 'Este campo es obligatorio *'
	}
	return errors
}

const fondoBoton = {
	background: "#ec671d"
}

function mapStateToProps(state) {
	return {
		token: state.user.token
	}
}

let formulario = reduxForm({
	form: 'loginUsuario', validate
})(Login)

export default connect(mapStateToProps, { actionLoginUsuario })(formulario);
