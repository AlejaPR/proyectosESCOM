import React from 'react';

import '../../css/menu.css'
import '../../css/registro.css'

import { Button } from 'reactstrap';
import { reduxForm, Field } from 'redux-form';

import { actionLoginUsuario, actualizarMensajeLogin } from '../../actions/actionsUsuario.js'
import { connect } from 'react-redux';
import { requerido, correo } from '../../utilitario/validacionCampos.js';

import CircularProgress from '@material-ui/core/CircularProgress';
import { withRouter } from 'react-router-dom';

class Login extends React.Component {

	state = {
		habilitado: false
	}

	componentDidUpdate() {
		switch (this.props.mensaje) {
			case 'Login correcto':
				this.props.history.push('/inicio');
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
														<a href="/editar" id="forget-password" className="small" >¿Olvido su contraseña?</a>
													</div>
												</div>
												<br />
												<div className="row">
													<div className="col-sm-6 center">
														<Button style={fondoBoton} disabled={this.state.habilitado} type="submit">Iniciar sesion</Button>

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
			<input {...input} value={label} placeholder={label} type={type} style={{ background: "none", border: "none", height: "10px", width: "400px", fontSize: "13px", fontFamily: "sans-serif", color: "#dc3545", margin: "0px 0px 16px" }} disabled={true} className="letra" />
		</div>
	</div>
)

const fondoBoton = {
	background: "#ec671d"
}

function mapStateToProps(state) {
	return {
		mensaje: state.user.mensajeLogin
	}
}

let formulario = reduxForm({
	form: 'loginUsuario'
})(Login)



export default withRouter(connect(mapStateToProps, { actionLoginUsuario, actualizarMensajeLogin })(formulario));
